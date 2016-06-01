package it.csttech;

import java.io.*;
import java.util.*;
import java.text.*;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.*;

public class PropertiesHandler {
  final String DEFAULT_PROPERTIES = "config/RandomListGenerator.properties";
  private long iMin;
  private long iMax;
  private  int iVar;
  private  int iSize;
  private File outputFile;
  private boolean appender;
  private long lLength;

  public PropertiesHandler(CommandLine commandLine){
    String propFile = commandLine.getOptionValue("p", DEFAULT_PROPERTIES);
    Properties properties = OptionsHandler.readProperties(propFile);

    if(opt.helpCalled) {
      return;
    }

    iMin =    Long.parseLong(commandLine.getOptionValue("m", properties.getProperty("default.minimum"   )));
    iMax =    Long.parseLong(commandLine.getOptionValue("M", properties.getProperty("default.Maximum"   )));
    iVar =  Integer.parseInt(commandLine.getOptionValue("v", properties.getProperty("dafault.variation" )));
    iSize = Integer.parseInt(commandLine.getOptionValue("s", properties.getProperty("default.size"      )));
    outputFile =    new File(commandLine.getOptionValue("f", properties.getProperty("default.outputFile")));
    appender =               commandLine.hasOption("a");
    lLength = Math.round(Math.ceil(Math.log10(iMax)));

  }

  public long getMin(){
    return iMin;
  }

  public long getMax(){
    return iMax;
  }

  public int getVar(){
    return iVar;
  }

  public int getSize(){
    return iSize;
  }

  public File getOutputFile(){
    return outputFile;
  }

  public boolean isAppender(){
    return appender;
  }

  public long getLength(){
    return lLength;
  }

}
