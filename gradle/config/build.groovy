// ############################################################################
// Global Configuration Variables
// ############################################################################
templates {
	scm = 'https://github.com/bhulliger/seedproject_templates.git'
	treeish = '79d3904a558e07c250e9e60b7293cb9a0af5d1de'
	path = '_templates'
}

group = 'your.group.goes.here'

// ############################################################################
// Operating System & Servers
// ############################################################################
os {
	type = 'unix' // unix / win
}

container {
	containerId = 'tomcat8x' // containerId (http://cargo.codehaus.org/Home) to use for deployment. 
	installUrl = 'http://mirror.switch.ch/mirror/apache/dist/tomcat/tomcat-8/v8.0.9/bin/apache-tomcat-8.0.9.zip' //Url where to download the container from. 
	port = 8080 // (Optional). Port where you want to run the applicationserver on

	context = '' // (Optional). The context where to run the application in the app server. if not provided, then the context is composed as <rootProjectName>-<projectName>

}

jenkins {

	url = 'http://jenkins.yourdomain.com' // url where jenkins can be reached

	username = '' // (optional). username can be provided by -DjenkinsUser=<username> for jenkins jobs
	password = '' // (optional). password can be provided by -DjenkinsPassword=<password> for jenkins jobs

	// project-based security: User or group that has access to the project. 
	authorization {
		permission = 'Anonymous' // Optional.
	}

	// credentials ID for scm access. the credentials ID must be accessed from the jenkins configuration file.
	scm {
		credentialsId = '<credentialId from jenkins configuration file>' // TODO
	}

	// Environment definitionfor 'Build multi-configuration project'-Job (distribution job). Only relevant for non-android projects.
	axes = [ 'integration', 'test', 'staging', 'prod' ]

	// comma-separated list of email addresses to be notified on failed builds
	notifications = '' 
}

sonar {
	host {
		url = 'http://sonar.yourdomain.com' // URL where sonar can be reached
	}
	jdbc {
		// JDBC Configuration for the sonar server. These configurations can be found in your sonar.properties 
		// file in your sonar intallation.
		url = 'jdbc:h2:tcp://localhost:9092/sonar' // jdbc url of sonar
		driverClassName = '' // jdbc driver class
		username = '' // db username for sonar
		password = '' // db password for sonar
	}

	exclusions = '' // (Optional) regex pattern for file exclusions
	jacoco {	
		excludes = ''; // (Optional). regex patterns for jacoco exclusions
	}
}

scm {
	type = 'git' // 'git' or 'svn' supported
	url = 'https://github.com/bhulliger/seedproject.git'
	tagBase = '' // only required for svn. usually something like http://svn.wherever.com/tags
}

// ############################################################################
// Artifacts
// ############################################################################
artifactRepository {
	url = 'http://artifactory.yourdomain.com/' // The context URL of your artifactory
	username = '' // Credentials to upload artifacts
	password = '' // ''
	publicRepo = 'http://localhost:8081/artifactory/public/' // public repo name of the artifact repository
}

// ############################################################################
// Environments
// ############################################################################
environments {

	// ########################################################################
	// Development
	// ########################################################################
	dev {
		env = 'dev'
		// TODO
	}

	// ########################################################################
	// Integration
	// ########################################################################
	integration {
		env = 'integration'
		// TODO
	}

	// ########################################################################
	// Test
	// ########################################################################
	test {
		env = 'test'
		// TODO
	}


	// ########################################################################
	// Staging
	// ########################################################################
	staging {
		env = 'staging'
		// TODO
	}

	// ########################################################################
	// Production
	// ########################################################################
	prod {
		env = 'prod'
		// TODO
	}

}