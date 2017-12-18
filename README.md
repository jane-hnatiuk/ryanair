# Test for booking up to a declined payment 

This test automation framework uses Java, Maven and JUnit5 and contains Selenide API, Cucumber JVM BDD with Allure reports.

**Selenide** is a framework for test automation powered by Selenium WebDriver (Selenium wrapper)that brings the following advantages:
- Concise fluent API for tests Ajax support for stable tests
- Powerful selectors
- Simple configuration
- Don't need to think how to shutdown browser, handle timeouts and 
- StaleElement Exceptions or search for relevant log lines, debugging your tests.

More advantages: https://github.com/codeborne/selenide/wiki/Selenide-vs-Selenium

Official documentation: http://selenide.org/

Page Object Pattern used along with mix of Selenide framework style

Test are written with behavior driven development (BDD) approach for UI automation of required task.
**Cucumber** is one such open source tool, which supports behavior driven development. 
Cucumber can be defined as a testing framework, driven by business stories.
Cucumber executes  .feature files, and those files contain executable specifications written in a language called Gherkin.
Cucumber-JVM is a Cucumber implementation for the most popular JVM languages, in our case this is Java.

Official documentation: https://cucumber.io/docs

 
**Allure** is an open-source framework designed to create test execution reports that are clear to everyone in the team.
Allure Framework is a flexible lightweight multi-language test report tool that not only shows a very concise representation of what have been tested in a neat web report form, but allows everyone participating in the development process to extract maximum of useful information from everyday execution of tests.
From the dev/qa perspective Allure reports shorten common defect lifecycle: 
test failures can be divided on bugs and broken tests, also logs, steps, fixtures, attachments, timings, history and integrations with TMS and bug-tracking systems can be configured, so the responsible developers and testers will have all information at hand.
From the managers perspective Allure provides a clear 'big picture' of what features have been covered, where defects are clustered, how the timeline of execution looks like and many other convenient things. Modularity and extensibility of Allure guarantees that you will always be able to fine-tune something to make Allure suit you better.

Official documentation: https://docs.qameta.io/allure/latest/
 
Pre-installation
--------------
To run tests you need to install Java, Maven and git. 
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

Installing
-------------
Clone the repo to get a working project:
git clone https://github.com/jane-hnatiuk/ryanair.git

Running the tests - command line mode
-------------------
Please note: Highly recommended for test stability to run test via Chrome browser.
To run test go to the project path:

cd ryanair

To run local test with local chrome:

mvn clean test -Dselenide.browser=chrome

To run local remote tests under Selenium Hub with Chrome:

mvn clean test -Dselenide.browser=chrome -Dremote=http://localhost:4444/wd/hub

To run local remote tests under Selenium Hub with Chrome for Dockerised Jenkins:

mvn clean test -Dselenide.browser=chrome -Dremote=http://hub:4444/wd/hub


Running the tests - from IDE  
-------------------
Run [src/test/java/testRunners/DeclinedPaymentTest.java](src/test/java/testRunners/DeclinedPaymentTest.java) as Maven test


More Documentation
-------------
* [Installation](doc/InstallationDoc.md)
* [Run tests](doc/RunTestsDoc.md)
* [All about reporting and how to use allure reports](doc/ReportsDoc.md)

Built With
-------------
* [Selenium](http://www.seleniumhq.org/) - The web framework to automate browsers
* [Selenide](http://selenide.org/) - Selenium Wrapper
* [Maven](https://maven.apache.org/) - Dependency Management
* [Cucumber](https://cucumber.io/) - Behavior Driven Development (BDD) library 
* [Allure reports](http://allure.qatools.ru/) - Multi-language test report tool

