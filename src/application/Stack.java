package application;

public class Stack {
	private int top,count;
	private Object [] stack;
	final static int size=10;
	
	public Stack() {
		this(size);
	}
	Stack(int size){
		stack=new Object[size];
		top=-1;
	}
	public int getSize() {
		return count;
	}
	public boolean isEmpty() {
		return top==-1;
	}
	public boolean isFull() {
		return top==stack.length-1;
	}
	public Object peek() {
		if(!isEmpty())
			return stack[top];
		return null;
	}
	public boolean push(Object key) {
		if(isFull()) 
			return false;
		stack[++top]=key;
		count++;
		return true;
	}
	public Object pop() {
		if(isEmpty())
			return null;
		Object key=peek();
		stack[top--]=null;
		count--;
		return key;
	}

}
