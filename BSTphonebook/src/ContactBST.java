class BSTNode<K> {
	private String key;
	private K data;
	private BSTNode<K> left, right;

	public BSTNode(String k, K data) {
		key = k;
		this.data = data;
		left = right = null;
	}

	public BSTNode(String k, K data, BSTNode<K> l, BSTNode<K> r) {
		key = k;
		this.data = data;
		left = l;
		right = r;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public K getData() {
		return data;
	}

	public void setData(K data) {
		this.data = data;
	}

	public BSTNode<K> getLeft() {
		return left;
	}

	public void setLeft(BSTNode<K> left) {
		this.left = left;
	}

	public BSTNode<K> getRight() {
		return right;
	}

	public void setRight(BSTNode<K> right) {
		this.right = right;
	}
}
/////////////////////////
class BooleanWrapper {
	private Boolean b;

	public BooleanWrapper(Boolean b) {
		this.b = b;
	}

	public Boolean get() {
		return b;
	}

	public void set(Boolean b) {
		this.b = b;
	}
}
////////////////////////////
public class BST<K extends Comparable<K>> {
	public BSTNode<K> root, current;

	public BST() {
		root = current = null;
	}

	boolean empty() {
		return root == null;
	}

	boolean full() {
		return false;
	}

	public K retrieve() {
		return current.getData();
	}

	public boolean update(String key, K data) {
		remove_key(current.getKey());
		return insert(key, data);
	}

	public boolean findKey(String k) {
		BSTNode<K> p = root, q = root;
		if (empty())
			return false;
		while (p != null) {
			q = p;
			if (k.compareTo(p.getKey()) == 0) {
				current = p;
				return true;
			} else if (k.compareTo(p.getKey()) < 0)
				p = p.getLeft();
			else
				p = p.getRight();
		}
		current = q;
		return false;
	}

	public boolean insert(String k, K val) {
		BSTNode<K> p, q = current;
		if (findKey(k)) {
			current = q; // findkey() modified current
			return false;// key already in the BST
		}
		p = new BSTNode<K>(k, val);
		if (empty()) {
			root = current = p;
			return true;
		} else { // current is pointing to parent of the new key
			if (k.compareTo(current.getKey()) < 0)
				current.setLeft(p);
			else
				current.setRight(p);;
			current = p;
			return true;
		}

	}

	private BSTNode<K> find_min(BSTNode<K> p) {
		if (p == null)
			return null;
		while (p.getLeft() != null)
			p = p.getLeft();
		return p;

	}

	private BSTNode<K> remove_aux(String key, BSTNode<K> p, BooleanWrapper flag) {
		BSTNode<K> q, child = null;
		if (p == null)
			return null;
		if (key.compareTo(p.getKey()) < 0)
			p.setLeft(remove_aux(key, p.getLeft(), flag)) ; // go left
		else if (key.compareTo(p.getKey()) > 0)
			p.setRight(remove_aux(key, p.getRight(), flag)); // go right
		else {// key is found
			flag.set(true);
			// if node has two children
			if (p.getLeft() != null && p.getRight() != null) {
				q = find_min(p.getRight());
				p.setKey(q.getKey()); 
				p.setData(q.getData()); 
				p.setRight(remove_aux(q.getKey(), p.getRight(), flag)); 
			} else {// if node has one child
				if (p.getRight() == null)
					child = p.getLeft();
				else if (p.getLeft() == null)
					child = p.getRight();
				return child;

			}
		}
		return p;
	}

	public boolean remove_key(String tkey) {
		BooleanWrapper removed = new BooleanWrapper(false);
		BSTNode<K> p = remove_aux(tkey, root, removed);
		current = root = p;
		return removed.get();
	}

	// print BST in order traversal
	public void printBST() {
		printBST(root);
	}

	private void printBST(BSTNode<K> node) {
		if (node == null) {
			return;
		}

		printBST(node.getLeft());
		System.out.println(node.getData().toString());
		System.out.println();
		printBST(node.getRight());
	}

	// check if phone number exist in BST in-order traversal
	public boolean checkPhoneNumber(String phone) {
		return checkPhoneNumber(root, phone);
	}

	private boolean checkPhoneNumber(BSTNode<K> node, String phone) {
		if (node == null)
			return false;
		if (checkPhoneNumber(node.getLeft(), phone)) {
			current = node;// delete current?
			return true;
		}
		if (((Contact) node.getData()).getPhoneNumber().equals(phone)) {
			current = node;
			return true;
		}
		return checkPhoneNumber(node.getRight(), phone);
	}

	// search specific information in BST in-order traversal
	public BST<K> searchContactByInformation(String inform) {
		BST<K> informBST = new BST<K>();
		searchContactByInformation(root, inform, informBST);
		return informBST;
	}

	private void searchContactByInformation(BSTNode<K> node, String inform, BST<K> informBST) {
		if (node == null)
			return;

		searchContactByInformation(node.getLeft(), inform, informBST);

		if (((Contact) node.getData()).getName().equalsIgnoreCase(inform)
				|| ((Contact) node.getData()).getPhoneNumber().equalsIgnoreCase(inform)
				|| ((Contact) node.getData()).getEmail().equalsIgnoreCase(inform)
				|| ((Contact) node.getData()).getAddress().equalsIgnoreCase(inform)
				|| ((Contact) node.getData()).getBirthday().equalsIgnoreCase(inform)
				|| ((Contact) node.getData()).getNotes().equalsIgnoreCase(inform))
			informBST.insert(((Contact) node.getData()).getName(), node.getData());

		searchContactByInformation(node.getRight(), inform, informBST);

	}

	// search contacts that share first name in BST in-order traversal
	public LinkedList<Contact> searchContactByFirstName(String name) {
		LinkedList<Contact> firstNameList = new LinkedList<Contact>();
		searchContactByFirstName(root, name, firstNameList);
		return firstNameList;
	}

	private void searchContactByFirstName(BSTNode<K> node, String name, LinkedList<Contact> firstNameList) {
		if (node == null)
			return;

		searchContactByFirstName(node.getLeft(), name, firstNameList);

		if (((Contact) node.getData()).getName().substring(0, ((Contact) node.getData()).getName().indexOf(' '))
				.equalsIgnoreCase(name))
			firstNameList.insert((Contact) node.getData());

		searchContactByFirstName(node.getRight(), name, firstNameList);

	}
}
