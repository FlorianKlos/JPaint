package de.florianklos.jpaint.shapes;

import java.awt.*;
import java.io.Serializable;

public interface CustomShape extends Serializable {
    public void paint(Graphics2D graphics2D, boolean isComplete);

    public void setEndPosition(Point endPosition);

    public boolean saveShape();
}
