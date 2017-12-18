
# Setup procedure  

- Install Java
- Install Maven
- Clone repository git clone https://github.com/jane-hnatiuk/ryanair.git

To run test in Jenkins CI you can use Allure Plugin and Jenkinsfile with predefined steps for Maven Build and reporting (Allure and Cucumber).

1) Add Allure Plugin in Jenkins CI:
This plugin allows to automatically generate Allure Report and attach it to build during Jenkins job run.

To do this:

* Global Tool Configuration :
* Add  Maven and Allure: 
  *  Maven installations: Maven 3.5 
  *  Allure Commandline: Allure 2.4

More info: https://docs.qameta.io/allure/latest/#_jenkins


2) Add Jenkinsfile as Pipeline script
Creating a Jenkinsfile, which is checked into source control, provides a number of immediate benefits:
- Code review/iteration on the Pipeline
- Audit trail for the Pipeline
- Single source of truth for the Pipeline, which can be viewed and edited by multiple members of the project.

To do this:

 Job > Configure:
   * choose Pipeline: Pipeline script from SCM
   * SCM: GIT
   * Repositories:
   * Repository URL: https://github.com/jane-hnatiuk/ryanair.git
   * Branches to build: */master
   * Script Path: Jenkinsfile 

More info: https://jenkins.io/doc/book/pipeline/jenkinsfile/
