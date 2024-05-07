import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.awt.image.BufferedImage;
import javax.swing.border.TitledBorder;


public class Extract extends javax.swing.JFrame
{
    private final String ls = System.getProperty("line.separator");
    private final Font personalFont = new Font("Cascadia Mono", Font.PLAIN, 14);
    private boolean lsbFirst = false;
    private boolean rowFirst = true;
    private BufferedImage bi = null;
    private byte[] extract = null;
    private int extractBitPos = 0;
    private int extractBytePos = 0;
    private int mask = 0;
    private int maskbits = 0;
    private int rgbOrder = 0;
    private ButtonGroup bitGroup;
    private ButtonGroup byGroup;
    private ButtonGroup planeGroup;
    private JButton cancelButton;
    private JButton previewButton;
    private JButton saveBinButton;
    private JButton saveTextButton;
    private JCheckBox ab0;
    private JCheckBox ab1;
    private JCheckBox ab2;
    private JCheckBox ab3;
    private JCheckBox ab4;
    private JCheckBox ab5;
    private JCheckBox ab6;
    private JCheckBox ab7;
    private JCheckBox aba;
    private JCheckBox bb0;
    private JCheckBox bb1;
    private JCheckBox bb2;
    private JCheckBox bb3;
    private JCheckBox bb4;
    private JCheckBox bb5;
    private JCheckBox bb6;
    private JCheckBox bb7;
    private JCheckBox bba;
    private JCheckBox gb0;
    private JCheckBox gb1;
    private JCheckBox gb2;
    private JCheckBox gb3;
    private JCheckBox gb4;
    private JCheckBox gb5;
    private JCheckBox gb6;
    private JCheckBox gb7;
    private JCheckBox gba;
    private JCheckBox hdInclude;
    private JCheckBox rb0;
    private JCheckBox rb1;
    private JCheckBox rb2;
    private JCheckBox rb3;
    private JCheckBox rb4;
    private JCheckBox rb5;
    private JCheckBox rb6;
    private JCheckBox rb7;
    private JCheckBox rba;
    private JLabel alphaLabel;
    private JLabel bitOrderLabel;
    private JLabel bitPlaneOrderLabel;
    private JLabel blueLabel;
    private JLabel extractByLabel;
    private JLabel greenLabel;
    private JLabel hdLabel;
    private JLabel redLabel;
    private JPanel alphaBitPanel;
    private JPanel bitOrderPanel;
    private JPanel bitPlaneOrderPanel;
    private JPanel bitPlanesPanel;
    private JPanel blueBitPanel;
    private JPanel buttonsPanel;
    private JPanel exPreviewPanel;
    private JPanel extractByPanel;
    private JPanel greenBitPanel;
    private JPanel lhSettingsPanel;
    private JPanel optionsPanel;
    private JPanel orderSettingsPanel;
    private JPanel prevSettingsPanel;
    private JPanel redBitPanel;
    private JPanel rhSettingsPanel;
    private JRadioButton BGRButton;
    private JRadioButton BRGButton;
    private JRadioButton byColumnButton;
    private JRadioButton byRowButton;
    private JRadioButton GBRButton;
    private JRadioButton GRBButton;
    private JRadioButton LSBButton;
    private JRadioButton MSBButton;
    private JRadioButton RBGButton;
    private JRadioButton RGBButton;
    private JScrollPane jScrollPane1;
    private JTextArea jPreview;
    private JFileChooser fileChooser;
    private StringBuilder prev = null;
    
    public Extract(BufferedImage b)
    {
        bi = b;
        initComponents();
    }
    
