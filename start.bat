@echo off

	set MAIN_CLASS_FILE="it.csttech.RandomListGenerator"
	set CLASS_PATH=".;lib\*;bin"
	set LOG_CONFIG="cfg\log4j2.xml"


	echo --------------------------------------------------------------
	echo ***  Launching %MAIN_CLASS_FILE% %* ***
	echo --------------------------------------------------------------
	echo.

java -Dlog4j.configurationFile=%LOG_CONFIG% -cp %CLASS_PATH% %MAIN_CLASS_FILE% %*

	echo.
	echo --------------------------------------------------------------
