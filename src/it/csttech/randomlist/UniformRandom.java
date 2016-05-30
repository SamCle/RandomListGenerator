package it.csttech.randomlist;

import java.security.*;

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
//		try{
//		secureRandom = SecureRandom.getInstanceStrong();
//		} catch (NoSuchAlgorithmException e)
//		{
//			e.getMessage();
//		}
	}

	public boolean checkNext(long iPrevious, long iNext) {
		if (Math.abs(iNext - iPrevious) <= 1)
			return false;
		else
			return true;
	}

	public double nextDouble() {
		return secureRandom.nextDouble();
	}
	
    public long nextLong() {
		return secureRandom.nextLong();
	}
                

}