    private void getMask()
    {
        mask = 0;
        maskbits = 0;
        if (ab7.isSelected())
        {
            mask += 1 << 31;
            maskbits++;
        }
        if (ab6.isSelected())
        {
            mask += 1 << 30;
            maskbits++;
        }
        if (ab5.isSelected())
        {
            mask += 1 << 29;
            maskbits++;
        }
        if (ab4.isSelected())
        {
            mask += 1 << 28;
            maskbits++;
        }
        if (ab3.isSelected())
        {
            mask += 1 << 27;
            maskbits++;
        }
        if (ab2.isSelected())
        {
            mask += 1 << 26;
            maskbits++;
        }
        if (ab1.isSelected())
        {
            mask += 1 << 25;
            maskbits++;
        }
        if (ab0.isSelected())
        {
            mask += 1 << 24;
            maskbits++;
        }
        if (rb7.isSelected())
        {
            mask += 1 << 23;
            maskbits++;
        }
        if (rb6.isSelected())
        {
            mask += 1 << 22;
            maskbits++;
        }
        if (rb5.isSelected())
        {
            mask += 1 << 21;
            maskbits++;
        }
        if (rb4.isSelected())
        {
            mask += 1 << 20;
            maskbits++;
        }
        if (rb3.isSelected())
        {
            mask += 1 << 19;
            maskbits++;
        }
        if (rb2.isSelected())
        {
            mask += 1 << 18;
            maskbits++;
        }
        if (rb1.isSelected())
        {
            mask += 1 << 17;
            maskbits++;
        }
        if (rb0.isSelected())
        {
            mask += 1 << 16;
            maskbits++;
        }
        if (gb7.isSelected())
        {
            mask += 1 << 15;
            maskbits++;
        }
        if (gb6.isSelected())
        {
            mask += 1 << 14;
            maskbits++;
        }
        if (gb5.isSelected())
        {
            mask += 1 << 13;
            maskbits++;
        }
        if (gb4.isSelected())
        {
            mask += 1 << 12;
            maskbits++;
        }
        if (gb3.isSelected())
        {
            mask += 1 << 11;
            maskbits++;
        }
        if (gb2.isSelected())
        {
            mask += 1 << 10;
            maskbits++;
        }
        if (gb1.isSelected())
        {
            mask += 1 << 9;
            maskbits++;
        }
        if (gb0.isSelected())
        {
            mask += 1 << 8;
            maskbits++;
        }
        if (bb7.isSelected())
        {
            mask += 1 << 7;
            maskbits++;
        }
        if (bb6.isSelected())
        {
            mask += 1 << 6;
            maskbits++;
        }
        if (bb5.isSelected())
        {
            mask += 1 << 5;
            maskbits++;
        }
        if (bb4.isSelected())
        {
            mask += 1 << 4;
            maskbits++;
        }
        if (bb3.isSelected())
        {
            mask += 1 << 3;
            maskbits++;
        }
        if (bb2.isSelected())
        {
            mask += 1 << 2;
            maskbits++;
        }
        if (bb1.isSelected())
        {
            mask += 1 << 1;
            maskbits++;
        }
        if (bb0.isSelected())
        {
            mask += 1;
            maskbits++;
        }
    }
    
    private void getBitOrderOptions()
    {
        rowFirst = byRowButton.isSelected();
        lsbFirst = LSBButton.isSelected();
        if (RGBButton.isSelected()) rgbOrder = 1;
        else if (RBGButton.isSelected()) rgbOrder = 2;
        else if (GRBButton.isSelected()) rgbOrder = 3;
        else if (GBRButton.isSelected()) rgbOrder = 4;
        else if (BRGButton.isSelected()) rgbOrder = 5;
        else rgbOrder = 6;
    }
    
    private void addBit(int num)
    {
        if (num != 0)
        {
            extract[extractBytePos] += extractBitPos;
        }
        extractBitPos >>= 1;
        if (extractBitPos >= 1) return;
        extractBitPos = 128;
        extractBytePos++;
        if (extractBytePos < extract.length) extract[extractBytePos] = 0;
    }
    
    private void extract8Bits(int nextByte, int bitMask)
    {
        for (int i = 0; i < 8; i++)
        {
            if ((mask & bitMask) != 0)
            {
                
                addBit(nextByte & bitMask);
            }
            if (lsbFirst) bitMask <<= 1;
            else bitMask >>>= 1;
        }
    }
    
