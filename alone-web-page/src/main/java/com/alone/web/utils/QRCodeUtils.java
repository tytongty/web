package com.alone.web.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.imageio.ImageIO;

public class QRCodeUtils
{
  public static final BufferedImage getQRCode(String content, int width, int height, String format)
  {
    Hashtable<EncodeHintType, String> hints = new Hashtable();
    hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
    BitMatrix bitMatrix = null;
    try
    {
      bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
    }
    catch (WriterException e1)
    {
      e1.printStackTrace();
    }
    BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
    
    return image;
  }
  
  public static final String deQRCode(String path)
  {
    File imageFile = new File(path);
    BufferedImage image = null;
    Result result = null;
    try
    {
      image = ImageIO.read(imageFile);
      LuminanceSource source = new BufferedImageLuminanceSource(image);
      Binarizer binarizer = new HybridBinarizer(source);
      BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
      Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
      hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
      
      result = new MultiFormatReader().decode(binaryBitmap, hints);
    }
    catch (Exception e)
    {
      e.getStackTrace();
    }
    return result.getText();
  }
}
