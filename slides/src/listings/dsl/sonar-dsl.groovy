String basePath = 'PLF'

def scmValue = 'H 21 * * *'
def cronValue = 'H 9 * * 0'

folder(basePath) {
    description 'All Sonar Jobs for PLF projects.'
}

[
    [project: 'chromattic', gitOrganization: 'exoplatform', privacy: 'public', gitBranch: 'master', dockerImage: 'exoplatform/ci:jdk8-maven32', mavenSonarGoals: 'org.jacoco:jacoco-maven-plugin:0.7.1.201405082137:prepare-agent verify', mavenSonarProfiles: 'run-all'],
    [project: 'social-client-java', gitOrganization: 'exoplatform', privacy: 'public', gitBranch: 'master', dockerImage: 'exoplatform/ci:jdk7-maven32', mavenSonarGoals: 'org.jacoco:jacoco-maven-plugin:0.7.1.201405082137:prepare-agent verify', mavenSonarProfiles: 'run-all'],
    [project: 'cf-parent', gitOrganization: 'exoplatform', privacy: 'public', gitBranch: 'develop', dockerImage: 'exoplatform/ci:jdk8-maven30', mavenSonarGoals: 'org.jacoco:jacoco-maven-plugin:0.7.1.201405082137:prepare-agent verify', mavenSonarProfiles: 'run-all'],
    [project: 'core', gitOrganization: 'exodev', privacy: 'public', gitBranch: 'develop', dockerImage: 'exoplatform/ci:jdk8-maven30', mavenSonarGoals: 'org.jacoco:jacoco-maven-plugin:0.7.1.201405082137:prepare-agent verify', mavenSonarProfiles: 'run-all'],
    [project: 'ws', gitOrganization: 'exodev', privacy: 'public', gitBranch: 'develop', dockerImage: 'exoplatform/ci:jdk8-maven30', mavenSonarGoals: 'org.jacoco:jacoco-maven-plugin:0.7.1.201405082137:prepare-agent verify', mavenSonarProfiles: 'run-all'],
    [project: 'kernel', gitOrganization: 'exodev', privacy: 'public', gitBranch: 'develop', dockerImage: 'exoplatform/ci:jdk8-maven30', mavenSonarGoals: 'org.jacoco:jacoco-maven-plugin:0.7.1.201405082137:prepare-agent verify', mavenSonarProfiles: 'run-all'],
    [project: 'jcr', gitOrganization: 'exodev', privacy: 'public', gitBranch: 'develop', dockerImage: 'exoplatform/ci:jdk8-maven30', mavenSonarGoals: 'org.jacoco:jacoco-maven-plugin:0.7.1.201405082137:prepare-agent verify', mavenSonarProfiles: 'run-all'],
    [project: 'jcr-services', gitOrganization: 'exodev', privacy: 'public', gitBranch: 'develop', dockerImage: 'exoplatform/ci:jdk8-maven30', mavenSonarGoals: 'org.jacoco:jacoco-maven-plugin:0.7.1.201405082137:prepare-agent verify', mavenSonarProfiles: 'run-all'],
    [project: 'gatein-portal', gitOrganization: 'exodev', privacy: 'public', gitBranch: 'develop', dockerImage: 'exoplatform/ci:jdk8-maven30', mavenSonarGoals: 'org.jacoco:jacoco-maven-plugin:0.7.1.201405082137:prepare-agent verify -Dgatein.dev=tomcat7 -Dexo.projects.directory.dependencies=/srv/ciagent/tmp', mavenSonarProfiles: 'download'],
    [project: 'maven-parent-pom', gitOrganization: 'exoplatform', privacy: 'public', gitBranch: 'develop', dockerImage: 'exoplatform/ci:jdk8-maven32', mavenSonarGoals: 'clean verify', mavenSonarProfiles: 'run-its,coverage'],
    [project: 'docs-style', gitOrganization: 'exodev', privacy: 'public', gitBranch: 'develop', dockerImage: 'exoplatform/ci:jdk8-maven32', mavenSonarGoals: 'clean verify', mavenSonarProfiles: 'run-its,coverage'],
    [project: 'platform-ui', gitOrganization: 'exodev', privacy: 'public', gitBranch: 'develop', dockerImage: 'exoplatform/ci:jdk8-maven32', mavenSonarGoals: 'clean verify', mavenSonarProfiles: 'run-its,coverage'],
    [project: 'commons', gitOrganization: 'exodev', privacy: 'public', gitBranch: 'develop', dockerImage: 'exoplatform/ci:jdk8-maven32', mavenSonarGoals: 'clean verify', mavenSonarProfiles: 'run-its,coverage'],
    [project: 'ecms', gitOrganization: 'exodev', privacy: 'public', gitBranch: 'develop', dockerImage: 'exoplatform/ci:jdk8-maven32', mavenSonarGoals: 'clean verify', mavenSonarProfiles: 'run-its,coverage'],
    [project: 'social', gitOrganization: 'exodev', privacy: 'public', gitBranch: 'develop', dockerImage: 'exoplatform/ci:jdk8-maven32', mavenSonarGoals: 'clean verify', mavenSonarProfiles: 'run-its,coverage'],
    [project: 'wiki', gitOrganization: 'exodev', privacy: 'public', gitBranch: 'develop', dockerImage: 'exoplatform/ci:jdk8-maven32', mavenSonarGoals: 'clean verify  -Dorg.xml.sax.driver=com.sun.org.apache.xerces.internal.parsers.SAXParser', mavenSonarProfiles: 'run-its,coverage'],
    [project: 'forum', gitOrganization: 'exodev', privacy: 'public', gitBranch: 'develop', dockerImage: 'exoplatform/ci:jdk8-maven32', mavenSonarGoals: 'clean verify', mavenSonarProfiles: 'run-its,coverage'],
    [project: 'calendar', gitOrganization: 'exodev', privacy: 'public', gitBranch: 'develop', dockerImage: 'exoplatform/ci:jdk8-maven32', mavenSonarGoals: 'clean verify', mavenSonarProfiles: 'run-its,coverage'],
    [project: 'integration', gitOrganization: 'exodev', privacy: 'public', gitBranch: 'develop', dockerImage: 'exoplatform/ci:jdk8-maven32', mavenSonarGoals: 'clean verify', mavenSonarProfiles: 'run-its,coverage'],
    [project: 'platform', gitOrganization: 'exodev', privacy: 'public', gitBranch: 'develop', dockerImage: 'exoplatform/ci:jdk8-maven32', mavenSonarGoals: 'clean verify', mavenSonarProfiles: 'run-its,coverage'],
    [project: 'platform-public-distributions', gitOrganization: 'exoplatform', privacy: 'public', gitBranch: 'develop', dockerImage: 'exoplatform/ci:jdk8-maven32', mavenSonarGoals: 'clean verify', mavenSonarProfiles: 'run-its,coverage'],
    [project: 'platform-private-distributions', gitOrganization: 'exoplatform', privacy: 'private', gitBranch: 'develop', dockerImage: 'exoplatform/ci:jdk8-maven32', mavenSonarGoals: 'clean verify', mavenSonarProfiles: 'run-its,coverage'],
    [project: 'platform-documentation', gitOrganization: 'exoplatform', privacy: 'private', gitBranch: 'develop', dockerImage: 'exoplatform/ci:jdk7-maven32', mavenSonarGoals: 'clean verify', mavenSonarProfiles: 'run-its,coverage'],
].each { Map config ->

    pipelineJob("$basePath/${config.project}-${config.gitBranch}-sonar") {
      description("<b><span style='color:red'>DO NOT EDIT HERE!</span></b>")

      logRotator(15, 15)

      authorization {
        if(config.privacy.equals('public')){
          permission('hudson.model.Item.Read', 'anonymous')
          permission('hudson.model.Item.Build', 'exo-profile-plf-release-manager')
        }
      }

      environmentVariables {
        keepSystemVariables(true)
        keepBuildVariables(true)
        // Env variables for exoSonar Groovy script
        env('gitUrl', "git@github.com:${config.gitOrganization}/${config.project}.git")
        env('gitBranch', "${config.gitBranch}")
        env('dockerImage', "${config.dockerImage}")
        env('mavenSonarGoals', "${config.mavenSonarGoals}")
        env('mavenSonarProfiles', "${config.mavenSonarProfiles}")
      }

      triggers {
            scm("${scmValue}")
            cron("${cronValue}")
      }

      definition {
        cps {
          script('''#!groovy
node('docker'){
    exoSonar{}
}
          ''')
        }
      }
    }
}