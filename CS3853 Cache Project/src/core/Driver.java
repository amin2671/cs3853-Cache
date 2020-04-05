package core;

import java.util.ArrayList;

import cache.Instruction;
import cache.Parser;
import output.Print;
import parameters.CommandLine;
import parameters.Parameters;

public class Driver {

	public static void main(String[] args) {
		
		CommandLine.readCommandLine(args);
		Print.printHeader(args);

		Parser test = new Parser(Parameters.trace);
		ArrayList<Instruction> list = test.generateInstructionList();
				
		Print.print20Lines(list);
	}

}
