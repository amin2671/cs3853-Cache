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
		boolean cmd= false;
//		if(lineOne.contains("mov")||lineOne.contains("add")||lineOne.contains("and")
//				||lineOne.contains("pop")||lineOne.contains("sub")||lineOne.contains("mul")
//				||lineOne.contains("push")||lineOne.contains("dec")||lineOne.contains("cmp")
//				||lineOne.contains("lea")||lineOne.contains("jnz")||lineOne.contains("or")
//				||lineOne.contains("jmp")||lineOne.contains("call")||lineOne.contains("xor")
//				||lineOne.contains("inc")||lineOne.contains("div")||lineOne.contains("cmc")
//				||lineOne.contains("adc")||lineOne.contains("sbb")||lineOne.contains("test")
//				||lineOne.contains("ret")||lineOne.contains("jb")||lineOne.contains("jnl")
//				||lineOne.contains("jz")||lineOne.contains("shr")||lineOne.contains("leave")
//				||lineOne.contains("shl")||lineOne.contains("sar")||lineOne.contains("jl")
//				||lineOne.contains("jnb")||lineOne.contains("lock")||lineOne.contains("adc")
//				||lineOne.contains("not")||lineOne.contains("nop")||lineOne.contains("neg")
//				||lineOne.contains("cc")){
			cmd = true;
//		}
		Instruction instruct = new Instruction(Integer.parseInt(len),instAdd, destAdd,srcAdd,cmd);
		//System.out.println("Length: " + len + " Inst Add: " + instAdd+ "Dest Add: " + destAdd + " Src Add: " + srcAdd);
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
