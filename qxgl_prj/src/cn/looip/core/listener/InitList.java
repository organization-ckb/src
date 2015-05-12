package cn.looip.core.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 配置项目名监听器
 *
 */
@WebListener
public class InitList implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event)  {
    	ServletContext application = event.getServletContext();
    	application.setAttribute("path", application.getContextPath());
    }
   public void contextDestroyed(ServletContextEvent event)  {}
	
}
