package tmall.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Danger on 2017/11/16.
 */
public class EncodingFilter implements Filter
{
    public void destroy(){

    }
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,ServletException{
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse) res;

        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request,response);
    }
    public void init(FilterConfig arg0) throws ServletException{

    }
}
