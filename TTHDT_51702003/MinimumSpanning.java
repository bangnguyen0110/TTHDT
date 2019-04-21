import java.util.*; 
import java.lang.*; 
import java.io.*; 
import java.util.Scanner;

class MinimumSpanning 
{ 
    class Edge implements Comparable<Edge> 
    { 
        int src, dest, weight; 
        public int compareTo(Edge compareEdge) 
        { 
            return this.weight-compareEdge.weight; 
        } 
    }; 

    class subset 
    { 
        int parent, rank; 
    }; 
  
    int V, E;    
    Edge edge[]; 
  
    MinimumSpanning(int v, int e) 
    { 
        V = v; 
        E = e; 
        edge = new Edge[E]; 
        for (int i=0; i<e; ++i) 
            edge[i] = new Edge(); 
    } 
   
    int find(subset subsets[], int i) 
    {  
        if (subsets[i].parent != i) 
            subsets[i].parent = find(subsets, subsets[i].parent); 
  
        return subsets[i].parent; 
    } 
 
    void Union(subset subsets[], int x, int y) 
    { 
        int xroot = find(subsets, x); 
        int yroot = find(subsets, y);  
        if (subsets[xroot].rank < subsets[yroot].rank) 
            subsets[xroot].parent = yroot; 
        else if (subsets[xroot].rank > subsets[yroot].rank) 
            subsets[yroot].parent = xroot; 
        else
        { 
            subsets[yroot].parent = xroot; 
            subsets[xroot].rank++; 
        } 
    } 
  
    void KruskalMST() 
    { 
        Edge result[] = new Edge[V]; 
        int e = 0; 
        int i = 0;  
        for (i=0; i<V; ++i) 
            result[i] = new Edge(); 
 
        Arrays.sort(edge); 
  
        subset subsets[] = new subset[V]; 
        for(i=0; i<V; ++i) 
            subsets[i]=new subset(); 
  
        // Create V subsets with single elements 
        for (int v = 0; v < V; ++v) 
        { 
            subsets[v].parent = v; 
            subsets[v].rank = 0; 
        } 
  
        i = 0;  // Index used to pick next edge 
  
        // Number of edges to be taken is equal to V-1 
        while (e < V - 1) 
        { 
            // Step 2: Pick the smallest edge. And increment  
            // the index for next iteration 
            Edge next_edge = new Edge(); 
            next_edge = edge[i++]; 
  
            int x = find(subsets, next_edge.src); 
            int y = find(subsets, next_edge.dest); 
  
            // If including this edge does't cause cycle, 
            // include it in result and increment the index  
            // of result for next edge 
            if (x != y) 
            { 
                result[e++] = next_edge; 
                Union(subsets, x, y); 
            } 
        } 
  
        System.out.println("Following are the edges in " +  
                                     "the constructed MST"); 
        for (i = 0; i < e; ++i) 
            System.out.println(result[i].src+" -- " +  
                   result[i].dest+" == " + result[i].weight); 
    } 
  
    // Driver Program 
    public static void main (String[] args) 
    { 
        int V ; 
        int E ; 
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter V : ");
		V=scanner.nextInt();
        System.out.print("Enter E : ");
		E=scanner.nextInt();
        MinimumSpanning MinimumSpanning = new MinimumSpanning(V, E); 
		for(int i=0;i<E;i++)
		{
			System.out.println("Edge number "+i+":");
			System.out.println("src :");
			MinimumSpanning.edge[i].src=scanner.nextInt();
			System.out.println("Dest :");
			MinimumSpanning.edge[i].dest=scanner.nextInt();
			System.out.println("Weight :");
			MinimumSpanning.edge[i].weight=scanner.nextInt();
		}
        MinimumSpanning.KruskalMST(); 
    } 
} 