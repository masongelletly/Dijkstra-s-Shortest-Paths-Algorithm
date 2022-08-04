/**
 * Class to represent the dijkstra table utilized during SSAD workings
 * 
 * @author masongelletly
 */
public class dijkstraTable 
{
    // allow all methods access to base array
    vertexObject[] table;
    
    /**
     * Constructs the dijkstra table and initializes the underlying array
     * 
     * inf is represented as a 999 distance (which all vertexObjects begin with)
     * 
     * @param size : the intended size of the table
     */
    public dijkstraTable(int size)
    {
        // intialize table
        table = new vertexObject[size];

        // fill table with vertexObjects
        for (int idx = 0; idx < size; idx++)
        {
            vertexObject beginning = new vertexObject(idx , -1);  
            table[idx] = beginning;
        }
    }
    
    /**
     * Update the distance at a specific indice
     * 
     * @param index : the specific indice
     * @param newDistance : the new distance
     */
    public void setDistance(int index, int newDistance)
    {
        // grab object at that indice
        vertexObject vert = table[index];
        
        // update distance
        vert.setDistance(newDistance);
    }
    
    /**
     * Getter method for the distance at a specific indice
     * 
     * @param index : the specific indice
     */
    public int getDistance(int index)
    {
        return table[index].getDistance();
    }
    
    /**
     * Getter method for the underlying table. Used in order to keep minHeap updated
     */
    public vertexObject[] getTable()
    {
        return table;
    }
    
    /**
     * Method that tells if all elements of the table are solved
     */
    public boolean fullySolved()
    {
        // iterate the table and check if solved is true for all
        for (int idx = 0; idx < table.length; idx++)
        {
            // if unsolved
            if (!table[idx].solved)
            {
                return false;
            }
        }
        
        // no falses found
        return true;
    }
    
    /**
     * Creates and returns a string representation of the table
     * 
     * @return : a string representation of the table
     */
    public String toString()
    {
        // initialize StringBuilder for ease of concatenation
        StringBuilder sb = new StringBuilder();
        
        // iterate through table
        for (int idx = 0; idx < table.length; idx++)
        {
            // check for infinity
            if (table[idx].getDistance() == -1)
            {
                sb.append("[inf] ");
            }
            else
            {
                // add surrounding format and distance for that indice
                sb.append("[" + table[idx].getDistance() + "] ");
            }
        }
        
        // return string
        return sb.toString();
    }
    
    /**
     * print the paths of all of the objects in table
     */
    public String pathToString()
    {
        // initialize stringbuilder for ease of concatenation
        StringBuilder sb = new StringBuilder();
        
        // iterate through table
        for (int idx = 0; idx < table.length; idx++)
        {
            table[idx].appendPath(idx);
            sb.append(idx + " : " + table[idx].vertexPathToString() + "\n");
        }
        
        // finale return
        return sb.toString();
    }
    
    /**
     * returns the properly formatted string representation of the table and its pathways
     */
    public String formattedToString()
    {
        // stringbuilder to assist with concatenation
        StringBuilder sb = new StringBuilder();
        
        // header information
        sb.append("\t  " + "Total\n");
        sb.append("  Dest | Weight | Path\n");
        sb.append("  ---------------------------------------------\n");
        
        // iterate through table
        for (int idx = 0; idx < table.length; idx++)
        {           
            // append dest
            //
            // case 1: single digit
            if (String.valueOf(idx).length() == 1)
            {
                sb.append("     " + idx + " |");
            }
            else
            // case 2: double digit
            {
                sb.append("    " + idx + " |");
            }
            
            // append weight (distance)
            //
            // case 1: single digit
            if (String.valueOf(table[idx].distance).length() == 1)
            {
                sb.append("      " + table[idx].distance + " |");
            }
            // case 2: double digit
            else if (String.valueOf(table[idx].distance).length() == 2)
            {
                // check for infinity
                if (table[idx].getDistance() == -1)
                {
                    sb.append("    inf |");
                }
                else
                {
                    sb.append("     " + table[idx].distance + " |");
                }
            }
            // case 3: triple digit
            else
            {
                sb.append("    " + table[idx].distance + " |");
            }
             
            // append path
            //  
            // case 1: path is empty
            if (table[idx].path.size() == 0)
            {
                sb.append("\n");
            }           
            else
            {
                sb.append(table[idx].vertexPathToString() + "\n");
            }
        }
        
        //finale return
        return sb.toString();
    }
}
