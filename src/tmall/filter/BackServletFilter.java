package tmall.filter;

import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Danger on 2017/11/8.
 */
public class BackServletFilter implements Filter{
    public void destroy() {

    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,ServletException{
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse) res;

        //应用的web目录的名称(tmall)
        String contextPath=request.getServletContext().getContextPath();
        //返回除去host（域名或者ip）部分的路径
        String uri=request.getRequestURI();
        //去掉tmall
        uri= StringUtils.remove(uri,contextPath);
        if (uri.startsWith("admin_")){
            String servletPath=StringUtils.substringsBetween(uri,"_","_")+"Servlet";
            String method=StringUtils.substringAfterLast(uri,"_");
            request.setAttribute("method",method);
            req.getRequestDispatcher("/"+servletPath).forward(request,response);
            return;
        }
        //关于chain.doFilter(request,response)
        //他的作用是将请求转发给过滤器链上下一个对象。这里的下一个指的是下一个filter，如果没有filter那就是你请求的资源。 一般filter都是一个链,web.xml 里面配置了几个就有几个。一个一个的连在一起
        //request -> filter1 -> filter2 ->filter3 -> .... -> request resource.
        chain.doFilter(request,response);
    }
    public void init(FilterConfig arg0) throws ServletException{}
}
