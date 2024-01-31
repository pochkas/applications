#H System of registration and processing of user requests. 

The user can submit applications to the operator for consideration through the system. 
The operator can view user requests and accept or reject them. 
The administrator manages access rights.

To start you need to locally pick up PostgreSQL, after login we get Cookie JSESSIONID for testing through Postman.

At the start of the attachment, three users with the starting roles are created: USER, ADMIN, MODERATOR

1) login: user password: user
2) login: admin password: admin
3) login: moderator password: moderator

Routes:

USER
* Get http://localhost:8080/application/ - receiving all the requests of that user with the possibility of sorting by date;
* Post http://localhost:8080/application/ - create a query in "message":String (raw(JSON));
* Patch http://localhost:8080/application/{id} - status modification of the application «draft»;
* Patch http://localhost:8080/application/send/{id} - send application;

ADMIN
* Get http://localhost:8080/admin/ - obtaining a list of all users with the possibility of sorting by date;
* Get http://localhost:8080/admin/{username} - search for a specific user by his/her name;
* Get http://localhost:8080/admin/byPrefix/{prefix} - search for a specific user by his part of the name;
* Patch http://localhost:8080/admin/update/{username} - assign the user the rights of the operator;;

MODERATOR
* Get http://localhost:8080/moderator/ - view all submitted applications with date-sorting;
* Get http://localhost:8080/moderator/byUser/{username} - view requests sent only by a specific user by name;
* Get http://localhost:8080/moderator/byPrefix/{prefix} - view requests sent only by a specific user by name;
* Patch http://localhost:8080/moderator/accept/{applicationId} - accept applications;
* Patch http://localhost:8080/moderator/reject/{applicationId} - reject applications;

Stack: Spring Boot, Spring Security, Hibernate, PostgreSQL.


