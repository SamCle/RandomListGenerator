#!/bin/bash

main=RandomListGenerator
logConfig=cfg/log4j2.xml
defaultProperties=./cfg/Default.properties

java -jar -Dlog4j.configurationFile=$logConfig bin/$main.jar -dp $defaultProperties $*