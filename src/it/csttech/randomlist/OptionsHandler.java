package it.csttech;

import java.io.*;
import java.util.*;
import java.util.LinkedHashMap;
import org.apache.commons.*;
import org.apache.commons.cli.*;

class OptionsHandler {

  protected Map<String,String>  sOptions;
  protected Map<String,Integer> iOptions;
  protected Map<String,Double>  dOptions;

  OptionsHandler(String[] args){

    this.sOptions = new HashMap<String,String>(0);
    this.iOptions = new HashMap<String,Integer>(0);

    Options options = new Options();
    DefaultParser parser = new DefaultParser();
    options.addOption(new Option("h", "help",    false, "Shows help."                 ));
    options.addOption(new Option("m", "minimum", true,  "Lower bound for the result." ));
    options.addOption(new Option("M", "Maximum", true,  "Upper bound for the result." ));
    options.addOption(new Option("s", "size",    true,  "Size of the result."         ));
    options.addOption(new Option("f", "file",    true,  "Output file name."           ));

    CommandLine commandLine = null;

    try {
      commandLine = parser.parse(options, args);
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (commandLine.hasOption("h")) {
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("Converter", options);
      return;
    }

    iOptions.put("m", Integer.parseInt(commandLine.getOptionValue("m",     "0")));
    iOptions.put("M", Integer.parseInt(commandLine.getOptionValue("M", "99999")));
    iOptions.put("s", Integer.parseInt(commandLine.getOptionValue("s", " 1000")));
    sOptions.put("f", commandLine.getOptionValue("f", "output.txt")));

  }

}
