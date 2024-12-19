# Water Logistics Application

## Project Description
This school project is part of the **Software Architecture course (2024-2025)**. The **Water Logistics Application** handles outbound logistics for shipments, including ship arrival management, inspection, bunkering, and loading operations for Krystal distributie Groep (KdG).

## Technologies Used
- **Backend**: Spring Boot (Java/Kotlin)
- **Database**: PostgreSQL (Water schema)
- **Queue System**: RabbitMQ
- **Identity Provider**: Keycloak
- **Build Tool**: Maven/Gradle
- **Logging**: Integrated with Spring Boot logging

## Features
- Ship docking and Purchase Order (PO) management.
- Automated inspections and limited daily bunkering operations.
- Prioritization of oldest stock for loading to minimize storage costs.
- RESTful API endpoints secured with OAuth2.
- Commission calculation based on total PO value.

## Related Projects
This project is part of a larger system for managing the logistics of Krystal distributie Groep (KdG). The following related projects provide additional functionality:
- **[Land Logistics Application](https://github.com/softtagz-sys/sa-land)**: Manages truck-based deliveries and inbound logistics.
- **[Warehouse Management Application](https://github.com/softtagz-sys/sa-warehouse)**: Manages inventory levels, storage operations, and daily cost calculations.

## Contributors
- [Nicolas Verachtert](https://github.com/NicolasVerachtert)
- [Kobe Ponet](https://github.com/softtagz-sys)
