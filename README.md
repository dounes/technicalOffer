# AF Technical Test Offer

__Spring-Boot RestAPI with an embedded H2 Database to register or retrieve a User__

### 1. You can clone it from github by running following command

```
  $ git clone https://github.com/dounes/technicalOffer.git
```

### 2. Import project into Eclipse/IntelliJ
```
  (Eclipse) File -> Import -> Maven -> Existing Maven Projects -> Browse Project from cloned location
  (IntelliJ) File -> Open -> Browse Project from cloned location
```
### 3. Update and Build from source
```
  (Eclipse) Right click on project in eclipse and then Maven -> Update Projects 
  (IntelliJ) Browse to pom.xml -> Add as a Maven Project -> Build (Top right corner)
```
### 4. Run the application !
```
  A postman collection is available under 'src/main/resources/postman'
```
## Once the application is started, you can test the endpoint using POSTMAN

#### 1. To display a user call following endpoint with GET Request, with the userID as a PathVariable
```
  http://localhost:8080/users/6
```
### 8.To create a new user use following url with POST Request
```
  http://localhost:8080/users/
```
### set content type as in header as `application/json`
### set request body as raw with JSON payload
```
  {
  "fullname": "string",
  "birthdate": "2023-02-06",
  "country": "string",
  "gender": "string",
  "phone": 0
}

```
## If you prefer testing the API using swagger-ui, you can do it by typing this url :
```
  http://localhost:8080/swagger-ui/index.html
```
