def call(body) {
    // evaluate the body block, and collect configuration into the object
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    def exoSWF = new org.exoplatform.swf.ExoSWFCommands()
    def utils = new org.exoplatform.swf.Utils()

    //init values
    def M2_REPO_IN_CONTAINER = utils.getValue('m2RepositoryPath', '/home/ciagent/.m2/repository', config, env)
    def JOB_SUFFIX = utils.getValue('jobSuffix', 'sonar', config, env)
    def SONAR_PLUGIN_VERSION = utils.getValue('sonarPluginVersion', '3.2', config, env)
    def SONAR_DOCKER_IMAGE = utils.getValue('sonarDockerImage', 'exoplatform/ci:jdk8-maven32', config, env)
    def MAVEN_SONAR_GOALS = utils.getValue('mavenSonarGoals', 'clean verify', config, env)
    def MAVEN_SONAR_PROFILES = utils.getValue('mavenSonarProfiles', 'run-its,coverage', config, env)
    def MAVEN_SETTINGS_FILE_ID = utils.getValue('m2SettingsId', 'exo-ci-maven-settings', config, env)
    def GIT_CREDENTIALS_ID = utils.getValue('gitCredentialsId', 'ciagent', config, env)
    def DOCKER_RUN_PARAMS = utils.getValue('dockerRunParams', '', config, env)
    def JOB_NAME = env.JOB_NAME.replaceAll('/','-')

    // required values
    def DOCKER_IMAGE = utils.getValue('dockerImage', '', config, env)
    def GIT_URL = utils.getValue('gitUrl', '', config, env)
    def GIT_BRANCH = utils.getValue('gitBranch', '', config, env)

    def mailTo = "mgreau@exoplatform.com"

    def eXoJDKMaven = docker.image(DOCKER_IMAGE);

    stage(DOCKER_IMAGE){
      eXoJDKMaven.pull()
    }

    stage('Checkout ' + GIT_BRANCH){
      // checkout scm with credentials
      git branch: GIT_BRANCH, credentialsId: GIT_CREDENTIALS_ID, url: GIT_URL
    }

    stage('Sonar Analysis'){
      // Create m2 cache (use docker volume)
      def m2Cache = exoSWF.createMavenCacheVolume(JOB_NAME)
      // Use custom settings.xml file on project workspace directory
      configFileProvider(
                  [configFile(fileId: "${MAVEN_SETTINGS_FILE_ID}",  targetLocation: 'settings.xml')]) {
        try {
            // Same docker image for the project and the sonar plugin
            if (SONAR_DOCKER_IMAGE.equalsIgnoreCase(DOCKER_IMAGE)){
              echo "Maven Builds & Sonar Scanner in 1 step."

              eXoJDKMaven.inside("${DOCKER_RUN_PARAMS} -v ${m2Cache}:${M2_REPO_IN_CONTAINER}") {
                  // Execute sonar Analysis
                  sh "mvn ${MAVEN_SONAR_GOALS} org.sonarsource.scanner.maven:sonar-maven-plugin:${SONAR_PLUGIN_VERSION}:sonar -P${MAVEN_SONAR_PROFILES} -s settings.xml"
              }
            }
            // Use different container for mvn verify and mvn sonar:sonar
            else {
              echo "Sonar and Project use differents docker images!"
              def exoDockerSonarImage = docker.image("${SONAR_DOCKER_IMAGE}")
              exoDockerSonarImage.pull()

              eXoJDKMaven.inside("${DOCKER_RUN_PARAMS} -v ${m2Cache}:${M2_REPO_IN_CONTAINER}") {
                // Build project
                echo "Maven Build"
                sh "mvn ${MAVEN_SONAR_GOALS} -P${MAVEN_SONAR_PROFILES} -s settings.xml"
              }
              exoDockerSonarImage.inside("${DOCKER_RUN_PARAMS} -v ${m2Cache}:${M2_REPO_IN_CONTAINER}") {
                // Execute sonar Analysis
                echo "Sonar Scanner"
                sh "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:${SONAR_PLUGIN_VERSION}:sonar -P${MAVEN_SONAR_PROFILES} -s settings.xml"
              }
            }
        } catch (error) {
          currentBuild.result = 'FAILURE'
        } finally {
        }
      }
    }

    stage('Publish Reports'){
      junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
    }

    stage('Send Notifications'){
      // Send notification to inform about Build status
      mailNotification(env,currentBuild, mailTo)

      // Add comment to JIRA
      jiraNotification(env,currentBuild)
    }
}