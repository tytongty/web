package com.alone.web.utils;

import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 加密工具
 * @author Administrator
 *
 */
public class Crypto {

	/**
	 * 密码加密
	 * @param password
	 * @param username
	 * @return
	 */
  public  static String cryptoPassword(String password,String username){
	  ByteSource salt =  cryptoSalt(username);
	  SimpleHash hashPassword = new SimpleHash(MyConst.ALGORITHMNAME, password, salt, MyConst.ITERATIONS);
	  return hashPassword.toString();
  }
  /**
   * 盐加密
   * @param username
   * @return
   */
  public static ByteSource cryptoSalt(String username){
	  SimpleHash salt = new SimpleHash(MyConst.ALGORITHMNAME, MyConst.SALT+username);//MyConst.SALT+username 加密
	  
	  return ByteSource.Util.bytes(salt);
  }
  
  /**
   * uuid加密并获取
   * @return
   */
  public static String getUUID(){
	  String uuid = new SimpleHash(MyConst.ALGORITHMNAME, UUID.randomUUID().toString()).toString();
	  return uuid;
  }
}
