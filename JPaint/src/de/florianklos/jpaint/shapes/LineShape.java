package de.florianklos.jpaint.shapes;

import java.awt.*;

public class LineShape implements CustomShape {

    private Point startPosition,
            endPosition;
    private Stroke basicStroke,
            dottedStroke;
    private Color color;

    public LineShape(Point startPosition, Point endPosition, int lineSize, Color color) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        basicStroke = new BasicStroke(lineSize);
        dottedStroke = new BasicStroke(lineSize, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        this.color = color;
    }

    @Override
    public void paint(Graphics2D graphics2D, boolean isComplete) {
        graphics2D.setStroke(isComplete ? basicStroke : dottedStroke);
        graphics2D.setPaint(color);
        graphics2D.drawLine(startPosition.x, startPosition.y, endPosition.x, endPosition.y);
    }

    @Override
    public void setEndPosition(Point endPosition) {
        this.endPosition = endPosition;
    }

    @Override
    public boolean saveShape() {
        return !startPosition.equals(endPosition);
    }
}
