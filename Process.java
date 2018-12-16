public class Process {

	int pid;
	String name;
	int type;
	int exp;
	boolean isCoordinator, isDown;
	
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setPid(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}
		
	public boolean isCoOrdinatorFlag() {
		return isCoordinator;
	}

	public void setCoOrdinatorFlag(boolean isCoOrdinator) {
		this.isCoordinator = isCoOrdinator;
	}
	
	public boolean isDownflag() {
		return isDown;
	}

	public void setDownflag(boolean downflag) {
		this.isDown = downflag;
	}

	public Process() {
		
	}

	public Process(int pid, String name, int type, int exp) {
		this.pid = pid;
		this.name = name;
		this.type = type;
		this.exp = exp;
		this.isDown = false;
		this.isCoordinator = false;
	}
}