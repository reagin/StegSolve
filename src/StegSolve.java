import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.event.*;
import java.awt.*;

public class StegSolve extends JFrame
{
    static StegSolve that;
    private JMenuItem about;
    private JMenuItem fileExit;
    private JMenuItem fileSave;
    private JMenuItem fileOpen;
    private JMenuItem analyseFormat;
    private JMenuItem analyseExtract;
    private JMenuItem stereoSolve;
    private JMenuItem frameBrowse;
    private JMenuItem imageCombine;
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenu menuAnalyse;
    private JMenu menuHelp;
    private JLabel nowShowing;
    private JPanel buttonPanel;
    private ZoomSlider zoomSlider;
    private JButton forwardButton;
    private JButton backwardButton;
    private DPanel dp;
    private JScrollPane scrollPane;
    private File sfile = null;
    private BufferedImage bi = null;
    private Transform transform = null;
    private Font personalFont = new Font("Cascadia Mono", Font.PLAIN, 14);
    private Action backButtonPress = new AbstractAction()
    {
        public void actionPerformed(ActionEvent e)
        {
            backwardButtonActionPerformed(e);
        }
    };
    private Action forwardButtonPress = new AbstractAction()
    {
        public void actionPerformed(ActionEvent e)
        {
            forwardButtonActionPerformed(e);
        }
    };
    
    private StegSolve()
    {
        that = this;
        initComponents();
    }
    
    public static void main(String args[])
    {
        EventQueue.invokeLater(() -> new StegSolve().setVisible(true));
    }
    
