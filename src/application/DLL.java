package application;




public class DLL {
	//============= data fields =================
	private DNode first,last;
	private int count;
	//==============constructors======================
	public DLL() {
		
	}
	//==============getters and setters=======================
	public DNode getFirst() {
		return first;
	}
	public void setFirst(DNode first) {
		this.first = first;
	}
	public DNode getLast() {
		return last;
	}
	public void setLast(DNode last) {
		this.last = last;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	//=======================================
	public void addFirst(Object element){ //a method to add SNode at the head of list
		DNode newNode= new DNode(element);
		if (count == 0) { // If this is the first element of the list
	        first = last = newNode;
	        newNode.setNext(newNode); // Point next to itself
	        newNode.setPrev(newNode);
		}
		else { // Otherwise, add to the front
	        newNode.setNext(first); 
	        first.setPrev(newNode); 
	        newNode.setPrev(last); 
	        last.setNext(newNode); 
	        first = newNode; 
		}
		count++;                           //add 1 to count
	}
	//=========================================
	public void addLast(Object element){ // a method to add SNode at the tail of list
		DNode newNode=new DNode(element);
		if(count==0) {                    // if this is the first element of the list then let last point at first
			first=last=newNode;
			newNode.setNext(newNode); 
	        newNode.setPrev(newNode); 
		}
		else{                            // otherwise
			last.setNext(newNode); 
	        newNode.setPrev(last); 
	        newNode.setNext(first); 
	        first.setPrev(newNode); 
	        last = newNode; 
		}
		count++;                         //add 1 to count      
	}
	//=============================================
	public void add(Object  element,int index){ // a method to add DNode at specific index of the list
		DNode newnode=new DNode(element);
		if(index<0||index>count){
			throw new IllegalArgumentException("index not valid");
		}
		if(index==0)
			addFirst(element);
		else if(index==count)
			addLast(element);
		else{
			DNode ptr=first;
			for(int i=0;i<index-1;i++){
				ptr=ptr.getNext();
			}
			newnode.setNext(ptr.getNext());
			ptr.getNext().setPrev(newnode);
			ptr.setNext(newnode);
			newnode.setPrev(ptr);
		}
		count++;
	}
	//==========================================
//	public void sortedAdd(Object element) {
//		if (element == null) {
//            throw new IllegalArgumentException("Element cannot be null");
//        }
//		if(searchforDuplicates(element)) {
//			throw new IllegalArgumentException("THIS MJOR ALREADY EXISTS ! , IF YOU NEED TO UPDATE IT GO TO UPDATE MAJOR TAB!");
//		}
//
//	        Major newMajor = (Major) element;
//	        DNode newNode = new DNode(newMajor);
//
//	        if (count == 0) { // If list is empty, add first
//	            addFirst(newMajor);
//	        } 
//	        else if (((Major) first.getElement()).compareTo(newMajor) > 0) { // Compare with first element
//	            addFirst(newMajor);
//	        } 
//	        else if (((Major) last.getElement()).compareTo(newMajor) < 0) { // Compare with last element
//	            addLast(newMajor);
//	        } 
//	        else { // In the middle
//	            DNode previous = first;
//	            DNode curr = first.getNext();
//
//	            while (curr != first) {
//	                if (( curr.getElement()).compareTo(newMajor) > 0) {
//	                	previous.setNext(newNode);
//	                    newNode.setPrev(previous);
//	                    newNode.setNext(curr);
//	                    curr.setPrev(newNode);
//	                 // Increment the count after successful addition
//	    	            count++;
//	                    break;
//	                }
//	                previous = curr;
//	                curr = curr.getNext(); // Move to the next node
//	            }
//	        }
//	}
	//==========================================
	public boolean removeFirst(){ // a method to remove DNode from the head of list
		if(count==0)
			return false;
		if(count==1)
			first=last=null;
		else{
			DNode temp=first;
			first=first.getNext();
			first.setPrev(last);
			last.setNext(first);
			temp.setNext(null);
			temp.setPrev(null);
		}
		count--;
		return true;
	}
	//===========================================
	public boolean removeLast(){ // a method to remove DNode from the tail of list
		if(count==0)
			return false;
		DNode temp=last;
		if(count==1)
			first=last=null;
		else{
			last=last.getPrev();
			last.setNext(first);
			first.setPrev(last);
		}
		temp.setPrev(null);
		temp.setNext(null);
		count--;
		return true;
	}
	//======================================
	public boolean remove(int index){ // a method to remove DNode from a specific index at list
		if(index<0||index>count)
			return false;
		if(index==0)
			return removeFirst();
		if(index==count)
			return removeLast();
		else{
			DNode previous=first;
			DNode current=first.getNext();
			for(int i=1;i<index;i++){
				previous=current;
				current=current.getNext();
			}
			previous.setNext(current.getNext());
			current.getNext().setPrev(previous);
			current.setNext(null);
			current.setPrev(null);
		}
		count--;
		return true;
	}
	//=======================================
	public boolean remove(Object element){ // a method to remove a specific element from list
		if(first!=null){
			
			if(first.getElement().equals(element))
				return removeFirst();
			
			if(last.getElement().equals(element))
				return removeLast();
			
			DNode ptr=first.getNext();
			DNode previous=first;
			
			while(ptr!=first){
				if (ptr.getElement().equals(element)) {
					
		            previous.setNext(ptr.getNext());
		            ptr.getNext().setPrev(previous);
		            // Check if ptr is the last node
		            if (ptr == last) {
		                last = previous; // Update last reference
		            }
		            ptr.setNext(null);
	                ptr.setPrev(null);
		            count--;
		            return true; // Element found and removed
		        }
				previous = ptr;
		        ptr = ptr.getNext();
			}
		}
		return false;
	}
	//==============================================
//	public boolean searchforDuplicates(Object element) {
//		if(first!=null) {
//			if(((Major)first.getElement()).equals((Major)element))
//				return true;
//			if(((Major)last.getElement()).equals((Major)element))
//				return true;
//			DNode ptr=first.getNext();
//			DNode previous=first;
//			for(int i=0;i<count-2;i++){
//				if(!((Major) ptr.getElement()).equals((Major)element)){
//					previous=ptr;
//					ptr=ptr.getNext();
//				}
//				else{
//					return true;
//				}
//			}
//		}
//		return false;
//	}
	//====================================================
//	public DLL searchwithdestance(String destance) {
//		DLL d=new DLL();
//		if(first!=null) { 
//			if(((Flight)first.getElement()).getDestination().equalsIgnoreCase(destance) ){
//				d.addLast((Flight)first.getElement());}
//			if(((Flight)last.getElement()).getDestination().equalsIgnoreCase(destance)) {
//				d.addLast((Flight)last.getElement());}
//			DNode ptr=first.getNext();
//			DNode previous=first;
//			for(int i=0;i<count-2;i++){
//				if(((Flight)ptr.getElement()).getDestination().equalsIgnoreCase(destance)){
//					d.addLast((Flight)ptr.getElement());
//				}
//				else{
//					previous=ptr;
//					ptr=ptr.getNext();
//				}
//			}
//			return d;
//		}
//		throw new NullPointerException();
//	}
	public DLL searchWithDestination(String destination) {
	    if (destination == null) {
	        throw new IllegalArgumentException("Destination cannot be null");
	    }

	    DLL result = new DLL();
	    DNode current = first;

	    for (int i = 0; i < count; i++) {
	        Flight flight = (Flight) current.getElement();
	        if (flight.getDestination().equalsIgnoreCase(destination)) {
	            result.addLast(flight);
	        }
	        current = current.getNext();
	    }

	    return result;
	}
	
	public DNode searchwithid(int id) {
		if(first!=null) { 
			if(((Flight)first.getElement()).getFid()==id)
				return first;
			if(((Flight)last.getElement()).getFid()==id)
				return last;
			DNode ptr=first.getNext();
			DNode previous=first;
			for(int i=0;i<count-2;i++){
				if(((Flight) ptr.getElement()).getFid()==id){
					return ptr;
				}
				else{
					previous=ptr;
					ptr=ptr.getNext();
				}
			}
		}
		throw new NullPointerException();
	}
//	
//	public Passenger isCheckedIn(int pid) {
//		if(first!=null) { 
//			DNode temp=first;
//			Passenger ptoreturn=null;
//			for(int i=0;i<count;i++) {
//				Queue vip=((Flight)temp.getElement()).getVIPpassengers();
//				Queue reg=((Flight)temp.getElement()).getRegularPassengers();
//				for(int j=0;j<vip.getSize();j++) {
//					Passenger p=(Passenger)vip.dequeue();
//					if(p!=null) {
//						if(p.getPid()==pid)
//							ptoreturn=p;
//						vip.enqueue(p);
//					}
//				}
//				for(int j=0;j<reg.getSize();j++) {
//					Passenger p=(Passenger)reg.dequeue();
//					if(p!=null) {
//						if(p.getPid()==pid)
//							ptoreturn=p;
//						reg.enqueue(p);
//					}
//				}
//				if(ptoreturn!=null)
//					break;
//				temp=temp.getNext();
//			}
//			return ptoreturn;
//		}
//		throw new NullPointerException();
//	}
	public Passenger searchforPassenger(int pid) {
		if(first!=null) { 
			DNode temp=first;
			Passenger ptoreturn=null;
			for(int i=0;i<count;i++) {
				SLL all=((Flight)temp.getElement()).getAllPasseners();
				SNode s=all.getFirst();
				for(int j=0;j<all.getCount();j++) {
					Passenger p=(Passenger)s.getElement();
					if(p!=null) {
						if(p.getPid()==pid)
							ptoreturn=p;
					}
					s=s.getNext();
				}
				if(ptoreturn!=null)
					break;
				temp=temp.getNext();
			}
			Queue vip=((Flight)temp.getElement()).getVIPpassengers();
			for(int j=0;j<vip.getSize();j++) {
				Passenger p=(Passenger)vip.dequeue();
				if(p!=null) {
					if(p.getPid()==pid)
						ptoreturn=p;
				}
				vip.enqueue(p);
			}
			Queue reg=((Flight)temp.getElement()).getRegularPassengers();
			for(int j=0;j<reg.getSize();j++) {
				Passenger p=(Passenger)reg.dequeue();
				if(p!=null) {
					if(p.getPid()==pid)
						ptoreturn=p;
				}
				reg.enqueue(p);
			}
			return ptoreturn;
		}
		throw new NullPointerException();
	}
	

	

}
