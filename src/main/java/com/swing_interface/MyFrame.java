package com.swing_interface;

import com.AppInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Serega on 31.07.2015.
 */
public class MyFrame extends JFrame implements AppInterface {

    static {
        ColorScheme.scheme = 1;
    }

    private Color color1 = ColorScheme.getColor((byte) 1);
    private Color color2 = new Color(50, 163, 233);
    private Color color3 = new Color(41, 147, 208);

    private JPanel actionPanel = new JPanel(new GridBagLayout());
    private JButton buttonAct[] = {
            new JButton("Sign in"),
            new JButton("Sign up"),
            new JButton("Quit"),
    };
    private int[] margin = {0, 0, 50, 0};
    private Font fontOfActBut = new Font("Times New Roman", Font.BOLD, 30);


    private void initUI() {
        ColorScheme.getColor((byte) 1);
        setTitle("Crypto");
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(actionPanel, BorderLayout.CENTER);
        setSize(900, 600);
        setVisible(true);

        GridBagConstraints c1 = new GridBagConstraints();
        GridBagConstraints c2 = new GridBagConstraints();
        GridBagConstraints c3 = new GridBagConstraints();
        c1.gridx = c2.gridx = c3.gridx = 0;
        c1.gridy = 0;
        c2.gridy = 1;
        c3.gridy = 2;
        c1.gridwidth = c2.gridwidth = c3.gridwidth = 1;
        c1.gridheight = c2.gridheight = c3.gridheight = 1;

        c1.anchor = c2.anchor = c3.anchor = GridBagConstraints.CENTER;
//        c1.insets = new Insets(margin[0], margin[1], margin[2], margin[3]);
        c1.insets = c2.insets = c3.insets = new Insets(margin[0], margin[1], margin[2], margin[3]);
        for (int i = 0; i < 3; i++) {
            buttonAct[i].setPreferredSize(new Dimension(300, 60));
            buttonAct[i].setFont(fontOfActBut);
            buttonAct[i].setBackground(color2);
//            buttonAct[i].setBackground(new Color(41, 147, 208));
            buttonAct[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    for (JButton b : buttonAct) {
                        for (MouseListener actL : b.getMouseListeners())
                            if (actL == this) {
                                System.out.println("HOVER");
                                b.setBackground(color1);
                                break;
                            }
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    for (JButton b : buttonAct) {
                        for (MouseListener actL : b.getMouseListeners())
                            if (actL == this) {
                                System.out.println("HOVER");
                                b.setBackground(color2);
                                break;
                            }
                    }
                }
            });
            buttonAct[0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            buttonAct[3].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

//            final JTabbedPane tabbedPane = new JTabbedPane(1);
//            JButton b1 = new JButton("Submit");
//            JButton b2 = new JButton("Submit");
//            JButton b3 = new JButton("Submit");
//            tabbedPane.add(b1);
//            tabbedPane.add(b2);
//            getContentPane().add();
////            tabbedPane.add(b3);
//            actionPanel.add(tabbedPane);
        }
        actionPanel.setBackground(new Color(219, 240, 255));
        actionPanel.add(buttonAct[0], c1);
        actionPanel.add(buttonAct[1], c2);
        actionPanel.add(buttonAct[2], c3);
    }


    @Override
    public void launch() {
        initUI();
    }
}
