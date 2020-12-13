# MVP-Employee-Salary-Mgmt

This file walks you through the process of creating a server application that can receive HTTP multi-part file(CSV) upload.

This applicaton is developed based on requirement for MVP employee salary management system assignment using spring boot REST API and MySQL DB to store uploaded data.

I have chosen to create spring boot application  in eclipse STS with maven dependencies. To upload files with Servlet containers, you need to register a MultipartConfigElement class (which would be <multipart-config> in web.xml). 

>Controller: RestController annotation is used to create RESTful web services using Spring MVC, so it will take care of request handler maping. UserController is tagged with RequestMapping with values and method type
to tie path with HTTP

Run the Application:

--mvn clean install-- to clean and install the packages.
--mvn spring-boot:run-- to run the application.

Thanks to spring boot for auto-configuration of API and in built web server to run the application easily.

That runs the server-side piece that receives file upload. Logging output is displayed. The service should be up and running within a few seconds.

With the server running, you need to open a browser and visit 
http://localhost:8080/mvp to see the upload form
http://localhost:8080/mvp/users to see list of users which are uploaded to DB
http://localhost:8080/mvp/users/{id} to see specific user details

Testing the Application:
There are multiple ways to test this particular feature in our application. I have used MockMVC so this doesn't require to starting the servlet container.
 


