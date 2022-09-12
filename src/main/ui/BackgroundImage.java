package ui;

import javax.swing.*;
import java.awt.*;

// Represent the background image for the graphical user interface of the NBA application.
public class BackgroundImage extends JComponent {
    private Image image;


    // EFFECTS: use the input image as the background image.
    public BackgroundImage(Image image) {
        this.image = image;
    }

    @Override
    // EFFECTS: draw the image on the background of the window.
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 250, 0, this);
    }
}