package de.florianklos.jpaint.shapes;

import java.awt.*;

public class CircleShape implements CustomShape {

    private Point startPosition,
            endPosition;
    private Stroke basicStroke,
            dottedStroke;
    private Color lineColor,
            fillColor;
    private boolean fillObject;

    public CircleShape(Point startPosition, Point endPosition, int contourSize, Color lineColor, Color fillColor, boolean fillObject) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        basicStroke = new BasicStroke(contourSize);
        dottedStroke = new BasicStroke(contourSize, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        this.lineColor = lineColor;
        this.fillColor = fillColor;
        this.fillObject = fillObject;
    }

    @Override
    public void paint(Graphics2D graphics2D, boolean isComplete) {
        int width = Math.abs(startPosition.x - endPosition.x);
        int height = Math.abs(startPosition.y - endPosition.y);
        int radius = width > height ? width : height;
        if (fillObject) {
            graphics2D.setPaint(fillColor);
            graphics2D.fillOval(startPosition.x - radius, startPosition.y - radius, 2 * radius, 2 * radius);
        }
        graphics2D.setStroke(isComplete ? basicStroke : dottedStroke);
        graphics2D.setPaint(lineColor);
        graphics2D.drawOval(startPosition.x - radius, startPosition.y - radius, 2 * radius, 2 * radius);
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
