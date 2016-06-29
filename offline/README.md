This project was developing using:

* Ubuntu (14.04 LTS)
* Eclipse (4.5.0)
* Maven 3 (3.0.4)
* Java 8 (1.8.0_74)

This application makes use of two libraries:
* libphonenumber (https://github.com/googlei18n/libphonenumber)
* Java Client for Google Maps Services (https://github.com/googlemaps/google-maps-services-java/)

Command line steps to build the application:

- git clone https://github.com/andreneonet/challenges.git
- cd challenges/offline
- mvn clean install assembly:single

Usage command line:

- java  -Djava.util.logging.config.file=src/main/resources/logging.properties -jar target/offline-0.0.1-SNAPSHOT-jar-with-dependencies.jar [--same-country-only] \<target number\> \<customer numbers...\>

Examples of usage:

- java  -Djava.util.logging.config.file=src/main/resources/logging.properties -jar target/offline-0.0.1-SNAPSHOT-jar-with-dependencies.jar +12018840000 +15148710000 +14159690000
>Output: +15148710000 ( Region: CA, Location: Montreal, QC)

- java -Djava.util.logging.config.file=src/main/resources/logging.properties -jar target/offline-0.0.1-SNAPSHOT-jar-with-dependencies.jar --same-country-only +12018840000 +15148710000 +14159690000
>Output: +14159690000 ( Region: US, Location: California)

- java  -Djava.util.logging.config.file=src/main/resources/logging.properties -jar target/offline-0.0.1-SNAPSHOT-jar-with-dependencies.jar --same-country-only +33975180000 +441732600000 +14159690000
>Output: no match found

- java -Djava.util.logging.config.file=src/main/resources/logging.properties -jar target/offline-0.0.1-SNAPSHOT-jar-with-dependencies.jar +12018840000 +15148710000 +94959690000
>Output: [+94959690000] invalid phone number

Limitations:
 * This application uses a standard key to access to the Google Maps Services. The usage limitations are: 2,500 free requests per day and 10 requests per second 
 * This application only processes phone numbers that are valid to libphonenumber library. Otherwise the phone numbers are considered invalid.






