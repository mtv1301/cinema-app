# Cinema application
A RESTful web application simulating a simple cinema service, where user can buy tickets for a movie session with a specific movie, cinema hall and air time. User can add multiple tickets to their shopping cart and finalize the order when ready. Using Spring Security for the authentication and authorization of the user. Here are some actions depending on the user's role (anonymous, admin, user):

### With User role:
* form the ticket with a specific movie, cinema hall and air time.
* add tickets to the shopping cart
* view all their tickets in the shopping cart
* complete the order
* view a history of all their orders
### With Admin role:
* find users by their email 
* create and add movies to the DB
* create and add cinema halls to the DB
* CRUD operations with the movie sessions
### Anonymous:
* registration
### Technologies used
* Frameworks: Spring - Core / MVC / Web / Security
* ORM: Hibernate
* DB: MySQL
* Packaging - Apache Maven
* WebServer: Tomcat

### Configuration:
1. Clone this project into your local folder and open the project in an IDE.
2. Edit mock data in the "db.properties" file to yours.
3. Configure Tomcat Server.
4. Run a project.
5. You can test the API with the existing ADMIN ("admin@i.ua", password:"admin123") and USER (name: "bob@gmail.com", password:"12345") roles.# cinema-app
