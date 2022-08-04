public class SSAD 
{   
    // allow all methods access to output file
    public static RandomAccessFile output;
    
    /**
     * main method to execute the behavior of SSAD
     * 
     * args[0] : input file name
     * args[1] : output file name
     * 
     * @param args : command parameters
     * @throws IOException 
     */
    public static void main(String args[]) throws IOException
    {
        // initialize graph field
        graphObject graph = new graphObject(args[0]);
        
        // initialize output file (must first create file)
        File RAFMaker = new File(args[1]);
        output = new RandomAccessFile(RAFMaker, "rw");
        output.setLength(0);
        
        // utilize SSAD function for calculations
        String rawPathInfo = ssad(graph);
        
        System.out.print(graph.getWeightList().formattedToString());
        System.out.print(rawPathInfo);
        
        // pass raw information to function to handle output
        writeTopToLog(graph.getWeightList().formattedToString());
        writeBottomToLog(rawPathInfo);
    }
    
    /**
     * the Dijkstra's algorithm function
     * 
     * lowercase ssad in order to prevent constructor confusion
     * 
     * @return : a String representation of pathways 
     */
    private static String ssad(graphObject graph)
    {
        // grab weight adjacency object
        weightAdjacency weightList = graph.getWeightList();
        
        // create dijkstraTable
        dijkstraTable dijkstraTable = new dijkstraTable(graph.numberVertices);
        
        // first pass set starting vertice to 0 
        dijkstraTable.setDistance(graph.startingVertice, 0);
        
        // initialize currVerticeIndex
        int currVerticeIndex = graph.startingVertice;
        
        // while all vertices are not solved 
        while (!dijkstraTable.fullySolved())
        {
            // make a new heap (clear if full)
            vMinHeap heap = new vMinHeap(graph.numberVertices);
            
            // load heap with current dijkstraTable
            for (int idx = 0; idx < dijkstraTable.table.length; idx++)
            {
                // insert into heap if unsolved
                if (dijkstraTable.table[idx].solved == false && dijkstraTable.table[idx].distance > -1) 
                {
                    // the getVertice() of the object is its indice in the dijkstra table
                    heap.insert(dijkstraTable.table[idx]);
                }
            }

            /**
             * this is to handle infinities. if the root is null and all are unsolved, then we know that
             * the table is finished aside from those indices that retain distance -1
             * 
             * 
             */
            if (heap.root() == null)
            {
                return dijkstraTable.formattedToString();
            }                   
            
            // keep
            currVerticeIndex = heap.root().vertice;

            // mark as solved
            dijkstraTable.table[currVerticeIndex].solved();
            
            // get list of successors
            LinkedList<vertexObject> successors = weightList.get(currVerticeIndex);
            
            // update table with successors
            // iterating linked list
            for (int idx = 0; idx < successors.size(); idx++)
            {
                // For every adjacent vertex v, if the sum of distance value of u (from source) and weight of 
                // edge u-v, is less than the distance value of v, then update the distance value of v.
                
                // current vertex object being dealt with // (7 : 97)
                vertexObject currentVertexObject = successors.get(idx);
                
                // if (distance table to current vertice + edge weight to new vertice) < (distance in table for new vertice)
                int currentVerticeDist = dijkstraTable.table[currVerticeIndex].distance;
                int edge = currentVertexObject.edgeWeight;
                int newVerticeDist = dijkstraTable.table[currentVertexObject.vertice].distance;
                
                // is it valid? make it big.
                if (newVerticeDist < 0)
                {
                    newVerticeDist = 999;
                }
                
                // need to update distance to currentVerticeDist + edge
                if ((currentVerticeDist + edge) < newVerticeDist)
                {
                    // update distance
                    dijkstraTable.table[currentVertexObject.vertice].setDistance(currentVerticeDist + edge); 
                    
                    // ---------------------------------------------
                    // currVerticeIndex = recently solved indice
                    // currentVertexObject = vertexObject in weightlist
                    
                    // simplification
                    vertexObject needPath = dijkstraTable.table[currentVertexObject.vertice];
                    
                    // clear current path
                    needPath.clearPath();
                    
                    // iterate through last vertex vertice's path
                    for (int jdx = 0; jdx < dijkstraTable.table[currVerticeIndex].path.size(); jdx++)
                    {
                        // append the last vertex vertice's path
                        needPath.appendPath(dijkstraTable.table[currVerticeIndex].path.get(jdx));
                    }
                        
                    // append the last vertex vertice. B in B-> A
                    needPath.appendPath(dijkstraTable.table[currVerticeIndex].vertice);  
                    
                }
            }         
        }
        
        // return the string representation of the table
        return dijkstraTable.formattedToString();
    }
    
    /**
     * a helper function that takes in the raw representation from SSAD
     * and writes to output
     * 
     * @param raw : the raw representation
     * @throws IOException 
     */
    private static void writeBottomToLog(String raw) throws IOException
    {
        // create scanner
        Scanner scanner = new Scanner(raw);
        
        // parse through raw line by line and add to output
        while (scanner.hasNextLine())
        {
            // current line
            String nextLine = scanner.nextLine();
            
            // write line to output
            output.writeBytes(nextLine + "\n");
        }
       
    }
    
    /**
     * a helper function that takes in the raw representation from SSAD
     * and writes to output
     * 
     * @param raw : the raw representation
     * @throws IOException 
     */
    private static void writeTopToLog(String raw) throws IOException
    {
        // create scanner
        Scanner scanner = new Scanner(raw);
        
        // parse through raw line by line and add to output
        while (scanner.hasNextLine())
        {
            // current line
            String nextLine = scanner.nextLine();
            
            // write line to output
            output.writeBytes(nextLine + "\n");
        }
        
        output.writeBytes("\n");
    }
}
