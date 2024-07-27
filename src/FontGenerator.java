/**FontGenerator.java
 * Description: This class generates a font with the assigned name and font size. Name has to be the file of the font.
 * Date: January 12, 2023
 * * 	Assignment: Guess Who Project
 *
 * Contributors:
 * - Aaron Tom
 * - Frank Ding
 * - Nick Zhu
 */


import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontGenerator {
    /**
     * This method generates a font and returns the font as a Font object
     * @param fontName name of the font (font file)
     * @param fontSize size of the font (float)
     * @return returns the font
     */
    public static Font fontCreator(String fontName, Float fontSize){
        //sets font to null
        Font font = null;
        try {GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //creates font to the file location
            font = Font.createFont(Font.TRUETYPE_FONT, new File(fontName)).deriveFont(fontSize);
            //creates the font and puts it in the graphics environment
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontName)));
            //catch
        } catch (IOException | FontFormatException e) {}
        //returns the font
        return font;
    }
}
