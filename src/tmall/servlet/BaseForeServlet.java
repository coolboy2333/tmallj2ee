package tmall.servlet;

import tmall.dao.*;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * Created by Danger on 2018/1/10.
 */
public class BaseForeServlet extends HttpServlet{
    protected CategoryDAO categoryDAO=new CategoryDAO();
    protected OrderDAO orderDAO=new OrderDAO();
    protected OrderItemDAO orderItemDAO=new OrderItemDAO();
    protected ProductDAO productDAO=new ProductDAO();
    protected ProductImageDAO productImageDAO = new ProductImageDAO();
    protected PropertyDAO propertyDAO = new PropertyDAO();
    protected PropertyValueDAO propertyValueDAO = new PropertyValueDAO();
    protected ReviewDAO reviewDAO = new ReviewDAO();
    protected UserDAO userDAO = new UserDAO();

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }
}
