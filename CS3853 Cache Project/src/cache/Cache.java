package cache;

public class Cache {
	int cacheSize, blockSize, associativity;
	String replacementPolicy;
	
	public Cache(int cSize, int bSize, int association, String rp) {
		cacheSize = cSize;
		blockSize = bSize;
		associativity = association;
		replacementPolicy = rp;
	}
	
	public int totalBlocks() {
		int blockCalc = cacheSize / blockSize;
		blockCalc *= 1024;
		return blockCalc;
	}
	
	public int indexSize() {
		int indexCalc = totalBlocks() / associativity;
		indexCalc = ((int) (Math.log(indexCalc) / Math.log(2)+1e-10));
		return indexCalc;
	}
	
	public int offsetSize() {
		int offsetCalc = (int) (Math.log(blockSize) / Math.log(2)+1e-10);
		return offsetCalc;
	}
	
	public int tagSize() {
		int tagCalc = 32 - indexSize() - offsetSize();
		return tagCalc;
	}
	
	public int overheadSize() {
		int overCalc = ((tagSize() + 1) * (int) (Math.pow(2, (int) (Math.log(totalBlocks()) / Math.log(2)+1e-10)))) / 8;
		return overCalc;
	}
	
	public double implementSize() {
		int iCalc = cacheSize + (overheadSize() / 1024);
		return iCalc;
	}
	
	public double cost() {
		double costCalc = implementSize() * 0.05;
		return costCalc;
	}
}
