package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(UserVo userVo) {

		sqlSession.insert("user.insertUser",userVo);
		
		//		int count=0;
//		try {
//			getConnection();
//
//			String query ="";
//			query += " insert into users ";
//			query += " values(seq_users_no.nextval, ?, ?, ?, ?) " ;
//
//			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, vo.getId() );    
//			pstmt.setString(2, vo.getPassword());
//			pstmt.setString(3, vo.getName());
//			pstmt.setString(4, vo.getGender());
//			
//			count = pstmt.executeUpdate();  
//			System.out.println(count + " 건이 등록되었습니다.[UserDao]");
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} 
//		
//		close();
//		
	}

	public UserVo getUser(UserVo userVo) {
		UserVo authVo = sqlSession.selectOne("user.getUser",userVo);
//		try {
//			getConnection();
//
//			String query ="";
//			query += " select no,  ";
//			query += " 		  name ";
//			query += " from users ";
//			query += " where id = ? and " ;
//			query += " 		 password = ? ";
//
//			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, userVo.getId());    
//			pstmt.setString(2, userVo.getPassword());
//			
//			rs = pstmt.executeQuery();  
//			while(rs.next()) {
//				vo = new UserVo();
//				vo.setNo(rs.getInt(1));
//				vo.setName(rs.getString(2));
//			}
//			
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} 
//		
//		close();
		
		return authVo; 
	}
	
	public UserVo getUserData(int no) {
		UserVo userVo = sqlSession.selectOne("user.getUserData",no);
//		try {
//			getConnection();
//
//			String query ="";
//			query += " select id, ";
//			query += " 		  password, ";
//			query += " 		  name, ";
//			query += " 		  gender ";
//			query += " from users ";
//			query += " where no = ? ";
//
//			pstmt = conn.prepareStatement(query);
//			pstmt.setInt(1, no );    
//			
//			rs = pstmt.executeQuery();  
//			while(rs.next()) {
//				vo = new UserVo(0, rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
//			}
//			
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} 
//		
//		close();
		
		return userVo; 
	}
	
	public void modify(UserVo userVo) {
		sqlSession.update("user.modifyUser", userVo);
//		try {
//			getConnection();
//
//			String query ="";
//			query += " update users ";
//			query += " set password = ?, " ;
//			query += "	   name = ?, " ;
//			query += "	   gender = ? " ;
//			query += " where id = ? ";
//
//			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, vo.getPassword() );    
//			pstmt.setString(2, vo.getName());
//			pstmt.setString(3, vo.getGender());
//			pstmt.setString(4, vo.getId());
//			
//			int count = pstmt.executeUpdate();  
//			System.out.println(count + " 건이 수정되었습니다.[UserDao]");
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} 
//		
//		close();
	}
	
	
}
