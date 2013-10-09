/**
 * HuffmanTree.java
 * @author LindseyHeller--LWH2115
 */
import javax.swing.JPanel;

public class HuffmanTree extends JPanel
{
	private int[] frequencyArray;

	private String inputtedString;
	private char[] inputCharArray;
	private int numInserts;
	private HuffmanHeap theHeap; 
	protected HuffmanNode root;
	public String inputString = new String();
	private String[] codeArray;

	int xs;
	int ys;
	public int totalnodes = 0;
	public int maxheight = 0;


	public HuffmanTree(String inInput)
	{
		inputtedString = inInput;
		inputCharArray = inputtedString.toCharArray();
		frequencyArray = new int[127];
		codeArray = new String[127];
		findFrequencies();
		makeBinaryHeap();
	}

	public void findFrequencies()
	{
		for(int i = 0; i < inputCharArray.length; i++)
		{
			int index = (int)inputCharArray[i];

			if(frequencyArray[index] == 0)
			{
				numInserts++;
			}

			frequencyArray[index]++;
		}
	}

	public void makeBinaryHeap()
	{	
		theHeap = new HuffmanHeap(numInserts);

		for(int i = 0; i < frequencyArray.length; i++)
		{
			if(frequencyArray[i] != 0)
			{
				HuffmanNode newNode = new HuffmanNode(frequencyArray[i], Character.toString((char) i), i);
				theHeap.insert(newNode);
			}
		}

		while(!theHeap.hasOne())
		{
			HuffmanNode nodeMostMin = theHeap.deleteMin();
			HuffmanNode nodeMin = theHeap.deleteMin();
			int newWeight = nodeMostMin.weight + nodeMin.weight;

			HuffmanNode newInternalNode = new HuffmanNode(newWeight, nodeMin, nodeMostMin);
			theHeap.insert(newInternalNode);
		}

		root = theHeap.deleteMin();
		inorder_traversal(root, 0);
		maxheight = treeHeight(root); //finds tree height for scaling y axis

		getCodes(root, new String());
	}

	public void inorder_traversal(HuffmanNode t, int depth) 
	{ 
		if (t != null) 
		{
			inorder_traversal(t.leftChild, depth + 1); //add 1 to depth (y coordinate) 
			t.setxPosition(totalnodes++); //x coord is node number in inorder traversal
			t.setyPosition(depth); // mark y coord as depth
			inorder_traversal(t.rightChild, depth + 1);
		}
	}

	public int treeHeight(HuffmanNode t)
	{
		if(t==null) return -1;
		else return 1 + max(treeHeight(t.leftChild),treeHeight(t.rightChild));
	}

	public int max(int a, int b)
	{
		if(a>b) return a; else return b;
	}

	public void getCodes(HuffmanNode root, String code) 
	{
		if(root != null)
		{
			if(root.isCharacter) 
			{
				root.charCode = code;
				codeArray[root.asciiCode] = code;
			} 
			else if (!root.isCharacter)
			{
				code += "1";
				getCodes(root.leftChild, code);
				code = code.substring(0, code.length() - 1);

				code += "0";
				getCodes(root.rightChild, code);
				code = code.substring(0, code.length() - 1);
			}
		}
	}

	public String getOutput()
	{
		String output = "\n\n CHAR\tASCII\tFREQ\tH.CODE \n";

		for(int i = 0; i < frequencyArray.length; i++)
		{
			if(frequencyArray[i] != 0)
			{
				output += "  " + (char) i + "\t" + i + "\t" + frequencyArray[i] + "\t" + codeArray[i] + "\n";
			}
		}
		return output;
	}

	public String returnHuffman()
	{
		String huffmanString = "";
		for(int i = 0; i < inputCharArray.length; i++)
		{
			int index = (int)inputCharArray[i];
			huffmanString += codeArray[index];
		}
		return huffmanString;
	}

}
