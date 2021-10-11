FROM registry.redhat.io/fuse7/fuse-java-openshift-jdk11-rhel8:1.9

# Application
COPY target/fuse-helm-example-*.jar /deployments