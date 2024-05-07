import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

interface SliderChangeListener
{
    void change(int value);
}

public class ZoomSlider extends JPanel
{
    
    private final JSlider slider;
    private final JTextField textField;
    private final List<SliderChangeListener> changeListeners = new ArrayList<>();
    private final Font personalFont = new Font("Cascadia Mono", Font.PLAIN, 14);
    
    public ZoomSlider(int min, int max, int defaultValue)
    {
        JLabel label = new JLabel("Zoom:");
        label.setFont(personalFont);
        add(label);
        
        slider = new JSlider(min, max, defaultValue);
        slider.addChangeListener(e -> setValue(slider.getValue()));
        add(slider);
        
        textField = new JTextField(String.valueOf(defaultValue), 5);
        
        textField.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                // 当获得焦点时选中所有文本
                textField.selectAll();
            }
            
            @Override
            public void focusLost(FocusEvent e)
            {
                try
                {
                    int value = Integer.parseInt(textField.getText());
                    setValue(value);
                }
                catch (NumberFormatException ex)
                {
                    // Handle invalid input here
                }
            }
        });
        
        textField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    slider.requestFocus();
                }
            }
        });
        
        slider.setMaximumSize(new Dimension(500, 25));
        add(textField);
    }
    
    public int getValue()
    {
        return Integer.parseInt(textField.getText());
    }
    
    public void setValue(int value)
    {
        slider.setValue(value);
        textField.setText(String.valueOf(value));
        
        for (SliderChangeListener listener : changeListeners)
        {
            listener.change(value);
        }
    }
    
    public void addChangeListener(SliderChangeListener listener)
    {
        changeListeners.add(listener);
    }
}