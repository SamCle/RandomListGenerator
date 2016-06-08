package it.csttech.randomlist;

import it.csttech.randomlist.*;
import java.io.*;
import java.security.*;
import java.util.*;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.*;

/**
* This class generates a list of integers contained in a given range.<br>
* No consecutive numbers can be generated.<br>
* See .properties file for default values.<br>
* Launch it together with the -h option to see the possible options available.
*/
public class RandomListBuilder {

  private long iMin;
  private long iMax;
  private int iVar;
  private int iSize;
  private List<Long> list;

  public RandomListBuilder(long iMin, long iMax, int iVar, int iSize){
    this.iMin = iMin;
    this.iMax = iMax;
    this.iSize = iSize;
    this.iVar = iVar;
    UniformRandom uniformRandom = new UniformRandom(iMin, iMax, iSize);
    this.list = setList(uniformRandom);
  }

  private List<Long> setSeparators(UniformRandom uniformRandom) {
    List<Long> separators = new ArrayList<Long>();
    long separator;

    separators.add(0, iMin);
    for(int i = 1; i < iSize; i++) {
      separator =  (i) * (iMax - iMin) / iSize + iMin;
      separator += Math.floor(2 * (uniformRandom.nextDouble() - 0.5) * ((iMax - iMin) / iSize) * iVar / 100);
      separators.add(i, separator);
    }
    separators.add(iSize, iMax);

    return separators;
  }

  private List<Long> setList(UniformRandom uniformRandom) {
    List<Long> separators = setSeparators(uniformRandom);
    List<Long> list = new ArrayList<Long>(iSize);
    long point;
    double dMaximumAttempts = 100 * ( iMax - iMin ) / iSize;
    int iAttemptsCounter = 0;

    for(int i = 0; i < iSize; ) {
      iAttemptsCounter = 0;
      point = (long) Math.floor(uniformRandom.nextDouble() * (separators.get(i+1) - separators.get(i)) + separators.get(i));
      if(uniformRandom.checkNext( (i == 0? -2 : list.get(i-1)), point)) {
        list.add(i, point);
        i++;
      } else {
        if ( iAttemptsCounter++ > dMaximumAttempts ){
          return null;
        }
      }
    }

    return list;
  }

  public List<Long> getList(){
    return list;
  }

}
