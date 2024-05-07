import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;


public class Stereo extends JFrame
{
    private DPanel dp;
    private JLabel nowShowing;
    private JPanel buttonPanel;
    private JButton forwardButton;
    private JButton backwardButton;
    private JButton saveButton;
    private JScrollPane scrollPane;
    private BufferedImage bi = null;
    private StereoTransform transform = null;
    private ZoomSlider zoomSlider;
    private Font personalFont = new Font("Cascadia Mono", Font.PLAIN, 14);
    private int dpNum = 0;
    
    private final Action backButtonPress = new AbstractAction()
    {
        public void actionPerformed(ActionEvent e)
        {
            backwardButtonActionPerformed(e);
        }
    };
    
    private final Action forwardButtonPress = new AbstractAction()
    {
        public void actionPerformed(ActionEvent e)
        {
            forwardButtonActionPerformed(e);
        }
    };
    
    public Stereo(BufferedImage b, int dpNumber)
    {
        bi = b;
        dpNum = dpNumber;
        initComponents();
        newImage();
    }
    
    private void initComponents()
    {
        setLayout(new BorderLayout());
        
        JPanel textZoom = new JPanel();
        textZoom.setLayout(new BorderLayout());
        nowShowing = new JLabel();
        nowShowing.setFont(personalFont);
        nowShowing.setHorizontalAlignment(JLabel.CENTER);
        zoomSlider = new ZoomSlider(10, 1000, 100);
        zoomSlider.addChangeListener(v ->
        {
            dp.apply(v);
            dp.revalidate();
        });
        textZoom.add(nowShowing, BorderLayout.NORTH);
        textZoom.add(zoomSlider, BorderLayout.SOUTH);
        add(textZoom, BorderLayout.NORTH);
        
        buttonPanel = new JPanel();
        backwardButton = new JButton("<");
        backwardButton.setFont(personalFont);
        backwardButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                backwardButtonActionPerformed(evt);
            }
        });
        forwardButton = new JButton(">");
        forwardButton.setFont(personalFont);
        forwardButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                forwardButtonActionPerformed(evt);
            }
        });
        saveButton = new JButton("Save");
        saveButton.setFont(personalFont);
        saveButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                saveButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(backwardButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(forwardButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        backwardButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "back");
        backwardButton.getActionMap().put("back", backButtonPress);
        forwardButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "forward");
        forwardButton.getActionMap().put("forward", forwardButtonPress);
        
        dp = new DPanel();
        scrollPane = new JScrollPane(dp);
        add(scrollPane, BorderLayout.CENTER);
        
        pack();
        
        this.setSize(660, 520);
        this.setTitle("Stereogram Solver");
        this.setLocationRelativeTo(null); // 将窗口放置在屏幕正中央
        this.setMaximumSize(getToolkit().getScreenSize());
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    private void backwardButtonActionPerformed(ActionEvent evt)
    {
        if (transform == null) return;
        transform.back();
        updateImage();
    }
    
    private void forwardButtonActionPerformed(ActionEvent evt)
    {
        if (transform == null) return;
        transform.forward();
        updateImage();
    }
    
    private void saveButtonActionPerformed(ActionEvent evt)
    {
        File sfile = null;
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "gif", "png", "bmp");
        fileChooser.setFileFilter(filter);
        fileChooser.setSelectedFile(new File("solved.bmp"));
        int rVal = fileChooser.showSaveDialog(this);
        System.setProperty("user.dir", fileChooser.getCurrentDirectory().getAbsolutePath());
        if (rVal == JFileChooser.APPROVE_OPTION)
        {
            sfile = fileChooser.getSelectedFile();
            try
            {
                BufferedImage bbx = transform.getImage();
                int rns = sfile.getName().lastIndexOf(".") + 1;
                if (rns == 0) ImageIO.write(bbx, "bmp", sfile);
                else ImageIO.write(bbx, sfile.getName().substring(rns), sfile);
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(this, "Failed to write file: " + e);
            }
        }
    }
    
    private void updateImage()
    {
        nowShowing.setText(transform.getText());
        dp.setImage(transform.getImage());
        repaint();
    }
    
    private void newImage()
    {
        transform = new StereoTransform(bi);
        nowShowing.setText(transform.getText());
        dp.setImage(transform.getImage());
        dp.setSize(transform.getImage().getWidth(), transform.getImage().getHeight());
        dp.setPreferredSize(new Dimension(transform.getImage().getWidth(), transform.getImage().getHeight()));
        dp.apply(dpNum);
        zoomSlider.setValue(dpNum);
        scrollPane.revalidate();
        repaint();
    }
}
