package cache;

public class Instruction {
	private int length;
	private long instructaddress, destaddress, srcaddress;

	public Instruction(int length, String instruct, String dest, String src) {
		this.length = length;
		instructaddress = Long.parseLong(instruct, 16);
		destaddress = Long.parseLong(dest, 16);;
		srcaddress = Long.parseLong(src, 16);;
	}

	public int getLength() {
		return length;
	}

	public long getInstructaddress() {
		return instructaddress;
	}

	public long getDestaddress() {
		return destaddress;
	}

	public long getSrcaddress() {
		return srcaddress;
	}
}
