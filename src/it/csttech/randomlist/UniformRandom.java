package it.csttech.randomlist;

import java.security.*;

public class UniformRandom {

	public SecureRandom secureRandom;

	long iMin;
	long iMax;
	int iSize;
	long iPrevious;

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

	public void setIPrevious(long iPrevious){
		this.iPrevious = iPrevious;
	}

	private boolean checkNext(long iNext) {
		if (Math.abs(iNext - this.iPrevious) < 1)
			return false;
		else
			return true;
	}

/*	NOT NEEDED
	public long uniformNext()	{
		long iNext = 0; //dummy for now
		return iNext;
	}
*/	
	public double nextDouble() {
		return secureRandom.nextDouble();
	}
	
    public long nextLong() {
		return secureRandom.nextLong();
	}
                

}
