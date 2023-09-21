# validationapi
* This project was generated with Spring Boot 3.0.10 and Java 17
* It implements the dependecies:
  1- spring-boot-starter-thymeleaf for the frontend.
  2- spring-boot-starter-validation for hibernate validator annotations.
  3- spring-boot-starter-web for making use of Spring MVC and embedded container Apache Tomcat )

Setting up the application:
* Download the "validationapi" from this repository to your local machine, extract the zip file and open the "valitation" folder with your favorite IDE. 
* Run the "ValidationapiApplication" main class.
  
Development server:
* On your browser, navigate to http://localhost:8080/
* A form will display, now you can start testing the API by entering the credit card details information, if the details are valid you will reach a "Success message", if not, you will receive an error message asking you to correct the invalid information.
* You can make use of the PayPal card generator for developers API for testing https://developer.paypal.com/api/rest/sandbox/card-testing/ 
