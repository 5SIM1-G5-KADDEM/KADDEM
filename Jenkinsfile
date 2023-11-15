pipeline {
    agent any

    environment {
        dockerCredentials               = 'dockerCredentials'
        registry                        = 'ghassendhif/ghassendhif-5sim1-g5'
        dockerImage                     = ''
        sonarToken                      = credentials('sonarToken')
    }

    stages {
        stage('Clean Projects') {
            steps {
                    sh "mvn clean"
                }
            
        }
        stage('Building project') {
            steps {
                    sh "mvn validate"
                    sh "mvn compile"
                }

        }
        stage('Docker Image') {
            steps {
                    script {
                            dockerImage = docker.build registry + ":1.0.0"
                    }

            }
        }
        stage('Docker Push to hub') {
            steps {
                    script {
                        docker.withRegistry('', dockerCredentials) { dockerImage.push() }
                    }
            }
        }
        stage("Docker Compose") {
                    steps {
                        catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                            sh "docker-compose start"
                        }
                    }
                }
        stage('Test the code') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                        sh 'mvn test -Dspring.profiles.active=test'
                    }
            }
        }
        stage('Jacoco') {
             steps {
                    sh "mvn jacoco:report"
                }
        }
        stage('SONAR') {
            steps {
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                        sh "mvn sonar:sonar -Dsonar.token=$sonarToken"
                    }
            }
        }
        stage('Nexus') {
            steps {
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                        sh "mvn deploy -DskipTests"
                    }
            }
        }
        stage('Prometheus ') {
                steps {

                        sh 'docker restart prometheus'
                    }
                }
                stage(' Grafana') {
                steps {


                        sh 'docker start grafana'
                    }
                }
    }
}

post{
        always{
            mail to: "dhif.ghassen@esprit.tn",
            subject: "Test Email Jenkins",
            body: "Pipeline exécuté avec succès !!"

        }
}