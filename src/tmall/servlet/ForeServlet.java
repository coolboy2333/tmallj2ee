package tmall.servlet;

import tmall.bean.Category;
import tmall.dao.CategoryDAO;
import tmall.dao.ProductDAO;
import tmall.util.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Danger on 2018/1/10.
 */
public class ForeServlet extends BaseForeServlet{
    public String home(HttpServletRequest request, HttpServletResponse response, Page page){
        List<Category> cs=new CategoryDAO().list();
        new ProductDAO().fill(cs);
        new ProductDAO().fillByRow(cs);
        request.setAttribute("cs",cs);
        return "home.jsp";
    }
}
