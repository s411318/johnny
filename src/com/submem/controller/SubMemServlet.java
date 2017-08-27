package com.submem.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.Member;
import com.submem.model.SubMem;
import com.submem.model.SubMemService;

public class SubMemServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
			doPost(req,res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
			
		HttpSession session = req.getSession();
		Member member = (Member)session.getAttribute("member");
		
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=Big5");
		String action = req.getParameter("action");
		
		if("insert".equals(action)){
			
			//**********�q�n�J�|������***************
			Integer actSubMemNo =null;
			actSubMemNo = member.getMemNo();
			
			
			//*********���X�Q�[�J���|���s��************
			Integer beSubMemNo = null;
			try{
				beSubMemNo =new Integer(req.getParameter("beSubMemNo"));
				
			}catch(Exception e){
				e.getMessage();
			}
			
			//*********�[�J�����A��0****�N������
			Integer subState = new Integer(0);
			
			//�}�l�ק��Ƶ�SubMemService
			SubMemService smSvc=null;
			
			smSvc = new SubMemService();
			smSvc.addSub(actSubMemNo, beSubMemNo, subState);
	
		}
		
		if("delete".equals(action)){
			
			//**********�q�n�J�|������***************
			Integer actSubMemNo =null;
			actSubMemNo = member.getMemNo();
			
			
			//*********���X�Q�������|���s��************
			Integer beSubMemNo = null;
			try{
				beSubMemNo =new Integer(req.getParameter("beSubMemNo"));
				
			}catch(Exception e){
				e.getMessage();
			}
			
			//*********�[�J�����A��1****�N�����
			Integer subState = new Integer(1);
			
			//�}�l�ק��Ƶ�SubMemService
			SubMemService smSvc = new SubMemService();
			SubMem subMem =new SubMem();
			subMem.setActSubMemNo(actSubMemNo);
			subMem.setBeSubMemNo(beSubMemNo);
			subMem.setSubState(subState);
			smSvc.deleteSub(subMem);
		
	
		}
		
		
		
		
	} 
}
