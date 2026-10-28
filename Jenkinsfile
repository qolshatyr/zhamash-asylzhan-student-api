pipeline {
    agent any

    tools {
        jdk 'jdk21'
    }

    environment {
        STUDENT_NAME = 'Asylzhan Zhamash'
        IMAGE_NAME   = 'zhamash-asylzhan-student-api'
        IMAGE_TAG    = 'v1.0-zhamash-asylzhan'
    }

    options {
        skipDefaultCheckout(true)
        buildDiscarder(logRotator(numToKeepStr: '20'))
    }

    stages {
        stage('Checkout') {
            steps {
                echo "=== Pipeline of student: ${STUDENT_NAME} ==="
                checkout scm
                sh 'git log -1 --oneline'
            }
        }

        stage('Build') {
            steps {
                echo "Building Spring Boot application of ${STUDENT_NAME}"
                sh 'chmod +x gradlew'
                sh './gradlew clean assemble --no-daemon'
            }
        }

        stage('Test') {
            steps {
                echo "Running tests for ${STUDENT_NAME}"
                sh './gradlew test --no-daemon'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: 'build/test-results/test/*.xml'
                }
            }
        }

        stage('Docker Build') {
            steps {
                echo "Building Docker image ${IMAGE_NAME}:${IMAGE_TAG}"
                sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
            }
        }

        stage('Verify') {
            steps {
                sh """
                    if docker image inspect ${IMAGE_NAME}:${IMAGE_TAG} > /dev/null 2>&1; then
                        echo "Image ${IMAGE_NAME}:${IMAGE_TAG} exists"
                        docker images ${IMAGE_NAME}
                    else
                        echo "Image ${IMAGE_NAME}:${IMAGE_TAG} NOT found"
                        exit 1
                    fi
                """
            }
        }
    }

    post {
        success {
            echo "SUCCESS: build of ${STUDENT_NAME} finished, image ${IMAGE_NAME}:${IMAGE_TAG} is ready"
        }
        failure {
            echo "FAILURE: pipeline of ${STUDENT_NAME} failed — check the stage logs above"
        }
    }
}
