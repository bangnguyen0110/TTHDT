import java.util.*; 
import java.lang.*; 
import java.io.*; 
import java.util.LinkedList; 
import java.util.Scanner;
  
class MaxFlow 
{ 
	public static int V;
    boolean bfs(int rGraph[][], int s, int t, int parent[]) 
    { 
        boolean visited[] = new boolean[V]; 
        for(int i=0; i<V; i++) 
            visited[i]=false; 
			LinkedList<Integer> hang = new LinkedList<Integer>(); 
			hang.add(s); 
			visited[s] = true; 
			parent[s]=-1; 
			while (hang.size()!=0) 
			{ 
				int u = hang.poll(); 
  
				for (int v=0; v<V; v++) 
				{ 
					if (visited[v]==false && rGraph[u][v] > 0) 
					{ 
						hang.add(v); 
						parent[v] = u; 
						visited[v] = true; 
					} 
				} 
			} 
        return (visited[t] == true); 
    } 
  
    // Returns tne maximum flow from s to t in the given graph 
    int fordFulkerson(int graph[][], int s, int t) 
    { 
        int u, v;  
        int rGraph[][] = new int[V][V]; 
  
        for (u = 0; u < V; u++) 
            for (v = 0; v < V; v++) 
                rGraph[u][v] = graph[u][v]; 
		int parent[] = new int[V]; 
  
        int max_flow = 0;
        while (bfs(rGraph, s, t, parent)) 
        { 
            int path_flow = Integer.MAX_VALUE; 
            for (v=t; v!=s; v=parent[v]) 
            { 
                u = parent[v]; 
                path_flow = Math.min(path_flow, rGraph[u][v]); 
            } 
			
            for (v=t; v != s; v=parent[v]) 
            { 
                u = parent[v]; 
                rGraph[u][v] -= path_flow; 
                rGraph[v][u] += path_flow; 
            } 
  
            max_flow += path_flow; 
        } 
  
        return max_flow; 
    } 
	
  
    public static void main (String[] args) throws java.lang.Exception 
    {
		
     
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter V : ");
		V=scanner.nextInt();
		int[][] graph = new int[V][V];
		for (int i = 0; i < V; i++) 
		{
			for (int j = 0; j < V; j++) 
			{
				System.out.print("[" + i + ", " + j + "]: ");
				graph[i][j] = scanner.nextInt();
			}
		}
		
		for (int i = 0; i < V; i++) 
		{
			for (int j = 0; j < V; j++) 
			{
				System.out.print(graph[i][j] + "\t");
			}
			System.out.println("\n");   
		}
		
        MaxFlow m = new MaxFlow(); 
        System.out.println("The maximum possible flow is " + 
                           m.fordFulkerson(graph, 0,1)); 
	}
}