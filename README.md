# RandomListGenerator
Asked 26 May 2016

This java program produces random integers 'uniformly' distributed over an interval specified by
the user. Such numbers will always be spaced out over the whole interval, as they are prevented
from clustering near any integer value. This means that asking to produce a 'large' amount of
numbers in a relatively small interval will result in an error and the program will stop.

Executing the program (use the appropriate run.* script) followed by -h shows the options:
usage: ./run.sh [options]. Where the possible options are:
 -a,--append                     Flag, appends new list to previous one.
 -dp,--defaultProperties <arg>   Default configuration file name. This
                                 should ONLY be set in the run.* file. Use
                                 option -p to load different properties.
 -h,--help                       Displays this help message.
 -if,--inputfile <arg>           Name list input file name.
 -m,--minimum <arg>              Lower bound for the generated numbers.
 -M,--Maximum <arg>              Upper bound for the generated.
 -of,--outputfile <arg>          Name of the output file.
 -p,--properties <arg>           Name of non-default configuration file
                                 name. This overrides the default
                                 configurations.
 -s,--size <arg>                 Size of the list of random numbers. If
                                 the -if option is used, this is
                                 overridden.
 -v,--variation <arg>            Maximal random change in boundaries.
                                 Recommended: less than 45.

The -dp option should ONLY be set in the appropriate run.* file. This allows you to specify the
file containing the default properties.

A default properties file should include the following:
default.minimum=0
default.maximum=99999
default.size=100
default.outputFile=output.txt
dafault.variation=45

You can provide different property files for occasional usage and feed it to the program, using
the -p option.

The variation parameter is a technical gimmick which is used in the program to increase the
randomness of the numbers that are generated. We recommend a (maximum) value of 45.

The append option is by default set to false, which means the output file specified will be
overwritten; in order to activate it, simply add the option -a.

This program also allows to create a list of uniformly distributed numbers, one for each line of
an input file (e.g. a list of names); in this case the size of the output is automatically
calculated based on the size of the input file (specifying both an input file and the size of
the list to be generated through the -s option will result in the latter being discarded). 
When this feature is used, the randomly generated numbers will be randomly shuffled so that
they do not appear in any order.
