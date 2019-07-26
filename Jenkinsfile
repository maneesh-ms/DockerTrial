pipeline { 
    agent any 
    options {
        skipDefaultCheckout true
    }
    stages {
        stage('Download Source') { 
            steps { 
                 git(
						url: "https://github.com/maneesh-ms/DockerTrial.git",
						branch: "master"
				)
			}
        }
        stage('Edit Source'){
            steps {
                sh "pwd"
         		sh "sed -i -e 's/browser=.*/browser='$browser'/g' runconfiguration.properties"
         		sh "sed -i -e 's/gridRun=.*/gridRun=true/g' runconfiguration.properties"
         	}
        }
        stage('Clean') {
            steps {
                sh "mvn clean"
            }
        }
	    stage('Compile Framework') {
            steps {
                sh "mvn compile"
            }
        }
	    stage('Compile Test') {
            steps {
                sh "mvn test-compile"
            }
        }
	    stage('Run Test') {
            steps {
                sh "mvn clean test -Dsurefire.suiteXmlFiles=./src/test/java/resources/testngxmls/web/web_all_tests_suite.xml"
            }
        }
    }
}