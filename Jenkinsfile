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
        stage('Sending email'){
                   steps {
                    mail bcc: '', body: '''Hello from Ghassen,
                    Devops Pipeline with success.
                    Cordialement''', cc: '', from: '', replyTo: '', subject: 'Devops', to: 'dhif.ghassen@esprit.tn'
                    }
               }

             stage('Slack'){
             steps{
             slackSend(color:'#FFFF00',message:"Build success")
             }
             }
    }
}

