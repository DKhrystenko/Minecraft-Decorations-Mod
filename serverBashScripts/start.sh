#!/bin/bash
SESSION_NAME="minecraft"

# Check if the server is already running
if screen -list | grep -q "\.${SESSION_NAME}[[:space:]]"; then
    echo "Server is already running in session '$SESSION_NAME'."
    exit 1
fi

# Start the server in a detached screen session (nogui to save memory)
screen -dmS $SESSION_NAME java -Xms4G -Xmx4G -jar fabric-server-mc.1.21.10-loader.0.18.4-launcher.1.1.1.jar nogui

echo "Server started in screen session '$SESSION_NAME'."
echo "To attach: screen -r $SESSION_NAME"
echo "To detach: Ctrl+A, D"
