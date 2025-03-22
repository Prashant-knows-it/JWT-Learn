POST-> http://localhost:8080/auth/register
{
  "username": "testuser",
  "email": "test@example.com",
  "password": "password123",
  "role": "ROLE_USER"
}




POST-> http://localhost:8080/auth/login
{
  "username": "testuser",
  "password": "password123"
}
