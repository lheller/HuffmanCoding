/**
 * From class notes by Professor Allen--
 * changed to implement HuffmanNodes.
 * 
 *Lindsey Heller -- LWH2115
 */
//This program generates a set of random integers and inserts
//them into a min-heap and then returns the numbers in order

public class HuffmanHeap
{
	private int currentSize; // Number of elements in heap
	private HuffmanNode[ ] array; // The heap array
	
	public HuffmanHeap( int capacity )
	{
		currentSize = 0;
		array = new HuffmanNode[ capacity + 1 ];
	}
	public void insert( HuffmanNode x )
	{
		//percolate up
		int hole = ++currentSize;
		for( ; hole > 1 && x.compareTo( array[ hole / 2 ] ) < 0; hole /= 2 )
			array[ hole ] = array[ hole / 2 ];
		array[ hole ] = x;
	}
	
	public HuffmanNode findMin( )
	{
		if( isEmpty( ) )
			return null;
		return array[1];
	}
	
	public HuffmanNode deleteMin( )
	{
		if( isEmpty( ) )
			return null;
		HuffmanNode minItem = findMin( );
		array[ 1 ] = array[ currentSize-- ];
		percolateDown( 1 );
		return minItem;
	}
	
	public void buildHeap( )
	{
		for( int i = currentSize / 2; i > 0; i-- )
			percolateDown( i );
	}
	
	public boolean isEmpty( )
	{ 
		return currentSize == 0; 
	}
	
	public boolean hasOne()
	{
		return currentSize == 1;
	}
	
	private void percolateDown( int hole )
	{
		int child;
		HuffmanNode tmp = array[ hole ];
		for( ; hole * 2 <= currentSize; hole = child ) {
			child = hole * 2;
			if( child != currentSize &&
					array[ child + 1 ].compareTo( array[ child ] ) < 0 )
				child++;
			if( array[ child ].compareTo( tmp ) < 0 )
				array[ hole ] = array[ child ];
			else break;
		}
		array[ hole ] = tmp;
	}
	
}
