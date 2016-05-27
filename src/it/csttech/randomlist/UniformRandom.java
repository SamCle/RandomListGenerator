package it.csttech.randomlist;

import java.security.SecureRandom;


public class UniformRandom extends SecureRandom {

	long iMin;
	long iMax;
	long iSize;
	long iPrevious;

	private boolean checkNext(long iNext) {
		return true;
	}


}
