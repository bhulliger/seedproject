package helper

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import groovyx.net.http.RESTClient
import net.sf.json.JSON
import groovy.text.SimpleTemplateEngine
import org.apache.http.HttpRequestInterceptor
import org.apache.http.HttpRequest
import org.apache.http.protocol.HttpContext
import java.util.regex.Matcher
import java.util.regex.Pattern

class CleanupArtifactory extends DefaultTask {

	@Input String path
	@Input String serverUrl
	@Input String user
	@Input String password
	@Input String repository
	@Input boolean dryRun
	@Input int maxArtifacts

	def engine = new SimpleTemplateEngine()

	@TaskAction
	void cleanArtifactsRecursively() {

		def versions = []

		// Search for all Versions
		JSON json = folderInfo(path)
		json.children.each { child ->
			if (child.folder && isVersionNumber(child.uri)) {
				versions.add(child.uri)
			}
		}

		// Sort list of versions and delete unused versions
		versions.sort()
		versions = versions.reverse().drop(maxArtifacts)

		versions.each { version -> removeItem(path, version) }
		println( versions.size() + ' artifacts were deleted.' )

	}

	def isVersionNumber(string) {
		text ==~ /^\/\d+\.\d+(\.\d+)?$/
	}

	private def JSON folderInfo(path) {
		def binding = [ repository: repository, path: path ]

		def template = engine.createTemplate('''/artifactory/api/storage/$repository/$path''').make(binding)
		def query = template.toString()

		def server = obtainServerConnection()
		def response = server.get(path: query)
		if (response.status != 200) {
			println 'ERROR: problem obtaining folder info: ' + response.status
			println query
			System.exit(-1)
		}

		return response.data
	}

	private RESTClient obtainServerConnection() {
		def server = new RESTClient(serverUrl)
		server.parser.'application/vnd.org.jfrog.artifactory.storage.FolderInfo+json' = server.parser.'application/json'
		server.parser.'application/vnd.org.jfrog.artifactory.repositories.RepositoryDetailsList+json' = server.parser.'application/json'
		return server
	}

	def removeItem(path, uri) {
		println 'DELETE ' + path + uri
		def binding = [repository: repository, path: path + uri]
		def template = engine.createTemplate('''/artifactory/$repository/$path''').make(binding)
		def query = template.toString()
		def final basicAuth = 'Basic' + (user + ':' + password).bytes.encodeBase64().toString()

		if (!dryRun) {
			def server = new RESTClient(serverUrl)
			server.client.addRequrestInterceptor(new HttpRequestInterceptor() {
				void process(HttpRequest request, HttpContext context) {
					request.addHandler('Authorization', basicAuth);
				}
			})

			server.delete(path: query)
		}
	}

}