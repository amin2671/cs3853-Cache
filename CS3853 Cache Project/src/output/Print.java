package output;

import java.util.*;

import cache.Instruction;
import cache.Cache;
import output.Print;
import parameters.Parameters;

public class Print {
	
	public static void printHeader(String [] args) {
		Cache cache = new Cache(Parameters.cacheSize, Parameters.blockSize, Parameters.associativity, Parameters.policy);
		
		System.out.printf("Cache Simulator CS 3853 Spring 2020 - Group #19\n\n");
		System.out.printf("Trace File: %s\n\n", Parameters.trace);
		
		System.out.printf("***** Cache Input Parameters *****\n\n");
		System.out.printf("Cache Size: \t\t\t%d KB\n", Parameters.cacheSize);
		System.out.printf("Block Size: \t\t\t%d bytes\n", Parameters.blockSize);
		System.out.printf("Associativity: \t\t\t%d\n", Parameters.associativity);
		System.out.printf("Policy: \t\t\t%s\n\n", Parameters.policy);
		
		System.out.printf("***** Cache Calculated Values *****\n\n");
		System.out.printf("Total # Blocks:\t\t\t%d\n", cache.totalBlocks());
		System.out.printf("Tag Size:\t\t\t%d bits\n", cache.tagSize());
		System.out.printf("Index Size:\t\t\t%d bits\n", cache.indexSize());
		System.out.printf("Overhead Size:\t\t\t%d bytes\n", cache.overheadSize());
		System.out.printf("Implementation Memory Size:\t%.2f KB (%d bytes)\n", cache.implementSize(), (int) cache.implementSize()*1024);
		System.out.printf("Cost:\t\t\t\t$%.2f\n\n", cache.cost());
	}
	
	public static void print20Lines(ArrayList <Instruction> list) {
		
		for(int i=0; i < list.size() && i < 20; i++){
			System.out.println(list.get(i));
		}
	}
	
	
	
}
