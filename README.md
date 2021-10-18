### VehicleApp
CRUD web application to manage vehicles.

### Technologies:
- Java + Spring Boot
- TypeScript (AngularJS)
- MySQL
- Bootstrap for styling

### Instructions:

#### To create database:
Open mysql in terminal:
```
 sudo mysql --password
 create database db_vehicles;
 create user 'springuser'@'%' identified by 'ThePassword';
 grant all on db_vehicles.* to 'springuser'@'%';
```
Here `db_vehicles` was used as the database name. In case that is already taken, use another name in the mysql commands and update the line referring to the database in the `application.properties` file in `backend/vehicle\src\main\`.

Old line:
```
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/db_vehicles
```
New:
```
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/<your_database_name>
```

In a new console window the application folder and run the backend for the first time:
```
cd backend/vehicle
./mvnw spring-boot:run
```
After the app has started, terminate it by ctrl + C. Go back to mysql cli:
```
revoke all on db_vehicles.* from 'springuser'@'%';
grant select, insert, delete, update on db_vehicles.* to 'springuser'@'%';
```
And update `application.properties`:

Old line:
```
spring.jpa.hibernate.ddl-auto=create-drop
```
New:
```
spring.jpa.hibernate.ddl-auto=none
```
Now the database is more secure and ready to use.


#### To run backend:
```
cd backend/vehicle
./mvnw spring-boot:run
```

#### To run frontend:
```
cd frontend
npm install
npm start
```
VehicleApp is ready to use in the browser at `http://localhost:4200/`.
