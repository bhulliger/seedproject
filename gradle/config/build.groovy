// ############################################################################
// Global Configuration Variables
// ############################################################################
templates {
	scm = "https://github.com/bhulliger/seedproject_templates.git"
	treeish = '1913a26c0d6edfc918b05d571607578e70870700'
	path = "_templates"
}

group = 'ch.essentialmustard'

// ############################################################################
// Operating System & Servers
// ############################################################################
os {
	type = "unix" // unix / win
}

container {
	containerId = "tomcat8x" // containerId (http://cargo.codehaus.org/Home) to use for deployment. 
	installUrl = "http://mirror.switch.ch/mirror/apache/dist/tomcat/tomcat-8/v8.0.9/bin/apache-tomcat-8.0.9.zip" //Url where to download the container from. 
	port = 8005 // (Optional). Port where you want to run the applicationserver on

	context = "" // (Optional). The context where to run the application in the app server. if not provided, then the context is composed as <rootProjectName>-<projectName>

}

jenkins {

	url = "http://localhost:8080" // url where jenkins can be reached

	username = "" // (optional). username can be provided by -DjenkinsUser=<username> for jenkins jobs
	password = "" // (optional). password can be provided by -DjenkinsPassword=<password> for jenkins jobs

	// project-based security: User or group that has access to the project. 
	authorization {
		permission = "Anonymous" // Optional
	}

	// credentials ID for scm access. the credentials ID must be accessed from the jenkins configuration file.
	scm {
		credentialsId = '<credentialId from jenkins configuration file>' // TODO
	}

	// Environment definitionfor 'Build multi-configuration project'-Job (distribution job). Only relevant for non-android projects.
	axes = [ 'integration', 'test', 'prod' ]

	// comma-separated list of email addresses to be notified on failed builds
	notifications = '' 
}

sonar {
	host {
		url = "http://localhost:9000" // URL where sonar can be reached
	}
	jdbc {
		// JDBC Configuration for the sonar server. These configurations can be found in your sonar.properties 
		// file in your sonar intallation.
		url = "jdbc:h2:tcp://localhost:9092/sonar" // jdbc url of sonar
		driverClassName = "" // jdbc driver class
		username = "sonar" // db username for sonar
		password = "sonar" // db password for sonar
	}

	exclusions = "" // (Optional) regex pattern for file exclusions
	jacoco {	
		excludes = ""; // (Optional). regex patterns for jacoco exclusions
	}
}

scm {
	type = 'git' // 'git' or 'svn' supported
	url = 'https://github.com/bhulliger/eduwice.git'
	tagBase = '' // only required for svn. usually something like http://svn.wherever.com/tags
}

// ############################################################################
// (Optional) Android Platform
// This section is only required in case you have an android app (app:android in your settings.gradle file.)
// ############################################################################
android {

	// Signing
	signing {
		// Consult the android userguide for further documentation: http://tools.android.com/tech-docs/new-build-system/user-guide#TOC-Signing-Configurations
		keyAlias = "<here goes your key alias>" // Key Alias Name
		storeFile = "<here goes the path to your storefile (.jks)" // The android store file
		storePassword = "" // Optional. The store password can be passed as command-line argument -Dkeypass=<password>. Command-line parameters are prioritized to this config (override this config).
	}


	products {
		environments = ['test', 'staging', 'prod']
		flavors = [ '' ] // a list of all your flavors (like customers)
	}
}


// ############################################################################
// Artifacts
// ############################################################################
artifactRepository {
	url = "http://localhost:8081/artifactory/" // The context URL of your artifactory
	username = "admin" // Credentials to upload artifacts
	password = "password" // ''
	publicRepo = "http://localhost:8081/artifactory/public/" // public repo name of the artifact repository
}

// ############################################################################
// Environments
// ############################################################################
environments {

	// ########################################################################
	// Development
	// ########################################################################
	dev {
		env = "dev"

		// Optional. Required if Deployment is done by Bash-Script (not Cargo)
		server {
			hostname = 'localhost'
			ip = '127.0.0.1' 
			port = 8088

			username = 'tomcat'
			password = 'tomcat'
			remoteDir = '/var/opt/testing/seedproject'
			context = ''

			jndiName = ''
			jndiNameAdmin = ''
			poolName = ''
		}

		db {
			driver = ''
			url = ''
			username = ''
			password = ''
			type = ''
		}

		// Optional. Required if Deployment is done by Cargo (not Bashscript)
		container {
			remote {
				hostname = 'localhost'
				port = 8088
				username = 'tomcat'
				password = 'tomcat'

			}
		}
	}

	// ########################################################################
	// Integration
	// ########################################################################
	integration {
		env = "integration"
		// TODO
	}

	// ########################################################################
	// Test
	// ########################################################################
	test {
		env = "test"
		// TODO
	}

	// ########################################################################
	// Production
	// ########################################################################
	prod {
		env = "prod"
		// TODO
	}

}