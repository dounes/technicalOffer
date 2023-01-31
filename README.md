#Spring boot project for AF technical offer test

The project uses **SpringBoot** for all the application pre-configuration, **MyBatis** as an ORM framework to map the embedded **H2** database used in the application, **Spring AOP** to display logs and informations about the execution of each method of the controller, and displays also the processing time   

###Build from source
- Run a simple mvn _clean install_ to download all the project's dependencies<br>
- Run the application

###Test the application
- Some JUnit unitary tests are available under the test folder<br>
- A postman collection is available under the resources folder, it contains two endpoints
###How to use the API
  - Running the postman collection
    - register a user : a body that represents a user should be passed as a RequestBody. a user is defined as below :</br>
      {</br>
        &nbsp;&nbsp;&nbsp;"name": _String_,</br>
        &nbsp;&nbsp;&nbsp;"countryOfResidence": _String_,</br>
        &nbsp;&nbsp;&nbsp;"birthDate": _String_,</br>
        &nbsp;&nbsp;&nbsp;"phoneNumber": _int_,</br>
        &nbsp;&nbsp;&nbsp;"gender": _char_</br>
     }
  </br>
    - retrieve a user : Two request parameters are required (Name and birthdate). Try to retrieve the same user you've just created to ensure that it has been well persisted.</br></br>
  - **_Only adult french users who are allowed to register_**