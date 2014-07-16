// ############################################################################
// Global Configuration Variables
// ############################################################################
templates {
	path = "C:/work/sandbox/seedproject_templates/"
}	

// ############################################################################
// Operating System & Server
// ############################################################################
os {
	type = "unix" // unix / win
}

server {	
	context = "web"	// TODO change to real context
	ip = ""
}

jenkins {
	url = "http://localhost:8080" // TODO
	username = "jenkins" // TODO
	password = "n0mor3secr3ts" // TODO

	authorization {
		permission = "jenkins" // TODO
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
	git {
		url = "https://github.com/bhulliger/seedproject.git"
	}

	svn {
		url = ""
	}
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
		env = "int"
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