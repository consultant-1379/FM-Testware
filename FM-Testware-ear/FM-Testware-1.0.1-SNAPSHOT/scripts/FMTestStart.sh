#!/bin/sh

#classpath
CP="`find ./target/*.jar`"
for i in `find ./target/lib/*`; do
	CP=$CP:$i
done

DEPENDANCY_JAR_PATH="`pwd`/target/jars/"

CP=$CP:$DEPENDANCY_JAR_PATH

# User props
suite="`pwd`/target/suites/FMServiceTest.xml"
logdir="`pwd`/Jcat_Logs"
name="TAFFIT"

# General props
jarfile=""
props=""
els=""


#Database logging
logdb="../src/main/resources/logdb.properties"
logwriters="se.ericsson.jcat.fw.ng.logging.writers.DbLogWriterRIPNG"


vmargs=" -Dname=$name -Dlogdir=$logdir -Dprops=$props -Dlogdb.configuration=$logdb -Dlogwriters=$logwriters -Dels=$els $@"

java $vmargs -cp $CP org.testng.TestNG $suite
