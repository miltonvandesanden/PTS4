/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayer;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;

/**
 *
 * @author Nick
 */
public class PhotoProfile {          

    public PhotoProfile()
    {
        
    }
    
    public String GetAllImages(ResultSet rs) throws SQLException, IOException, ClassNotFoundException
    {
        BufferedImage img = null;
        ResultSet resultSet = rs;               
        
        int imgId = resultSet.getInt(1);                                 
        Blob b = resultSet.getBlob(6);                 
        byte[] baa= b.getBytes(1, (int)b.length());                 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteArrayInputStream bais = new ByteArrayInputStream(baa);
        img = ImageIO.read(bais);
        ImageIO.write( img, "png", baos ); //write the image so it's in the baos
        baos.flush();
        baa = baos.toByteArray();//load all bytes in the image
        baos.close();
        String b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(baa);
        return b64;
    }
    
    public BufferedImage GetSingleImage(ResultSet rs) throws SQLException, IOException
    {
        BufferedImage img = null;
        ResultSet resultSet = rs;    
        int imgId = resultSet.getInt(1);                                 
        Blob b = resultSet.getBlob(6);                 
        byte[] baa= b.getBytes(1, (int)b.length());                 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteArrayInputStream bais = new ByteArrayInputStream(baa);
        img = ImageIO.read(bais);
        ImageIO.write( img, "png", baos ); //write the image so it's in the baos
        baos.flush();
        baa = baos.toByteArray();//load all bytes in the image
        baos.close();
        return img;
    }
    
    public String GetBlackAndWhite(ResultSet rs) throws SQLException, IOException
    {
        BufferedImage img = null;
        ResultSet resultSet = rs;               
        int nColors = 2;
        byte[] reds   = new byte[]{0,(byte)255};
        byte[] greens = new byte[]{0,(byte)255};
        byte[] blues  = new byte[]{0,(byte)255};
        IndexColorModel colorModel = new IndexColorModel(2,nColors,reds,greens,blues);        
        

        int imgId = resultSet.getInt(1);                                 
        Blob b = resultSet.getBlob(6);                 
        byte[] baa= b.getBytes(1, (int)b.length());                 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteArrayInputStream bais = new ByteArrayInputStream(baa);
        img = ImageIO.read(bais);
        BufferedImage img2 = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        //WritableRaster raster = img.getRaster();
        //for(int h=0;h<img.getHeight();h++)
        //for(int w=0;w<img.getWidth();w++)
        //if (((h/50)+(w/50)) % 2 == 0) raster.setSample(w,h,0,0); // checkerboard pattern.
        //else raster.setSample(w,h,0,1);
        Graphics2D g2d = img2.createGraphics();
        g2d.drawImage(img,0,0,null);
        ImageIO.write( img2, "png", baos ); //write the image so it's in the baos        
        baos.flush();
        baa = baos.toByteArray();//load all bytes in the image
        baos.close();
        String b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(baa);
        return b64;
    }
}
