#!/bin/bash
hostname=$(hostname)
lscpu_out=$(lscpu)
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out" | egrep "^Architecture:" | awk '{print $2}')
cpu_model=$(echo "$lscpu_out" | egrep "^Model name:" | awk '{print $3,$4,$5,$6,$7}')
cpu_mhz=$(echo "$lscpu_out" | egrep "^CPU MHz:" | awk '{print $3}')
l2_cache=$(echo "$lscpu_out" | egrep "^L2 cache:" | awk '{print $3/1024, "MB"}')
mem_info=$(vmstat --unit M | tail -1)
total_mem=$(echo "$mem_info" | awk '{print $4}')
memory_free=$(echo "$mem_info" | awk '{print $5}')
disk_info=$(df -BM /)
disk_available=$(echo "$disk_info" | awk 'NR==2{print $4}')
disk_io=$(vmstat --unit M -d | tail -1 | awk '{print $10}')
cpu_idle=$(vmstat 1 2 | tail -1 | awk '{print $15}')
cpu_kernel=$(vmstat 1 2 | tail -1 | awk '{print $14}')
timestamp=$(date -u +"%Y-%m-%d %H:%M:%S")
echo "System Info:"
echo "-----------------------"
echo "Hostname: $hostname"
echo "CPU Number: $cpu_number"
echo "CPU Architecture: $cpu_architecture"
echo "CPU Model: $cpu_model"
echo "CPU MHz: $cpu_mhz"
echo "L2 Cache: $l2_cache"
echo "Total Memory: $total_mem MB"
echo "Free Memory: $memory_free MB"
echo "Available Disk Space: $disk_available MB"
echo "Disk IO: $disk_io MB/s"
echo "CPU Idle: $cpu_idle"
echo "CPU Kernel: $cpu_kernel"
echo "Timestamp (UTC): $timestamp"