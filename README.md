Author: Asylzhan Zhamash
Description: Spring Boot + PostgreSQL REST API for managing students (Asylzhan Zhamash)

## Changes by Asylzhan Zhamash
- Implemented `PUT /api/students/{id}` — update student information.
- Returns 404 with JSON error if the student does not exist.

## Endpoints
| Method | URL | Description |
|---|---|---|
| GET | /api/info | Student name and app version |
| GET | /api/students | All students |
| GET | /api/students/{id} | One student |
| POST | /api/students | Create student |
| PUT | /api/students/{id} | Update student |
| DELETE | /api/students/{id} | Delete student |
- CI trigger test by Asylzhan Zhamash (2026-10-29)
