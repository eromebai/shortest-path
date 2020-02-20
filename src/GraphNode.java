
public class GraphNode {
	private int Name;
	private boolean marked;
	
	public GraphNode(int name){
		Name = name;
		marked = false;
	}
	
	public void setMark(boolean mark){
		marked = mark;
	}
	
	public boolean getMark(){
		return marked; 
	}
	
	public int getName(){
		return Name;
	}

}
