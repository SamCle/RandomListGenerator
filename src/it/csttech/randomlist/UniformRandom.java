package it.csttech.randomlist;

import java.security.*;

/** 
*	Please note: You are advised to instantiate SecureRandom using the following:
*	secureRandom = SecureRandom.getInstanceStrong();
*/

public class UniformRandom {

	public SecureRandom secureRandom;

	long iMin;
	long iMax;
	int iSize;

	public UniformRandom(long iMin, long iMax, int iSize) {
		this.iMin = iMin;
		this.iMax = iMax;
		this.iSize = iSize;
		secureRandom = new SecureRandom();
	}

	public boolean checkNext(long iPrevious, long iNext) {
		return !(Math.abs(iNext - iPrevious) <= 1);
	}

	public double nextDouble() {
		return secureRandom.nextDouble();
	}
	
    public long nextLong() {
		return secureRandom.nextLong();
	}
                

}
