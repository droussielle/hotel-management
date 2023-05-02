package com.hotelmanager.ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;

public class MyButton extends JToggleButton
{
    private Color hoverBackgroundColor = new Color(222, 222, 222);
    private Color pressedBackgroundColor = Color.CYAN;
    private Color buttonBackgroundColor = Color.LIGHT_GRAY;

    public MyButton(String text, Icon icon)
    {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setIcon(icon);
        setBackground(buttonBackgroundColor);
        setRolloverEnabled(true);
//        setOpaque(true);
        addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                if (getModel().isSelected())
                {
                    setBackground(pressedBackgroundColor);
                } else if (getModel().isRollover())
                {
                    setBackground(hoverBackgroundColor);
                } else
                {
                    setBackground(buttonBackgroundColor);
                }
            }
        });
    }

//    @Override
//    protected void paintComponent(Graphics g) {
//        if (getModel().isPressed()) {
//            g.setColor(pressedBackgroundColor);
//        } else if (getModel().isRollover()) {
//            g.setColor(hoverBackgroundColor);
//        } else {
//            g.setColor(buttonBackgroundColor);
//        }
//        g.fillRect(0, 0, getWidth(), getHeight());
//        super.paintComponent(g);
//    }

    @Override
    public void setContentAreaFilled(boolean b)
    {
    }

    public Color getHoverBackgroundColor()
    {
        return hoverBackgroundColor;
    }

    public Color getButtonBackgroundColor()
    {
        return buttonBackgroundColor;
    }

    public void setButtonBackgroundColor(Color buttonBackgroundColor)
    {
        this.buttonBackgroundColor = buttonBackgroundColor;
    }

    public void setHoverBackgroundColor(Color hoverBackgroundColor)
    {
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    public Color getPressedBackgroundColor()
    {
        return pressedBackgroundColor;
    }

    public void setPressedBackgroundColor(Color pressedBackgroundColor)
    {
        this.pressedBackgroundColor = pressedBackgroundColor;
    }

    public void setStatus(boolean b)
    {
        this.setSelected(b);
    }

    public boolean getStatus()
    {
        return this.isSelected();
    }
}
