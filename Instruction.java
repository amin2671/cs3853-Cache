package cache;

public class Instruction {
	private int length;
	private int instructaddress, destaddress, srcaddress;
	private String hexInstruct, hexDest, hexSrc;
	private boolean cmd;
	
	public Instruction(int length, String instruct, String dest, String src,boolean cmd){
		this.length = length;
		hexInstruct = instruct;
		hexDest = dest;
		hexSrc = src;
		this.setCMD(cmd);
		//instructaddress = Integer.parseInt(instruct, 16);
		//destaddress = Integer.parseInt(dest, 16);
		//srcaddress = Integer.parseInt(src, 16);
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
	
	public String toString(){
		String info = "";
		info += hexInstruct + ": ";
		info += length;
		if(destaddress > 0){
			info += " Destination Address: " + hexDest;
		}
		if(srcaddress > 0){
			info += " Source Address: " + hexSrc;
		}
		return info;
	}

	public boolean isCMD() {
		return cmd;
	}

	public void setCMD(boolean cmd) {
		this.cmd = cmd;
	}
}
