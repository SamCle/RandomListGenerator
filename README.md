# RandomListGenerator
Asked 26 May 2016

This java program produces random integers 'uniformly' distributed over an interval specified by
the user. Such numbers will always be spaced out over the whole interval, as they are prevented
from clustering near any integer value. This means that asking to produce a 'large' amount of
numbers in a relatively small interval will result in an error and the program will stop.


Executing the program (use the appropriate run.* script) followed by -h shows the options:
	usage: RandomListGenerator [options]. Where the possible options are:
	 -a,--append              Flag, appends new list to previous one.
	 -h,--help                Shows help.
	 -if,--inputfile <arg>    Input file name.
	 -m,--minimum <arg>       Lower bound for the result.
	 -M,--Maximum <arg>       Upper bound for the result.
	 -of,--outputfile <arg>   Output file name.
	 -p,--properties <arg>    Config file name.
	 -s,--size <arg>          Size of the result.
	 -v,--variation <arg>     Maximal change in boundaries.

A properties file is included in the cfg/ folder, which contains the following default values:
	default.minimum=0
	default.maximum=99999
	default.size=100
	default.outputFile=output.txt
	dafault.variation=45

You can provide your own properties file and feed it to the program with the -p option.

The variation parameter is a technical gimmick which is used in the program to increase the
randomness of the numbers that are generated. We recommend a (maximum) value of 45.

The append option is by default set to false, which means the output file specified will be
overwritten; in order to activate it, simply add the option -a.

This program also allows to create a list of uniformly distributed numbers, one for each line of
an input file (e.g. a list of names); in this case the size of the output is automatically
calculated based on the size of the input file. When this feature is used, the numbers will be
randomly shuffled so that they do not appear in order.
