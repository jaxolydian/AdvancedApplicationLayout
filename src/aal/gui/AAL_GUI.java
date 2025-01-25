package aal.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class AAL_GUI implements MouseListener, MouseMotionListener, ComponentListener
{
	boolean mouseDown = false;
	JFrame frame = new JFrame();
	
	JPanel holding_West = new JPanel(new GridBagLayout()); // main holder
	JPanel panelWest = new JPanel(); // add panels to this
	JPanel panelWest_resizeRight = new JPanel();
	
	JPanel holding_East = new JPanel(new GridBagLayout()); // main holder
	JPanel panelEast = new JPanel(); // add panels to this
	JPanel panelEast_resizeLeft = new JPanel();
	
	JTextArea newText = new JTextArea("This is westConst layout test");
	JTextArea newText2 = new JTextArea("This is eastConst layout test");
	
	JPanel panelNorth = new JPanel();
	JPanel panelSouth = new JPanel();
	JPanel panelCenter = new JPanel();
	GridBagConstraints westConst = new GridBagConstraints();
	GridBagConstraints eastConst = new GridBagConstraints();

	public AAL_GUI()
	{
		westConst.insets = new Insets(5,5,5,5);
		westConst.gridx = 0;
		westConst.gridy = 0;
		westConst.ipadx = 0;
		westConst.ipady = 0;
		
		holding_West.add(panelWest, westConst);
		westConst.gridx = 1;
		westConst.insets = new Insets(0,5,0,0);
		holding_West.add(panelWest_resizeRight, westConst);
		panelWest.add(newText, BorderLayout.CENTER);
		
		eastConst.insets = new Insets(5,0,5,5);
		eastConst.gridx = 0;
		eastConst.gridy = 0;
		eastConst.ipadx = 0;
		eastConst.ipady = 0;
		
		holding_East.add(panelEast_resizeLeft, eastConst);
		eastConst.gridx = 1;
		eastConst.insets = new Insets(5,5,5,5);
		holding_East.add(panelEast, eastConst);
		panelEast.add(newText2, BorderLayout.CENTER);
		
		frame.setTitle("testing");
		frame.add(holding_West, BorderLayout.WEST);
		frame.add(holding_East, BorderLayout.EAST);
		frame.add(panelNorth, BorderLayout.NORTH);
		frame.add(panelSouth, BorderLayout.SOUTH);
		frame.add(panelCenter, BorderLayout.CENTER);
		
		frame.addComponentListener(this);
		
		newText.setLineWrap(true);
		newText.setMinimumSize(new Dimension(160, 720));
		newText.setMaximumSize(new Dimension(1080, 720));
		newText.setPreferredSize(new Dimension(160, 720));
		newText2.setLineWrap(true);
		newText2.setMinimumSize(new Dimension(160, 720));
		newText2.setMaximumSize(new Dimension(1080, 720));
		newText2.setPreferredSize(new Dimension(160, 720));
		
		panelWest.setMinimumSize(new Dimension(160, 720));
		panelWest.setMaximumSize(new Dimension(1760, 1080));
		panelWest.setPreferredSize(new Dimension(160, 720));
		panelEast.setMinimumSize(new Dimension(160, 720));
		panelEast.setMaximumSize(new Dimension(1760, 1080));
		panelEast.setPreferredSize(new Dimension(160, 720));
		
		panelWest.setMaximumSize(new Dimension(panelWest.getMaximumSize().width - 160, panelWest.getMaximumSize().height));
		panelEast.setMaximumSize(new Dimension(panelEast.getMaximumSize().width - 160, panelEast.getMaximumSize().height));
		
		panelWest_resizeRight.setMinimumSize(new Dimension(10,720));
		panelWest_resizeRight.setPreferredSize(new Dimension(10,720));
		panelWest_resizeRight.setMaximumSize(new Dimension(10,720));
		panelWest_resizeRight.addMouseMotionListener(this);
		panelWest_resizeRight.addMouseListener(this);
		panelEast_resizeLeft.setMinimumSize(new Dimension(10,720));
		panelEast_resizeLeft.setPreferredSize(new Dimension(10,720));
		panelEast_resizeLeft.setMaximumSize(new Dimension(10,720));
		panelEast_resizeLeft.addMouseMotionListener(this);
		panelEast_resizeLeft.addMouseListener(this);
		
		holding_West.setBorder(BorderFactory.createLineBorder(Color.black));
		holding_East.setBorder(BorderFactory.createLineBorder(Color.black));
		holding_West.setBackground(Color.red);
		holding_East.setBackground(Color.yellow);
		panelNorth.setBackground(Color.green);
		panelSouth.setBackground(Color.cyan);
		panelCenter.setBackground(Color.magenta);
		panelWest_resizeRight.setBackground(Color.white);
		
		
		
		frame.setMinimumSize(new Dimension((160*2)+65, 720));
		frame.setMaximumSize(new Dimension(1920, 1080));
		frame.setSize(1920, 1080);
		frame.setVisible(true);
		/* UpdateUnit(); */
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	public void UpdateUnit() { // unused for now
		try
		{
			TimeUnit.NANOSECONDS.sleep(0);
			 if (panelWest.getPreferredSize().width < 160) {
				 panelWest.setPreferredSize(new Dimension(160,panelWest.getMinimumSize().height));
				 panelEast.setPreferredSize(new Dimension(frame.getWidth()-160,panelEast.getMinimumSize().height));
			 } else if (panelEast.getPreferredSize().width < 160) {
				 panelEast.setPreferredSize(new Dimension(160,panelEast.getMinimumSize().height));
				panelWest.setPreferredSize(new Dimension(frame.getWidth()-160,panelWest.getMinimumSize().height));
			 }
			 panelWest.revalidate();
			 panelWest.repaint();
			 panelEast.revalidate();
			 panelEast.repaint();
			 holding_West.revalidate();
			 holding_West.repaint();
			 holding_East.revalidate();
			 holding_East.repaint();
			 UpdateUnit();
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e)
	{
		mouseDown = true;
		if (e.getSource().equals(panelWest_resizeRight)) {
			panelWest_resizeRight.setBackground(Color.white.darker());	
			System.out.println("Success");
		} else if (e.getSource().equals(panelEast_resizeLeft)) {
			panelEast_resizeLeft.setBackground(Color.white.darker());	
			System.out.println("Success");
		}
		
	}
	@Override
	public void mouseReleased(MouseEvent e)
	{
		mouseDown = false;
		if (e.getSource().equals(panelWest_resizeRight)) {
			panelWest_resizeRight.setBackground(Color.white);
		} else if (e.getSource().equals(panelEast_resizeLeft)) {
			panelEast_resizeLeft.setBackground(Color.white);
			panelEast.setMinimumSize(new Dimension(160, 720));
			
		}
		
		
		
	}
	@Override
	public void mouseEntered(MouseEvent e)
	{
		
		
		
	}
	@Override
	public void mouseExited(MouseEvent e)
	{
		
		if (e.getSource().equals(frame)) {
			// the main checking code
			/*
			 * if (panelWest.getPreferredSize().width < 160) {
			 * panelWest.setPreferredSize(new
			 * Dimension(160,panelWest.getMinimumSize().height)); if
			 * (panelEast.getPreferredSize().width > frame.getWidth() - 160) {
			 * panelEast.setPreferredSize(new
			 * Dimension(160,panelEast.getMinimumSize().height)); } } else if
			 * (panelEast.getPreferredSize().width < 160) { panelEast.setPreferredSize(new
			 * Dimension(160,panelEast.getMinimumSize().height)); if
			 * (panelWest.getPreferredSize().width > frame.getWidth() - 160) {
			 * panelWest.setPreferredSize(new
			 * Dimension(160,panelWest.getMinimumSize().height)); } } if
			 * (panelWest.getPreferredSize().width > frame.getWidth() - 160) {
			 * panelWest.setPreferredSize(new Dimension(frame.getWidth() -
			 * 160,panelWest.getMinimumSize().height)); } else if
			 * (panelEast.getPreferredSize().width > frame.getWidth() - 160) {
			 * panelEast.setPreferredSize(new Dimension(frame.getWidth() -
			 * 160,panelEast.getMinimumSize().height)); }
			 */
			// the main checking code -- we need a constant updater. use Time() library or something idk
		}
		
	}
	@Override
	public void mouseDragged(MouseEvent e)
	{
		Point mousePoint = frame.getMousePosition();
		int x = mousePoint.x;
		int y = mousePoint.y;
		 panelWest.setMinimumSize(new Dimension(160, 720));
		 panelEast.setMinimumSize(new Dimension(160, 720));
		 holding_West.setMinimumSize(new Dimension(200, 720));
		 holding_East.setMinimumSize(new Dimension(200, 720));
		/*

		 * panelWest.setMaximumSize(new Dimension(1920 - holding_East.getWidth() + 25,
		 * 720)); holding_West.setMaximumSize(new Dimension(1920 -
		 * holding_East.getWidth(), holding_West.getHeight()));
		 * panelEast.setMaximumSize(new Dimension(1920 - holding_West.getWidth() + 25,
		 * 720)); holding_East.setMaximumSize(new Dimension(1920 -
		 * holding_West.getWidth(), holding_East.getHeight()));
		 */
		// the main checking code
		 if (panelWest.getPreferredSize().width < 160) {
			 panelWest.setPreferredSize(new Dimension(160,panelWest.getMinimumSize().height));
			 panelEast.setPreferredSize(new Dimension(frame.getWidth()-160,panelEast.getMinimumSize().height));
			 System.out.println("Condition 1");
		 } 
		 if (panelEast.getPreferredSize().width < 160) {
			 panelEast.setPreferredSize(new Dimension(160,panelEast.getMinimumSize().height));
			panelWest.setPreferredSize(new Dimension(frame.getWidth()-160,panelWest.getMinimumSize().height));
			System.out.println("Condition 2");
		 }
		 if (panelWest.getPreferredSize().width > frame.getWidth()-160) {
			 panelWest.setPreferredSize(new Dimension(frame.getWidth()-160,panelWest.getMinimumSize().height));
			 System.out.println("Condition 3");
		 }
		 if (panelEast.getPreferredSize().width > frame.getWidth()-160) {
			 panelEast.setPreferredSize(new Dimension(frame.getWidth()-160,panelEast.getMinimumSize().height));
			 System.out.println("Condition 4");
		 }
		 
		 
		// the main checking code
		 
		if (e.getSource().equals(panelWest_resizeRight)) {
			
			panelWest.setPreferredSize(new Dimension((int) (x) - 25,panelWest.getMinimumSize().height));
			if (panelWest.getPreferredSize().width < 160) {
				panelWest.setPreferredSize(new Dimension(160,panelWest.getMinimumSize().height));
				/* panelWest.getPreferredSize().width > 1920 - holding_East.getWidth()) */
			} else if (panelWest.getPreferredSize().width > frame.getWidth() - holding_East.getWidth() - 40) {
				if (panelEast.getPreferredSize().width > 160) {
					panelWest.setPreferredSize(new Dimension((int) (x) - 25,panelWest.getMinimumSize().height));
					panelEast.setPreferredSize(new Dimension(frame.getWidth() - holding_West.getWidth() - 40, panelEast.getMinimumSize().height));
					
				} else {
					panelWest.setPreferredSize(new Dimension(frame.getWidth() - holding_East.getWidth() - 40, panelWest.getMinimumSize().height));
				}
			}
			System.out.println("X-From-PanelWest: "+e.getX());
		}
		else if (e.getSource().equals(panelEast_resizeLeft)) {
			panelEast.setPreferredSize(new Dimension((int) (frame.getWidth() - x) - 25,panelEast.getMinimumSize().height));
			if (panelEast.getPreferredSize().width < 160) {
				panelEast.setPreferredSize(new Dimension(160,panelEast.getMinimumSize().height));
				/* panelWest.getPreferredSize().width > 1920 - holding_East.getWidth()) */
			} else if (panelEast.getPreferredSize().width > frame.getWidth() - holding_West.getWidth() - 40) {
				if (panelWest.getPreferredSize().width > 160) {
					panelEast.setPreferredSize(new Dimension((int) (frame.getWidth() - x) - 25,panelEast.getMinimumSize().height));
					panelWest.setPreferredSize(new Dimension(frame.getWidth() - holding_East.getWidth() - 40, panelWest.getMinimumSize().height));
					
				} else {
					panelEast.setPreferredSize(new Dimension(frame.getWidth() - holding_West.getWidth() - 40, panelEast.getMinimumSize().height));
				}
			}
			
			System.out.println("X-From-PanelEast: "+e.getX());
		}
		
		
		System.out.println("X Stop(East):"+holding_East.getWidth());
		System.out.println("Diff.(East):"+(holding_East.getWidth()-panelEast.getPreferredSize().width));
		System.out.println("X: "+x);
		System.out.println("Y: "+y);
		
		panelWest.revalidate();
		panelWest.repaint();
		panelEast.revalidate();
		panelEast.repaint();
		holding_West.revalidate();
		holding_West.repaint();
		holding_East.revalidate();
		holding_East.repaint();
		
	}
	@Override
	public void mouseMoved(MouseEvent e)
	{
		panelWest.revalidate();
		panelWest.repaint();
		panelEast.revalidate();
		panelEast.repaint();
		holding_West.revalidate();
		holding_West.repaint();
		holding_East.revalidate();
		holding_East.repaint();
		
		
	}
	@Override
	public void componentResized(ComponentEvent e)
	{
		
		if (e.getSource().equals(frame)) {
			panelWest.revalidate();
			panelWest.repaint();
			panelEast.revalidate();
			panelEast.repaint();
			holding_West.revalidate();
			holding_West.repaint();
			holding_East.revalidate();
			holding_East.repaint();

		}
		
	}
	@Override
	public void componentMoved(ComponentEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentShown(ComponentEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentHidden(ComponentEvent e)
	{
		// TODO Auto-generated method stub
		
	}

}
