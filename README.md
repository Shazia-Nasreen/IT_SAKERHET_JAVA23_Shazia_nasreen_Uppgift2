# Time Capsule Application

## Overview
This is a secure Time Capsule application that allows users to store and retrieve encrypted messages. Only registered users can access their messages by logging in. The application uses JWT for authentication and AES for encryption to ensure that the messages are stored securely in the database.

## Features
1. **User Registration**: Users can create an account with an email and password. Passwords are hashed using SHA-256 before being stored.
2. **User Login**: Registered users can log in to their accounts and receive a JWT token for authentication.
3. **Create Capsule**: Logged-in users can create a time capsule message, which is encrypted with AES before being saved to the database.
4. **Read Capsule**: Users can view and decrypt their stored messages after logging in.

## Technologies Used
- **Backend**: Java, Spring Boot
- **Database**: MySQL
- **Encryption**: AES (Advanced Encryption Standard)
- **Authentication**: JWT (JSON Web Token)
- **Frontend**: Basic HTML,CSS & JavaScript
