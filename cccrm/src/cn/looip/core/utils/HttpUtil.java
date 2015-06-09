package cn.looip.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class HttpUtil
{
	private static Logger logger = Logger.getLogger(HttpUtil.class);
	private static int ConnectionTimeout=20000;
	private static int SoTimeout=20000;
	/** 
     * 执行一个HTTP GET请求，返回请求响应的HTML 
     * 
     * @param url                 请求的URL地址 
     * @param queryString 请求的查询参数,可以为null 
     * @param charset         字符集 
     * @param pretty            是否美化 
     * @return 返回请求响应的HTML 
     */ 
	public static String doGet(String url, String queryString, String charset) { 
        StringBuffer response = new StringBuffer(); 
        HttpClient client = new HttpClient(); 
        //链接超时
		client.getHttpConnectionManager().getParams().setConnectionTimeout(ConnectionTimeout);
		//读取超时
		client.getHttpConnectionManager().getParams().setSoTimeout(SoTimeout);
        HttpMethod method = new GetMethod(url); 
        try { 
                if (StringUtils.isNotBlank(queryString)) 
                        //对get请求参数做了http请求默认编码，好像没有任何问题，汉字编码后，就成为%式样的字符串 
                        method.setQueryString(URIUtil.encodeQuery(queryString)); 
                client.executeMethod(method); 
                if (method.getStatusCode() == HttpStatus.SC_OK) { 
                        BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), charset)); 
                        String line; 
                        while ((line = reader.readLine()) != null) { 
//                                if (pretty) 
//                                        response.append(line).append(System.getProperty("line.separator"));
//                                else 
                                        response.append(line); 
                        } 
                        reader.close(); 
                } 
        } catch (URIException e) { 
        	logger.error("执行HTTP Get请求时，编码查询字符串“" + queryString + "”发生异常！", e);
//                log.error("执行HTTP Get请求时，编码查询字符串“" + queryString + "”发生异常！", e); 
        } catch (IOException e) { 
        	logger.error("执行HTTP Get请求" + url + "时，发生异常！", e);
//                log.error("执行HTTP Get请求" + url + "时，发生异常！", e); 
        } finally { 
                method.releaseConnection(); 
        } 
        return response.toString(); 
} 

	/**
	 * 执行一个HTTP POST请求，返回请求响应的HTML
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param params
	 *            请求的查询参数,可以为null
	 * @param charset
	 *            字符集
	 * @param pretty
	 *            是否美化
	 * @return 返回请求响应的HTML
	 */
	public static String doPost(String url, Map<String, String> params,
			String charset)
	{
		StringBuffer response = new StringBuffer();
		HttpClient client = new HttpClient();
		//链接超时
		client.getHttpConnectionManager().getParams().setConnectionTimeout(ConnectionTimeout);
		//读取超时
		client.getHttpConnectionManager().getParams().setSoTimeout(SoTimeout);
		HttpMethod method = new PostMethod(url);
		// 设置Http Post数据
		if (params != null)
		{
			HttpMethodParams p = new HttpMethodParams();
			for (Map.Entry<String, String> entry : params.entrySet())
			{
				p.setParameter(entry.getKey(), entry.getValue());
			}
			method.setParams(p);
		}
		try
		{
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK)
			{
				BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), charset));
				String line;
				while ((line = reader.readLine()) != null)
				{
					// if (pretty)
					// response.append(line).append(System.getProperty("line.separator"));
					// else
					response.append(line);
				}
				reader.close();
			}
		}
		catch (IOException e)
		{
			logger.error("执行HTTP Post请求" + url + "时，发生异常！", e);
			// log.error("执行HTTP Post请求" + url + "时，发生异常！", e);
		}
		finally
		{
			method.releaseConnection();
		}
		return response.toString();
	}

}
