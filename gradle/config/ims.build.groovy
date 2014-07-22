// ############################################################################
// Global Configuration Variables
// ############################################################################
templates {
	scm = "https://github.com/bhulliger/seedproject_templates.git"
	treeish = '56f88789491ae84755171d786f4c0c617be78d3d'
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
	ip = ""
}

jenkins {

	url = "http://localhost:8080" // url where jenkins can be reached

	username = "bhu" // (optional). username can be provided by -DjenkinsUser=<username> for jenkins jobs
	password = "Ascotel005*" // (optional). password can be provided by -DjenkinsPassword=<password> for jenkins jobs

	// project-based security: User or group that has access to the project. 
	authorization {
		permission = "bhu" // Optional
	}

	// credentials ID for scm access. the credentials ID must be accessed from the jenkins configuration file.
	scm {
		credentialsId = '' // TODO
	}

	// Environment definition (or android flavors) for 'Build multi-configuration project'-Job (distribution job)
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
	url = 'https://github.com/bhulliger/seedproject.git'
	tagBase = '<yourProjectRepository>/tags' // only required for svn. usually something like http://svn.wherever.com/tags
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
		server {
			url = "http://localhost:8080/" + server.context
			dierctory = "/path/to/jboss/home" // TODO
		}
		os {
			type = "win"
		}
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
		server {
			url = "http://localhost:8080/" + server.context
			dierctory = "/path/to/jboss/home" // TODO
		}
		os {
			type = "win"
		}
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