package com.alone.web.controller;

import com.alone.web.basic.controller.BasicController;
import com.alone.web.utils.PathConst;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"index"})
public class IndexController
  extends BasicController
{
  @RequestMapping({"aboutAloneCommunity"})
  public String infoAloneCommunity()
  {
    return PathConst.BASE_PATH + "/index/welcome";
  }
  
  @RequestMapping({"index"})
  public String index()
  {
    return PathConst.BASE_PATH + "/index/index";
  }
}
