import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Search {

	protected ArrayList<String> maze = new ArrayList<String>();
	protected int[][] visited;
	int mazeRBound = 0;
	int mazeBBound = 0;
	protected int pathCost = 0;
	Node startNode;

	public Search() {
		super();
	}

	protected void printMaze() {
		for(int i=0;i<maze.size();i++)
			System.out.println(maze.get(i));
	}

	protected void findPath(Node node) {
		pathCost++;
		//System.out.println(node.xPos+":"+node.yPos);
		if(node.parent==null)
			return;
		else{
			insertDot(node);
			findPath(node.parent);
		}
	}

	protected void readInput() throws IOException {
		int startRow=0, startCol=0;
		BufferedReader br = new BufferedReader(new FileReader("E:/Fall 2013/AI/Assignments/1/Input/mediumMaze.lay"));
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
		startNode = new Node(startCol, startRow, null,0);
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

	public void insertDot(Node node) {
		char[] str = maze.get(node.yPos).toCharArray();
		//System.out.println(node.xPos+":::"+str.length+"::"+maze.get(node.yPos));
		str[node.xPos]='.';
		maze.set(node.yPos, new String(str));
	}

}