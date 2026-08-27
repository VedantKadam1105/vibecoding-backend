pipeline {
    agent any

    tools {
        jdk 'jdk21'
    }

    environment {
        REMOTE_HOST = "34.207.200.82"
        REMOTE_USER = "ec2-user"
        JAR_NAME    = "backend-0.0.1-SNAPSHOT.jar"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/VedantKadam1105/vibecoding-backend.git'
            }
        }

        stage('Build') {
            steps {
                sh 'echo JAVA_HOME=$JAVA_HOME && which java && java -version'
                sh 'mvn clean package'
            }
        }

        stage('Deploy') {
            steps {
                sshagent(credentials: ['ec2-deploy-key']) {
                    sh """
                        scp -o StrictHostKeyChecking=no target/${JAR_NAME} ${REMOTE_USER}@${REMOTE_HOST}:/home/ec2-user/backend.jar
                        ssh -o StrictHostKeyChecking=no ${REMOTE_USER}@${REMOTE_HOST} 'sudo systemctl restart springapp'
                    """
                }
            }
        }
    }

    post {
        success {
            echo 'Deployment successful!'
        }
        failure {
            echo 'Pipeline failed — check console output above.'
        }
    }
}