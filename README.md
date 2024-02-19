User Service API Documentation

Overview
The User Service API provides endpoints for basic user management operations, including user creation and retrieval. This API is designed to be used by client applications to interact with the user database.

Base URL
The base URL for accessing the API is http://localhost:8080/api/v1/users.

Authentication
The API uses basic authentication for securing endpoints. Users must provide their username and password in the request headers to access protected endpoints.

Endpoints
GET /{userName}
Retrieves user information for the specified username.

Request Parameters:

userName (path variable): The username of the user to retrieve.
Response:

200 OK: Returns the user information in the response body.
Body: { "userName": "john_doe" }
401 Unauthorized: If the user making the request does not have any role (e.g., USER or ADMIN).
404 Not Found: If the user with the specified username does not exist.
POST /
Creates a new user with the provided details.

Request Body:

userName (string, required): The username of the new user.
password (string, required): The password of the new user.
Response:

201 Created: If the user is successfully created, returns the user information in the response body.
Body: { "userName": "john_doe" }
400 Bad Request: If a user with the specified username already exists.
403 Forbidden: If the user making the request does not have the ADMIN role.
