pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'cd /Users/rahultiwari/.jenkins/workspace/RestCucumber_master/RestAssured.Cucumber.Framework'
        sh 'pwd'
        sh 'mvn -f RestAssured.Cucumber.Framework/pom.xml clean install'
      }
    }

    stage('SonarQube Test') {
      steps {
        echo 'Initiating SonarQube test'
        sh 'mvn -f RestAssured.Cucumber.Framework sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dlicense.skip=true'
        echo 'SonarQube test Complete'
      }
    }

    stage('Unit Test') {
      steps {
        sh 'mvn -f RestAssured.Cucumber.Framework test'
      }
    }

    stage('Deployment') {
      parallel {
        stage('Deployment') {
          steps {
            echo 'mvn spring-boot:run'
          }
        }

      }
    }

  }
}