    private void extractBits(int nextByte)
    {
        if (lsbFirst)
        {
            extract8Bits(nextByte, 1 << 24);
            switch (rgbOrder)
            {
                case 1:
                    extract8Bits(nextByte, 1 << 16);
                    extract8Bits(nextByte, 1 << 8);
                    extract8Bits(nextByte, 1);
                    break;
                case 2:
                    extract8Bits(nextByte, 1 << 16);
                    extract8Bits(nextByte, 1);
                    extract8Bits(nextByte, 1 << 8);
                    break;
                case 3:
                    extract8Bits(nextByte, 1 << 8);
                    extract8Bits(nextByte, 1 << 16);
                    extract8Bits(nextByte, 1);
                    break;
                case 4:
                    extract8Bits(nextByte, 1 << 8);
                    extract8Bits(nextByte, 1);
                    extract8Bits(nextByte, 1 << 16);
                    break;
                case 5:
                    extract8Bits(nextByte, 1);
                    extract8Bits(nextByte, 1 << 16);
                    extract8Bits(nextByte, 1 << 8);
                    break;
                case 6:
                    extract8Bits(nextByte, 1);
                    extract8Bits(nextByte, 1 << 8);
                    extract8Bits(nextByte, 1 << 16);
                    break;
            }
        }
        else
        {
            extract8Bits(nextByte, 1 << 31);
            switch (rgbOrder)
            {
                case 1:
                    extract8Bits(nextByte, 1 << 23);
                    extract8Bits(nextByte, 1 << 15);
                    extract8Bits(nextByte, 1 << 7);
                    break;
                case 2:
                    extract8Bits(nextByte, 1 << 23);
                    extract8Bits(nextByte, 1 << 7);
                    extract8Bits(nextByte, 1 << 15);
                    break;
                case 3:
                    extract8Bits(nextByte, 1 << 15);
                    extract8Bits(nextByte, 1 << 23);
                    extract8Bits(nextByte, 1 << 7);
                    break;
                case 4:
                    extract8Bits(nextByte, 1 << 15);
                    extract8Bits(nextByte, 1 << 7);
                    extract8Bits(nextByte, 1 << 23);
                    break;
                case 5:
                    extract8Bits(nextByte, 1 << 7);
                    extract8Bits(nextByte, 1 << 23);
                    extract8Bits(nextByte, 1 << 15);
                    break;
                case 6:
                    extract8Bits(nextByte, 1 << 7);
                    extract8Bits(nextByte, 1 << 15);
                    extract8Bits(nextByte, 1 << 23);
                    break;
            }
        }
    }
    
    private void generateExtract()
    {
        getMask();
        getBitOrderOptions();
        int len = bi.getHeight() * bi.getWidth();
        len = len * maskbits;
        len = (len + 7) / 8;
        extract = new byte[len];
        extractBitPos = 128;
        extractBytePos = 0;
        
        if (rowFirst)
        {
            for (int j = 0; j < bi.getHeight(); j++)
            {
                for (int i = 0; i < bi.getWidth(); i++)
                {
                    
                    extractBits(bi.getRGB(i, j));
                }
            }
        }
        else
        {
            for (int i = 0; i < bi.getWidth(); i++)
            {
                for (int j = 0; j < bi.getHeight(); j++)
                {
                    extractBits(bi.getRGB(i, j));
                }
            }
        }
    }
    
    private void generatePreview()
    {
        boolean hexDump = hdInclude.isSelected();
        prev = new StringBuilder();
        for (int i = 0; i < extract.length; i += 16)
        {
            if (hexDump)
            {
                for (int j = 0; j < 16 && i + j < extract.length; j++)
                {
                    prev.append(m2(Integer.toHexString(((int) extract[i + j]) & 0xff)));
                    if (j == 7) prev.append(' ');
                }
                prev.append("  ");
            }
            for (int j = 0; j < 16 && i + j < extract.length; j++)
            {
                char c = (char) extract[i + j];
                if (c >= 32 && c <= 128) prev.append(c);
                else prev.append('.');
                if (j == 7) prev.append(' ');
            }
            prev.append(ls);
        }
        jPreview.setText(prev.toString());
        
        SwingUtilities.invokeLater(() ->
        {
            JScrollBar verticalScrollBar = jScrollPane1.getVerticalScrollBar();
            verticalScrollBar.setValue(0);
        });
    }
    
    private void savePreview()
    {
        fileChooser = new JFileChooser(System.getProperty("user.dir"));
        int rVal = fileChooser.showSaveDialog(this);
        System.setProperty("user.dir", fileChooser.getCurrentDirectory()
                .getAbsolutePath());
        File sfile = null;
        if (rVal == JFileChooser.APPROVE_OPTION)
        {
            sfile = fileChooser.getSelectedFile();
            try
            {
                FileWriter fw = new FileWriter(sfile);
                fw.write(jPreview.getText());
                fw.close();
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(this, "Failed to write file: " + e);
            }
        }
    }
    
