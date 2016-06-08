package it.csttech;

import java.io.*;
import java.util.*;
import java.text.*;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.*;

public class PropertiesHandler {

  private static final Logger log = LogManager.getLogger(PropertiesHandler.class.getName());

  private long min;
  private long max;
  private  int var;
  private  int size;
  private File outputFile;
  private File inputFile;
  private boolean appender;
  private long length;
  private List<String> nameList;
  private Properties properties;
  private CommandLine commandLine;

  public PropertiesHandler(CommandLine commandLine, String defaultProperties){

    if (commandLine.hasOption("h")) {
      return;
    }
    this.commandLine = commandLine;
    String propFile  = commandLine.getOptionValue("p", defaultProperties);
    properties = readProperties(propFile);

    min =    Long.parseLong(commandLine.getOptionValue("m",  properties.getProperty("default.minimum"   )).trim());
    max =    Long.parseLong(commandLine.getOptionValue("M",  properties.getProperty("default.maximum"   )).trim());
    var =  Integer.parseInt(commandLine.getOptionValue("v",  properties.getProperty("dafault.variation" )).trim());
    outputFile =   new File(commandLine.getOptionValue("of", properties.getProperty("default.outputFile")).trim());
    if (commandLine.hasOption("if")) {
      inputFile = new File(commandLine.getOptionValue("if").trim());
      populateNameList();
    }
    appender =              commandLine.hasOption("a");
    length = Math.round(Math.ceil(Math.log10(max)));
    size = calculateSize();
    log.trace("Properties are now ready.");
  }

  private void populateNameList(){
    nameList = new ArrayList<String>();
    String line;
	InputStream input = null;
	BufferedReader br = null;
    try {
      input = new FileInputStream(inputFile);
      br = new BufferedReader(new InputStreamReader(input));
      while ((line = br.readLine()) != null){
        nameList.add(line);
      }
    } catch (IOException e) {
      log.error("File not recognized.");
      e.printStackTrace();
    } finally {
      try {
		if(input != null) {
        	input.close();
		}
		if(br != null) {
			br.close();
		}
      } catch(IOException ioe) {
        ioe.printStackTrace();
      }
    }
  }

  private int calculateSize(){
    int result;
    if (hasInputFile()) {
      result = nameList.size();
    } else {
      result = Integer.parseInt(commandLine.getOptionValue("s",  properties.getProperty("default.size")).trim());
    }
    return result;
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
    return min;
  }

  public long getMax(){
    return max;
  }

  public int getVar(){
    return var;
  }

  public int getSize(){
    return size;
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
    return length;
  }

}
