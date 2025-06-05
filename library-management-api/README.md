Library Management API, when downloading modify the username and password for setting up the database in the file application.properties.

Once the application is set up, go to MySQL and create a database with the same name of the application.properties:
In this case, spring.datasource.url=jdbc:mysql://localhost:3306/bookdb?useSSL=false&serverTimezone=UTC
bookdb is the name of the database.

The application should create a table called books that would store all the data entered to the system.

Endpoints for testing (PORT value is usually 8080):
1. When initializing the app go to http://localhost:PORT/api/books. This should display an empty page if no previous posting has been made.
2. For getting a Book go to http://localhost:PORT/api/books/id. Where id indicates which book information you want to display.
3. For posting use Postman or curl on http://localhost:PORT/api/books, the request body should look like this:
  {
        "title": "Title",
        "author": "The Author",
        "isbn": "978-0446310789",
        "publishedDate": "1960-07-11"
  }
4. For updating a Book go to http://localhost:PORT/api/books/id, the request body should look like this:
  {
        "title": "New Title",
        "author": "Same or updated author",
        "isbn": "New ISBN",
        "publishedDate": "2023-05-10"
  }
5. For deleting a Book go to http://localhost:PORT/api/books/id. Where the id should be the id of a registered Book you want to delete.
