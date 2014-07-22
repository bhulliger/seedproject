// ############################################################################
// Global Configuration Variables
// ############################################################################
templates {
	scm = "https://github.com/bhulliger/seedproject_templates.git"
	treeish = '3fafd437dad6dfb0e337489cfc4f10456630e8b6'
	path = "_templates"
}	

// ############################################################################
// Operating System & Servers
// ############################################################################
os {
	type = "unix" // unix / win
}

// enter 'android' for android platforms. this results in jenkins jobs with android flavors
platform = ''

server {
	context = "web"	// TODO change to real context
	port = 8080 // preferred port for jboss
	ip = ""
}

jenkins {

	url = "<jenkins url>" // url where jenkins can be reached

	username = "" // (optional). username can be provided by -DjenkinsUser=<username> for jenkins jobs
	password = "" // (optional). password can be provided by -DjenkinsPassword=<password> for jenkins jobs

	// project-based security: User or group that has access to the project. 
	authorization {
		permission = "<jenkins permission>" // Optional
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
		url = "<sonar url>" // URL where sonar can be reached
	}
	jdbc {
		// JDBC Configuration for the sonar server. These configurations can be found in your sonar.properties 
		// file in your sonar intallation.
		url = "<jdbc url of sonar configuration>" // jdbc url of sonar
		driverClassName = "<driverClass>" // jdbc driver class
		username = "<sonarUser>" // db username for sonar
		password = "<sonarPassword>" // db password for sonar
	}

	projectName = "<sonarProjectName>" // human readable project name in sonar
	projectKey = "<sonarProjectKey>" // project key in sonar
	exclusions = "" // (Optional) regex pattern for file exclusions
	jacoco {	
		excludes = ""; // (Optional). regex patterns for jacoco exclusions
	}
}

scm {
	type = 'git' // 'git' or 'svn' supported
	url = '<yourProjectRepository>'
	tagBase = '<yourProjectRepository>/tags' // only required for svn. usually something like http://svn.wherever.com/tags
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
	url = "<artifactoryURL>" // The context URL of your artifactory
	username = "<artifactoryUser>" // Credentials to upload artifacts
	password = "<artifactoryPassword>" // ''
	name = "<artifactoryName>" // name of your artifact repository
	publicRepo = "<artifactoryPublicRepo>" // public repo name of the artifact repository
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
		
		db {
			driver = "org.hsqldb.jdbcDriver"
			type = "hsqldb"
			url = "jdbc:hsqldb:file:c:/tmp/seedproject/hsqldb/db;shutdown=true" // TODO
			user = "sa"
			password = ""
			jndiName = "java:/seedprojectDB" // TODO
			jndiNameAdmin = "java:/seedprojectDBAdmin" // TODO
			poolName = "seedprojectPool" // TODO
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