package com.lettertype.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lettertype.model.LetterTypeService;

public class LetterTypeServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
		doPost(req,res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res )
			throws ServletException, IOException{
		
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=Big5");
		String action = req.getParameter("action");
		PrintWriter out = res.getWriter();
		
		if("insert".equals(action)){
			
			//---------�s�W�����W��-----------------
			String letterTypeName = null;
			letterTypeName = req.getParameter("lettertypename").trim();
			
			//---------�s�W������---------------------
			String letterTypeText = null;
			letterTypeText = req.getParameter("lettertypetext").trim();
			
			//---------�I�sservice�e�ӳB�z---------------
			LetterTypeService ltpSvc = new LetterTypeService();
			ltpSvc.addLtrType(letterTypeName, letterTypeText);
			
			//�নjson���r����ajax���θ̭����
			String json = "{\"curr\":\""+ltpSvc.getCurr()+"\"}";
			out.println(json);
			out.close();
			
		}
		
		if("update".equals(action)){
			
			//---------�����ק�s��-----------------
			Integer letterTypeNo = null;
			letterTypeNo =new Integer(req.getParameter("lettertypeno"));
			
			//---------�ק�����W��-----------------
			String letterTypeName = null;
			letterTypeName = req.getParameter("lettertypename");
			
			//---------�ק��������-----------------
			String letterTypeText = null;
			letterTypeText = req.getParameter("lettertypetext");
			
			//---------�I�sservice�e�ӳB�z---------------
			LetterTypeService ltpSvc = new LetterTypeService();
			ltpSvc.update(letterTypeNo, letterTypeName, letterTypeText);
			
			
		}
		
		
		
	}

}
