import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class FrameBrowser extends JFrame
{
    private DPanel dp;
    private JLabel nowShowing;
    private ZoomSlider zoomSlider;
    private JScrollPane scrollPane;
    private BufferedImage bi = null;
    private java.util.List<BufferedImage> frames = null;
    private Font personalFont = new Font("Cascadia Mono", Font.PLAIN, 14);
    private int fnum = 0;
    private int dpNum = 0;
    private int numframes = 0;
    
    private final Action backButtonPress = new AbstractAction()
    {
        public void actionPerformed(ActionEvent e)
        {
            backwardButtonActionPerformed();
        }
    };
    
    private final Action forwardButtonPress = new AbstractAction()
    {
        public void actionPerformed(ActionEvent e)
        {
            forwardButtonActionPerformed();
        }
    };
    
    public FrameBrowser(BufferedImage b, File f, int dpNumber)
    {
        bi = b;
        fnum = 0;
        dpNum = dpNumber;
        numframes = 0;
        initComponents();
        BufferedImage bnext;
        frames = new ArrayList<BufferedImage>();
        try
        {
            ImageInputStream ii = ImageIO.createImageInputStream(f);
            if (ii == null) throw new IOException("Couldn't create input stream");
            ImageReader rr = ImageIO.getImageReaders(ii).next();
            if (rr == null) throw new IOException("Couldn't find image reader");
            rr.setInput(ii);
            int fread = rr.getMinIndex();
            while (true)
            {
                bnext = rr.read(numframes + fread);
                if (bnext == null) break;
                frames.add(bnext);
                numframes++;
            }
            System.out.println("total frames " + numframes);
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(this, "Failed to load file: " + e);
        }
        catch (IndexOutOfBoundsException ignored)
        {
        
        }
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
        
        JPanel buttonPanel = new JPanel();
        JButton backwardButton = new JButton("<");
        backwardButton.setFont(personalFont);
        backwardButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                backwardButtonActionPerformed();
            }
        });
        JButton forwardButton = new JButton(">");
        forwardButton.setFont(personalFont);
        forwardButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                forwardButtonActionPerformed();
            }
        });
        JButton saveButton = new JButton("Save");
        saveButton.setFont(personalFont);
        saveButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                saveButtonActionPerformed();
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
        this.setTitle("Frame Browser");
        this.setLocationRelativeTo(null); // 将窗口放置在屏幕正中央
        this.setMaximumSize(getToolkit().getScreenSize());
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    private void backwardButtonActionPerformed()
    {
        fnum--;
        if (fnum < 0) fnum = numframes - 1;
        updateImage();
    }
    
    private void forwardButtonActionPerformed()
    {
        fnum++;
        if (fnum >= numframes) fnum = 0;
        updateImage();
    }
    
    
    private void saveButtonActionPerformed()
    {
        File sfile = null;
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "gif", "png", "bmp");
        fileChooser.setFileFilter(filter);
        fileChooser.setSelectedFile(new File("frame" + (fnum + 1) + ".bmp"));
        int rVal = fileChooser.showSaveDialog(this);
        System.setProperty("user.dir", fileChooser.getCurrentDirectory().getAbsolutePath());
        if (rVal == JFileChooser.APPROVE_OPTION)
        {
            sfile = fileChooser.getSelectedFile();
            try
            {
                BufferedImage bbx = frames.get(fnum);
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
    
    
    private void newImage()
    {
        nowShowing.setText("Frame : " + (fnum + 1) + " of " + numframes);
        if (numframes == 0) return;
        dp.setImage(frames.get(fnum));
        dp.setSize(bi.getWidth(), bi.getHeight());
        dp.setPreferredSize(new Dimension(bi.getWidth(), bi.getHeight()));
        dp.apply(dpNum);
        zoomSlider.setValue(dpNum);
        scrollPane.revalidate();
        repaint();
    }
    
    
    private void updateImage()
    {
        nowShowing.setText("Frame : " + (fnum + 1) + " of " + numframes);
        if (numframes == 0) return;
        dp.setImage(frames.get(fnum));
        repaint();
    }
}
