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
        stage('Configure Grafana') {
            steps {
                script {
                    def grafanaBaseUrl = 'http://192.168.188.252:3000'
                    def grafanaApiUrl = "${grafanaBaseUrl}/api/dashboards/db"

                    // Define the dashboard configuration JSON. Replace this with your actual dashboard configuration.
                    def dashboardConfig = '''
                        {
                            "dashboard": {
                                "id": null,
                                "uid": null,
                                "title": "Your Dashboard Title",
                                "panels": [],
                                "editable": true,
                                "hideControls": false,
                                "sharedCrosshair": false,
                                "rows": [],
                                "schemaVersion": 22,
                                "version": 0,
                                "timezone": "browser",
                                "links": [],
                                "time": {
                                    "from": "now-5m",
                                    "to": "now"
                                }
                            },
                            "overwrite": false
                        }
                    '''

                    // Make a POST request to create a new dashboard
                    sh "curl -X POST -H 'Content-Type: application/json' -H 'Authorization: Bearer YOUR_GRAFANA_API_KEY' -d '${dashboardConfig}' ${grafanaApiUrl}"
                }
            }
        }
    }
}