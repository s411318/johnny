package com.auth.model;

import java.util.List;

public class AuthTest {

	public static void main(String[] args) {
		AuthJDBCDAO dao=new AuthJDBCDAO();
		
		//新增
//		Auth auth=new Auth();
//		auth.setAuthName("測試用");
//		dao.add(auth);
//		System.out.println("===================");
		
		//修改
//		Auth auth1=new Auth();
//		auth1.setAuthNo(2);
//		auth1.setAuthName("修改測試");
//		dao.update(auth1);
//		System.out.println("=======================");
		
		
		//刪除
//		dao.delete(2);
//		System.out.println("=======================");
		
		
		
		//查詢
//		Auth auth2=dao.findByPk(2);
//		System.out.println(auth2.getAuthNo());
//		System.out.println(auth2.getAuthName());
		
		//查全部
//		List<Auth> authList=dao.getAll();
//		for(Auth auth:authList){
//			System.out.println(auth.getAuthNo());
//			System.out.println(auth.getAuthName());
//			System.out.println("=======================");
//		}
	}

}
