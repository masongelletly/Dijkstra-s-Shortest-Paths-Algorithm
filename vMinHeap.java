**
 * A minHeap implementation meant to assist the dijstraTable class in efficiently finding
 * the lowest distance
 * 
 * minHeap is not generic, instead filled with vertexObject objects
 * 
 * it should be mentioned that the only real importance of this structure is to accurately 
 * update the root
 * 
 * @author masongelletly
 */
public class vMinHeap 
{
     // allow to access through all methods
     public vertexObject[] Heap;
     
     // private fields
     private int size;
     private int capacity;
  
     // Constructor of this class
     public vMinHeap(int capacityP)
     {
         // intialize givens
         capacity = capacityP;
         size = 0;
  
         Heap = new vertexObject[capacity + 1];
         
         // manufactured root (unused) root is essentially [1]
         vertexObject minVert = new vertexObject(-1, -1);
         Heap[0] = minVert;
     }
     
     /**
      * return the root
      * 
      */
     public vertexObject root()
     {
         return Heap[1];
     }
     
     /*
      * return the alternative root
      */
     public vertexObject alternativeRoot()
     {
         return Heap[2];
     }
  
     /**
      * returns the parent indice of the given indice
      * 
      * @param idx
      * @return
      */
     private int parent(int idx) 
     { 
         return (idx / 2);      
     }

     /**
      * helper to swap two nodes. does so for balancing issues
      * 
      * @param orig
      * @param spos
      */
     private void swap(int orig, int newly)
     {
  
         vertexObject tmp;
         tmp = Heap[orig];
  
         Heap[orig] = Heap[newly];
         Heap[newly] = tmp;
     }
  
     /**
      * handles insertion into heap
      * 
      * @param element
      */
     public void insert(vertexObject element)
     {
         // check if capable of insert
         if (size >= capacity) 
         {
             return;
         }
         
         // insert into underlying array
         Heap[++size] = element;
         int current = size;
  
         // rebalancing the root
         while (Heap[current].getDistance() < Heap[parent(current)].getDistance()) 
         {
             swap(current, parent(current));
             current = parent(current);
         }
     }
}
