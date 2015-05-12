package cn.looip.core.utils;


import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通用工具方法接口定义
 * @author nice.man 2015.01.07
 */
public interface GeneralTools {
	
	/**
	 * MD5加密字符�?
	 * @param str �?要加密的字符�?
	 * @return 返回加密后的字符�?
	 */
	public String MD5Make(String str);
	
	/**
	 * AES加密
	 * @param content 加密内容
	 * @param key 密钥
	 * @return	加密数据
	 */
	public String encrypt(String con);
	
	/**
	 * AES加密
	 * @param content 加密内容
	 * @param key 密钥
	 * @return	加密数据
	 */
	public  String decrypt(String con);
	
	/**
	 * 添加数据到cookie
	 * @param response 响应对象
	 * @param name	cookie名称
	 * @param value cookie�?
	 * @param time  保存时间
	 * @return 返回cookie对象
	 */
	public Cookie addCookie(HttpServletResponse response, String name,
			String value, Integer time);

	/**
	 * 获取cookie
	 * @param request 请求对象
	 * @param name	  cookie名称
	 * @return 返回cookie对象
	 */
	public Cookie getCookieByName(HttpServletRequest request,
			String name);

	/**
	 * 获取cookie的�??
	 * @param cookie cookie对象
	 * @return cookie中保存的�?
	 */
	public String getCookieValue(Cookie cookie);
	
	/**
	 * 格式化时�?
	 * @param date 时间
	 * @param format 参数
	 * @return 格式化后的时�?
	 */
	public String formatDate(Date date ,String format);
	
	/**
	 * 获得起始行数
	 * @param pagenum 页码�?
	 * @param maxNum  �?大结果集�?
	 * @return 得到的起始行�?
	 */
	public Integer getStartNum (Integer pagenum,Integer maxNum);
	
	/**
	 * 获得总页�?
	 * @param rowscount 结果总数
	 * @param maxNum  �?大结果集�?
	 * @return 总页�?
	 */
	public Integer getAllPage (Integer rowscount,Integer maxNum);
	
	/**
	 * 字符串转Long�?
	 * @param str 待转字符�?	
	 * @return Long�?
	 */
	public Long formatStringToLong(String str);
	
	/**
	 * 解析格式化字符串 
	 * @param str 形如�?1�?2�?3�?
	 * @param param 格式参数
	 * @return 返回字符串数�?
	 */
	public String [] formatStringSplit(String str,String param);
	
	
	
	
}