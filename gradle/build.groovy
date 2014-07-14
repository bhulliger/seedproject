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
	url = "http://jenkins:8080/jenkins"
	username = "" // TODO
	password = "" // TODO
}

sonar {
	host {
		url = "http://sonar:9000"
	}
	jdbc {
		url = "jdbc:oracle:thin:@imssvora11:1521:dora11gu"
		driverClassName = "oracle.jdbc.OracleDriver"
		username = "sonar"
		password = "sonar"
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
		url = "git@github.com/bhulliger/seedproject.git"
	}
}

// ############################################################################
// Artifacts
// ############################################################################
artifactRepository {
	url = "http://artifactory.ims.ch:8081"
	username = "developer"
	password = "ascotel"
	name = "libs-release-local"
	publicRepo = "http://artifactory.ims.ch:8081/artifactory/public/"
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
		// TODO
	}

	// ########################################################################
	// Test
	// ########################################################################
	test {
		// TODO
	}

	// ########################################################################
	// Production
	// ########################################################################
	prod {
		// TODO
	}

}