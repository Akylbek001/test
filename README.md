# Web automation framework 
This framework is designed to test web applications.

## Web start automation
For start, you need install this scope of program:

>- Git;
>- Java 11;
>- Maven
>- Maven Surefire 
>- testng 
>- Lombok 
>- Slf4j 
>- Allure 

## Configuration:

For cloning project you need change a global config file:
>git config --global --edit

After changing global config file you can try clone project:
>git clone
> >git may ask for your username and password for authorization

## FORK instructions
instructions of forking workflow in the link below:
https://docs.gitlab.com/ee/user/project/repository/forking_workflow.html 

### After forking project you can edit project for your usage. 
Edit "application.properties" file located in "ui_core" module to setup base properties (as base.url) in your project 
Path from content root is "src/main/resources/application.properties"

Open project on Intellij IDEA.

## Structure
Project consist of 2 module. 1 module is ui_core and second is for project automation tests
> ui_core/src/main/resources - property files
> 
> ui_core/src/main/java/helpers - wrappers for web elements
> 
> ui_core/src/main/java/driver - driver initialisation, options and capabilities
> 
> ui_core/src/main/java/common/utils - waiters, allure, pdf, random date, log and other utils
>
>your_module_for_test_development\src\test\java\tests\BaseTest.java - inits for managers and your pages and steps

## Configuration:
After installation, you need insert Java, Node, Maven and Git to environment paths;

For cloning project you need change a global config file:
>git config --global --edit

After changing global config file you can try clone project:
> git clone 
>
>--git may ask for your username and password for authorization

Open project on Intellij IDEA.

## Local Run 
local - mvn clean test
>mvn clean test-compile compile test -P MyTest

MyTest - id of Profile defined in pom.xml (this given as example)
 
### Reports:
reports - e2e_tests/target/allure-results (local runs)
To serve the report and open it in your default web browser, run the following command
mvn allure:serve

### Update forked project from main repo
instructions will be added !!!

## Test Example
next files given only as example:
src/test/java/tests/LoginTest.java
src/main/java/pages/LoginPage.java
src/main/java/steps/LoginSteps.java

in this project we are using chain pattern like test->step->page  

