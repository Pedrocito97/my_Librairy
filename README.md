# My Library Project

## 📖 Overview

My Library is a full-stack web application designed for book lovers who want to manage their personal book collections. The application allows users to register, log in, and perform CRUD operations on their book list while ensuring data security and user authentication through Spring security.

## 🏗️ Technologies Used

### Backend:

- Java & Spring Boot - Backend framework for handling business logic and API requests.

- Spring Security - Implements authentication for secure user login and data access.

- Spring Data JPA & Hibernate - ORM for database interaction.

- MySQL - Relational database for storing user and book data.

- Spring Boot Validation - Ensures data integrity and field validation.

### Frontend:

- React & TypeScript - Modern frontend framework for creating an interactive user experience.

- React Router - Enables seamless client-side navigation.

- Axios - Handles HTTP requests to the backend API.

- CSS & Styled Components - Ensures a responsive and visually appealing UI.

### Others:

- CORS Configuration - Manages cross-origin requests between frontend and backend.

- Global Exception Handling - Ensures proper error handling and informative responses.

## 🛠️ Features

### Authentication:

- Secure user registration with encrypted passwords.

- Secure session handling with logout functionality.

### Book Management:

- Add books to a personal library.

- View all books associated with the logged-in user.

- Delete books from the collection.

- Pagination support for large book collections.

- Search and filtering options for book retrieval.

### Additional Features:

- User-specific book storage (books are linked to individual accounts).

- Validation and error handling for book data.

- Clean and responsive UI with easy-to-use navigation.

## 📂 Project Structure and File Explanation

### Backend:

```backend/
├── model/           # Entity classes (User, Book)
│   ├── User.java    # Represents users in the database.
│   ├── Book.java    # Represents books and their attributes.
│
├── repository/      # JPA Repositories (UserRepository, BookRepository)
│   ├── UserRepository.java # Handles database queries for users.
│   ├── BookRepository.java # Handles database queries for books.
│
├── security/        # Authentication & Security (TokenUtil, Filters, SecurityConfig)
│   ├── TokenUtil.java # Handles JWT token generation and validation.
│   ├── TokenAuthentificationFilter.java # Filters requests based on token validation.
│   ├── SecurityConfig.java # Configures Spring Security settings.
│
├── controller/      # API Controllers (AuthController, BookController)
│   ├── AuthController.java # Handles user authentication (register/login/logout).
│   ├── BookController.java # Manages book-related operations (CRUD).
│
├── exception/       # Global Exception Handling
│   ├── GlobalExceptionHandler.java # Handles application-wide errors.
│
├── dto/             # Data Transfer Objects (BookDTO)
│   ├── BookDTO.java # Simplifies book data transfer.
│
├── config/          # Additional configurations (CORS, Security settings)
│
└── MainApplication  # Spring Boot Application Entry Point

Frontend:

frontend/
├── components/      # React Components (Login, Register, Books, AddBook)
│   ├── Login.tsx    # User login form.
│   ├── Register.tsx # User registration form.
│   ├── Books.tsx    # Displays book collection and allows deletion.
│   ├── AddBook.tsx  # Form for adding new books.
│
├── pages/          # Page views handling routing
│
├── App.tsx         # Main Routing File - Defines navigation between pages.
├── main.tsx        # React Entry Point - Mounts the React application.
├── styles/         # CSS Files for styling.
├── api.ts          # API Integration with Axios for making HTTP requests.
├── hooks/          # Custom hooks for state management.
├── utils/          # Utility functions used in different components.
```

## 🚀 Getting Started

### Backend Setup

Clone the repository

git clone https://github.com/your-username/my-library.git
cd my-library/backend

### Configure your application.properties with database credentials

spring.datasource.url=jdbc:mysql://localhost:3306/my_library

spring.datasource.username=root

spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update

spring.security.jwt.secret=mysecretkey

Run the Spring Boot application

mvn spring-boot:run

### Frontend Setup


Navigate to the frontend directory

cd my-library/frontend

Install dependencies

npm install

Start the React app

npm run dev

## 🎯 API Endpoints

### Authentication:

- POST /api/auth/register - Register a new user.

- POST /api/auth/login - Login and receive a JWT token.

- POST /api/auth/logout - Logout and invalidate session.

### Book Management:

- GET /api/books - Retrieve all books owned by the logged-in user.

- POST /api/books - Add a new book to the user's collection.

- DELETE /api/books/{id} - Delete a specific book (only if owned by the user).

## 📌 Roadmap & Future Improvements

Edit book functionality - Users should be able to update book details.

Advanced filtering options - Filter books by author, title, rating, etc.

Role-based access control - Implement admin roles for better management.

Unit & Integration testing - Increase test coverage with JUnit & Mockito.

Deployment pipeline - Setup CI/CD for automated deployments.

Frontend enhancements - Improve UI/UX with better animations and styling.

Admin dashboard - Implement an admin panel for better user and book management.

