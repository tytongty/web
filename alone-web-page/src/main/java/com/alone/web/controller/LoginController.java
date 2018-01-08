package com.alone.web.controller;

import com.alone.web.basic.controller.BasicController;
import com.alone.web.entity.AdminPermission;
import com.alone.web.entity.AdminRole;
import com.alone.web.entity.AdminUser;
import com.alone.web.service.AdminUserService;
import com.alone.web.utils.AjaxBean;
import com.alone.web.utils.Crypto;
import com.alone.web.utils.PathConst;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/login"})
public class LoginController
  extends BasicController
{
  @Autowired
  private AdminUserService adminUserService;
  
  @RequestMapping({"show"})
  public String show()
  {
    return PathConst.BASE_PATH+"/login/index";
  }
  
  @RequestMapping({"doLogin"})
  @ResponseBody
  public AjaxBean login(AdminUser adminUser, AjaxBean ajaxBean, String code, HttpServletRequest request)
  {
    Subject subject = SecurityUtils.getSubject();
    String codeValue = (String)request.getSession().getAttribute("capture");
    if (!codeValue.toLowerCase().equals(code.toLowerCase()))
    {
      ajaxBean.setMsg("验证码不对");
      ajaxBean.setSucFlag(Integer.valueOf(0));
      return ajaxBean;
    }
    UsernamePasswordToken token = new UsernamePasswordToken(adminUser.getUsername(), adminUser.getPassword());
    try
    {
      subject.login(token);
      ajaxBean.setSucFlag(Integer.valueOf(1));
      
      List<AdminPermission> parentPermissionList = this.adminUserService
        .findPermissionAllByUsername(adminUser.getUsername());
      
      request.getSession().setAttribute("parentPermissionList", parentPermissionList);
      
      AdminRole role = this.adminUserService.findRoleByUsername(adminUser);
      request.getSession().setAttribute("role", role);
    }
    catch (AuthenticationException e)
    {
      ajaxBean.setMsg("账户或密码错误");
      ajaxBean.setSucFlag(Integer.valueOf(0));
      adminUser.setUsername("");
      e.printStackTrace();
    }
    request.getSession().setAttribute("user", adminUser);
    return ajaxBean;
  }
  
  @RequestMapping({"register"})
  public ModelAndView register(ModelAndView mv, AdminUser adminUser)
  {
    mv.setViewName(PathConst.BASE_PATH+"/login/register");
    return mv;
  }
  
  @RequestMapping({"registerSave"})
  public ModelAndView registerSave(ModelAndView mv, AdminUser adminUser)
  {
    try
    {
      String oldPassword = adminUser.getPassword();
      
      String newPassword = Crypto.cryptoPassword(oldPassword, adminUser.getUsername());
      ByteSource salt = Crypto.cryptoSalt(adminUser.getUsername());
      String id = Crypto.getUUID();
      adminUser.setPassword(newPassword);
      adminUser.setSalt(salt.toString());
      adminUser.setUserId(id);
      adminUser.setRoleId("super");
      this.adminUserService.saveRegister(adminUser);
      mv.setViewName(PathConst.BASE_PATH+"/login/index");
    }
    catch (Exception e)
    {
      mv.setViewName(PathConst.BASE_PATH+"/login/fail");
      e.printStackTrace();
    }
    return mv;
  }
  
  @RequestMapping({"loginOut"})
  public ModelAndView loginOut(ModelAndView mv)
  {
    Subject subject = SecurityUtils.getSubject();
    subject.logout();
    mv.setViewName(PathConst.BASE_PATH+"/login/index");
    return mv;
  }
  
  @RequestMapping({"getMenu"})
  @ResponseBody
  public List<AdminPermission> getMenu(String username)
  {
    List<AdminPermission> parentPermissionList = this.adminUserService
      .findPermissionAllByUsername(username);
    return parentPermissionList;
  }
  
  @RequestMapping({"logined"})
  @ResponseBody
  public List<AdminPermission> login(AdminUser adminUser, HttpServletRequest request)
  {
    Subject subject = SecurityUtils.getSubject();
    List<AdminPermission> menuList = new ArrayList();
    UsernamePasswordToken token = new UsernamePasswordToken(adminUser.getUsername(), adminUser.getPassword());
    try
    {
      subject.login(token);
      
      menuList = this.adminUserService
        .findPermissionAllByUsername(adminUser.getUsername());
      
      request.getSession().setAttribute("parentPermissionList", menuList);
      
      AdminRole role = this.adminUserService.findRoleByUsername(adminUser);
      request.getSession().setAttribute("role", role);
    }
    catch (AuthenticationException e)
    {
      e.printStackTrace();
    }
    request.getSession().setAttribute("user", adminUser);
    return menuList;
  }
  
  @RequestMapping({"capture"})
  public void getCapture(HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
    response.setHeader("Pragma", "No-Cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expries", 0L);
    
    Random random = new Random();
    
    int size = 5;
    
    String vCode = "";
    for (int i = 0; i < size; i++)
    {
      int number = random.nextInt(26);
      char c;
      if (number % 2 == 0) {
        c = (char)('0' + (char)(int)(Math.random() * 10.0D));
      } else {
        c = (char)((char)(int)(Math.random() * 26.0D) + 'A');
      }
      vCode = vCode + c;
    }
    request.getSession().setAttribute("capture", vCode);
    
    int width = (int)Math.ceil(size * 20);
    int height = 41;
    BufferedImage image = new BufferedImage(width, height, 1);
    
    Graphics gr = image.getGraphics();
    
    gr.setColor(Color.WHITE);
    gr.fillRect(0, 0, width, height);
    
    gr.setColor(Color.GRAY);
    gr.drawRect(0, 0, width - 1, height - 1);
    for (int i = 0; i < 5; i++)
    {
      int x1 = random.nextInt(width);
      int y1 = random.nextInt(height);
      int x2 = random.nextInt(width);
      int y2 = random.nextInt(height);
      gr.setColor(randomColor());
      gr.drawLine(x1, y1, x2, y2);
    }
    gr.setColor(randomColor());
    gr.setFont(randomFont());
    gr.drawString(vCode, 10, 22);
    
    gr.dispose();
    
    ImageIO.write(image, "JPEG", response.getOutputStream());
  }
  
  private Font randomFont()
  {
    String[] fontNames = { "宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312" };
    Random r = new Random();
    int index = r.nextInt(fontNames.length);
    String fontName = fontNames[index];
    int style = r.nextInt(4);
    int size = r.nextInt(3) + 24;
    return new Font(fontName, style, size);
  }
  
  private Color randomColor()
  {
    Random r = new Random();
    int red = r.nextInt(150);
    int green = r.nextInt(150);
    int blue = r.nextInt(150);
    return new Color(red, green, blue);
  }
}
