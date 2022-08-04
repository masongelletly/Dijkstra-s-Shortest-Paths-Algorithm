import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Object to represent the graph information given from main's input
 * 
 * @author masongelletly
 */
public class graphObject 
{
    // field for starting vertice and number of vertices
    public int startingVertice = -1;
    public int numberVertices = -2;
    
    // field for weightAdjacency list
    public weightAdjacency list;
    
    /**
     * Constructor for graph object that intakes the command line parameter 
     * 
     * @param input
     * @throws IOException
     */
    public graphObject(String input) throws IOException
    {    
        // create file representing input
        File inputFile = new File(input);
        
        // create scanner for help with parsing file
        Scanner inputScanner = new Scanner(inputFile);
        
        // create RAF representing input (for parsing use)
        RandomAccessFile parse = new RandomAccessFile(inputFile, "rw");
        
        // BEGIN PARSING ----------------------------------------------
        // grab header information and update fields
        numberVertices = Integer.parseInt(parse.readLine().split(":")[1].replaceAll(" ", ""));
        startingVertice = Integer.parseInt(parse.readLine().split(":")[1].replaceAll(" ", ""));
        
        // initialize list
        list = new weightAdjacency(numberVertices);
  
        parse.readLine(); // skip blank line
        parse.readLine(); // skip x axis header
        parse.readLine(); // skip "----------"
        
        inputScanner.nextLine(); // number of vertices
        inputScanner.nextLine(); // starting vertice
        inputScanner.nextLine(); // blank line
        inputScanner.nextLine(); // x axis header
        inputScanner.nextLine(); // "----------"
        
        // begin parsing the matrix itself 
        while (inputScanner.hasNextLine())
        {
            // current line being read
            String currentLine = parse.readLine();

            // update current vertice 
            int currVertice = Integer.parseInt(currentLine.split("\\|")[0].replaceAll(" ", ""));
            
            // build list by splitting currLine
            ArrayList<Integer> listOfWeights= new ArrayList<Integer>(numberVertices - 1);
            
            // clear list
            listOfWeights.clear();
            
            // create character array of currLine
            char[] lineButChar = currentLine.toCharArray();
                     
            //-------------------------------------------------
            for (int j = 6; j < lineButChar.length; j++)
            {                
                // zero. no validation required
                if (lineButChar[j] == '0')
                {
                    listOfWeights.add(0);
                }
                
                // nonzero. must further check if double digit
                if (lineButChar[j] == '1' ||
                    lineButChar[j] == '2' ||
                    lineButChar[j] == '3' ||
                    lineButChar[j] == '4' ||
                    lineButChar[j] == '5' ||
                    lineButChar[j] == '6' ||
                    lineButChar[j] == '7' ||
                    lineButChar[j] == '8' ||
                    lineButChar[j] == '9'
                    )
                {
                    // check if last in line
                    if (j == lineButChar.length - 1)
                    {
                        listOfWeights.add(Character.getNumericValue(lineButChar[j]));
                    }
                    
                    // one digit number
                    else if (lineButChar[j + 1] == ' ')
                    {
                        listOfWeights.add(Character.getNumericValue(lineButChar[j]));
                    }
                    
                    // two digit number
                    else
                    {
                        // combine digits
                        StringBuilder sb = new StringBuilder();
                        sb.append(lineButChar[j]);
                        sb.append(lineButChar[j + 1]);
 
                        
                        // add number
                        listOfWeights.add(Integer.parseInt(sb.toString()));
                        
                        // increment past second digit
                        j = j + 1;
                    }
                    
                    
                }
                
                // ------------------------------------------------
                // if ready for object creation
                if (listOfWeights.size() == numberVertices)
                {
                    // iterate through list
                    for (int p = 0; p < listOfWeights.size(); p++)
                    {
                        // check for validity (nonzero)
                        if (listOfWeights.get(p) != 0)
                        {
                            // create vertexObject
                            vertexObject insert = new vertexObject(p, listOfWeights.get(p));
                            // add object to weightAdjacency
                            list.addVertex(currVertice, insert);
                        }
                    }
                }
            }
            
            // iterate scanner
            inputScanner.nextLine();
        }
        
        // go away sometime
        // System.out.println(list.toString());
        
        // close for anti-data leakage
        parse.close();
        inputScanner.close();
    }     
    
    /**
     * getter method for the weight adjacency list created by
     * the graphObject's constructor
     * 
     * @return list : the weight adjacency list
     */
    public weightAdjacency getWeightList()
    {
        return list;
    }
}
