package com.charge.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.charge.model.ChargesService;
import com.member.model.Member;
import com.member.model.MemberService;

@WebServlet("/chargePage")
public class chargePage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession();
		Member mem = (Member) session.getAttribute("member");
		req.setCharacterEncoding("UTF-8");
		

		java.util.Date appTime = new java.util.Date();
		java.sql.Date applyTime = new java.sql.Date(appTime.getTime());

		if (req.getParameter("action").equals("payment")) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				String carton = req.getParameter("cardone");
				if(carton==null || (carton.trim()).length() == 0) {
					errorMsgs.add("請輸入信用卡資訊");
				}
				String cartwo = req.getParameter("cardtwo");
				if(carton==null || (cartwo.trim()).length() == 0) {
					errorMsgs.add("請輸入信用卡資訊");
				}
				String cartth = req.getParameter("cardthree");
				if(cartth==null || (cartth.trim()).length() == 0) {
					errorMsgs.add("請輸入信用卡資訊");
				}
				String cartfour = req.getParameter("cardfour");
				if(cartfour==null || (cartfour.trim()).length() == 0) {
					errorMsgs.add("請輸入信用卡資訊");
				}
				String valimon = req.getParameter("valimon");
				if(valimon==null || (valimon.trim()).length() == 0) {
					errorMsgs.add("請輸入信用卡資訊");
				}
				String valiyear = req.getParameter("valiyear");
				if(valiyear==null || (valiyear.trim()).length() == 0) {
					errorMsgs.add("請輸入信用卡資訊");
				}
				String ccv = req.getParameter("ccv");
				if(ccv==null || (ccv.trim()).length() == 0) {
					errorMsgs.add("請輸入信用卡資訊");
				}
				String holder = req.getParameter("holder");
				if(holder==null || (holder.trim()).length() == 0) {
					errorMsgs.add("請輸入信用卡資訊");
				}
				Integer one = null;
				try {
					one = new Integer(carton);
				} catch (Exception e) {
					errorMsgs.add("編號err1");
				}
				Integer two = null;
				try {
					two = new Integer(cartwo);
				} catch (Exception e) {
					errorMsgs.add("編號err1");
				}
				Integer three = null;
				try {
					three = new Integer(cartth);
				} catch (Exception e) {
					errorMsgs.add("編號err1");
				}
				Integer four = null;
				try {
					four = new Integer(cartfour);
				} catch (Exception e) {
					errorMsgs.add("編號err1");
				}
				Integer mon = null;
				try {
					mon = new Integer(valimon);
				} catch (Exception e) {
					errorMsgs.add("編號err1");
				}
				Integer year = null;
				try {
					year = new Integer(valiyear);
				} catch (Exception e) {
					errorMsgs.add("編號err1");
				}
				Integer ccvno = null;
				try {
					ccvno = new Integer(ccv);
				} catch (Exception e) {
					errorMsgs.add("編號err1");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher dispatcher = req.getRequestDispatcher("/front_end/charge/chargePage.jsp");
					dispatcher.forward(req, res);
					return;
				}
				if (mem == null) {
					RequestDispatcher rd = req.getRequestDispatcher("front_end/member/login.jsp");
					rd.forward(req, res);
				} else {

					ChargesService chargeDao = new ChargesService();
					int chargeIn = Integer.valueOf(req.getParameter("chargeNum"));
					MemberService memDao = new MemberService();
					int chargeNow = mem.getMemPoint() + chargeIn;
					Member member = memDao.updateMember(mem.getMemNo(), mem.getMemId(), mem.getMemPwd(), mem.getMemName(),
							mem.getMemSname(), mem.getMemGender(), mem.getMemIdNo(), mem.getMemBday(), mem.getMemPhone(),
							mem.getMemAddress(), mem.getMemEmail(), mem.getMemImg(), mem.getMemReported(),
							mem.getMemStatus(), mem.getMemRelation(), mem.getMemSelfintro(), mem.getMemFollowed(),
							chargeNow, mem.getMemSaleRank(), mem.getMemLongtitude(), mem.getMemLatitude(),
							mem.getMemLocTime(), mem.getMemLocStatus());
					chargeDao.addCharge(mem.getMemNo(), chargeIn, applyTime, 1, 0);
					session.setAttribute("member", member);

					RequestDispatcher rd = req.getRequestDispatcher("front_end/product/Cart.jsp");
					rd.forward(req, res);
				}
				
			} 
			
			catch (Exception e) {
				System.out.println("error");
			}
			
			
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doGet(req, res);
	}

}
