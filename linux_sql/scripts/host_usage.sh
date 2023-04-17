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

# Retrieve server usage variables
memory_free=$(awk '/MemFree/ { printf("%.2f\n", $2 / 1024 / 1024) }' /proc/meminfo)
cpu_idle=$(wmic cpu get loadpercentage | awk 'NR==2 {printf "%.2f", 100-$1}')
cpu_kernel=$(systeminfo | awk '/Kernel.*:/ { print $3 }')
disk_io=$(typeperf -sc 1 "\PhysicalDisk(*)\Disk Transfers/sec" | awk 'NR==3 {printf "%.2f", $1}')
#memory_free=$(free | awk 'NR==2{printf "%.2f", $4/1024}')
#cpu_idle=$(mpstat | awk '$NF ~ /[0-9.]+/ { printf "%.2f", 100-$NF }')
#cpu_kernel=$(mpstat | awk '$NF ~ /[0-9.]+/ { printf "%.2f", $NF }')
#disk_io=$(vmstat -d | awk '{print $10}' | tail -1)
disk_available=$(df -BM / | awk 'NR==2{print $4}' | tr -d 'M')
timestamp=$(date +"%Y-%m-%d %H:%M:%S")

if [ -z "$memory_free" ]; then
  memory_free=0.0
fi

if [ -z "$cpu_idle" ]; then
  cpu_idle=0.0
fi

if [ -z "$cpu_kernel" ]; then
  cpu_kernel=0.0
fi

if [ -z "$disk_io" ]; then
  disk_io=0.0
fi

if [ -z "$disk_available" ]; then
  disk_available=0
fi

# Subquery to find matching id in host_info table
hostname=$(hostname)
#host_id="(SELECT id FROM host_info WHERE hostname='$hostname')";
host_id=$(psql -X -h $psql_host -U $psql_user -d $db_name -t -c "SELECT id FROM host_info WHERE hostname='$hostname'")

cpu_user="$psql_port"

echo "timestamp: $timestamp"
echo "host_id: $host_id"
echo "memory_free: $memory_free"
echo "cpu_idle: $cpu_idle"
echo "cpu_kernel: $cpu_kernel"
echo "disk_io: $disk_io"
echo "disk_available: $disk_available"
echo "cpu_user: $cpu_user"

# Construct INSERT statement
insert_stmt="INSERT INTO host_usage (timestamp, host_id, memory_free, cpu_idle, cpu_kernel, cpu_user, disk_io, disk_available) VALUES ('$timestamp', $host_id, $memory_free, $cpu_idle, $cpu_kernel, $cpu_user, $disk_io, $disk_available);"

# Set up env var for psql cmd
export PGPASSWORD=$psql_password 

# Execute INSERT statement
#psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
psql -h $psql_host -U $psql_user -d $db_name -c "$insert_stmt"

exit $?
