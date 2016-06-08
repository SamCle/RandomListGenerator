package it.csttech;

import java.io.*;
import java.util.*;
import java.text.*;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.*;

public class PropertiesHandler {

  private static final Logger log = LogManager.getLogger(PropertiesHandler.class.getName());

  private long iMin;
  private long iMax;
  private  int iVar;
  private  int iSize;
  private File outputFile;
  private File inputFile;
  private boolean appender;
  private long lLength;
  private List<String> nameList;
  private Properties properties;
  private CommandLine commandLine;

  public PropertiesHandler(CommandLine commandLine, String defaultProperties){

    this.commandLine = commandLine;
    String propFile  = commandLine.getOptionValue("p", defaultProperties);
    properties = readProperties(propFile);

    iMin =    Long.parseLong(commandLine.getOptionValue("m",  properties.getProperty("default.minimum"   )).trim());
    iMax =    Long.parseLong(commandLine.getOptionValue("M",  properties.getProperty("default.maximum"   )).trim());
    iVar =  Integer.parseInt(commandLine.getOptionValue("v",  properties.getProperty("dafault.variation" )).trim());
    outputFile =    new File(commandLine.getOptionValue("of", properties.getProperty("default.outputFile")).trim());
    if (commandLine.hasOption("if")) {
      inputFile  = new File(commandLine.getOptionValue("if").trim());
    }
    appender =               commandLine.hasOption("a");
    lLength = Math.round(Math.ceil(Math.log10(iMax)));
    iSize = calculateSize();
    log.trace("Properties are now ready.");
  }

  private List<String> populateNameList(){
    List<String> nameList = new ArrayList<String>();
    String line;
    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)))) {
      while ((line = br.readLine()) != null){
        nameList.add(line);
      }
    } catch (IOException e) {
      log.error("File not recognized.");
      e.printStackTrace();
    }
    this.nameList = nameList;
    return nameList;
  }

  private int calculateSize(){
    int iResult;
    if (hasInputFile()) {
      iResult = populateNameList().size();
    } else {
      iResult = Integer.parseInt(commandLine.getOptionValue("s",  properties.getProperty("default.size")).trim());
    }
    return iResult;
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

  public boolean hasInputFile(){
    return inputFile != null;
  }

  public List<String> getNameList(){
    return nameList;
  }

  public boolean isAppender(){
    return appender;
  }

  public long getLength(){
    return lLength;
  }

}
