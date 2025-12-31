package com.hngc.run;

import javax.swing.*;

public class ChooseView extends JFrame
{
    public ChooseView()
    {
        BackGroundImage backGroundImage = new BackGroundImage("image/main.png");
        this.add(backGroundImage);
        this.setSize(1136, 640);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
