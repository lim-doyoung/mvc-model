package com.bit.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Guest02Dao {
	
	// <Resource
			// auth="Container"
			// type="javax.sql.DataSource"
			// name="jdbc/oracle"
			// driverClassName="oracle.jdbc.driver.OracleDriver"
			// url="jdbc:oracle:thin:@localhost:1521:xe"
			// username="hr"
			// password="hr"
			// maxWait="3000"
			// minIdle="10"
			// maxIdle="100"
			// maxActive="100"
			// />
	
	javax.sql.DataSource source;
	
	public Guest02Dao(){
		try {
			InitialContext init = new InitialContext();//server°ú Ä¿³Ø¼Ç ¾ò¾î³»±â
			Context context = (Context) init.lookup("java:/comp/env");
			source = (DataSource) context.lookup("jdbc/oracle");
			Hashtable<?, ?> env = context.getEnvironment();
			Set<?> keys = env.keySet();
			Iterator<?> ite = keys.iterator();
			while(ite.hasNext()){
				String key=(String)ite.next();
				System.out.println(key+":"+env.get(key));
			}
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}
	
	public ArrayList<Guest02Dto> getList() {


		ArrayList<Guest02Dto> list = new ArrayList<Guest02Dto>();
		String sql = "select num,sub,(select name from user02 where num=unum) as uname,pay from guest02";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		InitialContext init;

//		javax.sql.DataSource source = null;
//		try {
//			init = new InitialContext();
//			Context context = (Context) init.lookup("java:/comp/env");
//			source = (DataSource) context.lookup("jdbc/oracle");
//		} catch (NamingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}//server°ú Ä¿³Ø¼Ç ¾ò¾î³»±â

		try {

			conn = source.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// System.out.println(conn);
			while (rs.next()) {
				Guest02Dto bean = new Guest02Dto();
				bean.setNum(rs.getInt("num"));
				bean.setSub(rs.getString("sub"));
				bean.setName(rs.getString("uname"));
				bean.setPay(rs.getInt("pay"));
				list.add(bean);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public void insert(String sub, int unum, int pay) {
		// TODO Auto-generated method stub
		String sql="insert into guest02 values(guest02_seq.nextval,?,?,sysdate,?)";
		javax.sql.DataSource source=null;//db¿¬°á
		
		try {
			InitialContext init = new InitialContext();
			source=(DataSource) init.lookup("java:/comp/env/jdbc/oracle");//»Ì¾Æ³¿
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=source.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, sub);
			pstmt.setInt(2, unum);
			pstmt.setInt(3, pay);
			int result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	public Guest02Dto detail(int num){
		Guest02Dto bean=new Guest02Dto();
		String sql="select num,sub,unum,(select name from user02 where num=unum) as name,pay from guest02 where num=?";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=source.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()){
				bean.setNum(rs.getInt("num"));
				bean.setSub(rs.getString("sub"));
				bean.setUnum(rs.getInt("unum"));
				bean.setName(rs.getString("name"));
				bean.setPay(rs.getInt("pay"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return bean;
		
	}
}
