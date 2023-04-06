Система регистрации и обработки пользовательских заявок. 

Пользователь посредством системы может подавать заявки оператору на рассмотрение. 
Оператор может просматривать пользовательские заявки и принимать или отклонять их. 
Администратор управляет правами доступа.

Для запуска нужно локально поднять PostgreSQL, после логина получаем Cookie JSESSIONID для тестирования через Postman.

На старте приложения создаются три пользователя со стартовыми ролями: USER, ADMIN, MODERATOR

1) логин: user пароль: user
2) логин: admin пароль: admin
3) логин: moderator пароль: moderator

Пути:

USER
Get-запрос http://localhost:8080/application/ - получение всех заявок данного пользователя с возможностью сортировки по дате;
Post-запрос http://localhost:8080/application/ - создание заявки в теле "message":String (raw(JSON));
Patch-запрос http://localhost:8080/application/{id} - редактирование заявки в статусе «draft»;
Patch-запрос http://localhost:8080/application/send/{id} - отправить заявку;

ADMIN
Get-запрос http://localhost:8080/admin/ - получение списка всех пользователей с возможностью сортировки по дате;
Get-запрос http://localhost:8080/admin/{username} - искать конкретного пользователя по его имени;
Get-запрос http://localhost:8080/admin/byPrefix/{prefix} - искать конкретного пользователя по его части имени;
Patch-запрос http://localhost:8080/admin/update/{username} - назначать пользователю права оператора;

MODERATOR
Get-запрос http://localhost:8080/moderator/ - просматривать все отправленные на рассмотрение заявки с возможностью сортировки по дате;
Get-запрос http://localhost:8080/moderator/byUser/{username} - просматривать отправленные заявки только конкретного пользователя по имени;
Get-запрос http://localhost:8080/moderator/byPrefix/{prefix} - просматривать отправленные заявки только конкретного пользователя по части имени;
Patch-запрос http://localhost:8080/moderator/accept/{applicationId} - принимать заявку;
Patch-запрос http://localhost:8080/moderator/reject/{applicationId} - отклонять заявку;

Stack: Rest архитектура, Spring Boot, Spring Security, Hibernate, PostgreSQL.


