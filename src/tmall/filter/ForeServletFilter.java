package tmall.filter;

import org.apache.commons.lang.StringUtils;
import tmall.bean.Category;
import tmall.bean.OrderItem;
import tmall.bean.User;
import tmall.dao.CategoryDAO;
import tmall.dao.OrderItemDAO;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Danger on 2018/1/10.
 */
public class ForeServletFilter implements Filter{
    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        String contextPath=request.getServletContext().getContextPath();
        request.getServletContext().setAttribute("contextPath",contextPath);

        User user=(User)request.getSession().getAttribute("user");
        int cartTotalItemNumber=0;
        if (null!=user){
            List<OrderItem> ois=new OrderItemDAO().listByUser(user.getId());
            for (OrderItem oi:ois){
                cartTotalItemNumber+=oi.getNumber();
            }
        }
        request.setAttribute("cartTotalNumber",cartTotalItemNumber);

        List<Category> cs=(List<Category>) request.getAttribute("cs");
        if (null==cs){
            cs=new CategoryDAO().list();
            request.setAttribute("cs",cs);
        }
        String uri=request.getRequestURI();
        uri= StringUtils.remove(uri,contextPath);
        if (uri.startsWith("/fore")&&!uri.startsWith("/foreServlet")){
            String method=StringUtils.substringAfterLast(uri,"/fore");
            request.setAttribute("method",method);
            servletRequest.getRequestDispatcher("/foreServlet").forward(request,response);
            return;
        }
        filterChain.doFilter(request,response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
