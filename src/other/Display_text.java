package other;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import frontend.GameView;

/**
 * Box in which the user can type some text
 */
public class Display_text {


	String value;
	Rectangle position = new Rectangle(283,217);

	/**
	 * Constructor function
	 * @param content
	 */
	public Display_text(String content) {
		value=content;
	}

	/**
	 * Set the value
	 * @param arg, the value
	 */
	public void setValue(String arg) {
		value = arg;
	}

	/**
	 * Returns the value
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Draw the text into the screen
	 * @param g2d
	 */
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.white);
		g2d.setFont(GameView.fontGame);
		g2d.drawString(value,308, 256);
	}
	
}
