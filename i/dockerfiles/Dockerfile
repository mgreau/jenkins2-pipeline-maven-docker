FROM jenkins:2.7.2

USER root

# Install plugins
COPY plugins.txt /usr/share/jenkins/plugins.txt
RUN /usr/local/bin/plugins.sh /usr/share/jenkins/plugins.txt

RUN apt-get update && apt-get install -y git gettext jq docker.io && rm -rf /var/lib/apt/lists/*

ADD https://github.com/tianon/gosu/releases/download/1.5/gosu-amd64 /usr/bin/gosu
RUN chmod 755 /usr/bin/gosu

COPY config/ /usr/share/jenkins/ref/
RUN chown -R jenkins:jenkins /usr/share/jenkins/ref

RUN chmod 0600 /usr/share/jenkins/ref/.ssh/*

ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

ENTRYPOINT usermod -g $(stat -c "%g" /var/run/docker.sock) jenkins && \
   gosu jenkins /bin/tini -- /usr/local/bin/jenkins.sh