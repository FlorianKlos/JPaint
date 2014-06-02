package de.florianklos.jpaint.shapes;

import java.awt.*;

public class RectangleShape implements CustomShape {

    private Point startPosition,
            endPosition;
    private Stroke basicStroke,
            dottedStroke;
    private Color lineColor,
            fillColor;
    private boolean fillObject;

    public RectangleShape(Point startPosition, Point endPosition, int contourSize, Color lineColor, Color fillColor, boolean fillObject) {
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
        Rectangle rectangle = new Rectangle(startPosition);
        rectangle.add(endPosition);
        if (fillObject) {
            graphics2D.setPaint(fillColor);
            graphics2D.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        graphics2D.setStroke(isComplete ? basicStroke : dottedStroke);
        graphics2D.setPaint(lineColor);
        graphics2D.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    @Override
    public boolean saveShape() {
        return !startPosition.equals(endPosition);
    }

    @Override
    public void setEndPosition(Point endPosition) {
        this.endPosition = endPosition;
    }
}
