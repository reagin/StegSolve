import javax.swing.*;
import java.awt.*;

public class AboutFrame extends javax.swing.JFrame
{
    private javax.swing.JPanel aboutPanel;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton okButton;
    private Font personalFont = new Font("Consolas", Font.PLAIN, 14);
    
    public AboutFrame()
    {
        initComponents();
        jEditorPane1.setCaretPosition(0);
    }
    
    private void initComponents()
    {
        
        okButton = new javax.swing.JButton();
        aboutPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        okButton.setText("OK");
        okButton.setFont(personalFont);
        okButton.setAlignmentX(0.5F);
        okButton.addActionListener(this::okButtonActionPerformed);
        
        aboutPanel.setPreferredSize(new java.awt.Dimension(450, 300));
        
        jEditorPane1.setContentType("text/html");
        jEditorPane1.setEditable(false);
        jEditorPane1.setText("<html>\n<center><b>StegSolve</b></center>\n<br>\nStegsolve is a stegano solver for challenges. It provides these main functions:\n<ul>\n<li>A quick view of different bit planes and some simple transformations.</li>\n<li>Data extraction from planes. This can be row order or column order, with bits treated as a bitstream and converted into bytes.</li>\n<li>Some simple checking of file formats and reporting on the filesize, additional bytes, file holes, etc. This is highly dependent upon the type of image.</li>\n<li>Stereogram solver - simply change the offset until the image becomes visible.</li>\n<li>Frame browser for animated images. This should also work for viewing layers in multi-layered PNG files.</li>\n<li>Image combiner to combine two images in a variety of ways and browse through the different combinations.</li>\n</ul>\n<p>Copy/Cut and paste is available from most text using CTRL-C to copy, CTRL-V to paste and CTRL-X for cut.\n<p>If an image fails to load, for example because it is corrupt, then file analysis will still open the file that you just tried to view. It may, however, crash out before reporting the information that you want to know. This will work though on images where the PNG has corrupted CRC values for example.\n</html>\n");
        jEditorPane1.setFont(personalFont);
        jEditorPane1.setMinimumSize(new java.awt.Dimension(150, 150));
        jEditorPane1.setPreferredSize(new java.awt.Dimension(150, 150));
        jScrollPane1.setViewportView(jEditorPane1);
        
        javax.swing.GroupLayout aboutPanelLayout = new javax.swing.GroupLayout(aboutPanel);
        aboutPanel.setLayout(aboutPanelLayout);
        aboutPanelLayout.setHorizontalGroup(aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE));
        aboutPanelLayout.setVerticalGroup(aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE));
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                                .addComponent(okButton))
                        .addComponent(aboutPanel, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
        );
        
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING,
                                layout.createSequentialGroup()
                                        .addComponent(aboutPanel, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGap(0)
                                        .addComponent(okButton))
        );
        pack();
        
        this.setSize(710, 480);
        this.setTitle("About StegSolve");
        this.setLocationRelativeTo(null); // 将窗口放置在屏幕正中央
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        dispose();
    }
}
