# Reports
Maven reports are stored in target folder.

Allure reports are stored in allure-report folder.

Screenshots are stored in target/surefire-reports folder.
 
Additionally screenshots are displayed on Allure Reports for failed test case:
 Allure > Suites > Scenario> click > Failed <Name of Scenario > screenshot > click.
 
Example of failed test reporting hosted here:
https://jane-hnatiuk.github.io/
 
Additionally examples of reports will be also provided with *.zip archive in the mail.


# Allure reports  
- Allure reports documentation: 
https://docs.qameta.io/allure/latest/  

- To use reports in Jenkins install Allure jenkins Plugin: 
https://docs.qameta.io/allure/latest/#_jenkins

- To generate the report (allure-report folder) locally run: 

```allure generate --clean```

  Report will generate in folder allure-report in project root
- To generate reports with automatic opening in default browser run:

```allure serve```
  Report will generate in temporary folder /var/folders/*
  
- To remove last test results run:

 ```rm -rf allure-results/```

