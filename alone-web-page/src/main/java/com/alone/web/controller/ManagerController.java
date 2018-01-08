package com.alone.web.controller;

import com.alone.web.basic.controller.BasicController;
import com.alone.web.utils.PathConst;
import com.alone.web.utils.QRCodeUtils;
import com.beust.jcommander.Strings;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/manager"})
public class ManagerController extends BasicController
{
  @GetMapping({"case"})
  public ModelAndView show3(ModelAndView mav, HttpServletRequest request, HttpServletResponse response, String message)
    throws IOException, ServletException
  {
    super.getMenuName(request);
    
    mav.setViewName(PathConst.BASE_PATH+"/manager/show");
    return mav;
  }
  
  @RequestMapping({"show"})
  public ModelAndView show(HttpServletRequest request, String message, HttpServletResponse response,ModelAndView mv)
    throws IOException, ServletException{
    if (Strings.isStringEmpty(message)) {
      message = "123";
    }
    BufferedImage image = QRCodeUtils.getQRCode(message, 200, 200, ".png");
    String pathName = new Date().getTime() + ".png";
    String realPath = request.getSession().getServletContext().getRealPath("/static/myResources/images");
    FileOutputStream outputStream = new FileOutputStream(new File(realPath, pathName));
    ImageIO.write(image, "png", outputStream);
    outputStream.flush();
    mv.setViewName(PathConst.BASE_PATH+"/manager/show");
    mv.addObject("pathName", pathName);
    return mv;
  }
}
