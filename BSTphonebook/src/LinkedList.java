class Node<T> {
	private T data;
	private Node<T> next;

	public Node() {
		data = null;
		next = null;
	}

	public Node(T value) {
		data = value;
		next = null;
	}

	public T getData() {
		return data;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

}
///////////////
public class LinkedList <T extends Comparable<T>> {

	private Node<T> head;
	private Node<T> current;
	private int size;

	public LinkedList() {
		head = current = null;
		size = 0;
	}

	public void setHead(Node<T> head) {
		this.head = head;
	}

	public void setCurrent(Node<T> current) {
		this.current = current;
	}

	public boolean empty() {
		return head == null;
	}

	public void findfirst() {
		current = head;
	}

	public void findnext() {
		current = current.getNext();
	}

	public T retrieve() {
		return current.getData();
	}

	public void update(T val) {
		current.setData(val);
	}

	public boolean last() {
		return current.getNext() == null;
	}

	public int length() {
		return size;
	}

	public boolean insert(T val) {
		Node<T> tmp = new Node<T>(val);
		Node<T> previous = null;
		findfirst();
		if (empty()) {
			head = current = new Node<T>(val);
			size++;
			return true;
		}
		// insert before head node
		if (head.getData().compareTo(val) >= 0) {
			tmp.setNext(head);
			head = tmp;
			size++;
			return true;
		} else {
			// insert after head node
			previous = current;
			current = current.getNext();
			while (current != null) {
				if (current.getData().compareTo(val) >= 0) {
					tmp.setNext(current);
					previous.setNext(tmp);
					size++;
					return true;
				}
				previous = current;
				current = current.getNext();
			}
			// inser tmp in the end of the list
			previous.setNext(tmp);
			size++;
			return true;
		}
	}

	public boolean delete(T val) {
		findfirst();
		Node<T> previous = head;
		if (empty())
			return false;
		// delete head node
		if (current.getData().compareTo(val) == 0) {
			current = current.getNext();
			head = head.getNext();
			size--;
			return true;
		} else {
			// delete after head node
			current = current.getNext();
			while (current.getNext() != null) {
				if (current.getData().compareTo(val) == 0) {
					previous.setNext(current.getNext());
					current = current.getNext();
					size--;
					return true;
				}
				previous = previous.getNext();
				current = current.getNext();
			}
			// delete last node
			if (current.getData().compareTo(val) == 0) {
				current = previous;
				previous.setNext(null);
				size--;
				return true;
			}
			return false;
		}
	}

	// search if data exit in list
	public boolean search(T val) {
		if (empty())
			return false;
		findfirst();
		while (current != null) {
			if (current.getData().compareTo(val) != 0)
				current = current.getNext();
			else
				return true;
		}
		return false;
	}



}
