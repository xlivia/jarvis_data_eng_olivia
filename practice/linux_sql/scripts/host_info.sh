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

#Save hardware specifications to variables
#Use the appropriate commands for your system to retrieve these specs
hostname=$(hostname -f)
cpu_number=$(lscpu | grep '^CPU(s):' | awk '{print $2}')
cpu_architecture=$(lscpu | grep 'Architecture:' | awk '{print $2}')
cpu_model=$(lscpu | grep 'Model name:' | awk '{$1=$2=""; print $0}' | xargs)
cpu_mhz=$(lscpu | grep 'CPU MHz:' | awk '{print $3}')
l2_cache=$(lscpu | grep 'L2 cache:' | awk '{print $3}' | sed 's/K//')
total_mem=$(cat /proc/meminfo | grep '^MemTotal:' | awk '{print $2}')
timestamp=$(date '+%Y-%m-%d %H:%M:%S')

#Construct the INSERT statement
insert_stmt="INSERT INTO host_info (hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, total_mem, timestamp) VALUES ('$hostname', $cpu_number, '$cpu_architecture', '$cpu_model', $cpu_mhz, $l2_cache, $total_mem, '$timestamp')"

#Insert data into PSQL
#export PGPASSWORD=$psql_password
#psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
psql -h $psql_host -U $psql_user -d $db_name -c "$insert_stmt"
exit $?
