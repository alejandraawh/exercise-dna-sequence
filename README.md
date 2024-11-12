# Project Spring Boot: DNA Sequence Analysis

This project implements a RESTful API to analyze DNA sequences and determine if they correspond to a human or a mutant. It uses Spring Boot, JPA and a MySQL database to store the DNA records.

![Spring Boot](https://img.shields.io/badge/Spring_Boot-2.5.4-brightgreen)

## Table of Contents

1. [Description](#description)
2. [Requirements](#requirements)
3. [Installation](#installation)
5. [EndPoints](#endpoints)
6. [Technologies](#technologies)
7. [Contribution](#contribution)
8. [License](#license)
---

## Description

This project is a RESTful API developed in Spring Boot for the validation of human and mutant DNA sequences. The API receives a DNA array as input and returns a result based on validation of the sequences.

### Characteristics:
- Endpoint to validate mutants based on sequence patterns.
- Registration of sequences in a database.
- Endpoint to get mutant and human stats.

---

## Requirements

To run this project, make sure you have the following requirements:

- **Java 17 or higher**.
- **Maven 3.6 or higher**.
- **MySQL or any compatible database**.
- **Docker** (if you want to run the container in Docker).

---

## Installation

Follow these steps to install and run the project on your local machine:

1. **Clone the repository**:

   ```bash
   git clone https://github.com/alejandraawh/exercise-dna-sequence.git
   
   cd exercise-dna-sequence

## Technologies

- **Spring Boot**: Java framework used to build web applications and microservices.
- **Docker**: Platform for developing, shipping, and running applications inside containers.
- **Java 17**: Programming language used to develop the application.
- **Maven**: Dependency management and build tool for the project.
- **AWS ECR**: Amazon Web Services container registry service for storing Docker images.
- **AWS EC2**: Elastic Compute Cloud service from AWS to host and run the application in a virtual machine.
- **AWS RDS**: Amazon Relational Database Service for hosting and managing the database in the cloud.
- **JUnit 5**: Framework for unit testing in Java.
- **Spring Data JPA**: Persistence framework to interact with databases using JPA.

## Endpoints

### GET: `/api/v1/stats`
Gets statistics about the processed DNA sequences.

- **URL**: `http://3.128.186.214:8080/api/v1/stats`
- **Method**: `GET`
- **Response**:
  ```json
  {
      "count_mutant_dna": 2,
      "count_human_dna": 1,
      "ratio": 2.0
  }
### POST: `/api/v1/mutant`
Validates the input string to know if the DNA is mutant or human.
- **URL**: `http://3.128.186.214:8080/api/v1/mutant`
- **Method**: `POST`
- **Request**:
    ```json
    {
        "dna": [
            "ATGCAA",
            "CAGTGC",
            "TCCTGT",
            "AGCAGG",
            "CCCCTA",
            "TCACTG"
        ]
    }

- **Response**:
    ```json
    {
        "isMutant": false
    }
### Validation Details:

The input DNA sequence is validated to check if it matches the pattern for either a human or mutant sequence.
If the DNA sequence is already stored in the database (i.e., it is a duplicate), the API will return a conflict error.
Error Response (Duplicate Entry): If the input DNA sequence already exists in the database, a 409 CONFLICT status is returned with a message indicating the duplication:

- **Example 409 - Duplicate Entry**:
    ```json
    {
    "timestamp": "2024-11-12T05:57:09.274763633",
    "status": "409",
    "error": "CONFLICT",
    "message": "Duplicate entry '[ATGCGA, CAGTGC, TCCTGT, AGCAGG, CCCCTA, TCACTG]' for key 'record_dna.UKri970613sb67uachjqu4tfgml'",
    "path": "uri=/api/v1/mutant"
}
- **Example 409 - Sequence Invalid**:
    ```json
   {
    "timestamp": "2024-11-12T06:48:02.743873260",
    "status": "409",
    "error": "CONFLICT",
    "message": "DNA sequence invalid. character not allowed: X",
    "path": "uri=/api/v1/mutant"
}
## Contributions

We welcome contributions from the community! To contribute to this project, please follow these steps:

1. Fork this repository.
2. Create a new branch for your changes (`git checkout -b feature/your-feature`).
3. Make your changes and commit them (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Open a Pull Request with a detailed description of your changes.

### Contributions Guidelines:
- Ensure your code follows the existing code style and conventions.
- Write tests for any new functionality or bug fixes.
- Update the documentation, if applicable.
- Please make sure that all tests pass before submitting a pull request.

If you have any suggestions or bug reports, feel free to open an issue in the Issues section of this repository.

Thank you for your contributions!

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
