package it.csttech;

import java.security.*;
import java.util.*;
import it.csttech.randomlist.*;

public class RandomListGenerator {
	public static void main(String[] args) {
		 //dummy values
		long iMin = 0;
		long iMax = 99999;
		int iPerc = 50;
		int iSize = 10;

		List<Long> list = new ArrayList<Long>(iSize);	
		List<Long> separators = new ArrayList<Long>();

//		Long[] iSeparators = new long[iSize + 1];
	
		UniformRandom uniformRandom = new UniformRandom(iMin, iMax, iSize);

		separators = setSeparators(iMin, iMax, iSize, iPerc, uniformRandom);
		
/*		iSeparators[0] = 0;
		for(int i = 1; i < iSize; i++) {
			iSeparators[i] =  (i) * (iMax - iMin) / iSize;
			iSeparators[i] += Math.floor(2 * (uniformRandom.nextDouble() - 0.5) * ((iMax - iMin) / iSize) * iPerc / 100 );
		}
		iSeparators[iSize] = iMax;
*/

		list = setList(iSize, separators, uniformRandom);

/*		for(int i = 0; i < iSize; i++) {
			long point = (long) Math.floor(uniformRandom.nextDouble() * (separators.get(i+1) - separators.get(i)) + separators.get(i)); 
			list.add(i, point);
			System.out.println(point);
		} 
*/		

		for(int i = 0; i < iSize; i++) {
			System.out.println(list.get(i));
		}
	}

	private static List<Long> setSeparators(long iMin, long iMax, int iSize, int iPerc, UniformRandom uniformRandom) {
		List<Long> separators = new ArrayList<Long>();
		long separator;

		separators.add(0, 0L);
		for(int i = 1; i < iSize; i++) {
			separator =  (i) * (iMax - iMin) / iSize;
			separator += Math.floor(2 * (uniformRandom.nextDouble() - 0.5) * ((iMax - iMin) / iSize) * iPerc / 100);
			separators.add(i, separator);
		}
		separators.add(iSize, iMax);

		return separators;
	}	

	private static List<Long> setList(int iSize, List<Long> separators, UniformRandom uniformRandom) {
		List<Long> list = new ArrayList<Long>(iSize);
		long point;

		for(int i = 0; i < iSize; i++) {
			point = (long) Math.floor(uniformRandom.nextDouble() * (separators.get(i+1) - separators.get(i)) + separators.get(i));
			list.add(i, point);
		}

		return list;
	}

//	public long... createSeparatorIndices(long iMin, long iMax, int iSize, int iPerc, UniformRandom uniformRandom) {}
}