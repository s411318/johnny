package com.dateitemrep.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.appreprec.model.AppRepRecService;
import com.dateitem.model.DateItemService;
import com.dateitem.model.DateItemVO;
import com.dateitemrep.model.DateItemRep;
import com.dateitemrep.model.DateItemRepService;
import com.letter.model.LetterService;
import com.member.model.Member;


public class DateItemRepServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res )
			throws ServletException, IOException{
			doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res )
			throws ServletException, IOException{
		
		HttpSession session = req.getSession();
		Member member = (Member)session.getAttribute("member");
		
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=Big5");
		String action = req.getParameter("action");
		
		
		
		if("insert".equals(action)){
			
			//---------檢舉人編號-----------------之後改成用session取
			Integer memNo  = null;
			memNo =member.getMemNo();
			
			//---------被檢舉約會商品編號-----------------
			Integer dateItemNo  = null;
			dateItemNo =new Integer(req.getParameter("dateItemNo").trim());
			System.out.println(dateItemNo);
			//---------新增的內文---------------------
			String repText = null;
			repText = req.getParameter("repText").trim();
			
			//--------檢舉時間為現在-------------------
			Date repDate = new Date(System.currentTimeMillis());
			Integer repState = new Integer(0);//代表還沒有處理
			
			//---------呼叫service前來處理---------------
			DateItemRepService dirSvc = new DateItemRepService();
			dirSvc.addRep(memNo, dateItemNo, repText, repDate, repState);
			
			//---------導回約會商品頁面---------------------
			Integer whichPage = null;
			whichPage =new Integer(req.getParameter("whichPage"));
			res.sendRedirect(req.getContextPath() + "/front_end/dateitem/select_page.jsp?whichPage="+whichPage);
			
			
		}
		
		
		if("updatepass".equals(action)){
			
			//---------約會商品檢舉編號-----------------
			Integer repNo  = null;
			repNo =new Integer(req.getParameter("repNo").trim());
			
			//---------呼叫service前來找被檢舉會員---------------
			DateItemRepService dirSvc = new DateItemRepService();
			DateItemService diSvc = new DateItemService();
			Integer memNo = diSvc.findByPK(dirSvc.findByPrimaryKey(repNo).getDateItemNo()).getSellerNo();
			Date recDate = new Date(System.currentTimeMillis());
			//---------呼叫Appreprec前來紀錄-------------------
			AppRepRecService arrSvc = new AppRepRecService();
			arrSvc.addrep(memNo, recDate);
			//-----------改變檢舉狀態--------------------------
			DateItemRep dateItemRep = dirSvc.findByPrimaryKey(repNo);
			dirSvc.updateRep(repNo, dateItemRep.getMemNo(), dateItemRep.getDateItemNo(), dateItemRep.getRepText(), dateItemRep.getRepDate(), 1);
			
			//------------將商品改為下架狀態--------------------
			DateItemVO dateItem = diSvc.findByPK(dirSvc.findByPrimaryKey(repNo).getDateItemNo());
			dateItem.setDateItemShow(1);
			diSvc.updateByVO(dateItem);
			
			//-----------寄信給被檢舉者------------------------
			LetterService ltrSvc = new LetterService();
			ltrSvc.addLtrOfRep(dateItem);
			
			
			
		}
		if("updatedeny".equals(action)){
			
			//---------約會商品檢舉編號-----------------
			Integer repNo  = null;
			repNo =new Integer(req.getParameter("repNo").trim());
			
			//-----------改變檢舉狀態--------------------------
			DateItemRepService dirSvc = new DateItemRepService();
			DateItemRep dateItemRep = dirSvc.findByPrimaryKey(repNo);
			dirSvc.updateRep(repNo, dateItemRep.getMemNo(), dateItemRep.getDateItemNo(), dateItemRep.getRepText(), dateItemRep.getRepDate(), 1);
			
		}
		
		
	}
	

}
