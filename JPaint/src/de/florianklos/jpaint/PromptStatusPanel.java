package de.florianklos.jpaint;

import javax.swing.*;

public class PromptStatusPanel extends JPanel {

    private static PromptStatusPanel instance = null;
    private JLabel label;

    private PromptStatusPanel() {
        label = new JLabel("Anwendung gestartet");
        this.add(label);
    }

    public static synchronized PromptStatusPanel getInstance() {
        if (instance == null) {
            instance = new PromptStatusPanel();
        }
        return instance;
    }

    public void setLabel(String currentStatus) {
        label.setText(currentStatus);
    }
}
