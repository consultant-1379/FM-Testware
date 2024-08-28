#!/bin/bash

OUTPUT="/tmp/list_message_output"
ALARM="/tmp/alarm.txt"

echo "here in /tmp"

/opt/ericsson/bin/list_msg2 240000 > $OUTPUT &



PID=$(echo $!)

sleep 30

disown $PID
kill -9 $PID

grep -v "start subscription manager" $OUTPUT | grep -v "Connection state changed for server IMH_FMAI_server on host" > $ALARM
