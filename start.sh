#!/bin/bash

main=RandomListGenerator
logConfig=config/log4j2.xml

#for i in `seq 1 10000`;

	do java -Dlog4j.configurationFile=$logConfig -cp bin:lib/*  it.csttech.$main $*

#done