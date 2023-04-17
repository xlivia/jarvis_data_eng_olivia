#!/bin/bash

#Setup and validate arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

#Check # of args
if [ "$#" -ne 5 ]; then
    echo "Illegal number of parameters"
    exit 1
fi

#Save machine usage statistics to variables
vmstat_out=$(vmstat -t)
memory_free=$(echo "$vmstat_out" | tail -1 | awk '{print $4}')
cpu_idle=$(echo "$vmstat_out" | tail -1 | awk '{print $15}')
cpu_kernel=$(echo "$vmstat_out" | tail -1 | awk '{print $14}')
disk_io=$(vmstat -d | tail -1 | awk '{print $10}')
disk_available=$(df -BM / | tail -1 | awk '{print $4}' | sed 's/M//')
timestamp=$(date '+%Y-%m-%d %H:%M:%S')

#Subquery to find matching id in host_info table
host_id="(SELECT id FROM host_info WHERE hostname='$(hostname -f)')"

#Construct the INSERT statement
insert_stmt="INSERT INTO host_usage (host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available, timestamp) VALUES ($host_id, $memory_free, $cpu_idle, $cpu_kernel, $disk_io, $disk_available, '$timestamp')"

#Insert data into PSQL
export PGPASSWORD=$psql_password
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?
