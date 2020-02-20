//BY EVAN ROME-BAILEY, STUDENT #250867976, LIST #195

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;

//A class that uses Graph object to create a city map based on an input file and implements a function which can find a path from S to D with a given number of bus changes or less
public class BusLines {
	private int width;
	private int height;
	private int maxChanges;
	private int startNode;
	private int endNode;
	
	Graph graph;
	
	//The constructor which creates a Graph object based the input file given
	public BusLines(String inputFile) throws IOException, GraphException,MapException{
		
		try {
			BufferedReader input = new BufferedReader(new FileReader(inputFile));
			String layout =  input.readLine();
			String layoutlist[] = layout.split(" ");
			
			width = (2*Integer.parseInt(layoutlist[1]))-1;
			height = (2*Integer.parseInt(layoutlist[2]))-1;
			maxChanges = Integer.parseInt(layoutlist[3]);
			graph = new Graph(((width +1)/2)*((height+1)/2));
			
			String[] connectorline = new String[width];
			int nodeCounter = 0;
			//i iterates each vertical line in the input file
			for(int i = 0; i < height; i++){
				String currentline[] = input.readLine().split("");
				
				if(i % 2 == 1){
					for(int j = 0; j < width; j++){
						connectorline[j] = currentline[j];
						
					}
					continue;
				}
				
				//k iterates each element in the line, checking to see if it is a node
				for(int k = 0; k < width; k++){
					if(currentline[k].equals("S") || currentline[k].equals("D")|| currentline[k].equals(".")){
						if(currentline[k].equals("S")){
							startNode = nodeCounter;
							if(k > 0){
								if(!currentline[k-1].equals(" ")){
									graph.insertEdge(graph.getNode(nodeCounter), graph.getNode(nodeCounter - 1), currentline[k-1].charAt(0));
								}
							}
							
							if(connectorline[0] != null){
								if(!connectorline[k].equals(" ")){
									graph.insertEdge(graph.getNode(nodeCounter), graph.getNode(nodeCounter - (width+1)/2), connectorline[k].charAt(0));
								}
							}
							nodeCounter++;
						}
						else if(currentline[k].equals("D")){
							endNode = nodeCounter;
							if(k > 0){
								if(!currentline[k-1].equals(" ")){
									graph.insertEdge(graph.getNode(nodeCounter), graph.getNode(nodeCounter - 1), currentline[k-1].charAt(0));
								}
							}
							
							if(connectorline[0] != null){
								if(!connectorline[k].equals(" ")){
									graph.insertEdge(graph.getNode(nodeCounter), graph.getNode(nodeCounter - (width+1)/2), connectorline[k].charAt(0));
								}
							}
							nodeCounter++;
						}
						else if(currentline[k].equals(".")){
							if(k > 0){
								if(!currentline[k-1].equals(" ")){
									graph.insertEdge(graph.getNode(nodeCounter), graph.getNode(nodeCounter - 1), currentline[k-1].charAt(0));
								}
							}
		
							if(connectorline[0] != null){
								if(!connectorline[k].equals(" ")){
									graph.insertEdge(graph.getNode(nodeCounter), graph.getNode(nodeCounter - (width+1)/2), connectorline[k].charAt(0));
								}
							}
							nodeCounter++;
						}
					}
				}
				
			}
			input.close();
			
		}catch (FileNotFoundException e) {
			throw(new MapException("The Map could not be found!"));	
	}
	}
	
	//returns the graph that was constructed
	public Graph getGraph(){
		return graph;
	}
	
	//Returns an iterator containing the nodes in the path found to the exit
	public Iterator<GraphNode> trip() throws GraphException{
		Stack<GraphNode> ansStack = new Stack<GraphNode>();
		ansStack.push(graph.getNode(startNode));
		maxChanges++;
		boolean solutionexists = pathfinder(ansStack, graph.getNode(startNode), 'S');
		
		if(solutionexists){
			Iterator<GraphNode> iter = ansStack.iterator();
			return iter;
		}
		else{
			return null;
		}
	}
	
	//Helper method which finds a path from the node marked with "S" to the node marked with "D"
	private boolean pathfinder(Stack<GraphNode> ansStack, GraphNode current, char line) throws GraphException{
		current.setMark(true);
		Iterator<GraphEdge> iter = graph.incidentEdges(current);
		boolean changedchecker = false;
		
		while(iter.hasNext()){
			GraphEdge next = iter.next();
			
			if(!(next.getBusLine() == line) && maxChanges == 0){
			}
			else if(next.secondEndpoint().getName() == endNode){
				ansStack.push(next.secondEndpoint());
				return true;
			}
			else if(next.secondEndpoint().getMark() == true){	
			}
			
			else{
				if(!(next.getBusLine() == line)){
					maxChanges--;
					changedchecker = true;
				}
				ansStack.push(next.secondEndpoint());
				boolean success = pathfinder(ansStack, next.secondEndpoint(), next.getBusLine());
				if(success){
					return true;
				}
			}
		}
		if(changedchecker){
			maxChanges++;
		}
		current.setMark(false);
		ansStack.pop();
		return false;
	}
	

}
