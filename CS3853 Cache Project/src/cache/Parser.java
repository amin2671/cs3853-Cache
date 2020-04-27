package cache;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Parser {
	ArrayList<Instruction> list;
	File file;
	public Parser(File trace){
		 list = new ArrayList<>();
		 file = trace;
	}
	
	private Instruction parseInstruction(String lineOne, String lineTwo){
		String len = lineOne.substring(5, 7);
		String instAdd = lineOne.substring(10,18);
		String destAdd = lineTwo.substring(6,14);
		String srcAdd = lineTwo.substring(33, 41);
		
		Instruction instruct = new Instruction(Integer.parseInt(len), instAdd, destAdd, srcAdd);
		
		return instruct;
	}
	
	public ArrayList<Instruction> generateInstructionList(){
		try {
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()){
				list.add(parseInstruction(scan.nextLine(), scan.nextLine()));
				if(scan.hasNextLine())
					scan.nextLine();
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
		
	}
}
