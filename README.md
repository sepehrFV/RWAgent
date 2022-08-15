# RWAgent
java posts,comments,todos Reader and Writer Agent --> RWAgent



Overview

This application is a multi-tier architecture single microservice that is responsible for reading and providing the data of posts, 
comments, and to-do’s from a single data source, manipulating and providing these data to other 
applications by REST API.
All application APIs documented by using the swagger framework.
Service Layer has Logging per each operation.


Guidelines

Clone this repository.
Open the application in IDE and run it.
Once the spring boot app starts and reach readyEvent , ApplicationListener.class call 3 run() method asynchronous from 3 diffrenet classes that extends runnable (ReadPosts.class, ReadComments.class and ReadToDos.class are in datacatchers.package).
These classes read data from 3 diffrent data source which write below:

1-https://jsonplaceholder.typicode.com/posts

2-https://jsonplaceholder.typicode.com/comments

3-https://jsonplaceholder.typicode.com/comments

Then persist them in corresponding tables in 
Postgressql as local database.
