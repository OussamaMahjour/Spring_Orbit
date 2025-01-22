# Comment Service


You Could access a responsive documentation at ```/swagger-ui/index.html```


## Post


 > ```/comment/add ```

### Request body 
```javascript
\\ example
{
  "id":"ed3",
  "userId": 1,
  "postId": "dfk423l",
  "content": "this is a test comment",
  "createTime": "2025-01-07T22:38:12.727Z"
}
```

## DELETE



 > ```/comment/delete/{id}```

- ### Response 
If the id exist
``` "deleted succefully"``` 
If Not
```"comment doesn't exist"```
___
 > ```/comment/byPost/{postId}```
- ### Response 
If the id exist
```"comments deleted"```
If Not
``` "post have no comments or doesn't exist"```


## GET



>```/comment/all```

- ### Response
```javascript
[
  {
    "id": "string",
    "user": {
      "id": 0,
      "name": "string",
      "gender": "string",
      "email": "string",
      "verified": true,
      "createdAt": "2025-01-07T23:47:26.595Z",
      "deletedAt": "2025-01-07T23:47:26.595Z"
    },
    "post": {
      "id": "string",
      "user": {
        "id": 0,
        "name": "string",
        "gender": "string",
        "email": "string",
        "verified": true,
        "createdAt": "2025-01-07T23:47:26.595Z",
        "deletedAt": "2025-01-07T23:47:26.595Z"
      },
      "createdAt": "2025-01-07T23:47:26.595Z",
      "votes": 0,
      "medias": [
        {
          "id": 0,
          "postId": 0,
          "type": "string",
          "content": "string",
          "path": "string"
        }
      ]
    },
    "content": "string",
    "createTime": "2025-01-07T23:47:26.595Z"
  }
]
```
___

>```/comment/byPost/{postId}```
- ### Response 
```javascript
[
  {
    "id": "677dacde704105389b66d1d5",
    "user": {
      "id": 0,
      "name": "oussama",
      "gender": "male",
      "email": "oussama@gmail.com",
      "verified": false,
      "createdAt": "2025-01-07T23:39:03.308608884",
      "deletedAt": null
    },
    "post": {
      "id": "3e2",
      "user": {
        "id": 353287330190114200,
        "name": "oussama",
        "gender": "male",
        "email": "oussama@gmail.com",
        "verified": false,
        "createdAt": "2025-01-07T23:39:03.308658351",
        "deletedAt": null
      },
      "createdAt": "2025-01-07T23:39:03.308670929",
      "votes": 12,
      "medias": null
    },
    "content": "string",
    "createTime": "2025-01-07T22:38:12.727"
  }
]


```

_NOTE:
The Entities User and Post are just mocked and not actually coming from the services_ 
    