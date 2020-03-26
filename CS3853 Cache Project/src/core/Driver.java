package core;

import java.io.File;
import java.util.ArrayList;

import cache.Instruction;
import cache.Parser;

public class Driver {

	public static void main(String[] args) {
		File trace = new File("Tiny Trace");
		Parser test = new Parser(trace);
		ArrayList<Instruction> list = test.generateInstructionList();
		for(int i=0; i < list.size(); i++){
			System.out.println(list.get(i));
		}
	}

}
