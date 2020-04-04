package output;

import java.io.*;
import java.util.*;

import cache.Instruction;
import parameters.Parameters;

public class Print {
	
	public static void printHeader(String [] args) {
		
		System.out.printf("Cache Simulator CS 3853 Spring 2020 - Group #19\n");
		System.out.printf("\n");
		System.out.printf("Cmd Line:");
		
		for (int i = 0; i < 10; ++i) {
			System.out.printf(" %s", args[i]);
		}
		
		System.out.printf("\n");
		System.out.printf("\n");
		System.out.printf("Trace File: %s\n", Parameters.trace);
		System.out.printf("Cache Size: %d KB\n", Parameters.cacheSize);
		System.out.printf("Block Size: %d bytes\n", Parameters.blockSize);
		System.out.printf("Associativity: %d\n", Parameters.associativity);
		System.out.printf("Policy: %s\n\n", Parameters.policy);
		
	}
	
	public static void print20Lines(ArrayList <Instruction> list) {
		
		for(int i=0; i < list.size() && i < 20; i++){
			System.out.println(list.get(i));
		}
	}
	
	
	
}
