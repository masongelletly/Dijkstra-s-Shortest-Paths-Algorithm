import java.util.ArrayList;

/**
 * Object to represent vertices and their important information
 * 
 * Stores the vertice number, current edge weight, total path weight, pathway list
 * 
 * @author masongelletly
 */
public class vertexObject 
{
    // vertice number
    public int vertice;
    
    // edge weight
    public int edgeWeight;
    
    // pathway arrayList
    public ArrayList<Integer> path;
    
    // distance (dijkstra table)
    public int distance = -1;
    
    // solved or unsolved
    public boolean solved = false;
   
    /**
     * Constructs the vertex object and initializes available information
     * 
     * @param verticeP : vertice number
     * @param edgeWeightP : edge weight of vertice
     */
    public vertexObject(int verticeP, int edgeWeightP)
    {
        // initialize fields (path, pathWeight ommitted due to in-house creation)
        vertice = verticeP;
        edgeWeight = edgeWeightP;
        
        // initialize path
        path = new ArrayList<Integer>(20);
    }
    
    /**
     * Getter method for vertice
     * 
     * @return the vertice field
     */
    public int getVertice() 
    {
        return vertice;
    }
    
    /**
     * Getter method for edgeWeight
     * 
     * @return the edgeWeight field
     */
    public int getEdgeWeight() 
    {
        return edgeWeight;
    }

    /**
     * Getter method for path
     * 
     * @return the path field
     */
    public ArrayList<Integer> getPath() 
    {
        return path;
    }
    
    /**
     * Append an integer to path field
     * 
     * @param appendNumber : the number to be appended
     */
    public void appendPath(int appendNumber)
    {
        path.add(appendNumber);
    }
    
    /**
     * Clear the path
     */
    public void clearPath()
    {
        path.clear();
    }
    
    /**
     * return string representation of the vertexObject
     */
    public String toString()
    {
        return (vertice + ": " + edgeWeight);
    }
    
    /**
     * set the distance of the object
     * 
     * @param newDistance : the new distance
     */
    public void setDistance(int newDistance)
    {
        // update field
        distance = newDistance;
    }
    
    /**
     * return the distance field
     */
    public int getDistance()
    {
        return distance;
    }
    
    /**
     * changes the object to "solved"
     */
    public void solved()
    {
        solved = true;
    }

    /**
     * toString for path
     */
    public String vertexPathToString()
    {
        StringBuilder sb = new StringBuilder();
        
        String indent = "    ";
        
        // iterate path
        for (int i = 1; i < path.size(); i++)
        {
            if (path.size() == 0)
            {
                return("");
            }
            
            // init pathlength
            int pathLength = String.valueOf(path.get(i)).length();
            
            sb.append(indent.substring(0, indent.length() - pathLength));
            sb.append(path.get(i));
        }     

        // append final 
        int pathLength = String.valueOf(this.vertice).length();
        sb.append(indent.substring(0, indent.length() - pathLength));
        sb.append(this.vertice);
        
        // final return
        return sb.toString();
    }
}
