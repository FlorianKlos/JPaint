package de.florianklos.jpaint.shapes;

import java.awt.*;

public class PointShape implements CustomShape {

    private Point position;
    private int size;
    private Color color;

    public PointShape(Point position, int size, Color color) {
        this.position = position;
        this.size = size > 1 ? size : 2;
        this.color = color;
    }

    @Override
    public void paint(Graphics2D graphics2D, boolean isComplete) {
        graphics2D.setPaint(color);
        graphics2D.fillOval(position.x - size / 2, position.y - size / 2, size, size);
    }

    @Override
    public void setEndPosition(Point endPosition) {
    }

    @Override
    public boolean saveShape() {
        return true;
    }

}
