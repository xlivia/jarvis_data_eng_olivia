# Core Java JDBC

## Introduction
(50-100 words)
What does this app do? What technoglies your have used? (JDBC, PSQL, MVN, etc..)

## Implementaiton

### ER Diagram
ER diagram

### Design Patterns
Discuss DAO and Repository design patterns (150-200 words)

## Test
How you test your app against the database? (e.g. database setup, test data set up, query result)

## File Structure
```
My-JDBC-App-Repo
├── jdbc
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   ├── com
│   │   │   │   │   └── myapp
│   │   │   │   │       ├── dao
│   │   │   │   │       │   └── UserDao.java
│   │   │   │   │       ├── model
│   │   │   │   │       │   └── User.java
│   │   │   │   │       └── repository
│   │   │   │   │           └── UserRepository.java
│   │   │   ├── resources
│   │   │   │   ├── db.sql
│   │   │   │   └── application.properties
│   │   └── test
│   │       └── java
│   │           └── com
│   │               └── myapp
│   │                   ├── dao
│   │                   │   └── UserDaoTest.java
│   │                   └── repository
│   │                       └── UserRepositoryTest.java
│   └── README.md
└── README.md
```


# Order of operations
These commands are for linux/Mac, changes will need to made if you are running this in Microsoft Windows.

## Prerequisites
Docker is installed
psql client is installed

## Actions To Run The Code

### Running PostgreSQL
1. Pull Docker Image
`docker pull postgres`

2. Run docker image
`docker run --rm --name jrvs-jdbc -e POSTGRES_PASSWORD=password -d -v $HOME/srv/postgres:/var/lib/postgresql/data -p 5432:5432 postgres`

### Stopping PostgreSQL
`docker stop jrvs-jdbc`

### Logging into Database
* `psql -h localhost -U postgres -d jdbcdb`

### Creating starter data
1. `psql -h localhost -U postgres -f database.sql`
2. `psql -h localhost -U postgres -d jdbcdb -f customer.sql`
3. `psql -h localhost -U postgres -d jdbcdb -f product.sql`
4. `psql -h localhost -U postgres -d jdbcdb -f salesperson.sql`
5. `psql -h localhost -U postgres -d jdbcdb -f orders.sql`

### Creating stored procedure
1. `psql -h localhost -U postgres -f stored_proc.sql`


[Back To Top](#core-java)
