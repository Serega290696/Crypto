package com.swing_interface;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Serega on 01.08.2015.
 */
public class Sample {
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JButton button3;

    public Sample() {
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }
}
