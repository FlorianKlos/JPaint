package de.florianklos.jpaint;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomMenuBar extends JMenuBar implements ActionListener, ChangeListener {

    private JMenu fileMenu,
            settingsMenu,
            drawMenu,
            helpMenu;
    private JMenuItem openItem,
            saveItem,
            resetItem,
            closeItem,
            lineColorItem,
            fillColorItem,
            infoItem,
            floodFillItem;
    private JCheckBoxMenuItem fillItem;
    private JRadioButtonMenuItem pointItem,
            lineItem,
            rectangleItem,
            circleItem;
    private JSlider objectSize;
    private Settings settings = Settings.getInstance();
    private PaintPanel paintPanel;

    public CustomMenuBar(PaintPanel paintPanel) {
        this.paintPanel = paintPanel;
        initializeMenu();
    }

    private void initializeMenu() {
        initializeFileMenu();
        initializeSettingsMenu();
        initializeDrawMenu();
        initializeHelpMenu();
    }

    private void initializeFileMenu() {
        fileMenu = new JMenu("Datei");
        openItem = new JMenuItem("Öffnen");
        saveItem = new JMenuItem("Speichern");
        resetItem = new JMenuItem("Zurücksetzen");
        openItem = new JMenuItem("Öffnen");
        closeItem = new JMenuItem("Beenden");

        this.add(fileMenu);

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(resetItem);
        fileMenu.addSeparator();
        fileMenu.add(closeItem);

        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        resetItem.addActionListener(this);
        closeItem.addActionListener(this);
    }

    private void initializeSettingsMenu() {
        settingsMenu = new JMenu("Einstellungen");
        lineColorItem = new JMenuItem("Linienfarbe");
        fillColorItem = new JMenuItem("Füllfarbe");
        JLabel label = new JLabel("Strichstärke");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        objectSize = new JSlider(JSlider.HORIZONTAL, 1, 30, settings.getContourSize());
        objectSize.setMajorTickSpacing(5);
        objectSize.setMinorTickSpacing(1);
        objectSize.setPaintTicks(true);
        fillItem = new JCheckBoxMenuItem("Füllen");

        this.add(settingsMenu);

        settingsMenu.add(lineColorItem);
        settingsMenu.add(fillColorItem);
        settingsMenu.addSeparator();
        settingsMenu.add(label);
        settingsMenu.add(objectSize);
        settingsMenu.add(fillItem);

        lineColorItem.addActionListener(this);
        fillColorItem.addActionListener(this);
        objectSize.addChangeListener(this);
        fillItem.addActionListener(this);
    }

    private void initializeDrawMenu() {
        drawMenu = new JMenu("Zeichnen");
        pointItem = new JRadioButtonMenuItem("Punkt");
        lineItem = new JRadioButtonMenuItem("Linie");
        rectangleItem = new JRadioButtonMenuItem("Rechteck");
        circleItem = new JRadioButtonMenuItem("Kreis");
        floodFillItem = new JRadioButtonMenuItem("Floodfill");

        this.add(drawMenu);

        ButtonGroup radioButtonGroup = new ButtonGroup();

        radioButtonGroup.add(pointItem);
        radioButtonGroup.add(lineItem);
        lineItem.setSelected(true);
        radioButtonGroup.add(rectangleItem);
        radioButtonGroup.add(circleItem);
        radioButtonGroup.add(floodFillItem);

        drawMenu.add(pointItem);
        drawMenu.add(lineItem);
        drawMenu.add(rectangleItem);
        drawMenu.add(circleItem);
        drawMenu.addSeparator();
        drawMenu.add(floodFillItem);

        pointItem.addActionListener(this);
        lineItem.addActionListener(this);
        rectangleItem.addActionListener(this);
        circleItem.addActionListener(this);
        floodFillItem.addActionListener(this);
    }

    private void initializeHelpMenu() {
        helpMenu = new JMenu("Hilfe");
        infoItem = new JMenuItem("Info");
        this.add(helpMenu);
        helpMenu.add(infoItem);
        infoItem.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == openItem)
            actionOpen();
        if (actionEvent.getSource() == saveItem)
            actionSave();
        if (actionEvent.getSource() == resetItem)
            actionReset();
        if (actionEvent.getSource() == closeItem)
            actionClose();
        if (actionEvent.getSource() == lineColorItem)
            actionLineColor();
        if (actionEvent.getSource() == fillColorItem)
            actionFillColor();
        if (actionEvent.getSource() == fillItem)
            actionFillItem();
        if (actionEvent.getSource() == pointItem)
            actionPointItem();
        if (actionEvent.getSource() == lineItem)
            actionLineItem();
        if (actionEvent.getSource() == rectangleItem)
            actionRectangleItem();
        if (actionEvent.getSource() == circleItem)
            actionCircleItem();
        if (actionEvent.getSource() == floodFillItem)
            actionFloodFillItem();
    }

    public void stateChanged(ChangeEvent changeEvent) {
        if (changeEvent.getSource() == objectSize) {
            actionObjectSize();
        }
    }

    private void actionObjectSize() {
        settings.setContourSize(objectSize.getValue());
    }

    private void actionOpen() {
        prompt("Das Bild wird geöffnet");
        paintPanel.loadImage();
    }

    private void actionSave() {
        prompt("Das Bild wird gespeichert");
        paintPanel.saveImage();
    }

    private void actionReset() {
        paintPanel.resetImage();
        prompt("Zeichnung zurücksetzen");
    }

    private void actionClose() {
        if (JOptionPane.showConfirmDialog(this,
                "Soll das Programm wirklich beendet werden?",
                "Beenden",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void actionLineColor() {
        Color chosenColor = JColorChooser.showDialog(this, "Linienfarbe wählen", settings.getDefaultLineColor());
        if (chosenColor == null)
            return;
        settings.setLineColor(chosenColor);
        prompt("Linienfarbe " + chosenColor.toString() + " ausgewählt");
    }

    private void actionFillColor() {
        Color chosenColor = JColorChooser.showDialog(this, "Füllfarbe wählen", settings.getDefaultFillColor());
        if (chosenColor == null)
            return;
        settings.setFillColor(chosenColor);
        prompt("Füllfarbe " + chosenColor.toString() + " ausgewählt");
    }

    private void actionFillItem() {
        settings.setFilled(!settings.isFilled());
        if (settings.isFilled())
            prompt("Objekte werden gefüllt");
        else
            prompt("Objekte werden nicht gefüllt");
    }

    private void actionPointItem() {
        prompt("Punkt gewählt");
        settings.setShape("Point");
    }

    private void actionLineItem() {
        prompt("Linie gewählt");
        settings.setShape("Line");
    }

    private void actionRectangleItem() {
        prompt("Rechteck gewählt");
        settings.setShape("Rectangle");
    }

    private void actionCircleItem() {
        prompt("Kreis gewählt");
        settings.setShape("Circle");
    }

    private void actionFloodFillItem() {
        prompt("Bereich wird gefüllt - Floodfill");
        settings.setShape("Floodfill");
    }

    private void prompt(String message) {
        PromptStatusPanel.getInstance().setLabel(message);
    }

}
