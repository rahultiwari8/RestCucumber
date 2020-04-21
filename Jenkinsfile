pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean install'
      }
    }
    
    stage('SonarQube Test') {
            steps {
              echo 'Initiating SonarQube test'
              sh 'mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dlicense.skip=true'
              echo 'SonarQube test Complete'
            }
          }

    stage('Unit Test') {
      steps {
        sh 'mvn test'
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