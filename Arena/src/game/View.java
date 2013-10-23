package game;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class for the GUI
 * 
 * @author Jacob Charles, Dean Hamlin
 *
 */

public class View extends JFrame {
	
	//TODO: Implement double buffering
	
	/**
	 * test constructor
	 */
	public View() {
		super();
		setSize(640, 480);
		setVisible(true);
	}
	
	/**
	 * Connects a controller to the screen
	 * 
	 * @param c
	 * 		the controller object to be connected
	 */
	public void attachController(Controller c) {
		this.addKeyListener(new ControlListener(c));
	}
	
	/**
	 * Draw a game state
	 * 
	 * @param state
	 * 		game state to draw
	 */
	public void reDraw(ClientGameState state){
		state.draw(this.getContentPane().getGraphics());
	}
}

