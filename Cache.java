package cache;

import java.lang.Math;
import java.util.*;

public class Cache {
	public int cacheSize, blockSize, associativity, hitCount, compulsoryMissCount, conflictMissCount, cycles;
	Map <Integer, List <Integer>> cacheMap;
	String replacementPolicy;
	ArrayList<Instruction> list;
	
	
	public Cache(int cSize, int bSize, int association, String rp, ArrayList<Instruction> L) {
		cacheSize = cSize;
		blockSize = bSize;
		associativity = association;
		replacementPolicy = rp;
		list = L;
		cacheMap = new HashMap();
		
		cycles = 0;
		hitCount = 0;
		compulsoryMissCount = 0;
		conflictMissCount = 0;
		populateCache();
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
	
	private void populateCache() {
		
		for (Instruction i : list) {
			if(i.isCMD()){
				cycles=cycles+2;
			}
			parseInstruction(Long.parseLong(i.getInstructaddress(), 16), i.getLength());
			if (Long.parseLong(i.getDestaddress(), 16) != 0) {
				parseInstruction(Long.parseLong(i.getDestaddress(), 16), 4);
			}
			if (Long.parseLong(i.getSrcaddress(), 16) != 0)
			parseInstruction(Long.parseLong(i.getSrcaddress(), 16), 4);
		}
		
	}
	
	void parseInstruction(long address, int length) {
		
		int offset = (int) (address % (long) Math.pow(2,offsetSize()));
		address = address / (int) Math.pow(2,offsetSize());
		int index = (int) (address % (long) Math.pow(2,indexSize()));
		int tag = (int) (address / (long) Math.pow(2,indexSize()));
		
		for (int bytesRemaining = length + offset; bytesRemaining > 0; bytesRemaining -= blockSize) {
			if (!cacheMap.containsKey(index)) {
				cacheMap.put(index, new LinkedList<Integer>());
			}
			
			boolean hit = false;
			for (Integer blockTag : cacheMap.get(index)) {
				if (blockTag == tag) {
					hit = true;
					break;
				}
			}
			
			if (hit == true) {
				++hitCount;
				cycles=cycles+1;
			}
			else {
				addTag(index, tag);
				cycles=cycles+3;
			}
			++index;
		}
		
		
	}
	
	void addTag(int index, int tag) {
		cacheMap.get(index).add(tag);
		
		if (cacheMap.get(index).size() > associativity) {
			
			++conflictMissCount;
			
			if (replacementPolicy.equals("RR")) {
				cacheMap.get(index).remove(0);
			}
			else {
				cacheMap.get(index).remove( (int) Math.floor(Math.random() * cacheMap.get(index).size()));
			}
		}
		else {
			++compulsoryMissCount;
		}
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
