import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.image.BufferedImage;
import java.util.TooManyListenersException;


public class DPanel extends JPanel
{
    private final Dimension defaultSize = new Dimension();
    private final Dimension currentSize = new Dimension();
    private boolean dragOver = false;
    private BufferedImage bi = null;
    private DropTarget dropTarget;
    private DropTargetHandler dropTargetHandler;
    private Point dragPoint;
    private Dimension preferredSize = new Dimension(200, 200);
    
    public DPanel(){}
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (bi != null)
            g.drawImage(bi, 0, 0, currentSize.width, currentSize.height, this);
    }
    
    
    public void setImage(BufferedImage bix)
    {
        bi = bix;
        defaultSize.width = bi.getWidth();
        defaultSize.height = bi.getHeight();
        setSize(bi.getWidth(), bi.getHeight());
        repaint();
        
    }
    
    @Override
    public Dimension getPreferredSize()
    {
        return preferredSize;
    }
    
    public void apply(int percent)
    {
        currentSize.width = (int) (defaultSize.width * (((float) percent) / 100));
        currentSize.height = (int) (defaultSize.height * (((float) percent) / 100));
        preferredSize = currentSize;
        revalidate();
        repaint();
    }
    
    protected DropTarget getMyDropTarget()
    {
        if (dropTarget == null)
        {
            dropTarget = new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE, null);
        }
        return dropTarget;
    }
    
    protected DropTargetHandler getDropTargetHandler()
    {
        if (dropTargetHandler == null)
        {
            dropTargetHandler = new DropTargetHandler();
        }
        return dropTargetHandler;
    }
    
    @Override
    public void addNotify()
    {
        super.addNotify();
        try
        {
            getMyDropTarget().addDropTargetListener(getDropTargetHandler());
        }
        catch (TooManyListenersException ex)
        {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void removeNotify()
    {
        super.removeNotify();
        getMyDropTarget().removeDropTargetListener(getDropTargetHandler());
    }
    
    protected class DropTargetHandler implements DropTargetListener
    {
        
        protected void processDrag(DropTargetDragEvent dtde)
        {
            if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor))
            {
                dtde.acceptDrag(DnDConstants.ACTION_COPY);
            }
            else
            {
                dtde.rejectDrag();
            }
        }
        
        @Override
        public void dragEnter(DropTargetDragEvent dtde)
        {
            processDrag(dtde);
            SwingUtilities.invokeLater(new DragUpdate(true, dtde.getLocation()));
            repaint();
        }
        
        @Override
        public void dragOver(DropTargetDragEvent dtde)
        {
            processDrag(dtde);
            SwingUtilities.invokeLater(new DragUpdate(true, dtde.getLocation()));
            repaint();
        }
        
        @Override
        public void dropActionChanged(DropTargetDragEvent dtde)
        {
        }
        
        @Override
        public void dragExit(DropTargetEvent dte)
        {
            SwingUtilities.invokeLater(new DragUpdate(false, null));
            repaint();
        }
        
        @Override
        public void drop(DropTargetDropEvent dtde)
        {
            
            SwingUtilities.invokeLater(new DragUpdate(false, null));
            
            Transferable transferable = dtde.getTransferable();
            if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor))
            {
                dtde.acceptDrop(dtde.getDropAction());
                try
                {
                    java.util.List transferData = (java.util.List) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                    
                    if (transferData.size() == 1)
                    {
                        StegSolve.that.loadImage(String.valueOf(transferData.get(0)));
                        dtde.dropComplete(true);
                    }
                    
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            else
            {
                dtde.rejectDrop();
            }
        }
    }
    
    public class DragUpdate implements Runnable
    {
        
        private final boolean dragOver;
        private final Point dragPoint;
        
        public DragUpdate(boolean dragOver, Point dragPoint)
        {
            this.dragOver = dragOver;
            this.dragPoint = dragPoint;
        }
        
        @Override
        public void run()
        {
            DPanel.this.dragOver = dragOver;
            DPanel.this.dragPoint = dragPoint;
            DPanel.this.repaint();
        }
    }
}
