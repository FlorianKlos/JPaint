package de.florianklos.jpaint.fillings;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FloodFill {

    private Color fillColor, oldColor;
    private BufferedImage image;

    public FloodFill(Point position, Color fillColor, BufferedImage image) {
        this.fillColor = fillColor;
        this.image = image;
        oldColor = new Color(image.getRGB(position.x, position.y));
        this.fill(position.x, position.y);
    }

    private void fill(int x, int y) {
        if (!executeFilling(x, y)) {
            return;
        }
        image.setRGB(x, y, fillColor.getRGB());
        this.fill(x + 1, y);
        this.fill(x, y + 1);
        this.fill(x - 1, y);
        this.fill(x, y - 1);
    }

    private Boolean executeFilling(int x, int y) {
        return !(isOutOfBounds(x, y) || !isColorToChange(x, y));
    }

    private Boolean isOutOfBounds(int x, int y) {
        return (x < 0 || y < 0 || x > image.getWidth() - 1 || y > image.getHeight() - 1);
    }

    private Boolean isColorToChange(int x, int y) {
        return image.getRGB(x, y) == oldColor.getRGB() && image.getRGB(x, y) != fillColor.getRGB();
    }

}
