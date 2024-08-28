#!/bin/bash


##/opt/ericsson/bin/list_msg2 240000 | egrep -i "Event time|Object of reference|Specific problem" > /tmp/temp_log_TOR &


/opt/ericsson/bin/list_msg2 240000 > /tmp/temp_log_TOR &

PID=$!
echo $PID
sleep 20

kill $PID
cat /tmp/temp_log_TOR | egrep -i "Event time|Object of reference|Specific problem"