FROM jenkins:2.7.2

USER root

# Install plugins
COPY plugins.txt /usr/share/jenkins/plugins.txt
RUN /usr/local/bin/plugins.sh /usr/share/jenkins/plugins.txt

RUN apt-get update && apt-get install -y git gettext jq && rm -rf /var/lib/apt/lists/*

COPY config/ /usr/share/jenkins/ref/
RUN chown -R jenkins:jenkins /usr/share/jenkins/ref

RUN chmod 0600 /usr/share/jenkins/ref/.ssh/*

ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

ENTRYPOINT /usr/local/bin/jenkins.sh