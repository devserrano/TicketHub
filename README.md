# TicketHub

Mesa de ayuda web: los usuarios reportan problemas, el equipo de soporte los atiende y todo queda registrado con folio, responsable, tiempos (SLA) y evidencia.

Proyecto final de **Sistemas de la Información** — FES Aragón, UNAM.

## Stack

| Capa | Tecnología |
| --- | --- |
| Backend | Java 21, Spring Boot 3.5, Maven |
| Seguridad | Spring Security + JWT |
| Datos | PostgreSQL 16, Spring Data JPA, Flyway |
| Archivos | S3 (MinIO en local) |
| Correo | Spring Mail (Mailpit en local) |
| Reportes | OpenPDF (PDF), Apache POI (Excel) |
| Pruebas | JUnit 5, Postman/Newman, k6 |
| Infra | Docker, docker-compose, GitHub Actions |

## Cómo levantarlo en local

**Requisitos:** JDK 21, Docker Desktop, IntelliJ IDEA (o Maven 3.9+).

1. Clona el repo:
   ```bash
   git clone https://github.com/<usuario>/tickethub.git
   cd tickethub
   ```
2. Levanta Postgres, MinIO y Mailpit:
   ```bash
   docker compose up -d
   ```
3. Corre la API desde IntelliJ (`TicketHubApplication`) o con Maven:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Listo:
   | Qué | URL |
   | --- | --- |
   | API | http://localhost:8080 |
   | Swagger UI | http://localhost:8080/swagger-ui.html |
   | Health check | http://localhost:8080/actuator/health |
   | Bandeja de correos (Mailpit) | http://localhost:8025 |
   | Consola MinIO | http://localhost:9001 (minioadmin / minioadmin) |

Todo dockerizado, incluida la API:

```bash
docker compose --profile app up -d --build
```

Las variables de entorno están en `.env.example`. En local no necesitas cambiar nada.

## Estructura del proyecto

Organizado **por módulo de negocio** (no por capa), cada uno con su `controller`, `service`, `repository` y `dto`:

```
src/main/java/com/tickethub
├── TicketHubApplication.java
├── auth/           # login, registro, JWT, recuperar contraseña
├── user/           # usuarios y roles
├── ticket/         # tickets, categorías, estados, SLA, bitácora
├── comment/        # comentarios públicos e internos
├── attachment/     # adjuntos (S3/MinIO)
├── notification/   # correos
├── report/         # PDF y Excel
├── dashboard/      # métricas
└── common/         # config, errores, clases base
src/main/resources
├── application.yml
└── db/migration/   # scripts Flyway (V1__..., V2__...)
postman/            # colección y ambientes
k6/                 # pruebas de carga
docs/               # diagramas e imágenes del README
```

## Diagramas

### Flujo de estados del ticket

```mermaid
stateDiagram-v2
    [*] --> OPEN
    OPEN --> IN_PROGRESS: asignado
    OPEN --> CANCELLED: cancela
    IN_PROGRESS --> ON_HOLD: falta info
    ON_HOLD --> IN_PROGRESS: responde
    IN_PROGRESS --> RESOLVED: solución escrita
    RESOLVED --> IN_PROGRESS: reabre (máx. 7 días)
    RESOLVED --> CLOSED: confirma o 3 días
    CLOSED --> [*]
    CANCELLED --> [*]
```

### Modelo entidad-relación

```mermaid
erDiagram
    USERS ||--o{ TICKETS : crea
    USERS ||--o{ TICKETS : "atiende (asignado)"
    CATEGORIES ||--o{ TICKETS : clasifica
    TICKETS ||--o{ COMMENTS : tiene
    TICKETS ||--o{ ATTACHMENTS : tiene
    COMMENTS ||--o{ ATTACHMENTS : "puede tener"
    TICKETS ||--o{ TICKET_HISTORY : registra
    USERS ||--o{ PASSWORD_RESET_TOKENS : solicita
```

### Arquitectura

_Pendiente: diagrama de arquitectura (frontend → API en capas → PostgreSQL / S3 / SMTP)._

## Cómo trabajamos (equipo)

- `main`: lo que está desplegado. Solo entra por Pull Request.
- `develop`: integración.
- Ramas de trabajo desde `develop`: `feature/auth-login`, `feature/tickets-crud`, `fix/...`
- Commits cortos y en presente: `feat: crear endpoint de login`, `fix: validar tamaño de adjunto`.
- Antes del PR: `mvn verify` en verde (lo mismo corre GitHub Actions).
- Nunca subir `.env` ni contraseñas reales.

## Pruebas

```bash
mvn test                                  # unitarias
newman run postman/TicketHub.postman_collection.json -e postman/local.postman_environment.json
k6 run k6/load-test.js                    # carga
```
