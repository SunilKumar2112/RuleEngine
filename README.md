# RuleEngineAST

This project implements a Rule Engine using Abstract Syntax Trees (AST). It provides a flexible system for creating, combining, and evaluating business rules. The application uses React+Vite for the front-end and Spring Boot for the back-end.

## Frontend (React + Vite)

The frontend provides a minimal setup to get React working in Vite with HMR and some ESLint rules.

Currently, two official plugins are available:

- [@vitejs/plugin-react](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react/README.md) uses [Babel](https://babeljs.io/) for Fast Refresh
- [@vitejs/plugin-react-swc](https://github.com/vitejs/vite-plugin-react-swc) uses [SWC](https://swc.rs/) for Fast Refresh

## Backend (Spring Boot)

The backend is built with Spring Boot, providing RESTful APIs for rule management and evaluation.

## Installation Instructions

### 1. Clone the Repository

To get a local copy of the project, clone the repository:
git clone [your-repository-url] cd RuleEngineAST

### 2. Set Up Frontend

Navigate to the frontend directory and install dependencies:
cd RuleEngineAST-Frontend 
npm install

### 3. Set Up Backend

Navigate to the backend directory:
cd ../RuleEngineAST-Backend

Ensure you have Java 17 and Maven installed.

### 4. Set Up Environment Variables

For the frontend, create a `.env` file in the `RuleEngineAST-Frontend` directory:
VITE_API_BASE_URL=http://localhost:8080

For the backend, configure the `application.properties` file in `src/main/resources`:
spring.datasource.url=jdbc:mysql://localhost:3306/ruleengineast spring.datasource.username=your_username spring.datasource.password=your_password

### 5. Run the Application

Start the backend:
cd RuleEngineAST-Backend 
./mvnw spring-boot:run

In a new terminal, start the frontend:
cd RuleEngineAST-Frontend 
npm run dev

The frontend will be available at http://localhost:5173 (or the port specified by Vite).

## Features

- Create rules with a user-friendly interface
- Combine existing rules using logical operators
- Evaluate rules against user-provided data
- Visualize rule structures

## API Endpoints

- `POST /rules/create`: Create a new rule
- `POST /rules/combine`: Combine two existing rules
- `POST /rules/evaluate`: Evaluate a rule against provided data

For detailed API documentation, refer to the backend controller files.

## Technologies Used

- Frontend: React, Vite, Axios
- Backend: Java 17, Spring Boot, Spring Data JPA, MySQL
- Build Tool: Maven

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License.
