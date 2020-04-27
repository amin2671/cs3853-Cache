package core;

import java.util.ArrayList;

import cache.Instruction;
import cache.Parser;
import output.Print;
import parameters.CommandLine;
import parameters.Parameters;

public class Driver {

	public static void main(String[] args) {
		//String[] a = {"-f", "Tiny Trace","-s","32","-b","4","-a","4","-r","RR"};
		String[] a = {"-f", "A-9_new_1.5.pdf.trc","-s","32","-b","4","-a","4","-r","RR"};
		CommandLine.readCommandLine(a);
		
		Parser test = new Parser(Parameters.trace);
		ArrayList<Instruction> list = test.generateInstructionList();
		Print.printHeader(list);

	}

}
