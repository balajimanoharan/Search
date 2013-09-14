import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;


public class DFS {
	ArrayList<String> maze=new ArrayList<String>();
	int[][] visited;
	Node startNode;
	Stack<Node> stack = new Stack<Node>();
	int mazeRBound=0, mazeBBound=0;
	int nodesExpanded=0;
	
	public static void main(String[] args) throws IOException {
		DFS dfs= new DFS();
		dfs.readInput();
		Node end = dfs.search();
		dfs.findPath(end);
		//System.out.println(end.xPos+":"+end.yPos);
	}

	private void findPath(Node node) {
		System.out.println(node.xPos+":"+node.yPos);
		if(node.parent==null)
			return;
		else
			findPath(node.parent);
	}
	
	private Node search() {
		while(!stack.isEmpty()){
			Node node = stack.pop();
			nodesExpanded++;
			int xPos = node.xPos, yPos = node.yPos;
			//System.out.println(xPos+";"+yPos);
			if(maze.get(yPos).charAt(xPos)=='.')
				return node;
			if(maze.get(yPos).charAt(xPos+1)!='%'){
				Node child = new Node(xPos+1,yPos,node);
				if(visited[xPos+1][yPos]!=1){
					visited[xPos+1][yPos]=1;
					stack.push(child);
				}
			}
			if(maze.get(yPos-1).charAt(xPos)!='%'){
				Node child = new Node(xPos,yPos-1,node);
				if(visited[xPos][yPos-1]!=1){
					visited[xPos][yPos-1]=1;
					stack.push(child);
				}
			}
			if(maze.get(yPos+1).charAt(xPos)!='%'){
				Node child = new Node(xPos,yPos+1,node);
				if(visited[xPos][yPos+1]!=1){
					visited[xPos][yPos+1]=1;
					stack.push(child);
				}
			}
			if(maze.get(yPos).charAt(xPos-1)!='%'){
				Node child = new Node(xPos-1,yPos,node);
				if(visited[xPos-1][yPos]!=1){
					visited[xPos-1][yPos]=1;
					stack.push(child);
				}
			}
		}
		return null;
	}

	private void readInput() throws IOException {
		int startRow=0, startCol=0;
		BufferedReader br = new BufferedReader(new FileReader("E:/Fall 2013/AI/Assignments/1/Input/openMaze.lay"));
		int i=-1;
		String str;
		while(((str = br.readLine())!=null)){
			//System.out.println(str);
			i++;
			if(str.contains("P")){
				startCol = str.indexOf('P');
				startRow = i;
			}
				maze.add(str);
		}
		startNode = new Node(startCol, startRow, null);
		stack.push(startNode);
		mazeBBound = maze.size();
		mazeRBound = maze.get(0).length();
		visited = new int[mazeRBound][];
		for(int j=0;j<mazeRBound;j++){
			visited[j]=new int[mazeBBound];
		}
		visited[startCol][startRow]=1;
		//System.out.println(startRow+":"+startCol+":"+maze.size()+":"+mazeRBound+":"+maze.get(mazeBBound-1).charAt(mazeRBound-1));
		br.close();
	}

}
