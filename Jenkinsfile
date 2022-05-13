pipeline {
    agent any
    tools{
       maven 'M3'
    }
    stages
    {
        
        stage('Build'){
            steps
            {
                git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                sh "mvn -Dmaven.test.failure.ignore =true clean package"
            }
            post 
            {
            success
            {
            junit '**/target/surefire-reports/TEST-*.xml'
            archiveArtifacts 'target/*.jar'
        }
        }
        
        
            stage('Test'){
            steps{
               catchError(buildResult:'SUCCESS',stageResult: 'FAILURE'){
               git 'https://github.com/Angapparaja/Expedia_selenium_Task.git'
               sh "mvn clean install"
            }
        }
        }
        stage('publish Allure Reports'){
            steps{
               script{
               allure([
               includeProperties:false,
               jdk:'',
               properties:[],
               reportBuildPolicy: 'ALWAYS',
               results:[[path:'/allure-results']]
               ])
            }
        }
     }
     }
}