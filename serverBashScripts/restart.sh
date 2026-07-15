#!/bin/bash
SESSION_NAME="minecraft"

echo "Restarting server..."

# Stop the server
./stop.sh

# Wait for it to shut down gracefully (give it 10 seconds)
sleep 10

# Start it again
./start.sh

echo "Restart complete."
