# Service Oriented Assignment
Developed an Online Course Provider Service.
- This Project Contains three major service
  - Course
  - Reviews
  - Gateway

* **Course**:
    This service provides the course details.
* **Reviews**:
    This service handles crud operations of the reviews of a specific course.
* **Gateway**:
    This service is responsible for routing the request to appropriate service(course or reviews) based on the endpoint called.


## Getting Started
Clone the github Repo\
**Notes:**
- If you have the zipped project, just unzip it and import in any IDE
- The config file for main program can be found in `src/main/config/` folder
- The config files for test program can be found in `src/test/config/` folder

## Built With
* Java 17
* Gradle

### Prerequisites
- Java 17
- Gradle
- Intellij (or your choice of IDE)

### Installing
- Install java 17 \
  You can use [sdman](https://sdkman.io/install) and choose 11.0.9.j9-adpt as java<br>
  `sdk install java 17.0.9-zulu`
- Install Gradle

### Running the project
- To properly use the whole project, you need to run individual Services
- Go into each service folder and follow instruction given in their respective Readme Files

*NOTE* - All services should be up to work with the project.

## Acknowledgments
- [Baeldung](https://www.baeldung.com)
- [StackoverFlow](https://stackoverflow.com/)