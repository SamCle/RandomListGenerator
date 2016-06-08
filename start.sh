#!/bin/bash

main=RandomListGenerator
logConfig=cfg/log4j2.xml
defaultProperties=./cfg/Default.properties

#for i in `seq 1 10000`;

# 	do 
		java -Dlog4j.configurationFile=$logConfig -cp bin:lib/*  it.csttech.$main -dp $defaultProperties $*

#done