package com.diamsg.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.diamsg.model.DiaMsg;
import com.diamsg.model.DiaMsgService;
import com.member.model.Member;
import com.member.model.MemberService;


public class DiaMsgServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res )
			throws ServletException, IOException{
		
		HttpSession session = req.getSession();
		Member member = (Member)session.getAttribute("member");
		
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=Big5");
		
		PrintWriter out = res.getWriter();
		
		String action = req.getParameter("action");
		
		
		if("insert".equals(action)){
			
			//**********�q�d���|�����s��***************
			Integer memNo =null;		
			memNo = member.getMemNo();
			//*********���X�Q�d������x�s��************
			Integer diaNo = null;
			try{
				diaNo =new Integer(req.getParameter("diano"));
				
			}catch(Exception e){
				e.getMessage();
			}
			//**********���X�d�������e*****************
			String diaMsgText = null;
			try{
				diaMsgText =req.getParameter("diamsgtext");
				
			}catch(Exception e){
				e.getMessage();
			}
			
			//**********�[�J�d�����ɶ�*****************
			Timestamp diaMsgTime = new Timestamp(System.currentTimeMillis());
			
			//*********�[�J�d�������A��0****�N���R��
			Integer diaMsgState = new Integer(0);
			
			//�}�l�s�W��Ƶ�DiaMsgService
			DiaMsgService dmgSvc= new DiaMsgService();
			dmgSvc.addDiaMsg(diaNo, memNo, diaMsgText, diaMsgTime, diaMsgState);
			
			//�নjson�榡�ǵ��e��
			MemberService memSvc = new MemberService();
			String Sname = memSvc.getOneMember(memNo).getMemSname();
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String json = "{\"sname\":\""+Sname+"\",\"diaMsgTime\":\""+sdFormat.format(diaMsgTime)
								+"\",\"memNo\":\""+memNo+"\",\"curr\":\""+dmgSvc.getCurrNo()+"\"}";
			out.println(json);
			out.close();
			
		}
		
		if("update".equals(action)){
			
			
			//*********���X�d�����d���s��************
			Integer diaMsgNo = null;
			try{
				diaMsgNo =new Integer(req.getParameter("diamsgno"));
				
			}catch(Exception e){
				e.getMessage();
			}
			//**********���X�d�������e*****************
			String diaMsgText = null;
			try{
				diaMsgText =req.getParameter("diamsgtext");
				
			}catch(Exception e){
				e.getMessage();
			}
			
			
			
			//�}�l�ק��Ƶ�DiaMsgService
			DiaMsgService dmgSvc= new DiaMsgService();
			DiaMsg diaMsgOld = dmgSvc.getOneDiaMsg(diaMsgNo);
			dmgSvc.updateDiaMsg(diaMsgNo, diaMsgOld.getDiaNo(), diaMsgOld.getMemNo(), diaMsgText, diaMsgOld.getDiaMsgTime(), diaMsgOld.getDiaMsgState());
	
		}
		
		if("delete".equals(action)){
			
			
			//*********���X�d�����d���s��************
			Integer diaMsgNo = null;
			try{
				diaMsgNo =new Integer(req.getParameter("diamsgno"));
				
			}catch(Exception e){
				e.getMessage();
			}
						
			//�}�l�ק��Ƶ�DiaMsgService
			DiaMsgService dmgSvc= new DiaMsgService();
			dmgSvc.deleteDiaMsg(diaMsgNo);
			
	
		}
		
		
		
		
		
	}
}
