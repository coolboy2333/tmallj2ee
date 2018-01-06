package tmall.dao;

import tmall.bean.Category;
import tmall.bean.Property;
import tmall.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Danger on 2017/10/17.
 */
public class PropertyDAO {
    /**
     * 获取某种分类下的属性总数，在分页显示的时候会用到
     * @param cid
     * @return
     */
    public int getTotal(int cid){
        int total=0;
        try(Connection c= DBUtil.getConnection(); Statement s=c.createStatement()){
            String sql="select count(*) from property where cid="+cid;
            ResultSet rs=s.executeQuery(sql);
            if (rs.next()){
                total=rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return total;
    }
    public void add(Property bean){
        String sql="insert into property values(null,?,?)";
        try(Connection c=DBUtil.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,bean.getCategory().getId());
            ps.setString(2,bean.getName());
            ps.execute();
            ResultSet rs=ps.getGeneratedKeys();
            if (rs.next()){
                int id=rs.getInt(1);
                bean.setId(id);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void update(Property bean){
        String sql="update property set cid=?,name=? where id=?";
        try(Connection c=DBUtil.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,bean.getCategory().getId());
            ps.setString(2,bean.getName());
            ps.setInt(3,bean.getId());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void delete(int id){
        String sql="delete from property where id=?";
        try(Connection c=DBUtil.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,id);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public Property get(int id){
        Property bean=null;
        String sql="select * from property where id=?";
        try(Connection c=DBUtil.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                bean.setId(id);
                bean.setName(rs.getString("name"));
                int cid=rs.getInt("cid");
                Category category=new CategoryDAO().get(cid);
                bean.setCategory(category);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return bean;
    }
    public Property get(String name,int cid){
        Property bean=null;
        String sql="select * from property where name=? and cid=?";
        try(Connection c=DBUtil.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,name);
            ps.setInt(2,cid);
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                bean.setId(rs.getInt("id"));
                bean.setName(name);
                Category category=new CategoryDAO().get(cid);
                bean.setCategory(category);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return bean;
    }
    public List<Property> list(int cid){
        return list(cid,0,Short.MAX_VALUE);
    }
    public List<Property> list(int cid,int start,int count){
        List<Property> beans=new ArrayList<Property>();
        String sql="select * from property where cid=? order by id desc limit ?,?";
        try(Connection c=DBUtil.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
           ps.setInt(1,cid);
           ps.setInt(2,start);
           ps.setInt(3,count);
           ResultSet rs=ps.executeQuery();
           while (rs.next()){
               Property bean=new Property();
               bean.setId(rs.getInt("id"));
               bean.setName(rs.getString("name"));
               Category category=new CategoryDAO().get(cid);
               bean.setCategory(category);

               beans.add(bean);
           }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return beans;
    }
}
