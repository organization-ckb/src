package cn.looip.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();// 请求的uri
		// 是否过滤
		boolean doFilter = false;
		if (uri.indexOf("resources") == -1) {// resources目录的不过滤，（=-1的为不包含在resources内）
			if (uri.indexOf("doLoginResult") == -1) {//doLoginResult登录的方法不过滤
				doFilter = true;// 当有不是resources目录，doLoginResult方法则过滤
				if (doFilter) {
					// 执行过滤
					// 从session中获取登录者实体
					HttpSession session = req.getSession();
					if (session.getAttribute("loginUser") == null) {
						// 如果session中不存在登录者实体，则弹出框提示重新登录
						// 设置request和response的字符集，防止乱码
						request.setCharacterEncoding("UTF-8");
						response.setCharacterEncoding("UTF-8");
						request.getRequestDispatcher("/user/login").forward(
								request, response);
					} else {
						// 如果session中存在登录者实体，则继续
						chain.doFilter(request, response);
					}
				} else {
					// 如果不执行过滤，则继续
					chain.doFilter(request, response);
				}

			} else {
				chain.doFilter(request, response);//执行登录不过滤
			}

		} else {
			// 如果uri中包含resources，不过滤
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {}

}
