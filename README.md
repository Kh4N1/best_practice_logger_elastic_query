# Mobile Device Management API

This project is a Spring Boot application that manages mobile device inventory with auditing capabilities. It provides REST APIs for device management with automatic tracking of creation and modification metadata.

## Features

- CRUD operations for mobile devices
- Advanced search functionality with multiple criteria
- Automatic audit logging for all entity changes
- Sample data generation
- In-memory H2 database
- Swagger/OpenAPI documentation

## Technical Stack

- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- H2 Database
- Lombok
- SpringDoc OpenAPI (Swagger)

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven

### Running the Application
1. Clone the repository
2. Run the application:
```bash
mvn spring-boot:run
```
The application will start on `http://localhost:8081`

## API Documentation

### Swagger UI
You can access the Swagger UI at:
```
http://localhost:8081/swagger-ui.html
```

![Swagger UI Screenshot](src/main/resources/swagger.png)

### Available Endpoints

1. **Generate Sample Data**
    - `POST /api/v1/devices/generate-samples`
    - Populates the database with sample device data

2. **Create New Device**
    - `POST /api/v1/devices`
    - Creates a new device with automatic audit metadata

3. **Update Device**
    - `PUT /api/v1/devices/{id}`
    - Updates an existing device while preserving audit history

4. **Search Devices**
    - `GET /api/v1/devices/search`
    - Supports multiple search criteria:
        - manufacturer (multiple values allowed)
        - color (multiple values allowed)
        - storage (multiple values allowed)
        - inStock (boolean)

5. **Get All Devices**
    - `GET /api/v1/devices`
    - Retrieves all devices with their audit information

## Audit System

The project implements a comprehensive auditing system that automatically tracks:

### Audit Metadata
- **createdBy**: Identifier of the user/system that created the record
- **createdAt**: Timestamp of record creation
- **lastModifiedBy**: Identifier of the user/system that last modified the record
- **lastModifiedAt**: Timestamp of the last modification

### Audit Implementation

1. **AuditMetadata (Embeddable)**
    - Stores the audit fields
    - Automatically embedded in audited entities

2. **Auditable Interface**
    - Defines the contract for auditable entities
    - Provides getter/setter for audit metadata

3. **AuditListener**
    - Automatically captures creation/modification events
    - Uses JPA's @PrePersist and @PreUpdate annotations
    - Retrieves current request information for user tracking

4. **AuditLogger**
    - Provides logging capabilities for audit events
    - Logs creation and modification events

### Sample Audit JSON Response
```json
{
  "id": 1,
  "manufacturer": "Apple",
  "model": "iPhone 13",
  "auditMetadata": {
    "createdBy": "system",
    "createdAt": "2024-12-15T10:30:00",
    "lastModifiedBy": "system",
    "lastModifiedAt": "2024-12-15T10:30:00"
  }
}
```

## Database Configuration

The application uses an H2 in-memory database with the following configuration:
- URL: `jdbc:h2:mem:devicedb`
- Username: `sa`
- Password: `` (empty)
- Console: `http://localhost:8081/h2-console`

## Project Structure

```
src/main/java/com/example/mobileapp/
├── audit/                    # Audit-related components
│   ├── AuditMetadata.java
│   ├── Auditable.java
│   ├── AuditListener.java
│   └── AuditLogger.java
├── controller/              # REST controllers
├── dto/                     # Data Transfer Objects
├── model/                   # Entity classes
├── repository/             # Data access layer
└── service/                # Business logic
```

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request