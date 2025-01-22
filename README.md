# faceboot

# **Media Service**

### The **Media Service** allows you to manage media entities, such as images, videos, and other media files. This service provides several API endpoints to handle the retrieval, upload, and deletion of media associated with posts and users.

## API Endpoints

## **1. GET /media/all**

*    ### **Description**: Retrieves all media entities stored in the system.
## **2. GET /post/{post_id}**
*   ### **Description**:: Retrieves media associated with a specific post using the post_id. This allows other services to access media linked to a given post.
## **3. POST /upload**
*   ### **Description**:: Uploads a new media entity along with its file. Use this endpoint to add new media to the system.
## **4. DELETE /delete/postid/{post_id}/{user_id}**
*   ### **Description**:: Deletes all media associated with a specific post identified by the post_id, along with the media files.
## **5. DELETE /delete/userid/{user_id}**
*   ### **Description**:: Deletes all media associated with a specific user identified by the user_id. This allows the user service to remove media for a given user.
## Setup Instructions
### To start using the Media Service, follow these steps:

1. Checkout the branch containing the service.
2. Launch the service and ensure the database is running.
3. In the MongoDB console, execute the following commands to set up the database and user:

`
> mongo
> use admin;
> db.createUser({
user: "root",
pwd: "root",
roles: [{ role: "root", db: "admin" }]
});
> use media;
> db.createUser({
user: "root",
pwd: "root",
roles: [{ role: "root", db: "admin" }]
});`

### Once the setup is complete, you can use the APIs to interact with the media service.