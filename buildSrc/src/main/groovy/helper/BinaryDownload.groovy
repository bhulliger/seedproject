package helper

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

class BinaryDownload extends DefaultTask {

	@Input
	String sourceUrl

	@OutputFile
	File targetBinary

	@TaskAction
	void download() {
		ant.get(src: sourceUrl, dest: targetBinary)
	}
}