package cache;

import java.lang.Math;
import java.util.*;

public class Cache {
	public int cacheSize, blockSize, associativity, hitCount, compulsoryMissCount, conflictMissCount, cycles,
			instructions;
	Map<Integer, List<Integer>> cacheMap;
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
		return ((int) (Math.log(totalRows()) / Math.log(2) + 1e-10));
	}

	public int offsetSize() {
		return (int) (Math.log(blockSize) / Math.log(2) + 1e-10);
	}

	public int tagSize() {
		return 32 - indexSize() - offsetSize();
	}

	public int overheadSize() {
		return ((tagSize() + 1) * (int) (Math.pow(2, (int) (Math.log(totalBlocks()) / Math.log(2) + 1e-10)))) / 8;
	}

	public double implementSize() {
		return cacheSize + (overheadSize() / 1024);
	}

	public double cost() {
		return implementSize() * 0.05;
	}

	private void populateCache() {
		for (Instruction i : list) {
			instructions += 1;
			cycles += 2;

			parseInstruction(i.getInstructaddress(), i.getLength());
			if (i.getDestaddress() != 0) {
				parseInstruction(i.getDestaddress(), 4);
				cycles++;
			}
			if (i.getSrcaddress() != 0) {
				parseInstruction(i.getSrcaddress(), 4);
				cycles++;
			}
		}
	}

	void parseInstruction(long address, int length) {

		int offset = (int) address % (int) Math.pow(2, offsetSize());
		address /= (int) Math.pow(2, offsetSize());
		int index = (int) address % (int) Math.pow(2, indexSize());
		int tag = (int) address / (int) Math.pow(2, indexSize());

		for (int bytesRemaining = length + offset; bytesRemaining > 0; bytesRemaining -= blockSize) {
			if (!cacheMap.containsKey(index)) {
				cacheMap.put(index, new LinkedList<Integer>());
			}

			boolean hit = false;
			for (Integer blockTag : cacheMap.get(index))
				if (blockTag == tag) {
					hit = true;
					break;
				}

			if (hit == true) {
				++hitCount;
				cycles++;
			} else {
				addTag(index, tag);
				cycles += 3 * Math.ceil(blockSize / 4);
			}
			if (index == totalRows() - 1)
				index = 0;
			else
				++index;
		}

	}

	void addTag(int index, int tag) {
		cacheMap.get(index).add(tag);

		if (cacheMap.get(index).size() > associativity) {
			++conflictMissCount;

			if (replacementPolicy.equals("RR"))
				cacheMap.get(index).remove(0);
			else
				cacheMap.get(index).remove((int) Math.floor(Math.random() * cacheMap.get(index).size()));
		} else
			++compulsoryMissCount;
	}

	public int unusedCache() {
		return totalBlocks() - compulsoryMissCount;
	}

	public double unusedKB() {
		return (double) (unusedCache() * implementSize()) / totalBlocks();
	}

	public double waste() {
		return unusedKB() * 0.05;
	}
}
