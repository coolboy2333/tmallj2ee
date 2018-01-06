package tmall.dao;

import tmall.bean.Product;
import tmall.bean.ProductImage;
import tmall.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Danger on 2017/10/17.
 */
public class ProductImageDAO {
    public static final String type_single="type_single";
    public static final String type_detail="type_detail";
    public int getTotal(){
        int total=0;
        try(Connection c= DBUtil.getConnection(); Statement s=c.createStatement()){
            String sql="select count(*) from productimage";
            ResultSet rs=s.executeQuery(sql);
            if (rs.next()){
                total=rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return total;
    }
    public void add(ProductImage bean){
        String sql="insert into productimage values(null,?,?)";
        try(Connection c=DBUtil.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,bean.getProduct().getId());
            ps.setString(2,bean.getType());
            ps.execute();
            ResultSet rs=ps.getGeneratedKeys();
            if (rs.next()){
                bean.setId(rs.getInt("id"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void update(ProductImage bean){
        String sql="update productimage set pid=?,type=? where id=?";
        try(Connection c=DBUtil.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,bean.getProduct().getId());
            ps.setString(2,bean.getType());
            ps.setInt(3,bean.getId());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void delete(int id){
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "delete from productimage where id = " + id;
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ProductImage get(int id){
        ProductImage bean=new ProductImage();
        try(Connection c=DBUtil.getConnection();Statement s=c.createStatement()){
            String sql="select * from productimage where id="+id;
            ResultSet rs=s.executeQuery(sql);
            if (rs.next()){
                int pid=rs.getInt("pid");
                Product product=new ProductDAO().get(pid);
                bean.setId(id);
                bean.setType(rs.getString("type"));
                bean.setProduct(product);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return bean;
    }
    //列出全部
    public List<ProductImage> list(Product p,String type){
        return list(p,type,0,Short.MAX_VALUE);
    }
    //分页查询
    //查询指定产品下，某种类型的ProductImage
    public List<ProductImage> list(Product p,String type,int start,int count){
        List<ProductImage> beans=new ArrayList<ProductImage>();
        String sql="select * from productimage where pid=? and type=? order by id desc limit ?,?";
        try(Connection c=DBUtil.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,p.getId());
            ps.setString(2,type);
            ps.setInt(3,start);
            ps.setInt(4,count);

            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                ProductImage bean=new ProductImage();
                int id=rs.getInt(1);
                bean.setProduct(p);
                bean.setType(type);
                bean.setId(id);

                beans.add(bean);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return beans;
    }
}
