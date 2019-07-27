pipeline { 
    agent any 
    options {
        skipDefaultCheckout true
         timeout(time: 1, unit: 'HOURS')
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
                    //catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    //sh "mvn clean test -Dsurefire.suiteXmlFiles=./src/test/java/resources/testngxmls/web/web_all_tests_suite.xml"
                    sh "exit 1"
            }
        }
    }

    post {
                always {

                    echo 'One way or another, Build have finished.'
                    echo 'Generating Report'
                    script {
                        allure([
                            includeProperties: false,
                            jdk: '',
                            properties: [],
                            reportBuildPolicy: 'ALWAYS',
                            results: [[path: 'target/allure-results']]
                        ])
                     }

                    echo 'Sending Emails'
                    mail to: 'maneesh.ms@oracle.com',
                    subject: "Job Completed: ${currentBuild.fullDisplayName}",
                    body: "Please find results at ${env.BUILD_URL}"
                    //deleteDir() /* clean up our workspace */
                }
    }
}