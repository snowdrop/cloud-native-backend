podTemplate(name: 'maven33', label: 'maven33', cloud: 'openshift', serviceAccount: 'jenkins', containers: [
    containerTemplate(name: 'jnlp',
        image: 'openshift/jenkins-slave-maven-centos7',
        workingDir: '/tmp',
        envVars: [
            containerEnvVar(key: 'MAVEN_MIRROR_URL',value: 'http://nexus.infra.svc:8081/nexus/content/groups/public/'),
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

    stage("Use appropriate namespace") {
        sh "oc project ${OPENSHIFT_NAMESPACE}"
    }

    stage("Deploy") {
      sh "sed -e 's/changeme/${env.OPENSHIFT_NAMESPACE}/g' src/main/fabric8/dc.yaml -i"
      sh "mvn  -Popenshift -DskipTests clean fabric8:deploy"
    }
  }
}