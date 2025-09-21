package desicionTreeCalc;

public class DecisionTree
{
	private DecisionTree yes, no;
	private String label;
	
	private int height, nodes;
	
    public DecisionTree(String root)
    {
    	this.label = root;
    	this.yes = null;
    	this.no = null;
    	
    	this.height = 1;
    	this.nodes = 1;
    }
    
    public boolean add(String label, String yesLabel, String noLabel)
    {
    	DecisionTree node = this.findNode(label, this);

    	if (node == null)
    	{
    		return false;
    	}

    	if (node.yes != null || node.no != null)
    	{
    		return false;
    	}

    	node.yes = new DecisionTree(yesLabel);
    	node.no  = new DecisionTree(noLabel);
    	
    	this.height += 1;
    	this.nodes  += 2;
        return true;
    }

    public int nodes(){
        return this.nodes;
    }
   
    public int height()
    {
        return this.height;
    }    

    public boolean isQuestion(String label)
    {
    	DecisionTree node = this.findNode(label, this);

    	if (node == null)
    	{
    		return false;
    	}
        return (node.yes != null && node.no != null);
    }

    public boolean isDecision(String label)
    {
    	DecisionTree node = this.findNode(label, this);
    	if (node == null)
    	{
    		return false;
    	}
        return (node.yes == null && node.no == null);
    }
    
    public String getLabel ()
    {
    	return this.label;
    }
    
    private DecisionTree findNode (String label, DecisionTree parent)
    {
    	if (parent == null)
    	{
    		return null;
    	}	
    	if (parent.getLabel().equals(label))
    	{
    		return parent;
    	}
    	return null;
    }
    
    /* === unused methods === */
    
    public boolean delete(String node){
        return true;
    }
    
    public DecisionTree eval(String [][] values){
        return null;
    }
    
    public boolean contains(String node){
        return false;
    } 
    
    public DecisionTree union (DecisionTree dt){
        return null;
    } 
    
    public boolean equals(DecisionTree dt){
        return false;
    }
    
    public boolean equals(Object g){
        return equals((DecisionTree)g);
    }
    
    //Trees are inside parentesis. The names are in lowercase. The childs must always be in yes no order.
    //For example, (a yes (b yes (c) no (d)) no (e)) 
    public String toString() {
      return "";
    }
}