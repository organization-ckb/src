package cn.looip.core.utils;
import static cn.looip.core.secretkey.SecretKeyConstant.AES_SECRET_KEY;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**
 * 通用工具方法接口实现
 * @author nice.man 2015.01.07
 */
@Component
public class GeneralToolsImpl implements GeneralTools { 

	//MD5加密字符
	public String MD5Make(String str) {
		MessageDigest md5 = null;
		StringBuffer hexValue = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
		if(str!=null && !str.trim().equals(""))
		{
			char[] charArray = str.toCharArray();
	
			byte[] byteArray = new byte[charArray.length];
	
			for (int i = 0; i < charArray.length; i++)
				byteArray[i] = (byte) charArray[i];
	
			byte[] md5Bytes = md5.digest(byteArray);
	
			hexValue = new StringBuffer();
	
			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16)
					hexValue.append("0");
				hexValue.append(Integer.toHexString(val));
			}
		}
		return hexValue.toString();
	}

	//AES加密
	public String encrypt(String con) {   
		if(con!=null && !con.trim().equals(""))
		{
	        try {              
	                KeyGenerator kgen = KeyGenerator.getInstance("AES");   
	                kgen.init(128, new SecureRandom(AES_SECRET_KEY.getBytes()));   
	                SecretKey secretKeyObj = kgen.generateKey();   
	                byte[] enCodeFormat = secretKeyObj.getEncoded();   
	                SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");   
	                Cipher cipher = Cipher.getInstance("AES");// 创建密码�?   
	                byte[] byteContent = con.getBytes("utf-8");   
	                cipher.init(Cipher.ENCRYPT_MODE, key);// 初始�?   
	                byte[] result = cipher.doFinal(byteContent); 
	                
	                return this.parseByte2HexStr(result); // 加密  .
	                
	        } catch (NoSuchAlgorithmException e) {   
	        	return null;    
	        } catch (NoSuchPaddingException e) {   
	        	return null;   
	        } catch (InvalidKeyException e) {   
	        	return null;   
	        } catch (UnsupportedEncodingException e) {   
	        	return null;   
	        } catch (IllegalBlockSizeException e) {   
	        	return null;   
	        } catch (BadPaddingException e) {   
	        	return null;   
	        }   
		}
        return null;   
}   
	
	//AES解密
	public String decrypt(String con) {   
		if(con!=null && !con.trim().equals(""))
		{
	        try {   
	        		byte[] content = this.parseHexStr2Byte(con);
	                KeyGenerator kgen = KeyGenerator.getInstance("AES");   
	                kgen.init(128, new SecureRandom(AES_SECRET_KEY.getBytes()));   
	                SecretKey secretKey = kgen.generateKey();   
	                byte[] enCodeFormat = secretKey.getEncoded();   
	                SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");               
	                Cipher cipher = Cipher.getInstance("AES");// 创建密码�?   
	                cipher.init(Cipher.DECRYPT_MODE, key);// 初始�?   
	                byte[] result = cipher.doFinal(content);   
	                String strResult =  new String(result,"UTF-8");
	                return strResult; // 加密   
	                
	        } catch (NoSuchAlgorithmException e) {   
	        	 return null;     
	        } catch (NoSuchPaddingException e) {   
	        	 return null;   
	        } catch (InvalidKeyException e) {   
	        	 return null;      
	        } catch (IllegalBlockSizeException e) { 
	        	e.printStackTrace();
	        	 return null;    
	        } catch (BadPaddingException e) {   
	        	 return null;   
	        } catch (UnsupportedEncodingException e) {
	        	 return null;   
			}   
		}
        return null;   
	}  
    
	//将二进制转换�?16进制  
	private String parseByte2HexStr(byte buf[]) {  
	        StringBuffer sb = new StringBuffer();  
	        for (int i = 0; i < buf.length; i++) {  
	                String hex = Integer.toHexString(buf[i] & 0xFF);  
	                if (hex.length() == 1) {  
	                        hex = '0' + hex;  
	                }  
	                sb.append(hex.toUpperCase());  
	        }  
	        return sb.toString();  
	}  
	
	//�?16进制转换为二进制   
	private byte[] parseHexStr2Byte(String hexStr) {  
	        if (hexStr.length() < 1)  
	                return null;  
	        byte[] result = new byte[hexStr.length()/2];  
	        for (int i = 0;i< hexStr.length()/2; i++) {  
	                int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
	                int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
	                result[i] = (byte) (high * 16 + low);  
	        }  
	        return result;  
	}  
	//添加数据到cookie
	public Cookie addCookie(HttpServletResponse response, String name,
			String value, Integer time) {
		try {
			value = URLEncoder.encode(value,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		 Cookie cookie = new Cookie(name,value); 
		 if(time!=null)
		 {
			 cookie.setMaxAge(time);
		 }
		 cookie.setHttpOnly(true);
		 response.addCookie(cookie);
		 return cookie;
	}

	//获取cookie
	public Cookie getCookieByName(HttpServletRequest request, String name) {
		
		Cookie[] cookies = request.getCookies();  
		if(cookies != null){  
			for(Cookie cookie : cookies){  
				if(cookie.getName().equals(name))
				{
					return cookie;
				}
		    }  
		}  
		return null;		
	}	
	//获取cookie�?
	public String getCookieValue(Cookie cookie)
	{
		if(cookie!=null)
		{
			String value = cookie.getValue();
			try {
				value = URLDecoder.decode(value,"UTF-8");
			} catch (UnsupportedEncodingException e) {				
				e.printStackTrace();
			}
			return value;
		}
		return null;
	}
	
	//时间字符�?
	public String formatDate(Date date ,String format) {
		SimpleDateFormat formater = new SimpleDateFormat(format);
		String folder = formater.format(date);
		return folder;
	}
	//将字符串转换成Long类型
	public Long formatStringToLong(String str)
	{
		Long num = null;
		try
		{
			num = Long.parseLong(str);
		}catch(Exception e)
		{
			return null;
		}
		return num;
	}
	
	/**
	 * 获得起始行数
	 * @param pagenum 页码�?
	 * @param maxNum  �?大结果集�?
	 * @return 得到的起始行�?
	 */
	public Integer getStartNum (Integer pagenum,Integer maxNum)
	{
		Integer startNum = maxNum*(pagenum-1);
		return startNum;
	}
	/**
	 * 获得总页�?
	 * @param rowscount 结果总数
	 * @param maxNum  �?大结果集�?
	 * @return 总页�?
	 */
	public Integer getAllPage (Integer rowscount,Integer maxNum)
	{
		Integer result = 0;
		int results = rowscount/maxNum;
		if (rowscount%maxNum==0)
		{
			result = results;
		}
		else
		{
			result = results+1;
		}
		return result;
	}
	/**
	 * 解析格式化字符串 
	 * @param str 形如�?1�?2�?3�?
	 * @return 返回字符串数�?
	 */
	public String [] formatStringSplit(String str,String param)
	{
		String [] result = null;
		try
		{
			result = str.split(param);
		}catch(Exception e)
		{
			result = null;
		}		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
