#!/bin/bash

# get latest postgres image
docker pull postgres

# create a new volume if not exist
docker volume create pgdata

# create a container using psql image with name=jrvs-psql
docker run --name jrvs-psql -e POSTGRES_PASSWORD=password -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres:9.6-alpine

# check if the container `jrvs-psql` is created or not
docker container ls -a -f name=jrvs-psql

# check if `jrvs-psql` container is running
docker ps -f name=jrvs-psql

# If the container is not running, start it by running the command
docker start jrvs-psql

# Set the password for the default user postgres by running the command:
export PGPASSWORD='password'

# Connect to the psql instance using the psql REPL (read-eval-print loop) by running the command
psql -h localhost -U postgres -d postgres -W
