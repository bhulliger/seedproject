// ############################################################################
// Global Configuration Variables
// ############################################################################
templates {
	scm = "https://github.com/bhulliger/seedproject_templates.git"
	treeish = '56f88789491ae84755171d786f4c0c617be78d3d'
	path = "_templates"
}	

// ############################################################################
// Operating System & Server
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
	url = "http://localhost:8080" // TODO
	username = "" // (optional). username can be provided by -DjenkinsUser=<username> for jenkins jobs
	password = "" // (optional). password can be provided by -DjenkinsPassword=<password> for jenkins jobs

	authorization {
		permission = "jenkins" // TODO
	}

	scm {
		credentialsId = '50df870f-a222-416c-8217-719d80b645d1'
	}

	axes = [ 'integration', 'test', 'prod' ]

	// comma-separated list of email addresses to be notified on failed builds
	notifications = ''
}

sonar {
	host {
		url = "http://localhost:9000"
	}
	jdbc {
		url = "" // TODO
		driverClassName = "" // TODO
		username = "" // TODO
		password = "" // TODO
	}
	projectName = "" // TODO
	projectKey = "" // TODO
	exclusions = "" // TODO
	jacoco {	
		excludes = ""; // TODO
	}
}

scm {
	type = 'git' // 'git' or 'svn' supported
	url = '<yourProjectRepository>'
	tagBase = '<yourProjectRepository>/tags' // only required for svn. usually something like http://svn.wherever.com/tags
}

// ############################################################################
// Artifacts
// ############################################################################
artifactRepository {
	url = "" // TODO
	username = ""
	password = ""
	name = ""
	publicRepo = "" 
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