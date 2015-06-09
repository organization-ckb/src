package cn.looip.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

/**
 * 日期格式化
 * 
 */
public final class DateUtil {

    public static final String STANDARD_DATE_TIME_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";

    public static final String STANDARD_DATE_FORMAT_STR = "yyyy-MM-dd";
    
//    public static final String STANDARD_DATE_FORMAT_STR1 = "yyyy-MM";

    public static final SimpleDateFormat SIMPLE_DATE_TIME_FORMAT = new SimpleDateFormat(
            STANDARD_DATE_TIME_FORMAT_STR);

    /**
     * 通用日期模式
     */
    private static final String[] GENERIC_DATE_PATTERNS = {
            STANDARD_DATE_TIME_FORMAT_STR, STANDARD_DATE_FORMAT_STR };

    /**
     * 日期字符串转化为日期
     * 
     * @param src
     *            日期字符串
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String str) {
    	try{
    		return DateUtils.parseDate(str, GENERIC_DATE_PATTERNS);
    	}catch(Exception e){
    		return null;
    	}
        
    }

    /**
     * 格式日期时间为系统的标准格式(yyyy-MM-dd HH:mm:ss)
     * 
     * @param date
     * @return
     * @throws ParseException
     */
    public static String formatDatetime(Date date) {
        return DateFormatUtils.format(date, STANDARD_DATE_TIME_FORMAT_STR);
    }

    /**
     * 格式日期为系统的标准格式(yyyy-MM-dd)
     * 
     * @param date
     * @return
     * @throws ParseException
     */
    public static String formatDate(Date date) {
        return DateFormatUtils.format(date, STANDARD_DATE_FORMAT_STR);
    }
    
    public static List<String> checkIntervalWeekTime(String[] params){
//        String[] detailParams=splitIntervalDateTime(params);
        List<String> duplicateResult=new ArrayList<String>();
        for(int i=0;i<params.length-1;i++){
            for(int j=i+1;j<params.length;j++){
                if(checkWeekTimeIntersect(params[i],params[j])){
                    duplicateResult.add((i+1)+"0"+(j+1));
                }
            }
        }
        return duplicateResult;
    }


    /*
     * 判断2个时期时间是否相交
     * */
    private static boolean checkWeekTimeIntersect(String range1, String range2) {
        String[] rangeArray1=range1.split("\\/");
        String[] rangeArray2=range2.split("\\/");
        String strWeek1=rangeArray1[0];
        String strWeek2=rangeArray2[0];
        if(strWeek1.equals("*")){
            strWeek1="1234567";
        }
        if(strWeek2.equals("*")){
            strWeek2="1234567";
        }
        
        String strTime1=rangeArray1[1];
        String strTime2=rangeArray2[1];
        if(strTime1.substring(0, 1).equals("*")){
            strTime1="00002400";
        }else{
            strTime1=strTime1.substring(0, 8);
        }
        if(strTime2.substring(0, 1).equals("*")){
            strTime2="00002400";
        }else{
            strTime2=strTime2.substring(0, 8);
        }
        
        if(!checkTimeIntersect(strTime1,strTime2)){//时间段不相交
            return false;
        }else{//时间段相交
            if(!checkWeekDayIntersect(strWeek1,strWeek2)){
                return false;
            }else{
                return true;
            }
        }       
    }

    /*
     * 判断2个日期段是否相交
     * */
    private static boolean checkWeekDayIntersect(String strDate1,
            String strDate2) {
        for(int i=0;i<strDate1.length();i++){
            char c=strDate1.charAt(i);
            if(strDate2.contains(String.valueOf(c))){
                return true;
            }
        }
        return false;
    }

    /*
     * 判断2个时间段是否相交
     * */
    private static boolean checkTimeIntersect(String strTime1, String strTime2) {
        String startTime2 = strTime2.substring(0, 4);
        String startTime1 = strTime1.substring(0, 4);
        if(startTime1.compareTo(startTime2)>0){
            return checkTimeIntersect(strTime2,strTime1);
        } else {
            String endTime1 = strTime1.substring(4, 8);
            String endTime2 = strTime2.substring(4, 8);
            if(endTime1.compareTo(startTime2)>0 || 
                    endTime1.compareTo(endTime2)>0){
                return true;
            }
        }
        return false;
    }

	public static Date stringToDate(String dateString, String dataFormat)
	{
		try{
			SimpleDateFormat format = new SimpleDateFormat(dataFormat);
    		return format.parse(dateString);
    	}catch(Exception e){
    		return null;
    	}
	}
}
