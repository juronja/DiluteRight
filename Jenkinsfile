
pipeline {
    agent any
    environment {
        BUILD_VERSION = VersionNumber (versionNumberString: '${BUILD_YEAR}.${BUILD_MONTH}.${BUILDS_THIS_MONTH}')
        DOCKERH_REPO = "juronja"
        IMAGE_TAG = "$JOB_NAME"
        CONTAINER_NAME = "$JOB_NAME"
    }
        
    stages {
        stage('Build Docker image for Docker Hub') {
            environment {
                DOCKERHUB_CREDS = credentials('dockerhub-creds')
            }
            steps {
                echo "Building Docker image for Docker Hub ..."
                sh "docker build -t $DOCKERH_REPO/$IMAGE_TAG:latest -t $DOCKERH_REPO/$IMAGE_TAG:$BUILD_VERSION ."
                // Next line in single quotes for security
                sh 'echo $DOCKERHUB_CREDS_PSW | docker login -u $DOCKERHUB_CREDS_USR --password-stdin'
                sh "docker push $DOCKERH_REPO/$IMAGE_TAG:latest"
                sh "docker push $DOCKERH_REPO/$IMAGE_TAG:$BUILD_VERSION"
            }
        }
        stage('Deploy on HOSTING-PROD') {
            environment {
                HOSTING_CREDS = credentials('creds-hosting-prod')
            }
            steps {
                script {
                    sshagent(['ssh-hosting-prod']) {
                        echo "Deploying Docker container on HOSTING-PROD ..."
                        sh "ssh -o StrictHostKeyChecking=no $HOSTING_CREDS_USR@$HOSTING_CREDS_PSW 'bash -c \"\$(wget -qLO - https://raw.githubusercontent.com/juronja/DiluteRight/refs/heads/main/compose-commands.sh)\"'"
                    }
                }
            }
        }
    }
}