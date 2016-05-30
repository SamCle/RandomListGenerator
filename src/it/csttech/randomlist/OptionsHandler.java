package it.csttech;

import java.io.*;
import java.util.*;
import java.text.*;
import org.apache.commons.cli.*;

public class OptionsHandler {

  public static final String DEFAULT_PROPERTIES = "config/RandomListGenerator.properties";
  protected Map<String,String>  sOptions;
  protected Map<String,Integer> iOptions;
  protected Map<String,Long>    lOptions;

  OptionsHandler(String[] args){

    this.sOptions = new HashMap<String,String>(0);
    this.iOptions = new HashMap<String,Integer>(0);
    this.lOptions = new HashMap<String,Long>(0);

    Options options = new Options();
    DefaultParser parser = new DefaultParser();
    options.addOption(new Option("h", "help",       false, "Shows help."                  ));
    options.addOption(new Option("m", "minimum",    true,  "Lower bound for the result."  ));
    options.addOption(new Option("M", "Maximum",    true,  "Upper bound for the result."  ));
    options.addOption(new Option("s", "size",       true,  "Size of the result."          ));
    options.addOption(new Option("f", "file",       true,  "Output file name."            ));
    options.addOption(new Option("p", "properties", true,  "Config file name."            ));
    options.addOption(new Option("v", "variation",  true,  "Maximal change in boundaries."));

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

    String propFile = commandLine.getOptionValue("p", DEFAULT_PROPERTIES);
    Properties properties = readProperties(propFile);

    lOptions.put("m", Long.parseLong(   commandLine.getOptionValue("m", properties.getProperty("default.minimum"   ))));
    lOptions.put("M", Long.parseLong(   commandLine.getOptionValue("M", properties.getProperty("default.Maximum"   ))));
    iOptions.put("s", Integer.parseInt( commandLine.getOptionValue("s", properties.getProperty("default.size"      ))));
    sOptions.put("f",                   commandLine.getOptionValue("f", properties.getProperty("default.outputFile")) );
    iOptions.put("v", Integer.parseInt( commandLine.getOptionValue("v", properties.getProperty("dafault.variation" ))));

  }

  protected static Properties readProperties( String propFile ){
    Properties prop = new Properties();
    try(InputStream input = new FileInputStream(propFile)) {
      prop.load(input);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return prop;
  }

}
