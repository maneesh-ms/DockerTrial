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
}