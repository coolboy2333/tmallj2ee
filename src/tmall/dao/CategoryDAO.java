package tmall.dao;

import tmall.bean.Category;
import tmall.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Danger on 2017/10/16.
 */
public class CategoryDAO {
    public int getTotal(){
        int total=0;
        try (Connection c= DBUtil.getConnection(); Statement s=c.createStatement()){
            String sql="select count(*) from category";
            ResultSet rs=s.executeQuery(sql);
            while (rs.next()){
                total=rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return total;
    }
    public void add(Category bean){
        String sql="insert into category values(null,?)";
        try(Connection c=DBUtil.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,bean.getName());
            ps.execute();
            ResultSet rs=ps.getGeneratedKeys();
            while (rs.next()){
                int id=rs.getInt(1);
                bean.setId(id);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void update(Category bean){
        String sql="update category set name=? where id=?";
        try(Connection c=DBUtil.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,bean.getName());
            ps.setInt(2,bean.getId());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void delete(int id){
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "delete from Category where id = " + id;
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Category get(int id){
        Category bean=null;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select * from Category where id = " + id;
            ResultSet rs=s.executeQuery(sql);
            if (rs.next()){
                bean=new Category();
                bean.setName(rs.getString(2));
                bean.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }
    public List<Category> list(){
        return list(0,Integer.MAX_VALUE);
    }
    public List<Category> list(int start,int count){
        List<Category> beans=new ArrayList<Category>();
        String sql="select * from category order by id desc limit ?,?";
        try(Connection c=DBUtil.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Category bean=new Category();
                bean.setId(rs.getInt(1));
                bean.setName(rs.getString(2));
                beans.add(bean);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return beans;
    }
}
