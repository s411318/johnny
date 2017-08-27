package com.restMember.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.restMember.model.RestMember;
import com.restMember.model.RestMemberService;
import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantService;








public class RestMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected RestMember allowUser(String restMemId,String restMemPsw){
		RestMemberService restMemberService = new RestMemberService();
		RestMember restMember = restMemberService.getOneRestMember(restMemId);
		
		if(restMember==null){
			return null;
		} else if(!restMember.getRestMemPsw().equals(restMemPsw)){
			return null;
		} else {
			return restMember;
		}
	}
	
	
	
	 
	protected Restaurant restUser(RestMember restMember){
		RestaurantService restaurantService = new RestaurantService();
		Restaurant restaurant = restaurantService.getOneRest(restMember.getRestNo());
		return restaurant;
	}
	
	
    public RestMemberServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		/////////////////////////////////�n�J/////////////////////////////
		if("login".equals(action)){
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
				///////////////////////////�ˬd////////////////////////////
				String restMemId = req.getParameter("restMemId");
				
				
				
				String restMemPsw = req.getParameter("restMemPsw");
				
				String restMemReg = "^[a-zA-Z0-9_]{2,10}$";
				if(restMemId == null || (restMemId.trim()).length() == 0 ){
					errorMsgs.put("","�\�U�|���b������ť�");
				} else if(!restMemId.trim().matches(restMemReg)){
					errorMsgs.put("","�\�U�|���b���u��^��B�Ʀr�M_�A����2��10");
				}
				
				
				
				if(restMemPsw == null || (restMemPsw.trim()).length() == 0 ){
					errorMsgs.put("","�\�U�|���K�X����ť�");
				}else if(!restMemPsw.trim().matches(restMemReg)){
					errorMsgs.put("","�\�U�|���K�X�u��^��B�Ʀr�M_�A����2��10");
				}
				
				
				
				
			
				
				
				///////////////////////////�n�J���\///////////////////////////////
				
				if(allowUser(restMemId, restMemPsw)==null){
					errorMsgs.put("","�|���b���B�K�X���~");
					RequestDispatcher requestDispatcher =req.getRequestDispatcher("/front_end/restMember/restMemberLogin.jsp");
					requestDispatcher.forward(req, res) ;
					return;
				}else{
					HttpSession session = req.getSession();
					
					RestMember restMember = allowUser(restMemId, restMemPsw);
					Restaurant restaurant = restUser(restMember);
					session.setAttribute("restMember", restMember);
					session.setAttribute("restaurant", restaurant);
					session.setAttribute("restMemId", restMemId);
					
					
					try {
						String location = (String)session.getAttribute("location");
							if(location!=null){
								
								session.removeAttribute("location");
								res.sendRedirect(location);
								return;
							}
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					if(!errorMsgs.isEmpty()){
						RequestDispatcher requestDispatcher =req.getRequestDispatcher("/front_end/restMember/restMemberLogin.jsp");
						requestDispatcher.forward(req, res) ;
						return;
					}
					
					RequestDispatcher requestDispatcher =req.getRequestDispatcher("/front_end/activity/activityManagent.jsp");
					requestDispatcher.forward(req, res);
				}
				
				
				
				
				
				
				
			} 
		
		
		
		
		////////////////////////���U/////////////////////////////
		else if("register".equals(action)){
			
			List<String> hasAUser = new ArrayList<>();
			req.setAttribute("hasAUser", hasAUser);
			
			/////////////////////���ұb�����L�Q���U/////////////////////
			String restMemId = req.getParameter("restMemId");
			String restMemPsw = req.getParameter("restMemPsw");
			
			
			Integer restNo = Integer.parseInt(req.getParameter("restNo").trim()) ;
			
			String restMemReg = "^[a-zA-Z0-9_]{2,10}$";
			if(restMemId == null || (restMemId.trim()).length() == 0 ){
				hasAUser.add("�\�U�|���b������ť�");
			} else if(!restMemId.trim().matches(restMemReg)){
				hasAUser.add("�\�U�|���b���u��^��B�Ʀr�M_�A����2��10");
			}
			
			
			
			if(restMemPsw == null || (restMemPsw.trim()).length() == 0 ){
				hasAUser.add("�\�U�|���K�X����ť�");
			}else if(!restMemPsw.trim().matches(restMemReg)){
				hasAUser.add("�\�U�|���K�X�u��^��B�Ʀr�M_�A����2��10");
			}
			
			
			if(!hasAUser.isEmpty()){
				RequestDispatcher requestDispatcher1 =req.getRequestDispatcher("/front_end/restMember/restMemberRegister.jsp");
				requestDispatcher1.forward(req, res);
				return;
			}
			
			
			
			////////////////////�s���b��////////////////////////////
			RestMemberService restMemberServicea = new RestMemberService();
			RestMember restMember = restMemberServicea.addRestMember(restMemId, restNo, restMemPsw);
			
			
			
			req.setAttribute("restMember", restMember);
			
			RestaurantService restaurantService = new RestaurantService();
			Restaurant restaurant = restaurantService.updateBack(3, restNo);
			
			req.setAttribute("restaurant", restaurant);
			
			////////////////////�ǳ����////////////////////////////
			hasAUser.add("���ߵ��U���\");
			
			RequestDispatcher requestDispatcher2 = req.getRequestDispatcher("/front_end/restMember/restMemberLogin.jsp");
			requestDispatcher2.forward(req, res);
			
			
		}
		
		
		//////////////////////�n�X////////////////////////////////
		else if("logout".equals(action)){
			
			
			HttpSession session = req.getSession();
			
			session.removeAttribute("restMember");
			session.removeAttribute("restMemId");
			session.removeAttribute("restaurant");
//			System.out.println("ddddddd::::"+req.getAttribute("restMember"));
		
			RequestDispatcher requestDispatcher3 = req.getRequestDispatcher("/front_end/restMember/restMemberLogin.jsp");
			requestDispatcher3.forward(req, res);
		}
		
		
		
		
		
		///////////////////////��K�X////////////////////////////
		else if("changeRestMemPsw".equals(action)){
			
			
			List<String> changePswError = new ArrayList<>();
			req.setAttribute("changePswError", changePswError);
			
			
			///////////////////���ұb���K�X//////////////////////
			String restMemId = req.getParameter("restMemId");
			
			Integer restNo = Integer.parseInt(req.getParameter("restNo").trim());
			
			String restMemPsw = req.getParameter("restMemPsw");
			String restMemOldPsw = req.getParameter("restMemOldPsw");
			String restMemNewPsw = req.getParameter("restMemNewPsw");
			
			String restMemReg = "^[a-zA-Z0-9_]{2,10}$";
			
			if(restMemId == null || (restMemId.trim()).length() == 0 ){
				changePswError.add("�\�U�|���b������ť�");
			} else if(!restMemId.trim().matches(restMemReg)){
				changePswError.add("�\�U�|���b���u��^��B�Ʀr�M_�A����2��10");
			}
			
			if(restMemPsw == null || (restMemPsw.trim()).length() == 0 ){
				changePswError.add("�\�U�|���K�X����ť�");
			}else if(!restMemPsw.trim().matches(restMemReg)){
				changePswError.add("�\�U�|���K�X�u��^��B�Ʀr�M_�A����2��10");
			}
			
			if(!restMemNewPsw.equals(restMemPsw)){
				changePswError.add("�s�K�X��J���@�P");
			}
			
			if(allowUser(restMemId, restMemOldPsw)==null){
				changePswError.add("��l�K�X��J���~");
			}
			
			
			if(!changePswError.isEmpty()){
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/front_end/restMember/restMemberChangePsw.jsp");
				requestDispatcher.forward(req, res);
				return;
			}
			
			
			
			/////////////////////////���K�X////////////////////////
			RestMemberService restMemberService = new RestMemberService();
			RestMember restMember = restMemberService.updateRestMember(restMemId, restNo, restMemPsw);
			
			HttpSession session = req.getSession();
			
			session.getAttribute("restMember");
			session.removeAttribute("restMember");
			session.setAttribute("restMember", restMember);	
			
			
			//////////////////���//////////////////////////////////////////
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/front_end/activity/activityManagent.jsp");
			requestDispatcher.forward(req, res);
		}
		
		
		/////////////////////�ѰO�K�X//////////////////////////////
		
		else if("findPsw".equals(action)){
//			
//			String restMemId = req.getParameter("restMemId");
//			String Email = req.getParameter("restMemEmail");
			
			
			
		}
		
		
		
		
		
		
		
	}

}
