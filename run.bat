@echo off

	set MAIN_CLASS_FULL_NAME="it.csttech.RandomListGenerator"
	set MAIN_CLASS_FILE="bin\RandomListGenerator.jar"
	set CLASS_PATH="lib\*;bin\*"
	set LOG_CONFIG="cfg\log4j2.xml"
	set DEFAULT_PROPERTIES="./cfg/Default.properties"


	echo -------------------------------------------------------------------------
	echo ***  Launching %MAIN_CLASS_FILE% %* ***
	echo -------------------------------------------------------------------------
	echo.

java -Dlog4j.configurationFile=%LOG_CONFIG% -jar %MAIN_CLASS_FILE% -dp %DEFAULT_PROPERTIES% %*

	echo.
	echo -------------------------------------------------------------------------
