podTemplate(name: 'maven33', label: 'maven33', cloud: 'openshift', serviceAccount: 'jenkins', containers: [
    containerTemplate(name: 'jnlp',
        image: 'openshift/jenkins-slave-maven-centos7',
        workingDir: '/tmp',
        envVars: [
            envVar(key: 'MAVEN_MIRROR_URL',value: 'http://nexus-infra.46.4.81.220.nip.io/nexus/content/groups/public/'),
            envVar(key: 'JENKINS_URL,value: 'https://jenkins-infra.46.4.81.220.nip.io/')
        ],
        cmd: '',
        args: '${computer.jnlpmac} ${computer.name}')
]){
  node("maven33") {
    checkout scm
    // git url: 'https://github.com/snowdrop/cloud-native-backend.git'
    stage("Test") {
      sh "mvn test"
    }
    stage("Deploy") {
      sh "mvn  -Popenshift -DskipTests clean fabric8:deploy"
    }
  }
}
