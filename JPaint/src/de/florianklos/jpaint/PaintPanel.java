package de.florianklos.jpaint;

import de.florianklos.jpaint.fillings.FloodFill;
import de.florianklos.jpaint.shapes.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PaintPanel extends JPanel {

    private BufferedImage image;
    private CustomShape currentShape;
    private Settings settings = Settings.getInstance();

    public PaintPanel() {

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initializeGraphicObject(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                completeGraphicObject();
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                updateGraphicObject(e);
            }
        });
    }

    private void initializeGraphicObject(MouseEvent e) {
        Point startPoint = new Point(e.getX(), e.getY());
        if (settings.getShape().equals("Point")) {
            currentShape = new PointShape(startPoint, settings.getContourSize(), settings.getLineColor());
        } else if (settings.getShape().equals("Line")) {
            currentShape = new LineShape(startPoint, startPoint, settings.getContourSize(), settings.getLineColor());
        } else if (settings.getShape().equals("Rectangle")) {
            currentShape = new RectangleShape(startPoint, startPoint, settings.getContourSize(), settings.getLineColor(), settings.getFillColor(), settings.isFilled());
        } else if (settings.getShape().equals("Circle")) {
            currentShape = new CircleShape(startPoint, startPoint, settings.getContourSize(), settings.getLineColor(), settings.getFillColor(), settings.isFilled());
        } else if (settings.getShape().equals("Floodfill")) {
            new FloodFill(startPoint, settings.getFillColor(), image);
        }
        repaint();
    }

    private void updateGraphicObject(MouseEvent e) {
        if (settings.getShape().equals("Floodfill"))
            return;
        Point endPoint = new Point(e.getX(), e.getY());
        currentShape.setEndPosition(endPoint);
        repaint();
    }

    private void completeGraphicObject() {
        if (settings.getShape().equals("Floodfill"))
            return;
        if (currentShape.saveShape()) {
            currentShape.paint(image.createGraphics(), true);
        }
        currentShape = null;
        repaint();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (image == null) {
            initializeImage();
        }
        graphics.drawImage(image, 0, 0, null);
        if (currentShape != null) {
            currentShape.paint((Graphics2D) graphics, false);
        }
    }

    public void resetImage() {
        initializeImage();
        repaint();
    }

    private void initializeImage() {
        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D init = (Graphics2D) image.getGraphics();
        init.setPaint(new Color(255, 255, 255));
        init.fillRect(0, 0, image.getWidth(), image.getHeight());
        init.dispose();
    }

    public void saveImage() {
        try {
            ImageIO.write(image, "png", new File("image.png"));
        } catch (IOException e) {
            PromptStatusPanel.getInstance().setLabel("Das Bild konnte nicht gespeichert werden.");
        }
    }

    public void loadImage() {
        try {
            image = ImageIO.read(new File("image.png"));
        } catch (IOException ignored) {
            PromptStatusPanel.getInstance().setLabel("Das Bild konnte nicht geöffnet werden.");
        }
        repaint();
    }
}