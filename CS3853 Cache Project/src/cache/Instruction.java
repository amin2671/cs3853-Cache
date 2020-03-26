package cache;

public class Instruction {
	private int length;
	private int instructaddress, destaddress, srcaddress;
	private String hexInstruct, hexDest, hexSrc;
	
	public Instruction(int length, String instruct, String dest, String src){
		this.length = length;
		hexInstruct = instruct;
		hexDest = dest;
		hexSrc = src;
		instructaddress = Integer.parseInt(instruct, 16);
		destaddress = Integer.parseInt(dest, 16);
		srcaddress = Integer.parseInt(src, 16);
	}
	
	public int getLength() {
		return length;
	}
	public int getInstructaddress() {
		return instructaddress;
	}
	public int getDestaddress() {
		return destaddress;
	}
	public int getSrcaddress() {
		return srcaddress;
	}
	
	public String toString(){
		String info = "";
		info += "Address: " + hexInstruct;
		info += " Length: " + length;
		if(destaddress > 0){
			info += " Destination Address: " + hexDest;
		}
		if(srcaddress > 0){
			info += " Source Address: " + hexSrc;
		}
		return info;
	}
}
