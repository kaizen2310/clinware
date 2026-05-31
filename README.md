# Internship Assignment: Weather-Based Activity Recommender

## Project Overview
Your task is to complete a "Weather-Based Activity Recommender" application. This service fetches current weather data for a given location and recommends an activity. It also integrates with a (simulated) MCP (Model Context Protocol) server for personalization.

The project is built using **Java 21**, **Maven**, and **Quarkus**.

## Tasks (TODOs)
There are 5 main tasks marked with `TODO` in the codebase:

1.  **TASK 1 (WeatherServiceClient.java):** Implement the REST client method to fetch weather data from Open-Meteo.
2.  **TASK 2 (ActivityService.java):** Implement the business logic to recommend activities based on temperature and weather conditions.
3.  **TASK 3 (ActivityService.java):** Implement the "Anomaly" Scoring Logic. This requires careful handling of bitwise operations and mathematical properties.
    *   **Bitwise XOR Condition:** The base score (rounded to `int`) must be XORed with `0x0F` *only* if the `weatherCode` is an ODD number.
    *   **Twin Prime Definition:** A number `p` is a Twin Prime if it is prime AND either `p-2` or `p+2` is also a prime number. If the final score meets this condition, the recommendation string must be reversed (character-wise).
4.  **TASK 4 (application.properties):** Configure the external service endpoints.
5.  **TASK 5 (ActivityResource.java):** Implement a status health-check endpoint.

## Requirements
- The assignment must be completed within **1 hour**.
- Do not use external libraries other than those already in `pom.xml`.
- Ensure all tests pass by running `mvn test`.
- Check your test coverage using JaCoCo (reports generated in `target/jacoco-report/index.html`).

## Evaluation Criteria
- **Correctness:** Do all tests pass?
- **Code Quality:** Is the code clean, idiomatic Java, and well-structured?
- **Completeness:** Are all TODOs addressed?
- **Logical Precision:** Did you correctly implement the Anomaly Scoring Logic?

## How to Run
1.  **Run in Dev Mode:**
    ```bash
    ./mvnw quarkus:dev
    ```
2.  **Run Tests:**
    ```bash
    ./mvnw test
    ```
3.  **Check Coverage:**
    After running tests, open `target/jacoco-report/index.html` in your browser.

---
**Good luck!**
