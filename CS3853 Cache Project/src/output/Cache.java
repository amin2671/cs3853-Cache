package cache;

import java.lang.Math;
import java.util.ArrayList;

public class Cache {
	int cacheSize, blockSize, associativity;
	String replacementPolicy;
	ArrayList<Instruction> list;
	
	public Cache(int cSize, int bSize, int association, String rp, ArrayList<Instruction> L) {
		cacheSize = cSize;
		blockSize = bSize;
		associativity = association;
		replacementPolicy = rp;
		list = L;
	}
	
	public int totalBlocks() {
		return (cacheSize / blockSize) * 1024;
	}
	
	public int totalRows() {
		return totalBlocks() / associativity;
	}
	
	public int indexSize() {
		return ((int) (Math.log(totalRows()) / Math.log(2)+1e-10));
	}
	
	public int offsetSize() {
		return (int) (Math.log(blockSize) / Math.log(2)+1e-10);
	}
	
	public int tagSize() {
		return 32 - indexSize() - offsetSize();
	}
	
	public int overheadSize() {
		return ((tagSize() + 1) * (int) (Math.pow(2, (int) (Math.log(totalBlocks()) / Math.log(2)+1e-10)))) / 8;
	}
	
	public double implementSize() {
		return cacheSize + (overheadSize() / 1024);
	}
	
	public double cost() {
		return implementSize() * 0.05;
	}
	
	public int totalAccess() {
		int accesses = 0;
		for (int i=0; i<list.size(); i++) {
			if(!(list.get(i).getDestaddress().equals("00000000")))
				accesses++;
			if(!(list.get(i).getSrcaddress().equals("00000000")))
				accesses++;
			accesses++;
		}
		return accesses;
	}
	
	public int instructions() {
		int temp = totalAccess();
		for (int i=0; i<list.size(); i++) {
			if(!(list.get(i).getDestaddress().equals("00000000")))
				temp--;
			if(!(list.get(i).getSrcaddress().equals("00000000")))
				temp--;
		}
		return temp;
	}
}
