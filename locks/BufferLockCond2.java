

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class BufferLockCond2
{
	private int[] contents;
	private final int size;
	private int front, back;
	
	private Lock lock = new ReentrantLock();
	

	// Constructor
	public Buffer(int s) {
		this.size = s;
		contents = new int[size];
		for (int i=0; i<size; i++)
		contents[i] = 0;
		this.front = 0;
		this.back = size - 1;
	}

	// Put an item into buffer
	public void put(int data) {

		
			back = (back + 1) % size;
			contents[back] = data;
			System.out.println("Prod " + Thread.currentThread().getName() + " No "+ data + " Loc " + back); //+ " Count = " + counter);
		
	}

	// Get an item from bufffer
	public int get() {
		int data = 0;
		
		lock.lock();
		try {
		
			data = contents[front];
			System.out.println("  Cons " + Thread.currentThread().getName() + " No "+ data + " Loc " + front); //+ " Count = " + (counter-1));
			front = (front + 1) % size;
			
			
		} finally {
			lock.unlock() ;
		}
		return data;
	}
}