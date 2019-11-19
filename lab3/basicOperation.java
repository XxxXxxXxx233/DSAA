package lab3;

public class basicOperation {
	public static void main(String[] args) {
		NodeTest a = new NodeTest(-1);
		NodeTest head = null;
		NodeTest tail = null;
		head = a;
		tail = a;
		NodeTest temp = null;
		for(int i=0; i<11; i++) {
			NodeTest c = new NodeTest(i);
			tail.next = c;
			tail = tail.next;
			if(i == 10)
				temp = c;
		}
		
	}
	
	public static void traverse(NodeTest A) {
		if(A == null) {
			System.out.println();
			return;
		}
		else {
			System.out.print(A.value + " ");
			traverse(A.next);
		}
	}
	
	public static void insertNode(NodeTest A, NodeTest q, int i) {
		int a = 0;
		NodeTest p = A;
		while(i-1 > a) {
			p = p.next;
			a++;
		}
		NodeTest temp = p.next;
		p.next = q;
		q.next = temp;
	}
	
	public static void deleteNode(NodeTest A, int i) {
		int a = 0;
		NodeTest p = A;
		while(i-1 > a) {
			p = p.next;
			a++;
		}
		p.next = p.next.next;
	}
	
	public static NodeTest findValue(NodeTest A, int x) {
		NodeTest p = A;
		while(p != null) {
			if(x == p.value)
				return p;
			p = p.next;
		}
		return null;
	}
	
	public static void updatesNodes(NodeTest A, int x, int y) {
		NodeTest p = A;
		while(p != null) {
			if(x == p.value)
				p.value = y;
			p = p.next;
		}
	}
}

class NodeTest {
	int value;
	int iniPos;
	NodeTest next;
	NodeTest prev;
	NodeTest(int value) {
		this.value = value;
	}
}