    private void initComponents()
    {
        menuBar = new JMenuBar();
        menuFile = new JMenu();
        fileOpen = new JMenuItem();
        fileSave = new JMenuItem();
        fileExit = new JMenuItem();
        menuAnalyse = new JMenu();
        analyseFormat = new JMenuItem();
        analyseExtract = new JMenuItem();
        stereoSolve = new JMenuItem();
        frameBrowse = new JMenuItem();
        imageCombine = new JMenuItem();
        menuHelp = new JMenu();
        about = new JMenuItem();
        nowShowing = new JLabel();
        
        
        fileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, 0));
        fileOpen.setText("Open");
        fileOpen.setFont(personalFont);
        fileOpen.addActionListener(this::fileOpenActionPerformed);
        menuFile.add(fileOpen);
        
        fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0));
        fileSave.setText("Save As");
        fileSave.setFont(personalFont);
        fileSave.addActionListener(this::fileSaveActionPerformed);
        menuFile.add(fileSave);
        
        fileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, 0));
        fileExit.setText("Exit");
        fileExit.setFont(personalFont);
        fileExit.addActionListener(this::fileExitActionPerformed);
        menuFile.add(fileExit);
        
        menuFile.setText("File");
        menuFile.setFont(personalFont);
        menuBar.add(menuFile);
        
        
        analyseFormat.setText("File Format");
        analyseFormat.setFont(personalFont);
        analyseFormat.addActionListener(this::analyseFormatActionPerformed);
        menuAnalyse.add(analyseFormat);
        
        analyseExtract.setText("Data Extract");
        analyseExtract.setFont(personalFont);
        analyseExtract.addActionListener(this::analyseExtractActionPerformed);
        menuAnalyse.add(analyseExtract);
        
        stereoSolve.setText("Stereogram Solver");
        stereoSolve.setFont(personalFont);
        stereoSolve.addActionListener(this::stereoSolveActionPerformed);
        menuAnalyse.add(stereoSolve);
        
        frameBrowse.setText("Frame Browser");
        frameBrowse.setFont(personalFont);
        frameBrowse.addActionListener(this::frameBrowseActionPerformed);
        menuAnalyse.add(frameBrowse);
        
        imageCombine.setText("Image Combiner");
        imageCombine.setFont(personalFont);
        imageCombine.addActionListener(this::imageCombineActionPerformed);
        menuAnalyse.add(imageCombine);
        
        menuAnalyse.setText("Analyse");
        menuAnalyse.setFont(personalFont);
        menuBar.add(menuAnalyse);
        
        
        about.setText("About");
        about.setFont(personalFont);
        about.addActionListener(this::aboutActionPerformed);
        menuHelp.add(about);
        
        menuHelp.setText("Help");
        menuHelp.setFont(personalFont);
        menuBar.add(menuHelp);
        
        setJMenuBar(menuBar);
        setLayout(new BorderLayout());
        
        JPanel textZoom = new JPanel();
        textZoom.setLayout(new BorderLayout());
        nowShowing.setText("Please open one picture...");
        nowShowing.setHorizontalAlignment(JLabel.CENTER);
        nowShowing.setFont(personalFont);
        zoomSlider = new ZoomSlider(10, 1000, 100);
        zoomSlider.addChangeListener(v ->
        {
            dp.apply(v);
            dp.revalidate();
        });
        
        textZoom.add(nowShowing, BorderLayout.NORTH);
        textZoom.add(zoomSlider, BorderLayout.SOUTH);
        
        add(textZoom, BorderLayout.NORTH);
        
        dp = new DPanel();
        scrollPane = new JScrollPane(dp);
        JFrame frame = this;
        frame.addKeyListener(new KeyListener()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                
                if (e.isShiftDown())
                {
                    frame.addMouseWheelListener(arg01 ->
                    {
                        
                        scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getValue() + arg01.getWheelRotation());
                    });
                }
            }
            
            @Override
            public void keyReleased(KeyEvent e)
            {
                if (!e.isShiftDown())
                {
                    frame.removeMouseWheelListener(frame.getMouseWheelListeners()[0]);
                }
            }
            
            @Override
            public void keyTyped(KeyEvent e)
            {
            }
        });
        
        add(scrollPane, BorderLayout.CENTER);
        
        buttonPanel = new JPanel();
        backwardButton = new JButton("<");
        backwardButton.addActionListener(this::backwardButtonActionPerformed);
        forwardButton = new JButton(">");
        forwardButton.addActionListener(this::forwardButtonActionPerformed);
        buttonPanel.add(backwardButton);
        buttonPanel.add(forwardButton);
        backwardButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "back");
        backwardButton.getActionMap().put("back", backButtonPress);
        forwardButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "forward");
        forwardButton.getActionMap().put("forward", forwardButtonPress);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        pack();
        
        this.setSize(760, 580);
        this.setTitle("StegSolve");
        this.setLocationRelativeTo(null); // 将窗口放置在屏幕正中央
        this.setMaximumSize(getToolkit().getScreenSize());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    private void fileExitActionPerformed(ActionEvent evt)
    {
        System.exit(0);
    }
    
    private void backwardButtonActionPerformed(ActionEvent evt)
    {
        if (transform == null) return;
        transform.back();
        updateImage();
    }
    
    private void forwardButtonActionPerformed(ActionEvent evt)
    {
        if (bi == null) return;
        transform.forward();
        updateImage();
    }
    
    private void aboutActionPerformed(ActionEvent evt)
    {
        new AboutFrame().setVisible(true);
    }
    
    private void analyseFormatActionPerformed(ActionEvent evt)
    {
        new FileAnalysis(sfile).setVisible(true);
    }
    
    private void stereoSolveActionPerformed(ActionEvent evt)
    {
        new Stereo(bi, zoomSlider.getValue()).setVisible(true);
    }
    
    private void frameBrowseActionPerformed(ActionEvent evt)
    {
        new FrameBrowser(bi, sfile, zoomSlider.getValue()).setVisible(true);
    }
    
    private void imageCombineActionPerformed(ActionEvent evt)
    {
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "gif", "bmp", "png");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Select image to combine with");
        int rVal = fileChooser.showOpenDialog(this);
        System.setProperty("user.dir", fileChooser.getCurrentDirectory().getAbsolutePath());
        if (rVal == JFileChooser.APPROVE_OPTION)
        {
            sfile = fileChooser.getSelectedFile();
            try
            {
                BufferedImage bi2 = ImageIO.read(sfile);
                new Combiner(bi, bi2, zoomSlider.getValue()).setVisible(true);
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(this, "Failed to load file: " + e.toString());
            }
        }
    }
    
    private void analyseExtractActionPerformed(ActionEvent evt)
    {
        new Extract(bi).setVisible(true);
    }
    
    private void fileSaveActionPerformed(ActionEvent evt)
    {
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
        fileChooser.setSelectedFile(new File("Solved.bmp"));
        int rVal = fileChooser.showSaveDialog(this);
        System.setProperty("user.dir", fileChooser.getCurrentDirectory().getAbsolutePath());
        if (rVal == JFileChooser.APPROVE_OPTION)
        {
            sfile = fileChooser.getSelectedFile();
            try
            {
                bi = transform.getImage();
                int rns = sfile.getName().lastIndexOf(".") + 1;
                if (rns == 0) ImageIO.write(bi, "bmp", sfile);
                else ImageIO.write(bi, sfile.getName().substring(rns), sfile);
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(this, "Failed to write file: " + e.toString());
            }
        }
    }
    
    private void fileOpenActionPerformed(ActionEvent evt)
    {
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "gif", "bmp", "png");
        fileChooser.setFileFilter(filter);
        int rVal = fileChooser.showOpenDialog(this);
        System.setProperty("user.dir", fileChooser.getCurrentDirectory().getAbsolutePath());
        if (rVal == JFileChooser.APPROVE_OPTION)
        {
            sfile = fileChooser.getSelectedFile();
            privateLoadImage(sfile);
        }
    }
    
    public void loadImage(String path)
    {
        File sfile = new File(path);
        privateLoadImage(sfile);
    }
    
    private void privateLoadImage(File sfile)
    {
        this.sfile = sfile;
        try
        {
            bi = ImageIO.read(sfile);
            transform = new Transform(bi);
            newImage();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load file: " + e.toString());
        }
    }
    
    private void newImage()
    {
        nowShowing.setText(transform.getText());
        nowShowing.setFont(personalFont);
        dp.setImage(transform.getImage());
        dp.setSize(transform.getImage().getWidth(), transform.getImage().getHeight());
        dp.setPreferredSize(new Dimension(transform.getImage().getWidth(), transform.getImage().getHeight()));
        dp.apply(100);
        zoomSlider.setValue(100);
        scrollPane.revalidate();
        repaint();
    }
    
    private void updateImage()
    {
        nowShowing.setText(transform.getText());
        nowShowing.setFont(personalFont);
        dp.setImage(transform.getImage());
        repaint();
    }
}