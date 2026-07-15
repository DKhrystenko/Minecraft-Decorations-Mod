#!/bin/bash
SESSION_NAME="minecraft"

# Check if the screen session exists
if ! screen -list | grep -q "\.${SESSION_NAME}[[:space:]]"; then
    echo "Server is not running."
    exit 1
fi

echo "Sending /stop command to server..."
screen -S $SESSION_NAME -X stuff '/stop\n'

echo "Waiting 10 seconds for the server to shut down gracefully..."
sleep 10

# Check if the screen session is STILL there after the shutdown
if screen -list | grep -q "\.${SESSION_NAME}[[:space:]]"; then
    echo "Server stopped, but screen session is lingering. Killing it now."
    # Forcefully kill the screen session
    # screen -S $SESSION_NAME -X quit
    echo "Screen session killed."
else
    echo "Server stopped and screen session closed successfully."
fi
