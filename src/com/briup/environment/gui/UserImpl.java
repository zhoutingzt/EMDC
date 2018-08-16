package com.briup.environment.gui;

import com.briup.environment.bean.UserBean;
import com.briup.environment.util.DBUtil;
import com.briup.environment.util.LogImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//用户功能实现
public class UserImpl implements User {
    private static String loginSql="select * from u where username=? and pwd=?";
    private static String searchByNameSql="select * from u where username=?";
    private static String registerSql="insert into u values(my_seq.nextval,?,?,?,?)";
    private static String changePwdSql="update u set pwd = ? where username = ?"; //本来想弄一个新旧密码的
    public boolean login(String username, String pwd) {
        try {
            Connection conn = DBUtil.getConn();
            PreparedStatement ps = conn.prepareStatement(loginSql);
            ps.setString(1,username);
            ps.setString(2,pwd);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean searchByName(String username) {
        try {
            Connection conn = DBUtil.getConn();
            PreparedStatement ps = conn.prepareStatement(searchByNameSql);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean register(String username,String pwd,String gender,String info) {
        try {
            Connection conn = DBUtil.getConn();
            PreparedStatement ps = conn.prepareStatement(registerSql);
            ps.setString(1,username);
            ps.setString(2,pwd);
            ps.setString(3, gender);
            ps.setString(4, info);
            int i = ps.executeUpdate();
            if (i>0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean changePwd(UserBean user, String newPwd) {
        try {
            Connection conn = DBUtil.getConn();
            PreparedStatement ps = conn.prepareStatement(changePwdSql);
            ps.setString(1,newPwd);
            ps.setString(2,user.getUsername());
            int i = ps.executeUpdate();
            if (i>0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new UserImpl().login("tom","12456"));
    }
}
