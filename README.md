# AW04 API Testing Framework
## Pivotal Tracker

### Content
---
[TOC]
### Pre-requisites
-----
The pre-requisites to install and run the framework are the following.
Is required to have these applications installed:
```
Git installed in the remote device.
Java Jdk_8 and Jre_8.
Gradle.
Intellj IDEA
```
### Install and configure
-----
To install the framework use the following steps:
```
clone with command: git clone https://github.com/AWT-04/pivotal-tests.git
using Intellj IDEA open the project recently cloned.
wait gradle complete the initial build
```
create a and set config.json file:
```
go to the folder "configJson" inside the project
create new file with the name and extension: config.json
copy the content of config.json.dist in the new file created in the previous step
set your account token in the field: "x-trackerToken": "{account_token}"
set your user name account in the field: "username": "{user_name}"
set your account token in the field: "apiToken": "{account_token}"
set the value api with url in the field: "api": "https://www.pivotaltracker.com/services/v5"
save the file changes
```
### Structure
-----
The project have the following structure:
Configuration file.

| Location  | File name  | Description |
| :------------: |:---------------:| -----|
| configJson  | config.json | Configuration file. |

In folder src/main/java

| Location  | File name  | Description |
| :------------: |:---------------:| -----|
| core/api | Authentication.java |  This class permit the authentication, using a singleton pattern. |
| core/api | RequestManager.java |  This class control the initial request manager and basic rest-api methods. |
| core/utils | EndpointHelper.java |  This class build the different endpoint. |
| core/utils | VariableNameHandler.java |  This class permit set variable with different options. |
| pivotal | AWT04exception.java |  This class is a personalized handler exception. |
| pivotal | Environment.java |  This class use the different variable environment. |
| pivotal | JSONHelper.java |  The class permit read JSON files. |
| pivotal | ScenarioContext.java |  This class permit set scennario context using hash maps. |

In folder src/main/test

| Location  | File name  | Description |
| :------------: |:---------------:| -----|
| pivotal/steps/ | Hooks.java | This allows set pre and post conditions for scenarios. |
| pivotal/steps/ | RequestStepDefs.java | This allows set request step definitions for scenarios. |
| pivotal/steps/ | ResponseStepDefs.java | This allows set response step definitions for scenarios. |
| pivotal/steps/ | Runner.java | This allows to clean pivotal account before run all scenarios. |
| features/ | Stories.feature | Feature file for stories. |
| features/ | Tasks.feature | Feature file for tasks. |
| features/ | Workspaces.feature | Feature file for workspaces. |

### Run tests
-----
- To run all the tests you can open Runner.java file from path {project_path}/src/test/java/org/fundacionjala/pivotal/steps/ and execute.
- To run a specific feature file go to {project_path}/src/test/resources/features/ select a feature file, open the file and run.
- To run a specific test, after open a feature file select a scenario inside feature file, open  menu with right click and select the option run to run the test.


[![Build status](https://travis-ci.com/AWT-04/pivotal-tests.svg?branch=develop)](https://travis-ci.com/AWT-04/pivotal-tests) 

[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=AWT-04_pivotal-tests&metric=alert_status)](https://sonarcloud.io/dashboard/index/AWT-04_pivotal-tests)
