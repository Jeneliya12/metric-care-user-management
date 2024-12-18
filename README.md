# metric-care-user-management
User Management and Role-Based Access Control

## Swagger API Reference
http://localhost:8081/swagger-ui.html

### SQL QUERIES:

1. select * from takeo_db.users;

## API CONTRACT:

### POST: /api/auth/register/super-admin
{
"username": "root",
"password": "root",
"email": "root@test.com"
}
