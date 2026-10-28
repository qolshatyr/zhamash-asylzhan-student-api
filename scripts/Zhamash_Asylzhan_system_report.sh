#!/bin/bash

STUDENT_NAME="Asylzhan Zhamash"
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
LOG_FILE="$SCRIPT_DIR/../logs/Zhamash_Asylzhan_system_report.log"

{
  echo "================ SYSTEM REPORT ================"
  echo "Student:      $STUDENT_NAME"
  echo "Date & time:  $(date '+%Y-%m-%d %H:%M:%S')"
  echo "Username:     $(whoami)"
  echo "OS:           $(grep PRETTY_NAME /etc/os-release | cut -d= -f2 | tr -d '"')"
  echo "Kernel:       $(uname -r)"
  echo
  echo "---------------- CPU ----------------"
  lscpu | grep -E '^Architecture|^CPU\(s\)|Model name'
  echo
  echo "---------------- Memory ----------------"
  free -h
  echo
  echo "---------------- Disk usage ----------------"
  df -h
  echo
  echo "---------------- Available disk space (/) ----------------"
  df -h / | awk 'NR==2 {print "Available: " $4 " of " $2 " (" $5 " used)"}'
  echo "==============================================="
  echo
} | tee -a "$LOG_FILE"
