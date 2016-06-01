package it.csttech.randomlist;

import java.security.*;

/*
*	Please note: You are advised to instantiate SecureRandom using the following:
*	secureRandom = SecureRandom.getInstanceStrong();
*/

/**
* Core methods for random numbers management.
*/
public class UniformRandom {

	/**
	* This is the value that refers to the methods used in random number generation.
	*/
	public SecureRandom secureRandom;

	long iMin;
	long iMax;
	int iSize;

	/**
	* Constructor that sets all the variables needed.
	* @param iMin Lower bound of the range.
	* @param iMax Upper bound of the range.
	* @param iSize Size of the list that is going to be created.
	*/
	public UniformRandom(long iMin, long iMax, int iSize) {
		this.iMin = iMin;
		this.iMax = iMax;
		this.iSize = iSize;
		secureRandom = new SecureRandom();
	}

	/**
	* A checker for the condition that two numbers shouldn't be at a distance smaller than 2.
	* @param iPrevious The first number to be checked.
	* @param iNext The second number to be checked.
	* @return true if the values respect the condition, false otherwise.
	*/
	public boolean checkNext(long iPrevious, long iNext) {
		return !(Math.abs(iNext - iPrevious) <= 1);
	}

	/**
	* Uses the wrapped secureRandom value to generate new random double.
	* @return the next pseudorandom, uniformly distributed double value between 0.0 and 1.0 from this random number generator's sequence.
	*/
	public double nextDouble() {
		return secureRandom.nextDouble();
	}

	/**
	* Uses the wrapped secureRandom value to generate new random long.
	* @return the next pseudorandom, long value from this random number generator's sequence
	*/
	public long nextLong() {
		return secureRandom.nextLong();
	}


}
