//BY EVAN ROME-BAILEY, STUDENT #250867976, LIST #195

import java.util.Arrays;
import java.util.Iterator;

// A class that creates a graph of GraphNodes and stores connecting edges in an adjacency matrix
public class Graph implements GraphADT{
	private GraphNode nodeList[];
	private GraphEdge adjMatrix[][];
		
	public Graph(int n){
		adjMatrix = new GraphEdge[n][n];
		nodeList = new GraphNode[n];
	
		for(int i = 0; i < n; i++){
			nodeList[i] = new GraphNode(i);
		}
		
		for(int j = 0; j < n; j++){
			for(int k = 0; k < n; k++){
				adjMatrix[j][k] = null;
			}
		}
	
	}
	
	//Checks if two nodes are in the graph, and if so, inserts and edge between them into the adjacency matrix
	public void insertEdge(GraphNode u, GraphNode v, char busLine) throws GraphException{
		
		boolean checker = false;
		boolean checker2 = false;
		
		for(int k = 0; k < nodeList.length; k++){
			if(nodeList[k].equals(u)){
				checker = true;
			}
			else if(nodeList[k].equals(v)){
				checker2 = true;
			}
		}
		
		if(!checker || !checker2){
			throw(new GraphException("ERROR: a given node was not in the graph."));
		}
		
		
		if(adjMatrix[u.getName()][v.getName()] != null){
			throw(new GraphException("ERROR: a given edge is already in the graph."));
		}
		
		adjMatrix[u.getName()][v.getName()] = new GraphEdge(u,v,busLine);
		adjMatrix[v.getName()][u.getName()] = new GraphEdge(v,u,busLine);
	}
	
	
	//Returns a node with the given node, if such a node exists in the graph
	public GraphNode getNode(int name) throws GraphException{
		
		if(name >= nodeList.length){
			throw(new GraphException("ERROR: a given node was not in the graph."));
		}
		else if(nodeList[name] == null){
			throw(new GraphException("ERROR: a given node was not in the graph."));
		}
		else{
			return nodeList[name];
		}
		
	}
	
	//Returns an iterator of all the edges connecting a given node to other nodes in the graph, if the given node exists
	public Iterator<GraphEdge> incidentEdges(GraphNode u) throws GraphException{
		
		boolean checker = false;
		for(int k = 0; k < nodeList.length; k++){
			if(nodeList[k].equals(u)){
				checker = true;
			}
		}
		
		if(!checker){
			throw(new GraphException("ERROR: a given node was not in the graph."));
		}
		
		int count = 0;
		
		for(int i = 0; i < nodeList.length; i++){
			if(adjMatrix[u.getName()][i] != null){
				count++;
			}
		}
		
		if(count == 0){
			return null;
		}
		
		GraphEdge[] tempList = new GraphEdge[count];
		int counter2 = 0;
		
		for(int j = 0; j < nodeList.length; j++){
			if(adjMatrix[u.getName()][j] != null){
				tempList[counter2] = adjMatrix[u.getName()][j];
				counter2++;
			}
		}
		Iterator<GraphEdge> iter = Arrays.asList(tempList).iterator();
		return iter;
	}
	
	//Returns the edge between two given nodes, if the nodes exist and have an edge between them
	public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException{
		
		boolean checker = false;
		boolean checker2 = false;
		
		for(int k = 0; k < nodeList.length; k++){
			if(nodeList[k].equals(u)){
				checker = true;
			}
			else if(nodeList[k].equals(v)){
				checker2 = true;
			}
		}
		
		if(!checker || !checker2){
			throw(new GraphException("ERROR: a given node was not in the graph."));
		}
		
		GraphEdge found = adjMatrix[u.getName()][v.getName()];
		
		if(found == null){
			throw(new GraphException("ERROR: a given edge was not in the graph."));
		}
		
		return found;
		
	}
	
	//Checks if an edge exists between two nodes, if both nodes are in the graph
	public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException{
		
		boolean checker = false;
		boolean checker2 = false;
		
		for(int k = 0; k < nodeList.length; k++){
			if(nodeList[k].equals(u)){
				checker = true;
			}
			else if(nodeList[k].equals(v)){
				checker2 = true;
			}
		}
		
		if(!checker || !checker2){
			throw(new GraphException("ERROR: a given node was not in the graph."));
		}
		
		GraphEdge found = adjMatrix[u.getName()][v.getName()];
		
		if(found == null){
			return false;
		}
		
		return true;
	}
}
