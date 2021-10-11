node ("maven") {
    stage("Clone") {
        sh "git clone https://github.com/garethahealy/fuse-helm-example.git"
    }

    stage("Build") {
        dir("fuse-helm-example") {
            sh "mvn clean install"
        }
    }

    stage("Helm") {
        dir("fuse-helm-example/chart") {
            sh "curl -L https://get.helm.sh/helm-v3.7.0-linux-amd64.tar.gz -o helm-v3.7.0-linux-amd64.tar.gz"
            sh "tar -zxvf helm-v3.7.0-linux-amd64.tar.gz"

            withEnv(['PATH+HELM=linux-amd64']) {
                sh """helm template --release-name v1 . | oc apply -f -"""
            }
        }
    }

    stage("Container Build") {
        sh "oc start-build v1-ej-fuse --follow"
    }

    stage("Rollout") {
        sh "oc rollout status deployment/v1-ej-fuse --watch=true"
    }
}