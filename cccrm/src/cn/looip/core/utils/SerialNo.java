package cn.looip.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 生成项目编号
 * @author gaoxiang
 *
 */
public class SerialNo {
	@SuppressWarnings("unused")
	private static long n=1;
	public static long serialNo(long i){
		String str=new SimpleDateFormat("yyyyMM").format(new Date());
		long m=Long.parseLong((str))*10000;
		long ret=m+i;
		n=i+1;
		return ret;
	}
}
