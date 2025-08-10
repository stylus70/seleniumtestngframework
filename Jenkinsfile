pipeline {

  agent any

  tools {
    jdk 'JDK-21'
    maven 'Apache Maven 3.9.11'
    git 'Git'
  }

  stages {
    stage('Checkout') {
      steps {
        git 'https://github.com/stylus70/seleniumtestngframework.git'
      }
    }

    stage('Build') {
      steps {        
        sh 'mvn clean install -DskipTests'  // Build the project, skipping tests for now
      }
    }

    stage('Test') {
      steps {        
        sh 'mvn clean test' // Execute Selenium tests using Maven
      }
    }

    stage('Publish Results') {
      steps {
        junit '**/target/surefire-reports/*.xml'
      }
    }
  }

  post {
    always {
        // Clean up workspace after the build
        cleanWs()
    }
    success {
        echo 'Selenium tests executed successfully!'
    }
    failure {
        echo 'Selenium tests failed. Check reports for details.'
    }
  }
}
