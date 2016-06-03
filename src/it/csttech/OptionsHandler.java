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

  private CommandLine commandLine;
  private boolean helpCalled;

  /**
  * Needs all the String arguments of the main to be able to recognize all the options set.
  */
  OptionsHandler(String[] args){

    Logger log = LogManager.getLogger();
    this.helpCalled = false;

    Options options = new Options();
    DefaultParser parser = new DefaultParser();
    options.addOption(new Option("h", "help",       false, "Shows help."                        ));
    options.addOption(new Option("m", "minimum",    true,  "Lower bound for the result."        ));
    options.addOption(new Option("M", "Maximum",    true,  "Upper bound for the result."        ));
    options.addOption(new Option("s", "size",       true,  "Size of the result."                ));
    options.addOption(new Option("f", "file",       true,  "Output file name."                  ));
    options.addOption(new Option("p", "properties", true,  "Config file name."                  ));
    options.addOption(new Option("v", "variation",  true,  "Maximal change in boundaries."      ));
    options.addOption(new Option("a", "append",     false, "Flag, appends new list to previous one."));

    try {
      commandLine = parser.parse(options, args);
    } catch (Exception e) {
      log.error("Option parser is not working");
      e.printStackTrace();
    }
    if (commandLine.hasOption("h")) {
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("RandomListGenerator [options]. Where the possible options are:", options);
      helpCalled = true;
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

}
