package parameters;

import java.io.*;
import java.util.*;

public class CommandLine {
	
	public static void readCommandLine(String [] args) {
		
		if (args.length != 10) {
			System.out.print("Error: number of arguments should equal 10\n");
			System.exit(1);
		}
		
		for (int i = 0; i < 10; i+=2) {
			
			switch (args[i]) {
			
			case "-f":
				if (Parameters.trace != null) {
					System.out.print("Error: multiple file parameters detected\n");
					System.exit(2);
				}
				
				Parameters.trace = new File(args[i+1]); 
				break;
			
			case "-s":
				if (Parameters.cacheSize != null) {
					System.out.print("Error: multiple cache size parameters detected\n");
					System.exit(3);
				}
				
				try {
					Parameters.cacheSize = Integer.parseInt(args[i+1]);
				} catch (NumberFormatException e) {
					System.out.print("Error: cache size should be an integer\n");
					System.exit(23);
				}
				
				break;
			
			case "-b":
				if (Parameters.blockSize != null) {
					System.out.print("Error: multiple block size parameters detected\n");
					System.exit(4);
				}
				
				try {
					Parameters.blockSize = Integer.parseInt(args[i+1]);
				} catch (NumberFormatException e) {
					System.out.print("Error: block size should be an integer\n");
					System.exit(24);
				}
				
				break;
				
			case "-a":
				if (Parameters.associativity != null) {
					System.out.print("Error: multiple associativity parameters detected\n");
					System.exit(5);
				}
				
				try {
					Parameters.associativity = Integer.parseInt(args[i+1]);
				} catch (NumberFormatException e) {
					System.out.print("Error: associativity should be an integer\n");
					System.exit(25);
				}
				break;
			
			case "-r":
				if (Parameters.policy != null) {
					System.out.print("Error: multiple replacement policy parameters detected\n");
					System.exit(6);
				}
				Parameters.policy = args[i+1];
				break;
				
			default:
				System.out.printf("Error: invalid parameter detected \"%s\"\n", args[i]);
				System.exit(7);
			}
		}
		
		checkParameterValidity();
	}
	
	static void checkParameterValidity() {
				
		Set <Integer> cacheSizeSet = new HashSet <Integer>();
		for (int i = 1; i <= Math.pow(2,13); i = i*2) {
			cacheSizeSet.add(i);
		}
		
		Set <Integer> blockSizeSet = new HashSet <Integer>();
		for (int i = 4; i <= 64; i = i*2) {
			blockSizeSet.add(i);
		}
		
		Set <Integer> associativitySet = new HashSet <Integer>();
		for (int i = 1; i <= 16; i = i*2) {
			associativitySet.add(i);
		}
		
		Set <String> policySet = new HashSet <String>();
		policySet.add("RR");
		policySet.add("RND");
		policySet.add("LRU");
		
		
		if (!cacheSizeSet.contains(Parameters.cacheSize)) {
			System.out.print("Error: cache size should be a power of 2 from 1 KB to 8 MB\n");
			System.exit(13);
		}
		
		if (!blockSizeSet.contains(Parameters.blockSize)) {
			System.out.print("Error: block size should be a power of 2 from 4 to 64 bytes\n");
			System.exit(14);
		}
		
		if (!associativitySet.contains(Parameters.associativity)) {
			System.out.print("Error: associativity should be 1, 2, 4, 8, or 16\n");
			System.exit(15);
		}
		
		if (!policySet.contains(Parameters.policy)) {
			System.out.print("Error: replacement policy should be RR, RND, or LRU\n");
			System.exit(16);
		}
		
	}
	
}
