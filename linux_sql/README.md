# Linux Cluster Monitoring Agent
This project is under development. Since this project follows the GitFlow, the final work will be merged to the main branch after Team Code Team.
EOF

Note: You are NOT allowed to copy any content from the scrum board, including text, diagrams, code, etc. Your Github will be visible and shared with Jarvis clients, so you have to create unique content that impresses your future boss😎.

# Introduction
Discuss the design of the project. What does this project/product do? Who are the users? What are the technologies you have used? (e.g. bash, docker, git, etc..) (about 100-150 words)

This project involves the design and implementation of a host agent that collects and monitors a host’s usage data and stores the data in a PostgreSQL database for analysis. The agent is a Bash script that uses Linux commands to collect usage data, and it runs as a background process that is continuously monitoring the host’s usage. The PostgreSQL database provides a centralized location for the data, allowing for easy analysis and reporting. The users of this project are system administrators and DevOps engineers who need to monitor their server usage and optimize their resources accordingly. The technologies used in this project include Bash scripting for the agent, PostgreSQL for the database, Git for version control, and Docker for containerization. By containerizing the agent and the database, the project can be easily deployed to multiple servers, making it a scalable solution.

# Quick Start
Use markdown code block for your quick-start commands
- [x] Start a psql instance using psql_docker.sh
- [x] Create tables using ddl.sql
- [x] Insert hardware specs data into the DB using host_info.sh
- [x] Insert hardware usage data into the DB using host_usage.sh
- [x] Crontab setup

# Implementation
Discuss how you implement the project.

The project involved implementing a system to collect and persist system metrics data from a host machine into a PostgreSQL database. The implementation process consisted of several steps:
1. Designing the database schema: We created a PostgreSQL database schema to store the collected data.
2. Writing a Bash script to collect system metrics data: We developed a Bash script that uses Linux commands to collect system metrics data such as CPU usage, memory usage, disk usage, etc.
3. Writing a Bash script to insert data into the database: We developed a Bash script that takes the system metrics data collected by the first script and inserts it into the PostgreSQL database.
4. Setting up a crontab job: We scheduled the Bash scripts to run every minute using a crontab job.
5. Developing SQL queries to analyze the collected data: We developed SQL queries to analyze the collected data and generate reports on the host machine’s system usage.

The technologies used in this project include Bash scripting, PostgreSQL, crontab scheduling, and SQL querying.

## Architecture
Draw a cluster diagram with three Linux hosts, a DB, and agents (use draw.io website).
Image must be saved to the `assets` directory.
```
            Linux Host 1    Linux Host 2    Linux Host 3
                    |               |               |
                    |               |               |
                    |               |               |
                    |               |               |
                    |               |               |
              +-----+-----+   +-----+-----+   +-----+-----+
              |           |   |           |   |           |
              |  Agent 1  |   |  Agent 2  |   |  Agent 3  |
              |           |   |           |   |           |
              +-----------+   +-----------+   +-----------+
                           \         |         /
                            \        |        /
                             \       |       /
                              \      |      /
                               \     |     /
                                \    |    /
                                 \   |   /
                             +-----+-----+
                             |           |
                             |     DB    |
                             |           |
                             +-----------+
```
In this diagram, there are three Linux hosts that are each running an agent (Agent 1, Agent 2, and Agent 3). The agents are collecting data about each host’s CPU usage, memory usage, disk usage, etc., and sending that data to a central database (DB). The DB is responsible for storing all of the collected data from each of the hosts.

## Scripts
Shell script description and usage (use markdown code block for script usage)
- psql_docker.sh
    - To create a psql instance:
        - `./psql_docker.sh create <db_username> <db_password>`
            - `<db_username>` is the username to create for the psql instance
            - `<db_password>` is the password for that username.
    - To start the psql instance:
        - `./psql_docker.sh start`
    - To stop the psql instance:
        - `./psql_docker.sh stop`
    - To connect to a table in a database
        - `psql -h localhost -U postgres -d host_agent`

- host_info.sh
    - `chmod +x scripts/host_info.sh`
    - `./scripts/host_info.sh localhost 5432 host_agent postgres password`

- host_usage.sh
    - `chmod +x scripts/host_usage.sh`
    - `./scripts/host_usage.sh localhost 5432 host_agent postgres password`

- crontab
    - edit crontab jobs
        - `bash > crontab -e`
    - add this to crontab
        - `bash /linux_sql/scripts/host_usage.sh localhost 5432 host_agent postgres password > /assets/host_usage.log`
    - list crontab jobs
        - `crontab -l`
    - validate your result from the psql instance
        - `psql -h localhost -U postgres -d host_agent`
            - `SELECT * FROM host_usage;`

- `queries.sql` (describe what business problem you are trying to resolve)

## Database Modeling
Describe the schema of each table using markdown table syntax (do not put any sql code)
- `host_info`
    ```
    host_info = {
        "id": "SERIAL PRIMARY KEY",
        "hostname": "VARCHAR(50) NOT NULL",
        "cpu_number": "INTEGER NOT NULL",
        "cpu_architecture": "VARCHAR(50) NOT NULL",
        "cpu_model_name": "VARCHAR(50) NOT NULL",
        "cpu_mhz": "FLOAT NOT NULL",
        "l2_cache": "VARCHAR(50) NOT NULL",
        "total_mem": "INTEGER NOT NULL",
        "timestamp": "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    }
    ```

- `host_usage`
    ```
    host_usage = {
        "id": "SERIAL PRIMARY KEY",
        "timestamp": "TIMESTAMP NOT NULL",
        "host_id": "INTEGER REFERENCES host_info(id)",
        "memory_free": "INTEGER NOT NULL",
        "cpu_idle": "FLOAT NOT NULL",
        "cpu_kernel": "FLOAT NOT NULL",
        "cpu_user": "FLOAT NOT NULL",
        "disk_io": "INTEGER NOT NULL",
        "disk_available": "INTEGER NOT NULL"
    }
    ```

# Test
How did you test your bash scripts DDL? What was the result?

To test the bash scripts DDL, we ran the scripts in the terminal and validated that the expected tables were created in the database. We also ran SQL queries to check that the table columns were defined correctly, and to ensure that the data was being inserted into the tables as expected.

For example, after running the `psql_host_agent.sh` script to create the `host_info` table, we ran the following SQL query to check that the table was created with the correct columns:
```
psql -h localhost -U postgres -d host_agent
> SELECT * FROM host_info;
```
This command displayed the table schema, which we checked against the expected schema. We also ran SQL queries to validate the `host_usage` table after running the `host_usage.sh` script, to ensure that data was being inserted into the table correctly.

# Deployment
How did you deploy your app? (e.g. Github, crontab, docker)

To deploy the app, we used the crontab tool to schedule the execution of the Bash scripts on the Linux hosts, and the scripts wrote data to the PostgreSQL database.

# Improvements
Write at least 3 things you want to improve 
1. Enhancing the security of the system
    - The project currently lacks strong security measures.
    - The database password is stored in plain text in the shell scripts, which is not secure.
    - The database connection can also be secured by using SSL certificates.
2. Improving error handling
    - The scripts could benefit from more robust error handling to provide better feedback when things go wrong.
    - This includes checking for network connectivity and available disk space before attempting to collect or store data.
3. Implementing more tests:
    - While the scripts have been tested manually, implementing automated tests could help catch bugs earlier and ensure that the system is working as intended.
    - This includes unit tests for the individual functions as well as end-to-end tests to ensure the entire pipeline is functioning correctly.