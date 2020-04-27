package output;

import java.util.*;

import cache.Instruction;
import cache.Cache;
import output.Print;
import parameters.Parameters;

public class Print {
	
	public static void printHeader(ArrayList<Instruction> list) {
		Cache cache = new Cache(Parameters.cacheSize, Parameters.blockSize, Parameters.associativity, Parameters.policy, list);
		
		System.out.printf("Cache Simulator CS 3853 Spring 2020 - Group #19\n\n");
		System.out.printf("Trace File: %s\n\n", Parameters.trace);
		
		System.out.printf("***** Cache Input Parameters *****\n\n");
		System.out.printf("Cache Size: \t\t\t%d KB\n", Parameters.cacheSize);
		System.out.printf("Block Size: \t\t\t%d bytes\n", Parameters.blockSize);
		System.out.printf("Associativity: \t\t\t%d\n", Parameters.associativity);
		System.out.printf("Replacement Policy: \t\t%s\n\n", Parameters.policy);
		
		System.out.printf("***** Cache Calculated Values *****\n\n");
		System.out.printf("Total # Blocks:\t\t\t%d\n", cache.totalBlocks());
		System.out.printf("Tag Size:\t\t\t%d bits\n", cache.tagSize());
		System.out.printf("Index Size:\t\t\t%d bits\n", cache.indexSize());
		System.out.printf("Total # Rows:\t\t\t%d\n", cache.totalRows());
		System.out.printf("Overhead Size:\t\t\t%d bytes\n", cache.overheadSize());
		System.out.printf("Implementation Memory Size:\t%.2f KB (%d bytes)\n", cache.implementSize(), (int) cache.implementSize()*1024);
		System.out.printf("Cost:\t\t\t\t$%.2f\n\n", cache.cost());
				
		System.out.printf("***** CACHE SIMULATION RESULTS *****\n\n");
		System.out.printf("Total Cache Accesses:\t%d\n", cache.hitCount + cache.conflictMissCount + cache.compulsoryMissCount);
		System.out.printf("Cache Hits:\t\t%d\n", cache.hitCount);
		System.out.printf("Cache Misses:\t\t%d\n", cache.compulsoryMissCount + cache.conflictMissCount);
		System.out.printf("--- Compulsory Misses:\t   %d\n", cache.compulsoryMissCount);
		System.out.printf("--- Conflict Misses:  \t   %d\n\n\n", cache.conflictMissCount);
		
		System.out.printf("***** ***** CACHE HIT & MISS RATE: ***** *****\n\n");
		System.out.printf("Hit Rate:\t\t%.4f%%\n", 100.0 * cache.hitCount / (cache.hitCount + cache.conflictMissCount + cache.compulsoryMissCount));
		System.out.printf("Miss Rate:\t\t%.4f%%\n", 100.0 * (cache.compulsoryMissCount + cache.conflictMissCount) / (cache.hitCount + cache.conflictMissCount + cache.compulsoryMissCount));
		System.out.printf("CPI:\t\t\t%.2f Cycles/Instruction\n",((float)cache.cycles/(float)cache.instructions()));
		System.out.printf("Unused Cache Space:\t%.2f KB / %.2f KB = %.2f%%  Waste: $%.2f\n", 0.0, cache.implementSize(), (0.0 * 100) / cache.implementSize(), 0.0);
		System.out.printf("Unused Cache Blocks:\t%d / %d", 0, cache.totalBlocks());
		
	}	
	
}
