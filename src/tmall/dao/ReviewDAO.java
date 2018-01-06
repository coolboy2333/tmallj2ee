package tmall.dao;

import tmall.bean.Product;
import tmall.bean.Review;
import tmall.bean.User;
import tmall.util.DBUtil;
import tmall.util.DateUtil;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by Danger on 2017/11/1.
 */
public class ReviewDAO {
    public int getTotal(){
        int total=0;
        try(Connection c= DBUtil.getConnection(); Statement s=c.createStatement()){
            String sql="selcet count(*) from review";
            ResultSet rs=s.executeQuery(sql);
           if (rs.next()){
               total=rs.getInt(1);
           }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return total;
    }
    public int getTotal(int pid){
        int total=0;
        try(Connection c= DBUtil.getConnection(); Statement s=c.createStatement()){
            String sql="selcet count(*) from review where pid="+pid;
            ResultSet rs=s.executeQuery(sql);
            if (rs.next()){
                total=rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return total;
    }
    public void add(Review bean){
        String sql="insert into review values(null,?,?,?,?)";
        try(Connection c=DBUtil.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,bean.getContent());
            ps.setInt(2,bean.getUser().getId());
            ps.setInt(3,bean.getProduct().getId());
            ps.setTimestamp(4, DateUtil.d2t(bean.getCreateDate()));
            ps.execute();
            ResultSet rs=ps.getGeneratedKeys();
            if (rs.next()){
                bean.setId(rs.getInt("id"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void update(Review bean){
        String sql="update review set content=?,uid=?,pid=?,createDate=? where id=?";
        try(Connection c=DBUtil.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,bean.getContent());
            ps.setInt(2,bean.getUser().getId());
            ps.setInt(3,bean.getProduct().getId());
            ps.setTimestamp(4, DateUtil.d2t(bean.getCreateDate()));
            ps.setInt(5,bean.getId());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void delete(int id){
        try(Connection c=DBUtil.getConnection();Statement s=c.createStatement()) {
            String sql="delete from review where id="+id;
            s.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public Review get(int id){
        Review bean=new Review();
        try(Connection c=DBUtil.getConnection();Statement s=c.createStatement()) {
            String sql="select * from review where id="+id;
            ResultSet rs=s.executeQuery(sql);
            if (rs.next()){

                int pid=rs.getInt("pid");
                int uid=rs.getInt("uid");
                Product product=new ProductDAO().get(pid);
                User user=new UserDAO().get(uid);

                bean.setContent(rs.getString("content"));
                bean.setProduct(product);
                bean.setUser(user);
                bean.setId(id);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return bean;
    }
    public List<Review> list(int pid){
        return list(pid,0,Short.MAX_VALUE);
    }
    public List<Review> list(int pid,int start,int count){
        List<Review> beans=new ArrayList<Review>();
        String sql="select * from review where pid=? order by id desc limit ?,?";
        try(Connection c=DBUtil.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,pid);
            ps.setInt(2,start);
            ps.setInt(3,count);

            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Review bean=new Review();
                int id=rs.getInt("id");
                int uid=rs.getInt("uid");
                Date createDate=DateUtil.t2d(rs.getTimestamp("createDate"));
                Product product=new ProductDAO().get(pid);
                User user=new UserDAO().get(uid);

                bean.setContent(rs.getString("content"));
                bean.setProduct(product);
                bean.setUser(user);
                bean.setCreateDate(createDate);
                bean.setId(id);

                beans.add(bean);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return beans;
    }
    public int getCount(int pid){
        String sql="select count(*) from review where pid=?";
        try(Connection c=DBUtil.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,pid);
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                return rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public boolean isExist(String content,int pid){
        String sql="select * from review where content like %?% and pid=?";
        try(Connection c=DBUtil.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,content);
            ps.setInt(2,pid);
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
