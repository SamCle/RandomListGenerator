package it.csttech.randomlist;

import java.security.*;


public class UniformRandom {

	SecureRandom secureRandom;

	long iMin;
	long iMax;
	long iSize;
	long iPrevious;

	public UniformRandom(long iMin, long iMax, long iSize) {
		this.iMin = iMin;
		this.iMax = iMax;
		this.iSize = iSize;
		try{
		secureRandom = SecureRandom.getInstanceStrong();
		} catch (NoSuchAlgorithmException e)
		{
			e.getMessage();
		}
	}

	private boolean checkNext(long iNext) {
		return true;
	}

	public void setIPrevious(long iPrevious){
		this.iPrevious = iPrevious;
	}
	
}
