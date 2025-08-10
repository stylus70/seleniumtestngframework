pipeline {

  agent any

  tools {
    jdk 'JDK-21'
    maven 'Apache Maven 3.9.11'
  }

  stages {
    stage('Checkout') {
      steps {
        git branch: 'main', url: 'https://github.com/stylus70/seleniumtestngframework.git'
      }
    }

    stage('Build') {
      steps {
        sh 'mvn clean install'
      }
    }

    stage('Test') {
      steps {
        sh 'mvn test'
      }
    }

    stage('Publish Results') {
      steps {
        junit '**/target/surefire-reports/*.xml'
      }
    }
  }
}
