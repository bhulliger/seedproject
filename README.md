seedproject - projectsetup with gradle
======================================

## Project setup

1. Checkout the seedproject as your preferred name:

```
$> git clone https://github.com/bhulliger/seedproject.git **<yourProjectName>**

```

2. Change the remote origin of your project to your preferred repository

```
$> git remote rm origin
$> git remote add origin **<yourProjectRepository>**

```

3. Edit the file ```gradle/config/build.groovy``` and configure your project. At the very least, configure your project repository:

```
scm {
	type = 'git' // 'git' or 'svn' supported
	url = **'<yourProjectRepository>'**
	tagBase = **'<yourProjectRepository>/tags'**
}

```

4. Change the groupname of your company in the build file (```build.gradle```):

```
// ############################################################################
// Group Definition
// ############################################################################
group = '<your group name goes here>' # i.e. ch.essentialmustard

```

5. (Optional) Configure the needed subprojects in ```settings.gradle```. The seedproject supports 'app:web' and 'app:core' for now.

```
include "app:web", "app:core"

```

6. Generate your project structure. This job generates the Project and all subprojects. Copies templates from a remote repository and sets up the basic structure of your project with all gradle build files.

```
$> gradle initProject

```

## Scaffolding GWT App

Scaffold your basic app:

```
$> gradle scaffold

```

This scaffolds a basic GWT App based on GWTP Framework (https://github.com/ArcBees/GWTP).

## Build CI Jobs (Jenkins)

1. Provide your jenkins configuration in ```gradle/config/build.groovy```:

```
jenkins {

	// URL of jenkins 
	url = "<jenkins url>" 

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

	// Environment definition (or android flavors) for 'Build multi-configuration project'-Job (distribution job)
	axes = [ 'integration', 'test', 'prod' ]

	// comma-separated list of email addresses to be notified on failed builds
	notifications = '' 
}

```

2. Provide your sonar configuration in ```gradle/config/build.groovy```:

```
sonar {
	host {
		url = "**<sonar url>**" // URL where sonar can be reached
	}
	jdbc {
		// JDBC Configuration for the sonar server. These configurations can be found in your sonar.properties 
		// file in your sonar intallation.
		url = "**<jdbc url of sonar configuration>**" // jdbc url of sonar
		driverClassName = "**<driverClass>**" // jdbc driver class
		username = "**<sonarUser>**" // db username for sonar
		password = "**<sonarPassword>**" // db password for sonar
	}

	projectName = "**<sonarProjectName>**" // human readable project name in sonar
	projectKey = "**<sonarProjectKey>**" // project key in sonar
	exclusions = "" // (Optional) regex pattern for file exclusions
	jacoco {	
		excludes = ""; // (Optional). regex patterns for jacoco exclusions
	}
}

```

3. Configure your artifact repository in ```gradle/config/build.groovy``` (used to upload your project artifacts):

```

artifactRepository {
	url = "**<artifactoryURL>**" // The context URL of your artifactory
	username = "**<artifactoryUser>**" // Credentials to upload artifacts
	password = "**<artifactoryPassword>**" // ''
	name = "**<artifactoryName>**" // name of your artifact repository
	publicRepo = "**<artifactoryPublicRepo>**" // public repo name of the artifact repository
}


```

4. (Optional) If you have different environments, then add the configuration for each environment  in ```gradle/config/build.groovy```:


```
environments {

	** ... **

}

```

5. Generate Jenkins Jobs:

_Note_: Depending on whether you configured your jenkins credentials globally in the ```gradle/config/build.groovy``` file or not, the jenkinsuser and jenkinspassword parameters may be omitted.

```
$> gradle updateJenkinsItems -DjenkinsUser=<jenkinsUser> -DjenkinsPassword=<jenkinsPassword>

```

** Go see your jenkins :-). And happy coding. The rest is on you! **

