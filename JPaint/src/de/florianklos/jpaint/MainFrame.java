package de.florianklos.jpaint;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        PaintPanel paintPanel = new PaintPanel();
        PromptStatusPanel promptStatusPanel = PromptStatusPanel.getInstance();

        this.setJMenuBar(new CustomMenuBar(paintPanel));

        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(paintPanel, BorderLayout.CENTER);
        contentPane.add(promptStatusPanel, BorderLayout.SOUTH);
    }

}
