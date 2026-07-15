#!/bin/bash
SESSION_NAME="minecraft"

if screen -list | grep -q "\.${SESSION_NAME}[[:space:]]"; then
    echo "Server is running."
    # Show how long it's been running
    screen -list | grep "\.${SESSION_NAME}" | awk '{print "Uptime:", $3, $4, $5}'
else
    echo "Server is NOT running."
fi
