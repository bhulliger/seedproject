seedproject for a Java Projectsetup with CI Integration
=======================================================

h2. Project setup

Checkout the seedproject as your preferred name:

```
$> git clone https://github.com/bhulliger/seedproject.git <yourProjectName>

```

Change the remote origin of your project to your preferred repository

```
$> git remote rm origin
$> git remote add origin <yourProjectRepository>

```

Edit the file````gradle/config/build.groovy``` and configure your project. At the very least, configure your project repository:

```
scm {
	type = 'git' // 'git' or 'svn' supported
	url = '<yourProjectRepository>'
	tagBase = '<yourProjectRepository>/tags'
}

```


Change the groupname of your company in the build file (```build.gradle```):

```
// ############################################################################
// Group Definition
// ############################################################################
group = '<your group name goes here>' # i.e. ch.essentialmustard

```