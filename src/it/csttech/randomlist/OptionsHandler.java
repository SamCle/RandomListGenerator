package it.csttech;

import java.io.*;
import java.util.*;
import java.text.*;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.*;

public class OptionsHandler {

  public CommandLine commandLine;
  protected boolean helpCalled;

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
    options.addOption(new Option("a", "append",    false, "Flag, appends new list to previous one."));

    try {
      commandLine = parser.parse(options, args);
    } catch (Exception e) {
      log.error("Option parser is not working");
      e.printStackTrace();
    }
    if (commandLine.hasOption("h")) {
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("Converter", options);
      helpCalled = true;
    }
    log.trace("Options have been read.");
  }

  protected static Properties readProperties( String propFile ){
    Logger log = LogManager.getLogger();
    Properties prop = new Properties();
    try(InputStream input = new FileInputStream(propFile)) {
      prop.load(input);
    } catch (IOException e) {
      log.error("File not recognized.");
      e.printStackTrace();
    }
    log.trace("Properties have been read.");
    return prop;
  }

}
