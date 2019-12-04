package algorithms;

import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public class JoinList<T> extends AbstractList<T> {

	private int length;
	private Node head;
	private Node tail;

	public JoinList() {
		head = tail = null;
		length = 0;
	}

	private class Node {
		private T item;
		private Node next;

		public Node(T e) {
			this.item = e;
		}
	}

	@Override
	public boolean add(T e) {
		if (head == null)
			head = tail = new Node(e);
		else
			tail = tail.next = new Node(e);
		length++;
		return true;
	}

	@Override
	public T get(int index) {
		if (index >= length) {
			throw new IndexOutOfBoundsException(index + " is beyond bounds of list, size:" + length);
		}
		Node item = head;
		for (int i = 0; i < index; i++) {
			item = item.next;
		}
		return item.item;
	}

	@Override
	public int size() {
		return length;
	}

	@Override
	public Iterator<T> iterator() {
		return listIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		return new Iter(head);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(Collection<? extends T> c) {
		if (c instanceof JoinList) {
			if (head == null) {
				head = ((JoinList) c).head;
				tail = ((JoinList) c).tail;
				this.length = c.size();
			} else {
				this.tail.next = ((JoinList) c).head;
				this.tail = ((JoinList) c).tail;
				this.length += c.size();
			}

		} else {
			return super.addAll(c);
		}

		return true;
		/**/
	}

	private class Iter implements ListIterator<T> {
		Node current;

		public Iter(JoinList<T>.Node head) {
			current = head;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			Node toret = current;
			current = current.next;
			return toret.item;
		}

		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public T previous() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int nextIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}

		@Override
		public void set(T e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void add(T e) {
		}

	}
}
