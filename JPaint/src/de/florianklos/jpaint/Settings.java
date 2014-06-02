package de.florianklos.jpaint;

import java.awt.*;

public class Settings {

    private static Settings instance = null;
    private Color defaultLineColor,
            defaultFillColor,
            lineColor,
            fillColor;
    private String shape;
    private int contourSize;
    private boolean filled;

    private Settings() {
        defaultLineColor = new Color(50, 50, 50);
        defaultFillColor = new Color(150, 150, 150);
        lineColor = defaultLineColor;
        fillColor = defaultFillColor;
        shape = "Line";
        contourSize = 2;
        filled = false;
    }

    public static synchronized Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    public Color getDefaultLineColor() {
        return defaultLineColor;
    }

    public Color getDefaultFillColor() {
        return defaultFillColor;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public int getContourSize() {
        return contourSize;
    }

    public void setContourSize(int contourSize) {
        this.contourSize = contourSize;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
}
