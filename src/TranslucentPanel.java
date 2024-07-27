/**TranslucentPanel.java
 * Description: This class creates a new TranslucentPanel object. Basically, it creates a new object to manage transparencies.
 *              We had to make this to prevent visual glitches from happening
 * Date: January 12, 2023
 * * 	Assignment: Guess Who Project
 *
 * Contributors:
 * - Aaron Tom
 * - Frank Ding
 * - Nick Zhu
 */

import javax.swing.*;
import java.awt.*;

public class TranslucentPanel extends JPanel {
    //initialize variables - alpha for alpha value, color for color desired
    public float alpha;
    public Color color;
    //create new translucent panel object with the assigned attributes

    public TranslucentPanel(Color colorValue, float alphaValue) {
        //sets value as alphaValue
        color = colorValue;
        alpha = alphaValue;
        //sets opaque to false
        setOpaque(false);
    }

    /**
     * This method creates a new paint component - generates and creates the translucent panel
     * @param graphicsObject the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics graphicsObject) {
        //graphics object has information on the color, hence why this needed to be created

        super.paintComponent(graphicsObject); //calls JPanel class to be able to change

        Graphics newGraphics = graphicsObject.create();

        Graphics2D graphics2D = (Graphics2D) newGraphics; //create 2DGraphics object of the original graphics object

        graphics2D.setColor(color);
        graphics2D.setComposite(AlphaComposite.SrcOver.derive(alpha)); //sets alpha value for transparency, alphaComposite helps
        graphics2D.fillRect(0, 0, getWidth(), getHeight()); //fills a rectangle for the width of the panel, gets width and height of panel
        graphics2D.dispose(); //disposes system resources
    }
}