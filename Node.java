public class Node{
	int xPos, yPos, depth;
	Node parent;
	
	public Node(int xPos, int yPos, Node parent, int depth) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.parent = parent;
		this.depth = depth;
	}
}