package de.florianklos.jpaint;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setTitle("JPaint");
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}