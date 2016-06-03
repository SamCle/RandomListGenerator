package it.csttech;

import java.io.*;
import java.util.*;
import java.text.*;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.*;

public class PropertiesHandler {
  private long iMin;
  private long iMax;
  private  int iVar;
  private  int iSize;
  private File outputFile;
  private boolean appender;
  private long lLength;

  public PropertiesHandler(CommandLine commandLine, String defaultProperties){
    Logger log = LogManager.getLogger();
    String propFile = commandLine.getOptionValue("p", defaultProperties);
    Properties properties = readProperties(propFile);

    iMin =    Long.parseLong(commandLine.getOptionValue("m", properties.getProperty("default.minimum"   )));
    iMax =    Long.parseLong(commandLine.getOptionValue("M", properties.getProperty("default.maximum"   )));
    iVar =  Integer.parseInt(commandLine.getOptionValue("v", properties.getProperty("dafault.variation" )));
    iSize = Integer.parseInt(commandLine.getOptionValue("s", properties.getProperty("default.size"      )));
    outputFile =    new File(commandLine.getOptionValue("f", properties.getProperty("default.outputFile")));
    appender =               commandLine.hasOption("a");
    lLength = Math.round(Math.ceil(Math.log10(iMax)));

  }

    private Properties readProperties( String propFile ){
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
