# Course Service
This service provides the course details. 
This service connects review service to fetch reviews of courses.

## Getting Started
Clone the github Repo and import in your fav IDE\
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
  You can use [sdman](https://sdkman.io/install) and choose 17.0.9-zulu as java<br>
  `sdk install java 17.0.9-zulu`
- Install Gradle

### Running the project
- To run the Service, run below commands
    ```
    gradle clean build
    java -jar build/libs/Courses-0.0.1-SNAPSHOT.jar
    ```

## Acknowledgments
- [Baeldung](https://www.baeldung.com)
- [StackoverFlow](https://stackoverflow.com/)