package application;

public class Node {
	private Object element;
	Node prev;
	public Node next;
    Node numword ;
	public Object data;
	
	public Node(Object element){
		this.element=element;
	}
	public Object getElement() {
		return element;
	}
	public void setElement(Object element) {
		this.element = element;
	}
	

}
