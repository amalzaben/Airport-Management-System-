package application;

public class Passenger {
	private static int defaultid=1001;
	private final int pid;
	private String pname;
	private int flightid;
	private boolean status;//true->VIP,false->regular
	private boolean canceled;
	private boolean boarded;
	private boolean checkedin;
	
	//========= constructor====================
	public Passenger(String pname, int flightid, boolean status) {
		super();
		this.pid=defaultid++;
		this.pname = pname;
		this.flightid = flightid;
		this.status = status;
	}
	//============ getters and setters===============

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getFlightid() {
		return flightid;
	}

	public void setFlightid(int flightid) {
		this.flightid = flightid;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getPid() {
		return pid;
	}
	
	//============== toString =================

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}
	

	public boolean isBoarded() {
		return boarded;
	}

	public void setBoarded(boolean boarded) {
		this.boarded = boarded;
	}

	public boolean isCheckedin() {
		return checkedin;
	}

	public void setCheckedin(boolean checkedin) {
		this.checkedin = checkedin;
	}

	public static int getDefaultid() {
		return defaultid;
	}

	@Override
	public String toString() {
		return "Passenger [pid=" + pid + ", pname=" + pname + ", flightid=" + flightid + ", status=" + status + "]";
	}
	
	

}
