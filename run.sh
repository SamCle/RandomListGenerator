#!/bin/bash

main=RandomListGenerator
logConfig=config/log4j2.xml

java -jar -Dlog4j.configurationFile=$logConfig bin/$main.jar $*