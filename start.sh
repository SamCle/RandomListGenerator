#!/bin/bash

main=RandomListGenerator
logConfig=config/log4j2.xml

java -Dlog4j.configurationFile=$logConfig -cp bin:lib/*  it.csttech.$main $*