# Run test
- Go to  project root:

cd ryanair
- to delete /target content folder run:

mvn clean 

- To run local test with local Chrome browser:

mvn clean test -Dselenide.browser=chrome

-  To run local test with local Chrome and particular Cucumber tags of the features:
 
mvn clean test -Dselenide.browser=chrome-Dcucumber.options=--tags @<TAG_NAME>

- To run local remote tests under Selenium Hub with Chrome:

mvn clean test -Dselenide.browser=chrome -Dremote=http://localhost:4444/wd/hub

- To run local remote tests under Selenium Hub with Chrome for Dockerised Jenkins:

mvn clean test -Dselenide.browser=chrome -Dremote=http://hub:4444/wd/hub

*Browser to pass:  
-- chrome as most stable browser for December 2017