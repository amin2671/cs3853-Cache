package cache;

public class Instruction {
	private int length;
	private long instructaddress, destaddress, srcaddress;
	private String hexInstruct, hexDest, hexSrc;

	public Instruction(int length, String instruct, String dest, String src){
		this.length = length;
		hexInstruct = instruct;
		hexDest = dest;
		hexSrc = src;
		
		instructaddress = 0;
		destaddress = 0;
		srcaddress = 0;
	}

	public int getLength() {
		return length;
	}

	public String getInstructaddress() {
		return hexInstruct;
	}

	public String getDestaddress() {
		return hexDest;
	}

	public String getSrcaddress() {
		return hexSrc;
	}
}
