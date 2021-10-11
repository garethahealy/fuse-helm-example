# fuse-helm-example

## build locally
```bash
mvn clean install
podman build . -t fuse-helm-example:v1
podman run fuse-helm-example:v1
```

## deploy to OCP
```bash
oc import-image registry.redhat.io/fuse7/fuse-java-openshift-jdk11-rhel8:1.9 --confirm
helm template --release-name v1 . | oc apply -f -
oc start-build v1-ej-fuse --follow
oc rollout status deployment/v1-ej-fuse --watch=true
```
