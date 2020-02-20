
public class GraphEdge {
	private GraphNode node1;
	private GraphNode node2;
	private char busline;
	
	public GraphEdge(GraphNode u, GraphNode v, char busLine){
		node1 = u;
		node2 = v;
		busline = busLine;		
	}
	
	public GraphNode firstEndpoint(){
		return node1;
	}
	
	public GraphNode secondEndpoint(){
		return node2;
	}
	
	public char getBusLine(){
		return busline;
	}

}
