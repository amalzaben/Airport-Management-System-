package application;

public class Flight {
	
	private String fname;
	private final int fid;
	private String destination;
	private boolean status;//true->active,false->inactive
	private Queue regularPassengers=new Queue();
	private Queue VIPpassengers=new Queue();
	private Stack undo= new Stack();
	private Stack redo=new Stack();
	private SLL bordedPassengers=new SLL();
	private SLL canceledPassengers=new SLL();
	private SLL allPasseners=new SLL();
	private static int defaultid=1;
	private String date;
	private int nvipcancelled;
	private int nregcancelled;
	private int nvipcheckedin;
	private int nregcheckedin;
	private int nvipbordered;
	private int nregboredred;
	
	//=============== constructor=====================
	public Flight(String destination,String date,boolean status) {
		this.fid=defaultid++;
		this.fname="Flight"+fid;
		this.destination=destination;
		this.status=status;
		this.date=date;
	}
	//=============== getters and setters ===============

	public String getFname() {
		return fname;
	}
	public int getDefaultID() {
		return defaultid;
	}

	public int getFid() {
		return fid;
	}
    
	public SLL getAllPasseners() {
		return allPasseners;
	}

	public Queue getRegularPassengers() {
		return regularPassengers;
	}

	public Queue getVIPpassengers() {
		return VIPpassengers;
	}

	public Stack getUndo() {
		return undo;
	}

	public Stack getRedo() {
		return redo;
	}

	public SLL getBordedPassengers() {
		return bordedPassengers;
	}

	public SLL getCanceledPassengers() {
		return canceledPassengers;
	}

	public static int getDefaultid() {
		return defaultid;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
    
	public int getNvipcancelled() {
		nvipcancelled=0;
		SNode temp=canceledPassengers.getFirst();
		if(temp!=null) {
			for(int i=0;i<canceledPassengers.getCount();i++) {
				if(((Passenger)temp.getElement()).isStatus()) {
					nvipcancelled++;
				}
				temp=temp.getNext();
			}
		}
		return nvipcancelled;
	}

	public int getNregcancelled() {
		nregcancelled=0;
		SNode temp=canceledPassengers.getFirst();
		if(temp!=null) {
			for(int i=0;i<canceledPassengers.getCount();i++) {
				if(!((Passenger)temp.getElement()).isStatus()) {
					nregcancelled++;
				}
				temp=temp.getNext();
			}
		}
		return nregcancelled;
	}

	public int getNvipcheckedin() {
		nvipcheckedin=0;
		for(int i=0;i<VIPpassengers.getSize();i++) {
			Passenger p=(Passenger)VIPpassengers.dequeue();
		    nvipcheckedin++;
			VIPpassengers.enqueue(p);
		}
		return nvipcheckedin;
	}

	public int getNregcheckedin() {
		nregcheckedin=0;
		for(int i=0;i<regularPassengers.getSize();i++) {
			Passenger p=(Passenger)regularPassengers.dequeue();
		    nregcheckedin++;
		    regularPassengers.enqueue(p);
		}
		return nregcheckedin;
	}

	public int getNvipbordered() {
		nvipbordered=0;
		SNode temp=bordedPassengers.getFirst();
		if(temp!=null) {
			for(int i=0;i<bordedPassengers.getCount();i++) {
				if(((Passenger)temp.getElement()).isStatus()) {
					nvipbordered++;
				}
				temp=temp.getNext();
			}
		}
		return nvipbordered;
	}

	public int getNregboredred() {
		nregboredred=0;
		SNode temp=bordedPassengers.getFirst();
		if(temp!=null) {
			for(int i=0;i<bordedPassengers.getCount();i++) {
				if(!((Passenger)temp.getElement()).isStatus()) {
					nregboredred++;
				}
				temp=temp.getNext();
			}
		}
		return nregboredred;
	}

	//===================== to String ======================
	@Override
	public String toString() {
		return fid+"";
	}
	
	
	

}
