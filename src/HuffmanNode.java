/**
 * HuffmanNode.java
 * @author LindseyHeller--LWH2115
 */
import java.awt.geom.Ellipse2D;

public class HuffmanNode implements Comparable<HuffmanNode>
{
	protected int weight, asciiCode;
	protected String character, charCode;
	protected HuffmanNode leftChild, rightChild;
	protected boolean isCharacter;
	protected int xPosition, yPosition;
	protected Ellipse2D.Double boundingOval;

	//Constructs a new Character Node
	public HuffmanNode(int inWeight, String inCharacter, int inAsciiCode)
	{
		weight = inWeight;
		character = inCharacter;
		asciiCode = inAsciiCode;
		isCharacter = true;
	}
	
	//Constructs a new Internal Node
	public HuffmanNode(int inWeight, HuffmanNode left, HuffmanNode right)
	{
		weight = inWeight;
		leftChild = left;
		rightChild = right;
		character = "-";
		isCharacter = false;
	}
	
	public void setBoundingOval(int dx, int dy, double width, double height)
	{
		boundingOval = new Ellipse2D.Double((dx - 10) , (dy - 20), width, height);
	}

	public int compareTo(HuffmanNode inNode) 
	{
		if (this.weight < inNode.weight) return -1;
		else if (this.weight > inNode.weight)	return 1;
		return 0;
	}
	
	public String toString()
	{
		String printedNode = character + " : " + weight;
		return printedNode;
	}
	
	public int getxPosition() 
	{
		return xPosition;
	}

	public void setxPosition(int xPosition) 
	{
		this.xPosition = xPosition;
	}

	public int getyPosition() 
	{
		return yPosition;
	}

	public void setyPosition(int yPosition) 
	{
		this.yPosition = yPosition;
	}

}
