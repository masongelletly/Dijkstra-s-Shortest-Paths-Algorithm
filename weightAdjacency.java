import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Class to represent the weight adjacency list of the graph. Works hand in hand with
 * graphObject and is created by graphObject
 * 
 * @author masongelletly
 */
public class weightAdjacency 
{
    // arrayList that contains LinkedLists that contains vertexObjects (basis of weightAdjacency list)
    public ArrayList<LinkedList<vertexObject>> list;

    /**
     * Constructor that initializes the above list field as well as all linkedLists that
     * reside within it
     * 
     * @param sizeP : size of arrayList
     */
    public weightAdjacency(int sizeP)
    {
        // initialize list
        list = new ArrayList<LinkedList<vertexObject>>(sizeP);
        
        // iterate through table and fill list with empty linkedLists 
        for (int idx = 0; idx < sizeP; idx++)
        {
            LinkedList<vertexObject> init = new LinkedList<vertexObject>();
            list.add(init);
        }
    }
    
    /**
     * simple getter for any linkedlist present 
     * 
     * @param index
     * @return the list at that location
     */
    public LinkedList<vertexObject> get(int index)
    {
        return list.get(index);
    }
    
    /**
     * adds a vertex to the list at the specified index 
     * 
     * @param index : the intended insert index
     * @param addMe : the vertexObject to be inserted
     */
    public void addVertex(int index, vertexObject addMe)
    {
        // the linked list where we are inserting the object
        LinkedList<vertexObject> insertHere = list.get(index);
        
        // add the object 
        insertHere.add(addMe);
    }

    /**
     * returns the string representation of the weightAdjacency object
     * 
     * format: 
     *      indice : linkedList of that indice
     *      
     * @return the string representation of the weightAdjacency object
     */
    public String toString()
    {
        // initialize stringbuilder object for ease of concatenation
        StringBuilder sb = new StringBuilder();
        
        // iterate through arrayList
        for (int idx = 0; idx < list.size(); idx++)
        {
            // append indice 
            sb.append(idx + " | ");
            
            // iterate through linkedList
            for (int jdx = 0; jdx < list.get(idx).size(); jdx++)
            {
                // append vertexObjects
                sb.append(list.get(idx).get(jdx).toString() + " ");
            }
            
            // newline character
            sb.append("\n");
        }
        
        // finale return
        return sb.toString();
    }
    
    /**
     * return string representation of formatted weight table
     */
    public String formattedToString()
    {
        // stringbuilder for concatenation
        StringBuilder sb = new StringBuilder();
        
        // header
        sb.append("  Node  | Successors\n");
        sb.append("  ---------------------------------------------------------------\n");
        
        // iterate arraylist
        for (int arrayIndex = 0; arrayIndex < list.size(); arrayIndex++)
        {
            // single digit
            if (arrayIndex < 10)
            {
                sb.append("      " + arrayIndex + " |");
            }
            // double digit
            else
            {
                sb.append("     " + arrayIndex + " |");
            }
            
            // iterate linked list
            for (int j = 0; j < list.get(arrayIndex).size(); j++)
            {
                String indent = "         ";
                int vertLength = list.get(arrayIndex).get(j).toString().length();

                
                
                // case 1: single digit weight & single digit vertice
                if (String.valueOf(list.get(arrayIndex).get(j).vertice).length() == 1
                    && String.valueOf(list.get(arrayIndex).get(j).edgeWeight).length() == 1)
                {
                    sb.append("    " + list.get(arrayIndex).get(j).toString() + " ");
                }
                // case 2: single digit weight & double digit vertice
                else if (String.valueOf(list.get(arrayIndex).get(j).vertice).length() == 2
                    && String.valueOf(list.get(arrayIndex).get(j).edgeWeight).length() == 1)
                {
                    sb.append("   " + list.get(arrayIndex).get(j).toString() + " ");
                }
                else
                {
                    sb.append(indent.substring(0, indent.length() - vertLength));
                    sb.append(list.get(arrayIndex).get(j).toString());
                }
            }
            
            sb.append("\n");
        }
        
        sb.append("\n  Start vertex is: ");
        
        sb.append("\n");
        
        return sb.toString();
    }
}
