# User Service


## **POST**

### **Create a User**
> **`/api/v1/users`**

#### Request Body:
```json
{
  "name": "John Doe",
  "gender": "MALE",
  "email": "john.doe@example.com",
  "password": "password123"
}
```

#### Response:
```json
{
  "id": 1,
  "name": "John Doe",
  "gender": "MALE",
  "email": "john.doe@example.com",
  "verified": false,
  "createdAt": "2025-01-10T19:42:44.080359",
  "deletedAt": null
}
```

---

## **DELETE**

### **Soft Delete a User**
> **`/api/v1/users/{id}`**

#### Response:
- **If the user exists:**  
  No content (`204 No Content`).

- **If the user does not exist:**  
  An internal error is logged, and no specific response is sent to the client.

---

### **Reactivate a Soft-Deleted User**
> **`/api/v1/users/{id}/reactivate`**

#### Response:
- **If successful:** No content (`200 OK`).
- **If the user does not exist:**  
  An internal error is logged, and no specific response is sent to the client.

---

## **GET**

### **Retrieve All Users**
> **`/api/v1/users`**

#### Response:
```json
[
  {
    "id": 1,
    "name": "John Doe",
    "gender": "MALE",
    "email": "john.doe@example.com",
    "verified": false,
    "createdAt": "2025-01-10T19:42:44.080359",
    "deletedAt": "2025-01-10T19:56:59.761332"
  }
]
```

---

### **Retrieve User by ID**
> **`/api/v1/users/{id}`**

#### Response:
```json
{
  "id": 1,
  "name": "John Doe",
  "gender": "MALE",
  "email": "john.doe@example.com",
  "verified": false,
  "createdAt": "2025-01-10T19:42:44.080359",
  "deletedAt": "2025-01-10T19:56:59.761332"
}
```

---

## **PUT**

### **Update a User**
> **`/api/v1/users/{id}`**

#### Request Body:
```json
{
  "name": "Johnathan Doe",
  "gender": "MALE",
  "email": "john.doe@example.com",
  "verified": true
}
```

#### Response:
```json
{
  "id": 1,
  "name": "Johnathan Doe",
  "gender": "MALE",
  "email": "john.doe@example.com",
  "verified": true,
  "createdAt": "2025-01-10T19:42:44.080359",
  "deletedAt": null
}
```

---

### **Update User’s Password**
> **`/api/v1/users/{id}/password`**

#### Request Body:
```json
{
  "newPassword": "newPassword456"
}
```

#### Response:
```json
{
  "id": 1,
  "name": "John Doe",
  "gender": "MALE",
  "email": "john.doe@example.com",
  "verified": false,
  "createdAt": "2025-01-10T19:42:44.080359",
  "deletedAt": null
}
```

---

## **POST**

### **Verify a User**
> **`/api/v1/users/{id}/verify`**

#### Response:
```json
{
  "id": 1,
  "name": "John Doe",
  "gender": "MALE",
  "email": "john.doe@example.com",
  "verified": true,
  "createdAt": "2025-01-10T19:42:44.080359",
  "deletedAt": null
}
```

---

## Automated Cleanup: Delete Users Permanently

A scheduled job runs daily at midnight to permanently delete users who have been soft-deleted for more than 30 days.

- **Permanently Delete Users Older Than 30 Days:**  
  The system automatically removes users from the database who have been soft-deleted for over 30 days.

---