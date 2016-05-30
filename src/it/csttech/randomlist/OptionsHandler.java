package it.csttech;

import java.io.*;
import java.util.*;
import java.text.*;
import org.apache.commons.cli.*;

public class OptionsHandler {

  public static final String DEFAULT_PROPERTIES = "config/RandomListGenerator.properties";
  protected Map<String,String>  sOptions;
  protected Map<String,Integer> iOptions; //TODO: min max sono long, var e size sono int
  protected Map<String,Double>  dOptions;
  NumberFormat format;

  OptionsHandler(String[] args){

    this.sOptions = new HashMap<String,String>(0);
    this.iOptions = new HashMap<String,Integer>(0);
    this.dOptions = new HashMap<String,Double>(0);

    Options options = new Options();
    DefaultParser parser = new DefaultParser();
    options.addOption(new Option("h", "help",       false, "Shows help."                  ));
    options.addOption(new Option("m", "minimum",    true,  "Lower bound for the result."  ));
    options.addOption(new Option("M", "Maximum",    true,  "Upper bound for the result."  ));
    options.addOption(new Option("s", "size",       true,  "Size of the result."          ));
    options.addOption(new Option("f", "file",       true,  "Output file name."            ));
    options.addOption(new Option("p", "properties", true,  "Config file name."            ));
    options.addOption(new Option("v", "variation",  true,  "Maximal change in boundaries."));
    options.addOption(new Option("l", "language",   true,  "Language used for the locale."));

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

    iOptions.put("m", Integer.parseInt(            commandLine.getOptionValue("m", properties.getProperty("default.minimum"   )))              );
    iOptions.put("M", Integer.parseInt(            commandLine.getOptionValue("M", properties.getProperty("default.Maximum"   )))              );
    iOptions.put("s", Integer.parseInt(            commandLine.getOptionValue("s", properties.getProperty("default.size"      )))              );
    sOptions.put("f",                              commandLine.getOptionValue("f", properties.getProperty("default.outputFile"))               );

    try{
      format = NumberFormat.getInstance(new Locale(commandLine.getOptionValue("l", properties.getProperty("default.language"))));
      dOptions.put("v", format.parse(              commandLine.getOptionValue("v", properties.getProperty("dafault.variation" ))).doubleValue());
    } catch (java.text.ParseException e) {
      e.getMessage();
    }

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
