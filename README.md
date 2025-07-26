# Portfolio API

This is the backend API for [devincoopers.space](https://devincoopers.space), a personal portfolio site built with Angular and Spring Boot. The API serves project data and is deployed to a Kubernetes (k3s) cluster via GitHub Actions using Helm.

## Features

- RESTful API built with Spring Boot
- Flyway database migrations
- MyBatis for database interactions
- MySQL for persistent storage
- API key-based authentication
- CI/CD pipeline with GitHub Actions
- Containerized using Docker
- Deployed via Helm to a home Kubernetes cluster

## Endpoints

| Method | Endpoint            | Description            |
|--------|---------------------|------------------------|
| GET    | /api/projects       | Get all portfolio projects |
| GET    | /api/projects/{id}  | Get project by ID      |
| POST   | /api/projects       | Create a new project   |
| PUT    | /api/projects/{id}  | Update a project       |
| DELETE | /api/projects/{id}  | Delete a project       |

## Environment Variables

| Variable           | Description                          |
|--------------------|--------------------------------------|
| SPRING_DATASOURCE_URL | JDBC URL for MySQL database       |
| SPRING_DATASOURCE_USERNAME | MySQL username             |
| SPRING_DATASOURCE_PASSWORD | MySQL password             |
| API_KEY            | API key required for protected endpoints |

## Running Locally

```bash
./gradlew bootRun
```

Or with Docker:

```bash
docker build -t portfolio-api .
docker run -p 8081:8081 portfolio-api
```

## License

MIT License
