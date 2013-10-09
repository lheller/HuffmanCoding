/**
 * HuffmanDisplayPanel.java
 * @author LindseyHeller--LWH2115
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class HuffmanDisplayPanel extends JPanel
{
	HuffmanTree t;
	int xs;
	int ys;

	Font myFont;
	private Rectangle2D nodeStringBounds;
	private int topOfChildX, topOfChildY;
	private int bottomOfOvalX, bottomOfOvalY;

	public HuffmanDisplayPanel(HuffmanTree t)
	{
		this.t = t; // allows dispay routines to access the tree
		setBackground(Color.white);
		setForeground(Color.black);
	}

	protected void paintComponent(Graphics graphic) 
	{
		graphic.setColor(getBackground()); //colors the window
		graphic.fillRect(0, 0, getWidth(), getHeight());
		graphic.setColor(getForeground()); //set color and fonts

		xs=10;   //where to start printing on the panel
		ys=10;
		int start=0;
		/* print input string on panel, 150 chars per line
		   if string longer than 23 lines don't print 		*/
		if(t.inputString.length()<23*150)
		{
			while((t.inputString.length()-start)>150)
			{
				graphic.drawString(t.inputString.substring(start,start+150),xs,ys);        
				start+=151;
				ys+=15;
			}
			graphic.drawString(t.inputString.substring(start,t.inputString.length()),xs,ys);
		}
		myFont = new Font("Cambria",Font.BOLD,15); //bigger font for tree
		graphic.setFont(myFont);

		this.drawTree(graphic, t.root); // draw the tree
		revalidate(); //update the component panel
	}

	public void drawTree(Graphics graphic, HuffmanNode root) 
	{//actually draws the tree
		int dx, dy, dx2, dy2;
		int SCREEN_WIDTH = 900; //screen size for panel
		int SCREEN_HEIGHT = 600;
		int XSCALE, YSCALE;  
		XSCALE=SCREEN_WIDTH/t.totalnodes; //scale x by total nodes in tree

		if (root != null) 
		{ // inorder traversal to draw each node
			drawTree(graphic, root.leftChild); // do left side of inorder traversal 
			drawTree(graphic, root.rightChild); //now do right side of inorder traversal 
			String nodeString = root.toString(); //get the word at this node
			Graphics2D graphic2 = (Graphics2D) graphic;
			FontRenderContext context = graphic2.getFontRenderContext();
			nodeStringBounds = myFont.getStringBounds(nodeString, context);
			dx = root.getxPosition() * XSCALE + 15; // get x,y coords., and scale them 
			dy = (int) (root.getyPosition() * (nodeStringBounds.getHeight() + 12) + 50);
			graphic.drawString(nodeString, dx, dy); // draws the word
			if(root.isCharacter)
			{
				String codeString = root.charCode;
				int codePosX = (int) (dx + nodeStringBounds.getWidth()) - 30;
				int codePosY = (int) (dy + nodeStringBounds.getHeight()) + 8;
				graphic.drawString(codeString, codePosX, codePosY);
			}
			root.setBoundingOval(dx , dy, nodeStringBounds.getWidth() + 15, nodeStringBounds.getHeight() + 15);
			Ellipse2D.Double oval = root.boundingOval;
			graphic2.draw(oval);
			bottomOfOvalX = (int) (oval.getX() + (oval.getWidth()/2));
			bottomOfOvalY = (int) (oval.getY() + (oval.getHeight()));

			// this draws the lines from a node to its children, if any
			if(root.leftChild!=null)
			{ //draws the line to left child if it exists
				Ellipse2D.Double childOval = root.leftChild.boundingOval;
				topOfChildX = (int) (childOval.getX() + (childOval.getWidth()/2));
				topOfChildY = (int) (childOval.getY());
				graphic.drawLine(bottomOfOvalX,bottomOfOvalY,topOfChildX,topOfChildY);
			}
			
			if(root.rightChild !=null) //draws the line to right child if it exists
			{
				Ellipse2D.Double childOval = root.rightChild.boundingOval;
				topOfChildX = (int) (childOval.getX() + (childOval.getWidth()/2));
				topOfChildY = (int) (childOval.getY());
				graphic.drawLine(bottomOfOvalX, bottomOfOvalY,topOfChildX,topOfChildY);
			}

		}
	}

	
}

