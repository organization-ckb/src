package cn.looip.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.junit.Test;

import cn.looip.core.utils.DateUtil;
import cn.looip.core.utils.HttpUtil;
import cn.looip.core.utils.JsonUtil;
import cn.looip.core.webconstant.WebConstant;

public class JUnitTest
{
	private static Logger logger = Logger.getLogger(WebConstant.PRODUCT_NAME);

	@Test
	public void test()
	{
		fail("Not yet implemented");
	}
	
	//测试添加车主车辆信息
	@Test
	public void testAddUserCarJson()
	{
//		fail("Not yet implemented");
//		UserCar userCar=new UserCar();
//		userCar.setCarName("宝来2014");
//		userCar.setCarSeed((long)2);
//		userCar.setCarAlias(""); //必填字段
//		userCar.setCarLicenseNo("");
//		userCar.setCarOwner("胡坚");
//		userCar.setInsuranceCompany("");
//		userCar.setKilometer((long)120);
//		
//		userCar.setCreateDate(new Date());
//		userCar.setUpdateDate(new Date());
//		JSONObject jsonObject 
//		= JsonUtil.getJsonObject4Object(userCar, DateUtil.STANDARD_DATE_TIME_FORMAT_STR);
//		
//		String url="http://localhost:8080/car_cms/user/addUserCarJson";
//		Map<String, String> params = new HashMap<String,String>();
//		params.put("json", jsonObject.toString());
//		String result = HttpUtil.doPost(url, params, "utf-8");
//		logger.info(result);
//		System.out.println(result);
	}

	@Test
	public void testHttpGet()
	{
		
		String url="http://gc.ditu.aliyun.com/regeocoding";
//		Map<String, String> params = new HashMap<String,String>();
//		params.put("json", jsonObject.toString());
		String result = HttpUtil.doGet(url, "l=39.938133,116.395739&type=001", "utf-8");
		logger.info(result);
		System.out.println(result);
	}
	
	@Test
	public void testHttpPost()
	{
		
		String url="http://gc.ditu.aliyun.com/regeocoding";
		Map<String, String> params = new HashMap<String,String>();
		params.put("l", "39.938133,116.395739");
		params.put("type", "001");
		String result = HttpUtil.doPost(url, params, "utf-8");
		logger.info(result);
		System.out.println(result);
	}
}
