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

  private static final Logger log = LogManager.getLogger(RandomListBuilder.class.getName());
  private long min;
  private long max;
  private int var;
  private int size;
  private List<Long> list;

  public RandomListBuilder(long min, long max, int var, int size){
    this.min = min;
    this.max = max;
    this.size = size;
    this.var = var;
    UniformRandom uniformRandom = new UniformRandom(min, max, size);
    this.list = setList(uniformRandom);
  }

  private List<Long> setSeparators(UniformRandom uniformRandom) {
    List<Long> separators = new ArrayList<Long>();
    long separator;

    separators.add(0, min);
    for(int i = 1; i < size; i++) {
      separator =  (i) * (max - min) / size + min;
      separator += Math.floor(2 * (uniformRandom.nextDouble() - 0.5) * ((max - min) / size) * var / 100);
      separators.add(i, separator);
    }
    separators.add(size, max);
    return separators;
  }

  private List<Long> setList(UniformRandom uniformRandom) {
    List<Long> separators = setSeparators(uniformRandom);
    List<Long> list = new ArrayList<Long>(size);
    long point;
    int maximumAttempts = (int) Math.ceil( 100 * ( max - min ) / size );
    log.trace("This program will run for at most " + maximumAttempts + " attempts.");
    int attemptsCounter = 0;

    for(int i = 0; i < size; ) {
      point = (long) Math.floor(uniformRandom.nextDouble() * (separators.get(i+1) - separators.get(i)) + separators.get(i));
      if(uniformRandom.checkNext( (i == 0? -2 : list.get(i-1)), point)) {
        list.add(i, point);
        i++;
        attemptsCounter = 0;
      } else {
        if ( attemptsCounter++ > maximumAttempts ){
          log.error(RandomListBuilder.class.getName() + " won't work on this options, please try to reduce the value of size.");
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
