package com.alone.web.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.imageio.ImageIO;
import org.junit.Test;

public class CodeTest
  extends ClassLoader
{
  @Test
  public void testEncoder()
  {
    String text = "http://www.baidu.com";
    int width = 200;
    int height = 200;
    String format = "png";
    
    Hashtable<EncodeHintType, String> hints = new Hashtable();
    hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
    BitMatrix bitMatrix = null;
    try
    {
      bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
    }
    catch (WriterException e1)
    {
      e1.printStackTrace();
    }
    File outputFile = new File("src/main/webapp/static/myResources/images/2." + format);
    
    BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
    try
    {
      MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  protected URL findResource(String name)
  {
    String url = "src/main/webapp/static/myResources/images/" + name;
    URL url2 = null;
    try
    {
      url2 = new URL(url);
    }
    catch (MalformedURLException e)
    {
      e.printStackTrace();
    }
    return url2;
  }
  
  @Test
  public void testDecoder()
    throws NotFoundException
  {
    String path = findResource("2.png").getPath();
    
    File imageFile = new File(path);
    BufferedImage image = null;
    try
    {
      image = ImageIO.read(imageFile);
      LuminanceSource source = new BufferedImageLuminanceSource(image);
      Binarizer binarizer = new HybridBinarizer(source);
      BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
      Map<DecodeHintType, Object> hints = new HashMap();
      hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
      
      Result result = new MultiFormatReader().decode(binaryBitmap, hints);
      System.out.println(result.getText());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
