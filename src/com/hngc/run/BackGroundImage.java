package com.hngc.run;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class BackGroundImage extends JPanel
{
    Image image = null;
    public BackGroundImage(String path)
    {
        try
        {
            image = ImageIO.read(new File(path).toURI().toURL());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g)
    {
        g.drawImage(image, 0, 0, null);
    }
}
