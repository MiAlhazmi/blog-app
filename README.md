# blog-app

## **Code acceptance criteria**
I will not accept any code that is not:
1. Clean and well-formatted: Ensure that the code follows proper indentation, uses meaningful variable names, and adheres to the coding style and conventions of the programming language.
2. Commented and documented: Include comments throughout the code to explain the logic, assumptions, and any complex parts. Additionally, provide clear documentation that describes the purpose and usage of the code.
3. Efficient and optimized: Optimize the code for performance and resource usage. Avoid unnecessary loops, redundant operations, and excessive memory consumption.

- **Conventions for naming things:**
1. CSS: use meaningful class names.
2. Java: use `camelCase` (aka lowerCamelCase) for attirbutes, methods and variables. And `PascalCase` (aka UpperCamelCase) for classes and interfaces.
3. HTML: structure the markup semantically and use proper element hierarchy.
4. TypeScript:
   1. DO NOT depend on type inference.
   2. use `camelCase` for variables, parameters, functions, methods and properties
   3. use `PascalCase` for classes, interfaces, types, enums, decorators and type parameters
   4. use `CONSTANT_CASE` for global constant values, including enum values.  
## *NOTE*
npm_modules are not included in the fronetd folder (they are big in size so i added them to .gitignore). Once you clone the project, cd into `frontend/ui` folder and run the command `npm install`. This will install all the npm_moduels you need. 
## Database connection:

Each one of us will have a database setup in their machine (this is the only free option). 
Once you clone the project, navigate to [this application.properties](https://github.com/Yazeed1s/blog-app/blob/main/backend/blogApp/src/main/resources/application.properties)

you will find the following configs:
```
#--- db_test is the name of the database i have in my machine
spring.datasource.url=jdbc:mysql://localhost:3306/db_test 
spring.datasource.username=yourUserName
spring.datasource.password=yourPassowrd
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql: true
spring.jpa.hibernate.ddl-auto=create 
```
### To connect the database with the application:
\
1- You need to create a database in your machine and substitute its name with `db_test`.
\
2- Since the database is hosted locally, leave `localhost` as is.
\
3- `3306` is the default port mysql uses, (can be different in your case, double check).
\
4- The values of `spring.jpa.hibernate.ddl-auto` can be any of `create, update, validate, or create-drop`. 

- `create` tells the application (hibernate's sessionFactory) to drop the old tables and recreate new tables everytime the app strats. **if you want to keep the old tables do not use this vlaue** 
- `update` tells the application to keep the old tables and update them with every app run or API request.
- `create-drop` tells the application to drop the tables at the end of the session. (tables will be created at run time, then destroyed when the app stops)
- `validate` no idea, google it!
- Usually, you will need to switch values between `create` & `update` as you test your APIs or test the proper hibernate annotations.


## API testing:  

To test the API, you will need to download [postman](https://www.postman.com/downloads/) or [insomnia](https://insomnia.rest), then once you set it up, run the spring application and send the http request to localhost:8080/followed/by/the/api/endpoint from the API tester. (i will elaborate more on this with some pictures)

## Usefull links:

[Spring Boot and Angular Full Stack Development | 4 Hour Course](https://www.youtube.com/watch?v=8ZPsZBcue50)
\
[Spring Boot Full Stack with Angular | Full Course [2021]](https://www.youtube.com/watch?v=Gx4iBLKLVHk&t=8340s)
\
[Full Stack Spring Boot 3 API with Angular (ADVANCED)](https://www.youtube.com/watch?v=tX7t45m-4H8)
\
[JSON Web Token with Spring Security and Angular](https://www.youtube.com/watch?v=FMGQsW_B9Rs)
\
[Spring Boot 3 + Spring Security 6 - JWT Authentication](https://www.youtube.com/watch?v=KxqlJblhzfI)
\
[Official documentation for Angular](https://angular.io/start)
\
[Official documentation for springboot](https://spring.io/guides)
\
[Angular Routing](https://angular.io/guide/routing-overview)
\
[Hibernate Inheritance Mapping](https://www.baeldung.com/hibernate-inheritance)
\
[SpringBoot tuturial](https://www.javatpoint.com/spring-boot-jpa)
