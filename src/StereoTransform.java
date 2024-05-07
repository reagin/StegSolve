import java.awt.image.BufferedImage;


public class StereoTransform
{
    private int transNum;
    private BufferedImage transform;
    private BufferedImage originalImage;
    
    StereoTransform(BufferedImage bi)
    {
        transNum = 0;
        originalImage = bi;
        calcTrans();
    }
    
    private void calcTrans()
    {
        transform = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < originalImage.getWidth(); i++)
        {
            for (int j = 0; j < originalImage.getHeight(); j++)
            {
                int col = 0;
                int fcol = originalImage.getRGB(i, j);
                col = fcol ^ (originalImage.getRGB((i + transNum) % originalImage.getWidth(), j) & 0x00ffffff);
                transform.setRGB(i, j, col);
            }
        }
    }
    
    public void back()
    {
        transNum--;
        if (transNum < 0) transNum = originalImage.getWidth() - 1;
        calcTrans();
    }
    
    public void forward()
    {
        transNum++;
        if (transNum >= originalImage.getWidth()) transNum = 0;
        calcTrans();
    }
    
    public String getText()
    {
        return "Offset: " + transNum;
    }
    
    public BufferedImage getImage()
    {
        return transform;
    }
}
