### Checking updates for dependencies
    mvn versions:display-dependency-updates

### Running tests
mvn clean test

mvn clean test -Dbrowser=REMOTE

### Generate report
allure serve allure-results

### Generate report (Allure 3)
allure allure2 allure-results
allure open

allure awesome allure-results
allure open    