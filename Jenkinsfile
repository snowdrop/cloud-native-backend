// podTemplate(name: 'maven33', label: 'maven33', cloud: 'openshift', serviceAccount: 'jenkins', containers: [
//     containerTemplate(name: 'jnlp',
//         image: 'openshift/jenkins-slave-maven-centos7',
//         workingDir: '/tmp',
//         envVars: [
//             containerEnvVar(key: 'MAVEN_MIRROR_URL',value: 'http://nexus.infra.svc:8081/nexus/content/groups/public/'),
//         ],
//         cmd: '',
//         args: '${computer.jnlpmac} ${computer.name}')
// ]){
//   node("maven33") {
//     checkout scm
//     // git url: 'https://github.com/snowdrop/cloud-native-backend.git'
//     stage("Test") {
//       sh "mvn test"
//     }
//     stage("Deploy") {
//       sh "mvn  -Popenshift -DskipTests clean fabric8:deploy"
//     }
//   }
// }

pipeline {
  agent {
    kubernetes {
      //cloud 'kubernetes'
      label 'mypod'
      containerTemplate {
        name 'maven'
        image 'maven:3.3.9-jdk-8-alpine'
        ttyEnabled true
        command 'cat'
      }
    }
  }
  stages {
    stage('Run maven') {
      steps {
        container('maven') {
          sh 'mvn -version'
        }
      }
    }
  }
}