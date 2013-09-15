import java.io.IOException;
import java.util.Stack;


public class DFS extends Search{
	Stack<Node> stack = new Stack<Node>();
	int nodesExpanded=0, maxDepth = 0, maxFrontierSize=0;
	
	public static void main(String[] args) throws IOException {
		DFS dfs= new DFS();
		dfs.readInput();
		dfs.stack.push(dfs.startNode);
		Node end = dfs.search();
		dfs.findPath(end);
		System.out.println("Path Cost: "+dfs.pathCost);
		System.out.println("Nodes Expanded: "+dfs.nodesExpanded);
		System.out.println("Max Depth: "+dfs.maxDepth);
		System.out.println("Maximum size of the frontier: "+dfs.maxFrontierSize);
		dfs.printMaze();
		//System.out.println(end.xPos+":"+end.yPos);
	}
	
	private Node search() {
		while(!stack.isEmpty()){
			if(stack.size()>maxFrontierSize)
				maxFrontierSize = stack.size();
			Node node = stack.pop();
			nodesExpanded++;
			int xPos = node.xPos, yPos = node.yPos;
			//System.out.println(xPos+";"+yPos);
			if(maze.get(yPos).charAt(xPos)=='.')
				return node;
			if(maze.get(yPos).charAt(xPos+1)!='%'){
				Node child = new Node(xPos+1,yPos,node,node.depth+1);
				if(node.depth+1>maxDepth)
					maxDepth = node.depth+1;
				if(visited[xPos+1][yPos]!=1){
					visited[xPos+1][yPos]=1;
					stack.push(child);
				}
			}
			if(maze.get(yPos-1).charAt(xPos)!='%'){
				Node child = new Node(xPos,yPos-1,node,node.depth+1);
				if(node.depth+1>maxDepth)
					maxDepth = node.depth+1;
				if(visited[xPos][yPos-1]!=1){
					visited[xPos][yPos-1]=1;
					stack.push(child);
				}
			}
			if(maze.get(yPos+1).charAt(xPos)!='%'){
				Node child = new Node(xPos,yPos+1,node,node.depth+1);
				if(node.depth+1>maxDepth)
					maxDepth = node.depth+1;
				if(visited[xPos][yPos+1]!=1){
					visited[xPos][yPos+1]=1;
					stack.push(child);
				}
			}
			if(maze.get(yPos).charAt(xPos-1)!='%'){
				Node child = new Node(xPos-1,yPos,node,node.depth+1);
				if(node.depth+1>maxDepth)
					maxDepth = node.depth+1;
				if(visited[xPos-1][yPos]!=1){
					visited[xPos-1][yPos]=1;
					stack.push(child);
				}
			}
		}
		return null;
	}
}
