import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;


public class Astar extends Search{
	Comparator<Node> comparator = new NodeComparator();
	PriorityQueue<Node> pq = new PriorityQueue<Node>(20, comparator);
	
	public static void main(String[] args) throws IOException{
		Astar aStar= new Astar();
		aStar.readInput();
		aStar.pq.add(aStar.startNode);
		Node end = aStar.search();
		aStar.findPath(end);
		System.out.println("Path Cost: "+aStar.pathCost);
		System.out.println("Nodes Expanded: "+aStar.nodesExpanded);
		System.out.println("Max Depth: "+aStar.maxDepth);
		System.out.println("Maximum size of the frontier: "+aStar.maxFrontierSize);
		aStar.printMaze();
	}
	
	private Node search(){
		while(!pq.isEmpty()){
			if(pq.size()>maxFrontierSize)
				maxFrontierSize = pq.size();
			Node node = pq.poll();
			nodesExpanded++;
			int xPos = node.xPos, yPos = node.yPos;
			if(maze.get(yPos).charAt(xPos)=='.')
				return node;
			if(maze.get(yPos).charAt(xPos+1)!='%'){
				Node child = new Node(xPos+1,yPos,node,node.depth+1);
				if(node.depth+1>maxDepth)
					maxDepth = node.depth+1;
				if(visited[xPos+1][yPos]!=1){
					visited[xPos+1][yPos]=1;
					pq.add(child);
				  }	
			   }
				if(maze.get(yPos-1).charAt(xPos)!='%'){
					Node child = new Node(xPos,yPos-1,node,node.depth+1);
					if(node.depth+1>maxDepth)
						maxDepth = node.depth+1;
					if(visited[xPos][yPos-1]!=1){
						visited[xPos][yPos-1]=1;
						pq.add(child);
					}
				}
				if(maze.get(yPos+1).charAt(xPos)!='%'){
					Node child = new Node(xPos,yPos+1,node,node.depth+1);
					if(node.depth+1>maxDepth)
						maxDepth = node.depth+1;
					if(visited[xPos][yPos+1]!=1){
						visited[xPos][yPos+1]=1;
						pq.add(child);
					}
				}
				if(maze.get(yPos).charAt(xPos-1)!='%'){
					Node child = new Node(xPos-1,yPos,node,node.depth+1);
					if(node.depth+1>maxDepth)
						maxDepth = node.depth+1;
					if(visited[xPos-1][yPos]!=1){
						visited[xPos-1][yPos]=1;
						pq.add(child);
					}
				}
			}
		return null;
		}
	
	class NodeComparator implements Comparator<Node>{
		private int totalCost(Node node){
			return Math.abs(node.xPos-goalCol)+ Math.abs(node.yPos-goalRow)+
					Math.abs(node.xPos-startNode.xPos)+ Math.abs(node.yPos-startNode.yPos);
		}
		@Override
		public int compare(Node arg0, Node arg1) {
			return totalCost(arg0)-totalCost(arg1);
		}	
	}

}
