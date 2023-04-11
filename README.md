This application filters the ip range for selectable regions from AWS ip ranges from the below json : 

https://ip-ranges.amazonaws.com/ip-ranges.json

Technologies used: • Java 17 • Spring Boot 3.0.5 • Docker • Maven

Steps to run the application
-----------------------------

1. Import the Maven project in IDE after cloning or downloading the project 

2. Run as a spring boot application. 
   Endpoints for testing : 

   http://localhost:8080/ip-ranges
  
   http://localhost:8080/ip-ranges?region=US
  
3. Use the following commands to build and run the application on Docker : 

   docker build -t region-filter .

   docker run -it -p 8080:8080 region-filter
   
 4. GitHub Actions has been configured to build and test this application : 
   
    https://github.com/Arun-Togaf/region-filter/actions/runs/4672864481
   
   
