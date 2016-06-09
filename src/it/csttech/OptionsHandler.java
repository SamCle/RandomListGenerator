package it.csttech;

import java.io.*;
import java.util.*;
import java.text.*;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.*;

/**
*  This class can be used to handle option within the package randomlist.
*/
public class OptionsHandler {

  private static final Logger log = LogManager.getLogger(OptionsHandler.class.getName());
  private CommandLine commandLine;
  private Options options = new Options();
  private DefaultParser parser = new DefaultParser();
  
  private String OS = System.getProperty("os.name").toLowerCase();
  private String helpMessage = isWindows()? "run.bat [options]. Where the possible options are:" : "./run.sh [options]. Where the possible options are:";
  
  private boolean helpCalled;

  /**
  * Needs all the String arguments of the main to be able to recognize all the options set.
  */
  OptionsHandler(String[] args) {

    this.helpCalled = false;

    options.addOption(new Option("h",  "help",        false, "Displays this help message."));
    options.addOption(new Option("dp", "defaultProperties", true,  "Default configuration file name. This should ONLY be set in the run.* file. Use option -p to load different properties."));
    options.addOption(new Option("m",  "minimum",     true,  "Lower bound for the generated numbers."));
    options.addOption(new Option("M",  "Maximum",     true,  "Upper bound for the generated."));
    options.addOption(new Option("s",  "size",        true,  "Size of the list of random numbers. If the -if option is used, this is overridden."));
    options.addOption(new Option("of", "outputfile",  true,  "Name of the output file."));
    options.addOption(new Option("p",  "properties",  true,  "Name of non-default configuration file name. This overrides the default configurations."));
    options.addOption(new Option("v",  "variation",   true,  "Maximal random change in boundaries. Recommended: less than 45."));
    options.addOption(new Option("if", "inputfile",   true,  "Name list input file name."));
    options.addOption(new Option("a",  "append",      false, "Flag, appends new list to previous one."));

    try {
      commandLine = parser.parse(options, args);
    } catch (Exception e) {
      log.error("Option parser is not working, have you checked the possible options?");
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp(helpMessage, options);
      helpCalled = true;
      e.getMessage();
      return;
    }
    if (commandLine.hasOption("h")) {
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp(helpMessage, options);
      helpCalled = true;
      return;
    }
    log.trace("Options have been read.");
  }

  /**
  * Contains all the option needed to make RandomListGenerator run.
  */
  public CommandLine getCommandLine(){
    return commandLine;
  }

  /**
  * States whether "help" has been called or not.
  */
  public boolean isHelpCalled(){
    return helpCalled;
  }

  public boolean isWindows() {
    return (OS.indexOf("win") >= 0);
  }

  public boolean isUnix() {
    return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
  }

}