    private void saveExtract()
    {
        fileChooser = new JFileChooser(System.getProperty("user.dir"));
        int rVal = fileChooser.showSaveDialog(this);
        System.setProperty("user.dir", fileChooser.getCurrentDirectory()
                .getAbsolutePath());
        File sfile = null;
        if (rVal == JFileChooser.APPROVE_OPTION)
        {
            sfile = fileChooser.getSelectedFile();
            try
            {
                FileOutputStream fw = new FileOutputStream(sfile);
                fw.write(extract);
                fw.close();
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(this, "Failed to write file: " + e);
            }
        }
    }
    
    private String m2(String hx)
    {
        if (hx.length() < 2) return "0" + hx;
        return hx;
    }
    
    private void initComponents()
    {
        
        byGroup = new ButtonGroup();
        bitGroup = new ButtonGroup();
        planeGroup = new ButtonGroup();
        optionsPanel = new JPanel();
        lhSettingsPanel = new JPanel();
        bitPlanesPanel = new JPanel();
        alphaBitPanel = new JPanel();
        alphaLabel = new JLabel();
        aba = new JCheckBox();
        ab7 = new JCheckBox();
        ab6 = new JCheckBox();
        ab5 = new JCheckBox();
        ab4 = new JCheckBox();
        ab3 = new JCheckBox();
        ab2 = new JCheckBox();
        ab1 = new JCheckBox();
        ab0 = new JCheckBox();
        redBitPanel = new JPanel();
        redLabel = new JLabel();
        rba = new JCheckBox();
        rb7 = new JCheckBox();
        rb6 = new JCheckBox();
        rb5 = new JCheckBox();
        rb4 = new JCheckBox();
        rb3 = new JCheckBox();
        rb2 = new JCheckBox();
        rb1 = new JCheckBox();
        rb0 = new JCheckBox();
        greenBitPanel = new JPanel();
        greenLabel = new JLabel();
        gba = new JCheckBox();
        gb7 = new JCheckBox();
        gb6 = new JCheckBox();
        gb5 = new JCheckBox();
        gb4 = new JCheckBox();
        gb3 = new JCheckBox();
        gb2 = new JCheckBox();
        gb1 = new JCheckBox();
        gb0 = new JCheckBox();
        blueBitPanel = new JPanel();
        blueLabel = new JLabel();
        bba = new JCheckBox();
        bb7 = new JCheckBox();
        bb6 = new JCheckBox();
        bb5 = new JCheckBox();
        bb4 = new JCheckBox();
        bb3 = new JCheckBox();
        bb2 = new JCheckBox();
        bb1 = new JCheckBox();
        bb0 = new JCheckBox();
        prevSettingsPanel = new JPanel();
        hdLabel = new JLabel();
        hdInclude = new JCheckBox();
        rhSettingsPanel = new JPanel();
        orderSettingsPanel = new JPanel();
        extractByPanel = new JPanel();
        extractByLabel = new JLabel();
        byRowButton = new JRadioButton();
        byColumnButton = new JRadioButton();
        bitOrderPanel = new JPanel();
        bitOrderLabel = new JLabel();
        MSBButton = new JRadioButton();
        LSBButton = new JRadioButton();
        bitPlaneOrderPanel = new JPanel();
        bitPlaneOrderLabel = new JLabel();
        RGBButton = new JRadioButton();
        RBGButton = new JRadioButton();
        GBRButton = new JRadioButton();
        GRBButton = new JRadioButton();
        BRGButton = new JRadioButton();
        BGRButton = new JRadioButton();
        exPreviewPanel = new JPanel();
        jScrollPane1 = new JScrollPane();
        jPreview = new JTextArea();
        buttonsPanel = new JPanel();
        previewButton = new JButton();
        saveTextButton = new JButton();
        saveBinButton = new JButton();
        cancelButton = new JButton();
        
        int bitPlanesWidth = 480;
        
        getContentPane().setLayout(new java.awt.BorderLayout(5, 5));
        
        optionsPanel.setMinimumSize(new java.awt.Dimension(720, 280));
        optionsPanel.setPreferredSize(new java.awt.Dimension(720, 280));
        optionsPanel.setLayout(new java.awt.BorderLayout());
        
        lhSettingsPanel.setMinimumSize(new java.awt.Dimension(360, 280));
        lhSettingsPanel.setPreferredSize(new java.awt.Dimension(360, 280));
        
        bitPlanesPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Bit Planes", TitledBorder.LEFT, TitledBorder.TOP, personalFont));
        bitPlanesPanel.setMinimumSize(new java.awt.Dimension(bitPlanesWidth, 200));
        bitPlanesPanel.setPreferredSize(new java.awt.Dimension(bitPlanesWidth, 200));
        
        alphaBitPanel.setName("alphaBitPanel");
        alphaBitPanel.setPreferredSize(new java.awt.Dimension(bitPlanesWidth - 10, 34));
        
        alphaLabel.setText("Alpha");
        alphaLabel.setFont(personalFont);
        alphaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        alphaLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        alphaLabel.setMaximumSize(new java.awt.Dimension(40, 14));
        alphaLabel.setMinimumSize(new java.awt.Dimension(40, 14));
        alphaLabel.setPreferredSize(new java.awt.Dimension(40, 14));
        alphaBitPanel.add(alphaLabel);
        
        aba.setText("all");
        aba.setFont(personalFont);
        alphaBitPanel.add(aba);
        
        ab7.setText("7");
        ab7.setFont(personalFont);
        alphaBitPanel.add(ab7);
        
        ab6.setText("6");
        ab6.setFont(personalFont);
        alphaBitPanel.add(ab6);
        
        ab5.setText("5");
        ab5.setFont(personalFont);
        alphaBitPanel.add(ab5);
        
        ab4.setText("4");
        ab4.setFont(personalFont);
        alphaBitPanel.add(ab4);
        
        ab3.setText("3");
        ab3.setFont(personalFont);
        alphaBitPanel.add(ab3);
        
        ab2.setText("2");
        ab2.setFont(personalFont);
        alphaBitPanel.add(ab2);
        
        ab1.setText("1");
        ab1.setFont(personalFont);
        alphaBitPanel.add(ab1);
        
        ab0.setText("0");
        ab0.setFont(personalFont);
        alphaBitPanel.add(ab0);
        
        checkAllListener(aba, ab7, ab6, ab5, ab4, ab3, ab2, ab1, ab0);
        
        bitPlanesPanel.add(alphaBitPanel);
        alphaBitPanel.getAccessibleContext().setAccessibleName("alphaBitPanel");
        
        
        redBitPanel.setPreferredSize(new java.awt.Dimension(bitPlanesWidth - 10, 34));
        
        redLabel.setText("Red");
        redLabel.setFont(personalFont);
        redLabel.setHorizontalAlignment(SwingConstants.CENTER);
        redLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        redLabel.setMaximumSize(new java.awt.Dimension(40, 14));
        redLabel.setMinimumSize(new java.awt.Dimension(40, 14));
        redLabel.setPreferredSize(new java.awt.Dimension(40, 14));
        redBitPanel.add(redLabel);
        
        rba.setText("all");
        rba.setFont(personalFont);
        redBitPanel.add(rba);
        
        rb7.setText("7");
        rb7.setFont(personalFont);
        redBitPanel.add(rb7);
        
        rb6.setText("6");
        rb6.setFont(personalFont);
        redBitPanel.add(rb6);
        
        rb5.setText("5");
        rb5.setFont(personalFont);
        redBitPanel.add(rb5);
        
        rb4.setText("4");
        rb4.setFont(personalFont);
        redBitPanel.add(rb4);
        
        rb3.setText("3");
        rb3.setFont(personalFont);
        redBitPanel.add(rb3);
        
        rb2.setText("2");
        rb2.setFont(personalFont);
        redBitPanel.add(rb2);
        
        rb1.setText("1");
        rb1.setFont(personalFont);
        redBitPanel.add(rb1);
        
        rb0.setText("0");
        rb0.setFont(personalFont);
        redBitPanel.add(rb0);
        
        checkAllListener(rba, rb7, rb6, rb5, rb4, rb3, rb2, rb1, rb0);
        
        bitPlanesPanel.add(redBitPanel);
        
        
        greenBitPanel.setPreferredSize(new java.awt.Dimension(bitPlanesWidth - 10, 34));
        
        greenLabel.setText("Green");
        greenLabel.setFont(personalFont);
        greenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        greenLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        greenLabel.setMaximumSize(new java.awt.Dimension(40, 14));
        greenLabel.setMinimumSize(new java.awt.Dimension(40, 14));
        greenLabel.setPreferredSize(new java.awt.Dimension(40, 14));
        greenBitPanel.add(greenLabel);
        
        gba.setText("all");
        gba.setFont(personalFont);
        greenBitPanel.add(gba);
        
        gb7.setText("7");
        gb7.setFont(personalFont);
        greenBitPanel.add(gb7);
        
        gb6.setText("6");
        gb6.setFont(personalFont);
        greenBitPanel.add(gb6);
        
        gb5.setText("5");
        gb5.setFont(personalFont);
        greenBitPanel.add(gb5);
        
        gb4.setText("4");
        gb4.setFont(personalFont);
        greenBitPanel.add(gb4);
        
        gb3.setText("3");
        gb3.setFont(personalFont);
        greenBitPanel.add(gb3);
        
        gb2.setText("2");
        gb2.setFont(personalFont);
        greenBitPanel.add(gb2);
        
        gb1.setText("1");
        gb1.setFont(personalFont);
        greenBitPanel.add(gb1);
        
        gb0.setText("0");
        gb0.setFont(personalFont);
        greenBitPanel.add(gb0);
        
        checkAllListener(gba, gb7, gb6, gb5, gb4, gb3, gb2, gb1, gb0);
        
        bitPlanesPanel.add(greenBitPanel);
        
        blueBitPanel.setPreferredSize(new java.awt.Dimension(bitPlanesWidth - 10, 34));
        
        blueLabel.setText("Blue");
        blueLabel.setFont(personalFont);
        blueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        blueLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        blueLabel.setMaximumSize(new java.awt.Dimension(40, 14));
        blueLabel.setMinimumSize(new java.awt.Dimension(40, 14));
        blueLabel.setPreferredSize(new java.awt.Dimension(40, 14));
        blueBitPanel.add(blueLabel);
        
        bba.setText("all");
        bba.setFont(personalFont);
        blueBitPanel.add(bba);
        
        bb7.setText("7");
        bb7.setFont(personalFont);
        blueBitPanel.add(bb7);
        
        bb6.setText("6");
        bb6.setFont(personalFont);
        blueBitPanel.add(bb6);
        
        bb5.setText("5");
        bb5.setFont(personalFont);
        blueBitPanel.add(bb5);
        
        bb4.setText("4");
        bb4.setFont(personalFont);
        blueBitPanel.add(bb4);
        
        bb3.setText("3");
        bb3.setFont(personalFont);
        blueBitPanel.add(bb3);
        
        bb2.setText("2");
        bb2.setFont(personalFont);
        blueBitPanel.add(bb2);
        
        bb1.setText("1");
        bb1.setFont(personalFont);
        blueBitPanel.add(bb1);
        
        bb0.setText("0");
        bb0.setFont(personalFont);
        blueBitPanel.add(bb0);
        
        checkAllListener(bba, bb7, bb6, bb5, bb4, bb3, bb2, bb1, bb0);
        
        bitPlanesPanel.add(blueBitPanel);
        
        lhSettingsPanel.add(bitPlanesPanel);
        
        prevSettingsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Preview Settings", TitledBorder.LEFT, TitledBorder.TOP, personalFont));
        prevSettingsPanel.setMinimumSize(new java.awt.Dimension(bitPlanesWidth - 40, 50));
        prevSettingsPanel.setPreferredSize(new java.awt.Dimension(bitPlanesWidth - 40, 50));
        prevSettingsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));
        
        hdLabel.setText("Include Hex Dump In Preview");
        hdLabel.setFont(personalFont);
        prevSettingsPanel.add(hdLabel);
        
        hdInclude.setSelected(true);
        prevSettingsPanel.add(hdInclude);
        
        lhSettingsPanel.add(prevSettingsPanel);
        
        optionsPanel.add(lhSettingsPanel, java.awt.BorderLayout.CENTER);
        
        rhSettingsPanel.setMinimumSize(new java.awt.Dimension(300, 280));
        rhSettingsPanel.setPreferredSize(new java.awt.Dimension(300, 280));
        rhSettingsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 5));
        
        orderSettingsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Order settings", TitledBorder.LEFT, TitledBorder.TOP, personalFont));
        orderSettingsPanel.setPreferredSize(new java.awt.Dimension(280, 260));
        orderSettingsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        
        // 右侧选项 - 第一项
        extractByLabel.setText("Extract By: ");
        extractByLabel.setFont(personalFont);
        
        byGroup.add(byRowButton);
        byRowButton.setSelected(true);
        byRowButton.setText("Row");
        byRowButton.setFont(personalFont);
        
        byGroup.add(byColumnButton);
        byColumnButton.setText("Column");
        byColumnButton.setFont(personalFont);
        
        GroupLayout extractByPanelLayout = new GroupLayout(extractByPanel);
        extractByPanel.setLayout(extractByPanelLayout);
        extractByPanel.setPreferredSize(new java.awt.Dimension(250, 50));
        extractByPanelLayout.setHorizontalGroup(extractByPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(extractByPanelLayout.createSequentialGroup()
                        .addComponent(extractByLabel))
                .addGroup(extractByPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(byRowButton)
                        .addGap(10, 10, 10)
                        .addComponent(byColumnButton))
        );
        extractByPanelLayout.setVerticalGroup(extractByPanelLayout.createSequentialGroup()
                .addGroup(extractByPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(extractByLabel))
                .addGroup(extractByPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(byRowButton)
                        .addGap(10, 10, 10)
                        .addComponent(byColumnButton))
                .addGap(10, 10, 10)
        );
        orderSettingsPanel.add(extractByPanel);
        
        // 右侧选项 - 第二项
        bitOrderLabel.setText("Bit Order: ");
        bitOrderLabel.setFont(personalFont);
        
        bitGroup.add(MSBButton);
        MSBButton.setSelected(true);
        MSBButton.setText("MSB First");
        MSBButton.setFont(personalFont);
        
        bitGroup.add(LSBButton);
        LSBButton.setText("LSB First");
        LSBButton.setFont(personalFont);
        
        GroupLayout bitOrderPanelLayout = new GroupLayout(bitOrderPanel);
        bitOrderPanel.setLayout(bitOrderPanelLayout);
        bitOrderPanel.setPreferredSize(new java.awt.Dimension(250, 50));
        bitOrderPanelLayout.setHorizontalGroup(bitOrderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(bitOrderPanelLayout.createSequentialGroup()
                        .addComponent(bitOrderLabel))
                .addGroup(bitOrderPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(MSBButton)
                        .addGap(10, 10, 10)
                        .addComponent(LSBButton))
        );
        bitOrderPanelLayout.setVerticalGroup(bitOrderPanelLayout.createSequentialGroup()
                .addGroup(bitOrderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(bitOrderLabel))
                .addGroup(bitOrderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(MSBButton)
                        .addGap(10, 10, 10)
                        .addComponent(LSBButton))
                .addGap(10, 10, 10)
        );
        orderSettingsPanel.add(bitOrderPanel);
        
        // 右侧选项 - 第三项
        bitPlaneOrderLabel.setText("Bit Plane Order: ");
        bitPlaneOrderLabel.setFont(personalFont);
        
        planeGroup.add(RGBButton);
        RGBButton.setSelected(true);
        RGBButton.setText("RGB");
        RGBButton.setFont(personalFont);
        
        planeGroup.add(RBGButton);
        RBGButton.setText("RBG");
        RBGButton.setFont(personalFont);
        
        planeGroup.add(GBRButton);
        GBRButton.setText("GBR");
        GBRButton.setFont(personalFont);
        
        planeGroup.add(GRBButton);
        GRBButton.setText("GRB");
        GRBButton.setFont(personalFont);
        
        planeGroup.add(BRGButton);
        BRGButton.setText("BRG");
        BRGButton.setFont(personalFont);
        
        planeGroup.add(BGRButton);
        BGRButton.setText("BGR");
        BGRButton.setFont(personalFont);
        
        GroupLayout bitPlaneOrderPanelLayout = new GroupLayout(bitPlaneOrderPanel);
        bitPlaneOrderPanel.setLayout(bitPlaneOrderPanelLayout);
        bitPlaneOrderPanel.setPreferredSize(new java.awt.Dimension(250, 120));
        bitPlaneOrderPanelLayout.setHorizontalGroup(bitPlaneOrderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(bitPlaneOrderPanelLayout.createSequentialGroup()
                        .addComponent(bitPlaneOrderLabel))
                .addGroup(bitPlaneOrderPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(bitPlaneOrderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(bitPlaneOrderPanelLayout.createSequentialGroup()
                                        .addComponent(RGBButton)
                                        .addGap(15, 15, 15)
                                        .addComponent(GBRButton)
                                        .addGap(15, 15, 15)
                                        .addComponent(BRGButton))
                                .addGroup(bitPlaneOrderPanelLayout.createSequentialGroup()
                                        .addComponent(BGRButton)
                                        .addGap(15, 15, 15)
                                        .addComponent(RBGButton)
                                        .addGap(15, 15, 15)
                                        .addComponent(GRBButton))))
        );
        bitPlaneOrderPanelLayout.setVerticalGroup(bitPlaneOrderPanelLayout.createSequentialGroup()
                .addGroup(bitPlaneOrderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(bitPlaneOrderLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bitPlaneOrderPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(RGBButton).addComponent(GBRButton).addComponent(BRGButton))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bitPlaneOrderPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(BGRButton).addComponent(RBGButton).addComponent(GRBButton))
        );
        
        orderSettingsPanel.add(bitPlaneOrderPanel);
        rhSettingsPanel.add(orderSettingsPanel);
        optionsPanel.add(rhSettingsPanel, java.awt.BorderLayout.EAST);
        optionsPanel.getAccessibleContext().setAccessibleName("saveText");
        
        getContentPane().add(optionsPanel, java.awt.BorderLayout.CENTER);
        
        exPreviewPanel.setLayout(new java.awt.BorderLayout());
        
        jPreview.setRows(12);
        jPreview.setColumns(20);
        jPreview.setEditable(false);
        jPreview.setFont(new java.awt.Font("Consolas", Font.PLAIN, 14));
        jPreview.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jScrollPane1.setViewportView(jPreview);
        
        exPreviewPanel.add(jScrollPane1, java.awt.BorderLayout.PAGE_END);
        
        getContentPane().add(exPreviewPanel, java.awt.BorderLayout.NORTH);
        
        previewButton.setText("Preview");
        previewButton.setFont(personalFont);
        previewButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                previewButtonActionPerformed(evt);
            }
        });
        buttonsPanel.add(previewButton);
        previewButton.getAccessibleContext().setAccessibleName("previewButton");
        
        saveTextButton.setText("Save Text");
        saveTextButton.setFont(personalFont);
        saveTextButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                saveTextButtonActionPerformed(evt);
            }
        });
        buttonsPanel.add(saveTextButton);
        saveTextButton.getAccessibleContext().setAccessibleName("saveTextButton");
        
        saveBinButton.setText("Save Bin");
        saveBinButton.setFont(personalFont);
        saveBinButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                saveBinButtonActionPerformed(evt);
            }
        });
        buttonsPanel.add(saveBinButton);
        saveBinButton.getAccessibleContext().setAccessibleName("saveBinButton");
        
        cancelButton.setText("Cancel");
        cancelButton.setFont(personalFont);
        cancelButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cancelButtonActionPerformed(evt);
            }
        });
        buttonsPanel.add(cancelButton);
        cancelButton.getAccessibleContext().setAccessibleName("cancelButton");
        
        getContentPane().add(buttonsPanel, java.awt.BorderLayout.SOUTH);
        
        pack();
        
        this.setTitle("Data Extract");
        this.setMinimumSize(new java.awt.Dimension(820, 560));
        this.setLocationRelativeTo(null); // 将窗口放置在屏幕正中央
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    private void checkAllListener(JCheckBox ca, JCheckBox c7, JCheckBox c6, JCheckBox c5, JCheckBox c4, JCheckBox c3, JCheckBox c2, JCheckBox c1, JCheckBox c0)
    {
        ca.addItemListener(e ->
        {
            c7.setSelected(ca.isSelected());
            c6.setSelected(ca.isSelected());
            c5.setSelected(ca.isSelected());
            c4.setSelected(ca.isSelected());
            c3.setSelected(ca.isSelected());
            c2.setSelected(ca.isSelected());
            c1.setSelected(ca.isSelected());
            c0.setSelected(ca.isSelected());
        });
    }
    
    private void previewButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        generateExtract();
        generatePreview();
    }
    
    private void saveTextButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        generateExtract();
        generatePreview();
        savePreview();
    }
    
    private void saveBinButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        generateExtract();
        saveExtract();
    }
    
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        dispose();
    }
}
