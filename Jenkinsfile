pipeline {
    agent { 
        label 'maven'
    }

    environment {
        PROJECT_NAME = 'helloworld'
        APP_NAME    = 'helloworld'
        JAR_NAME    = 'fuse-helm-example-*.jar'
    }

    stages {
        stage('Git Clone') { 
            steps {
                checkout scm
            }
        }

        stage('Build') { 
            steps {
                sh "mvn clean install -DskipTests"
            }
        }

        stage('Test') { 
            steps {
                sh "mvn test"
            }
        }

        stage('Deploy Build Resources') { 
            steps {
                sh "oc import-image registry.redhat.io/fuse7/fuse-java-openshift-jdk11-rhel8:1.9 --confirm -n ${PROJECT_NAME}"
                sh "oc process -f templates/build.yaml -p APP_NAME=${APP_NAME} | oc apply -n ${PROJECT_NAME} -f -"
            }
        }

        stage('Start Build') {
            steps {
                sh "cp target/${JAR_NAME} target/${APP_NAME}.jar"
                sh "oc start-build ${APP_NAME}-binary-build --from-file=target/${APP_NAME}.jar --follow -n ${PROJECT_NAME}"
                sh "oc tag ${APP_NAME}:latest ${APP_NAME}:${GIT_BRANCH} -n ${PROJECT_NAME}"
            }
        }

        stage('Deploy Runnable Resources') { 
            steps {
                sh "oc process -f templates/run.yaml -p APP_NAME=${APP_NAME} -p JENKINS_BUILD_URL=${BUILD_URL} -p PROJECT_NAME=${PROJECT_NAME} -p IMAGE_TAG=${GIT_BRANCH} | oc apply -n ${PROJECT_NAME} -f -"
            }
        }

        stage('Watch Deployment') { 
            steps {
                sh "oc rollout status deployment/${APP_NAME} --watch=true -n ${PROJECT_NAME}"
            }
        }
    }
}