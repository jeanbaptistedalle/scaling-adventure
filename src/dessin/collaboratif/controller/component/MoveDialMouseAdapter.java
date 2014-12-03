package dessin.collaboratif.controller.component;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JToggleButton;

import dessin.collaboratif.misc.ScaleEnum;
import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;

/**
 * Adapteur 
 */
public class MoveDialMouseAdapter implements MouseListener,	MouseMotionListener 
{
	private boolean mouseDown = false;
	private String dir = null;

	public void mousePressed(MouseEvent e) {
		mouseDown = true;
		dir = ((JToggleButton) e.getSource()).getActionCommand();
		System.out.println("##"+dir);
		try {
			Thread.sleep(100);
			initThread();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
		mouseDown = false;
	}
	
	private boolean isRunning = false;
	private synchronized boolean checkAndMark() {
	    if (isRunning) return false;
	    isRunning = true;
	    return true;
	}
	private void initThread() {
	    if (checkAndMark()) {
	        new Thread() {
	            public void run() {
	                do {
	                    if(!dir.equals(ScaleEnum.INCREASE.toString()) && !dir.equals(ScaleEnum.DECREASE.toString()))
	                		Client.getInstance().move(dir);
	                    else
	                		Client.getInstance().scale(dir);
	                    	
	                    System.out.println("Pressed");
	            		MainFrame.getInstance().repaintDrawPanel();
	                    try {
							sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
	                } while (mouseDown);
	                isRunning = false;
	            }
	        }.start();
	    }
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}
}
