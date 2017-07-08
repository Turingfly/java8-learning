package genericsAndCollections;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 
 * @author chengfeili 
 * Jun 7, 2017 7:52:08 PM 
 * 
 * 		   LinkedList: In addition to being a
 *         list, it is a double-ended queue. A double-ended queue is different
 *         from a regular queue in that you can insert and remove elements from
 *         both the front and back of the queue. The main benefit of a
 *         LinkedList is that it implements both the List and Queue interfaces.
 *         The tradeoff is that it isn’t as efficient as a “pure” queue.
 * 
 *         An ArrayDeque is a “pure” double-ended queue. It was introduced in
 *         Java 6, and it stores its elements in a resizable array. The main
 *         benefit of an ArrayDeque is that it is more efficient than a
 *         LinkedList . Deque is supposed to be pronounced “deck,” but many
 *         people, including the authors, say it wrong as “d-queue.”
 *         
 *         Method 				Description 																For queue For stack
 *         boolean add(E e)   Adds an element to the back of the queue and returns true or throws an exception Yes  	 No
 *         E element() 		  Returns next element or throws an exception if empty queue 					   Yes		 No
 *         boolean offer(E e) Adds an element to the back of the queue and returns whether successful 		   Yes		 No
 *         E remove() 	      Removes and returns next element or throws an exception if empty queue 		   Yes		 No
 *         void push(E e)     Adds an element to the front of the queue 									   Yes 	     Yes
 *         E poll() 		  Removes and returns next element or returns null if empty queue 				   Yes		 No
 *         E peek() 		  Returns next element or returns null if empty queue 							   Yes 		 Yes
 *         E pop() 			  Removes and returns next element or throws an exception if empty queue		   No		 Yes
 *         
 *         Time Complexity: add() O(1); remove() O(1)
 */

public class UsingQueue {
	public static void testQueue() {
		Queue<Integer> queue = new ArrayDeque<>();
		System.out.println(queue.offer(10)); // true
		System.out.println(queue.offer(4)); // true
		System.out.println(queue.peek()); // 10
		System.out.println(queue.poll()); // 10
		System.out.println(queue.poll()); // 4
		System.out.println(queue.peek()); // null
	}
	
	public static void testStack() {
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		stack.push(10);
		stack.push(1);
		System.out.println(stack.peek()); // 1
		System.out.println(stack.poll()); // 1
		System.out.println(stack.poll()); // 10
		System.out.println(stack.peek()); // null
	}

	public static void main(String[] args) {
		testQueue();
		testStack();
	}
}
