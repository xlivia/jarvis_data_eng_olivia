#!/bin/bash

# Setup and validate arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

if [ "$#" -ne 5 ]; then
    echo "Illegal number of parameters"
    exit 1
fi

# Parse host hardware specifications using bash cmds
hostname=$(hostname)

#cpu_number=$(lscpu | grep "^CPU(s):" | awk '{print $2}')
#cpu_architecture=$(lscpu | grep "^Architecture:" | awk '{print $2}')
#cpu_model=$(lscpu | grep "^Model name:" | awk '{$1=$2=""; print $0}' | xargs)
#cpu_mhz=$(lscpu | grep "^CPU MHz:" | awk '{print $3}')
#l2_cache=$(lscpu | grep "^L2 cache:" | awk '{print $3}' | sed 's/K//' | xargs)

cpu_number=$(awk -F':' '/^CPU\(s\)/ {print $2}' /proc/cpuinfo | xargs)
cpu_architecture=$(uname -m)
cpu_model=$(grep "^model name" /proc/cpuinfo | sed 's/^[^:]*:\s*//' | head -1)
cpu_mhz=$(awk -F':' '/^cpu MHz/ {print $2; exit}' /proc/cpuinfo | xargs)
l2_cache=$(awk -F':' '/^L2 cache/ {print $2}' /proc/cpuinfo | sed 's/^[ \t]*//' | sed 's/ K//' | xargs)
total_mem=$(cat /proc/meminfo | grep "^MemTotal:" | awk '{print $2}')
timestamp=$(date +"%Y-%m-%d %H:%M:%S")

# Check and set default values for empty variables
if [ -z "$hostname" ]; then
  hostname=$(hostname)
fi

if [ -z "$cpu_number" ]; then
  cpu_number=0
fi

if [ -z "$cpu_architecture" ]; then
  cpu_architecture="unknown"
fi

if [ -z "$cpu_model" ]; then
  cpu_model="unknown"
fi

if [ -z "$cpu_mhz" ]; then
  cpu_mhz=0
fi

if [ -z "$l2_cache" ]; then
  l2_cache=0
fi

if [ -z "$total_mem" ]; then
  total_mem=0
fi

if [ -z "$timestamp" ]; then
  timestamp=$(date "+%Y-%m-%d %H:%M:%S")
fi

#echo "hostname: $hostname"
#echo "cpu_number: $cpu_number"
#echo "cpu_architecture: $cpu_architecture"
#echo "cpu_model: $cpu_model"
#echo "cpu_mhz: $cpu_mhz"
#echo "l2_cache: $l2_cache"
#echo "total_mem: $total_mem"
#echo "timestamp: $timestamp"

# Construct the INSERT statement from specification variables
insert_stmt="INSERT INTO host_info (hostname, cpu_number, cpu_architecture, cpu_model_name, cpu_mhz, l2_cache, total_mem, timestamp) VALUES ('$hostname', $cpu_number, '$cpu_architecture', '$cpu_model', $cpu_mhz, $l2_cache, $total_mem, '$timestamp');"

#echo "insert_stmt: $insert_stmt"

# Execute the INSERT statement through the psql CLI tool
export PGPASSWORD=$psql_password
#psql -h $psql_host -p $psql_port -U $psql_user -d $db_name -c "$insert_stmt"
psql -h $psql_host -U $psql_user -d $db_name -c "$insert_stmt"

exit $?
