pipeline { 
    agent any 
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Download Source') { 
            steps { 
                 git(
						url: 'https://github.com/maneesh-ms/DockerTrial.git',
						branch: 'master'
				)
			}
        }
    }

    stage('Edit Source'){
         steps {
    		sh 'sed -i -e 's/browser=.*/browser='$browser'/g' runconfiguration.properties'
    		sh 'sed -i -e 's/gridRun=.*/gridRun=true/g' runconfiguration.properties'
    	 }
    }

}