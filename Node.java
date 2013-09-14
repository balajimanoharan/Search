public class Node{
	int xPos, yPos;
	Node parent;
	Node[] children;
	
	public Node(int xPos, int yPos, Node parent) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.parent = parent;
	}
}