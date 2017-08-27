package com.dateitem.controller;

import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import com.lettertype.model.LetterTypeService;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.dateitem.model.*;
import com.dateitem.model.DateItemService;
import com.dateitem.model.DateItemVO;
import com.letter.model.LetterService;
import com.member.model.Member;
import com.member.model.MemberService;
import com.pet.model.Pet;
import com.pet.model.PetService;
import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantService;
@MultipartConfig(fileSizeThreshold =500* 1024 * 1024, maxFileSize = 500 * 1024 * 1024, maxRequestSize = 5 * 500 * 1024 * 1024)
public class DateItemServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=Big5");
		String action = req.getParameter("action");
		
		
		
		
		//��a�P�@�Ӯɶ����O�w�g���W�[�ӫ~
		if("QRC".equals(action)){	
				try {
					int dateItemNo = Integer.parseInt(req.getParameter("dateImteNo"));
					DateItemService dSvc = new DateItemService();
					DateItemVO  dateItemVO = dSvc.getOneDateItem(dateItemNo);
					dateItemVO.setDateItemStatus(3);
					dSvc.updateByVO(dateItemVO);

					
//	                ���ڪ�����	
//					MemberService mSvc = new MemberService();
//					Member seller =  mSvc.getOneMember(dateItemVO.getSellerNo());
//					int currentPoint = seller.getMemPoint();
//					seller.setMemPoint(currentPoint+dateItemVO.getDateItemPrice());
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Parse Error");
				}
		}		

		// �ˬd�O�_���d��
		if ("check_Seller".equals(action)) { 

			
			
			MemberService memSvc = new MemberService();
			List<Pet> myPetList = new ArrayList<Pet>();
			
			HttpSession session = req.getSession();
			Member member = (Member) session.getAttribute("member");

			myPetList = memSvc.getPetsByMemNo(member.getMemNo());
			
			
			//�S�d�����ϥΪ̾ɨ��d�����U
			if(myPetList.size()==0){
				String url = "/front_end/pet/petRegister.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}else{//���d�����ϥΪ̨�W�[
			req.setAttribute("myPetList", myPetList);
			String url = "/front_end/dateitem/addDateItem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}								
		}	
		
		//�R�a�˵��w�ʶR��������������ӫ~
		if ("list_buyer_future".equals(action)) { // �Ӧ�select_page.jsp�W�[���|�ӫ~
			       
			String url = "/front_end/dateitem/list_buyer_future.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
			successView.forward(req, res);
										
		}
		
		//�R�a�˵��w������������v�ӫ~
		if ("list_buyer_history".equals(action)) { // �Ӧ�select_page.jsp�W�[���|�ӫ~
			        
			String url = "/front_end/dateitem/list_buyer_history.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
			successView.forward(req, res);
										
		}
		
		if("checkCharge".equals(action)){
			HttpSession session = req.getSession();
			Member member = (Member) session.getAttribute("member");
			DateItemService dSvc = new DateItemService();
			
			System.out.println(req.getParameter("dateItemNo"));
			DateItemVO dateItemVO =dSvc.findByPK(Integer.parseInt(req.getParameter("dateItemNo")));
			if(member.getMemPoint()< dateItemVO.getDateItemPrice()) {
				PrintWriter out = res.getWriter();
				out.print("�l�B�����l�B����");
			}else{
				PrintWriter out = res.getWriter();
				out.print(" ");
			}
			
		}
		
		
		//��a�P�@�Ӯɶ����O�w�g���W�[�ӫ~
		if("checkTime".equals(action)){
			
				HttpSession session = req.getSession();
				Member member = (Member) session.getAttribute("member");
				DateItemService dSvc = new DateItemService();
				List<DateItemVO> list = dSvc.findBySeller_onsale(member.getMemNo());
				List<DateItemVO> list2 = dSvc.findBySeller_future(member.getMemNo());
				System.out.println("�@�����|"+list.size());
				String timeStr = req.getParameter("time");
				System.out.println(timeStr);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				try {
					Date t1 = sdf.parse(timeStr);
					System.out.println("t1"+t1);
					int count=0;
					for(DateItemVO dateItemVO:list){
						//��C�Ӱӫ~��ثe�n�W�[���ɶ����নlong����O�_�t�Z�|�p��
						long diff = t1.getTime()- dateItemVO.getDateMeetingTime().getTime();
						int diffhour = (int)(diff/(60 * 60 * 1000)) ;
						if (diffhour<4 && diffhour>-4){
							count++;
						}
						
					}
					for(DateItemVO dateItemVO:list2){
						//��C�Ӱӫ~��ثe�n�W�[���ɶ����নlong����O�_�t�Z�|�p��
						long diff = t1.getTime()- dateItemVO.getDateMeetingTime().getTime();
						int diffhour = (int)(diff/(60 * 60 * 1000)) ;				
						if (diffhour<4 && diffhour>-4){
							count++;
						}
						
					}					
					PrintWriter out = res.getWriter();
					
					out.print(count);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					System.out.println("Parse Error");
				}
		}					
		
		
		
		//�R�a�ʶR�@�Ӱӫ~
		if("buy_date".equals(action)){
		//�ˬd�x��
			
			
			HttpSession session = req.getSession();
			Member member = (Member) session.getAttribute("member");
			int buyerNo = member.getMemNo();
						
						
			Integer dateItemNo = new Integer(req.getParameter("dateItemNo").trim());
			DateItemService dSvc = new DateItemService();
			DateItemVO dateItemVO = dSvc.findByPK(dateItemNo);
			
			//call�t�@�����O�� ������w������k:�ʶR
			BuyDateItem buyDateItem = new BuyDateItem();
			Boolean result= buyDateItem.buyAItem(buyerNo, dateItemVO);
			//�����ʶR������setAttribute,�����ʶR�����˵�
			if (result==true){
				req.setAttribute("itemPurchased", dateItemVO);
				String url = "/front_end/dateitem/list_buyer_future.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				LetterService lSvc = new LetterService();
				lSvc.addLtrOfDateBeBought(dateItemVO);
				lSvc.addLtrOfBuyDateItemSucess(dateItemVO);
				}else{
					req.setAttribute("itemNotFound", dateItemVO);
					String url = "/front_end/dateitem/list_buyer_future.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);	
				}
		}
				
				
		if("reinsert".equals(action)){
		//���s�W�[�ӫ~
			
			HttpSession session = req.getSession();
			Member member = (Member) session.getAttribute("member");
			int sellerNo = member.getMemNo();
			int dateItemNo = Integer.parseInt(req.getParameter("dateItemNo"));
			DateItemService dSvc = new DateItemService();
			DateItemVO dateItemVO = dSvc.findByPK(dateItemNo);
			
			try {

				
				//�ˬd���U�s�����T�ꬰ��a�åB�Ӱӫ~�T��Q����
				if(sellerNo==dateItemVO.getSellerNo()&& dateItemVO.getDateItemShow()==1 && dateItemVO.getDateItemStatus()==2){
					//�ˬd�Ӱӫ~���L��
					if(dateItemVO.getDateMeetingTime().getTime()>System.currentTimeMillis()){
						dSvc.addDateItem(sellerNo, dateItemVO.getRestListNo(), dateItemVO.getDateItemTitle(), dateItemVO.getDateItemImg(),
						dateItemVO.getDateItemText(), new Timestamp(System.currentTimeMillis()), dateItemVO.getDateMeetingTime(), dateItemVO.getDateItemLocate(), dateItemVO.getDateItemPeople(),
						dateItemVO.getHasMate(), dateItemVO.getDateItemPrice(), 0, 0, dateItemVO.getDateItemViewer(), 5010,
						dateItemVO.getIsQRCChecked(), dateItemVO.getBuyerRep(), dateItemVO.getSellerRep(), dateItemVO.getIsInstantDate() , dateItemVO.getPetNo());
						req.setAttribute("itemAdded", dateItemVO);
						String url = "/front_end/dateitem/list_seller_onsale.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
						successView.forward(req, res);	
					}
				}else{
					req.setAttribute("canNotReinsert", dateItemVO);
					String url = "/front_end/dateitem/list_seller_onsale.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);	
				}
			} catch (Exception e) {
					req.setAttribute("canNotReinsert", dateItemVO);
					String url = "/front_end/dateitem/list_seller_onsale.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					}
			}				
			
			

			
						
			

		
		
		
		//�����@�Ӱӫ~
		if("cancel_date".equals(action)){
		//�����L�ˬd�x�Ȫ�����
			
			
						
			Integer dateItemNo = new Integer(req.getParameter("dateItemNo").trim());
			DateItemService dSvc = new DateItemService();
			MemberService mSvc = new MemberService();
			DateItemVO dateItemVO = dSvc.findByPK(dateItemNo);
			
			//�ק窱�A��������
			dateItemVO.setDateItemStatus(2);
			dateItemVO.setDateItemShow(1);
			dSvc.updateByVO(dateItemVO);
			
			//�x�Ȫ��B����
			int currentPoint = mSvc.getOneMember(dateItemVO.getBuyerNo()).getMemPoint();
			mSvc.getOneMember(dateItemVO.getBuyerNo()).setMemPoint(currentPoint+dateItemVO.getDateItemPrice());
			
			//��������Ӧ۶R���٬O���,���O�ɦ^�R��誺���v����
			try {
				
				req.setAttribute("itemCanceled", dateItemVO);
				if (req.getParameter("fromwho").equals("buyer")){
					LetterService lSvc = new LetterService();
					lSvc.addLtrOfBuyerCancelDate(dateItemVO);
					
					String url = "/front_end/dateitem/list_buyer_history.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					}
				if	(req.getParameter("fromwho").equals("seller")){
					String url = "/front_end/dateitem/list_seller_history.jsp";
					LetterService lSvc = new LetterService();
					lSvc.addLtrOfSellerCancelDate(dateItemVO);
					
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					}
			} catch (Exception e) {
					String url = "/front_end/dateitem/select_page.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					}
			}			
		
		
		
		
            if ("insert".equals(action)) { // �Ӧ�addDateItem.jsp���ШD  
			
            	List<String> errorMsgs = new LinkedList<String>();
            
            	// Store this set in the request scope, in case we need to
            	// send the ErrorPage view.
            	req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				
				if (req.getParameter("time") == null || (req.getParameter("time").trim()).length() == 0) {
					errorMsgs.add("�п�ܮɶ�");
				}
				
				String htmltime = req.getParameter("time");
				;
				
				Timestamp dateMeetingTime = getTimestamp(htmltime);
				System.out.println(getTimestamp(htmltime));
				Timestamp dateItemTime = new Timestamp(System.currentTimeMillis());
				
				// Send the use back to the form, if there were errors
				
				String dateItemTitle = req.getParameter("dateItemTitle");
				if (dateItemTitle == null || (dateItemTitle.trim()).length() == 0 ) {
					errorMsgs.add("�п�J���D");
					dateItemTitle = "";
				}
				// Send the use back to the form, if there were errors
				
				String dateItemText = req.getParameter("dateItemText");
				if (dateItemText == null || (dateItemText.trim()).length() == 0) {
					errorMsgs.add("�п�J���e");
					dateItemText = "";
				}
				// Send the use back to the form, if there were errors
						
				//���ҹϤ�����=====================================================
				byte[] dateItemImg= null;
				String fileName=null;
				Part part = req.getPart("dateItemImg");
				
				
				
					if(part.getSize()!=0){
						fileName = getFileNameFromPart(part);
						System.out.println(part.equals(null));
						if(getServletContext().getMimeType(fileName).substring(0,5).equals("image")){
							dateItemImg = getByteArrayImg(part);
							System.out.println("�Ϥ��榡���T!");
						}
					}else {
					errorMsgs.add("�ФW�ǹϤ�");
				}
				
				
				

		        //============================================================================		
				
				//�p�G���~�^�Ǥ@��VO=====================================
				
				HttpSession session = req.getSession();
				Member member = (Member) session.getAttribute("member");
				DateItemVO dateItemVO = new DateItemVO();
				
				dateItemVO.setSellerNo(member.getMemNo());
				
				dateItemVO.setRestListNo(Integer.parseInt(req.getParameter("restListNo")));
			
				dateItemVO.setPetNo(Integer.parseInt(req.getParameter("petNo")));
				dateItemVO.setDateItemTitle(dateItemTitle);
			
				dateItemVO.setDateItemText(dateItemText);
				dateItemVO.setDateItemPrice(Integer.parseInt(req.getParameter("dateItemPrice")));
			
			
				
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					//���s���a�d����T
					MemberService memSvc = new MemberService();
					List<Pet> myPetList = new ArrayList<Pet>();
					myPetList = memSvc.getPetsByMemNo(member.getMemNo());
					
					req.setAttribute("myPetList", myPetList);
					
					req.setAttribute("dateItemVO", dateItemVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/dateitem/addDateItem.jsp");
					failureView.forward(req, res);
					return;
				}
				

				
				/***************************2.�}�l�s�W���***************************************/
				//sellerno�૬
				
				int sellerno = member.getMemNo();
				int restListNo = Integer.parseInt(req.getParameter("restListNo"));
				int petNo = Integer.parseInt(req.getParameter("petNo"));
				int dateItemPeople = Integer.parseInt(req.getParameter("dateItemPeople"));
				Boolean hasMate = Boolean.parseBoolean((req.getParameter("hasMate")));
				int dateItemPrice = Integer.parseInt(req.getParameter("dateItemPrice"));
				//���ܼƦb��Ʈw������l��
				int dateItemStatus = 0 ;
				int dateItemShow = 0;
				int dateItemViewer = 0;
				int buyerNo = 5010 ;
				boolean isQRCChecked = false;
				int buyerRep = 0 ;
				int sellerRep = 0 ;
				boolean isInstantDate = false;

				//�ˬd�O�_����
//				System.out.println(sellerno);
//				System.out.println(restListNo);
//				System.out.println(dateItemTitle);
//				System.out.println(dateItemText);
//				System.out.println(dateItemTime);
//				System.out.println(dateMeetingTime);
//				System.out.println(dateItemPeople);
//				System.out.println(hasMate);
//				System.out.println(dateItemPrice);
//				System.out.println(dateItemStatus);
//				System.out.println("===================");
//				System.out.println(dateItemShow);
//				System.out.println(dateItemViewer);
//				System.out.println(buyerNo);
//				System.out.println(isQRCChecked);
//				System.out.println(buyerRep);
//				System.out.println(sellerRep);
//				System.out.println(isInstantDate);
//				System.out.println(petNo);
//				
//				System.out.println("66");
				RestaurantService rSvc = new RestaurantService();
//				System.out.println("===================2");
				Restaurant restaurant = rSvc.getOneRest(Integer.parseInt(req.getParameter("restListNo"))); 
//				System.out.println("===================1");
				String dateItemLocate = restaurant.getRestLocate();
//				System.out.println(dateItemLocate);
				
				System.out.println("77");
				DateItemService dateItemSvc = new DateItemService();
				System.out.println("�ǳƷs�W");
				System.out.println("88");
				dateItemVO = dateItemSvc.addDateItem(sellerno, restListNo, dateItemTitle, dateItemImg, dateItemText, dateItemTime, dateMeetingTime, dateItemLocate, dateItemPeople, hasMate, dateItemPrice, dateItemStatus, dateItemShow, dateItemViewer, buyerNo, isQRCChecked, buyerRep, sellerRep, isInstantDate, petNo);
				System.out.println("99");
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				req.setAttribute("itemAdded", dateItemVO);
				String url = "/front_end/dateitem/list_seller_onsale.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				HttpSession session = req.getSession();
				Member member = (Member) session.getAttribute("member");
				MemberService memSvc = new MemberService();
				List<Pet> myPetList = new ArrayList<Pet>();
				myPetList = memSvc.getPetsByMemNo(member.getMemNo());
				
				req.setAttribute("myPetList", myPetList);
				errorMsgs.add("�п�J����ӫ~��T");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/dateitem/addDateItem.jsp");
				failureView.forward(req, res);
			}
		}
            
            
            
            
    		// �Ӧ�select_page.jsp�ƦX�d�߬��|�ӫ~
    		if ("listDItems_ByCompositeQuery".equals(action)) {

    			List<String> errorMsgs = new LinkedList<String>();
    			// Store this set in the request scope, in case we need to
    			// send the ErrorPage view.
    			req.setAttribute("errorMsgs", errorMsgs);

    			try {

    				/*************************** 1.�N��J����ରMap **********************************/
    				// �ĥ�Map<String,String[]> getParameterMap()����k
    				// �`�N:an immutable java.util.Map
    				// Map<String, String[]> map = req.getParameterMap();
//    				HashMap<String, String[]> map = (HashMap<String, String[]>) req.getParameterMap();
    				HttpSession session = req.getSession();
    				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
    				if (req.getParameter("whichPage") == null) {
    					HashMap<String, String[]> map1 = (HashMap<String, String[]>) req.getParameterMap();
    					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
    					map2 = (HashMap<String, String[]>) map1.clone();
    					session.setAttribute("map", map2);
    					map = (HashMap<String, String[]>) req.getParameterMap();
    				}
    				
    				/*************************** 2.�}�l�ƦX�d�� ***************************************/
    				DateItemService dateItemSvc = new DateItemService();
    				List<DateItemVO> list = dateItemSvc.getAll(map);
    				MemberService memSvc=new MemberService();
    				PetService petSvc=new PetService();
    				System.out.println("******************************�s����*************************************");
    				for(DateItemVO dateItem:list){
    					System.out.println("�ӫ~�s��: :"+dateItem.getDateItemNo());
    					System.out.println("�ӫ~���|�ɶ�: :"+dateItem.getDateMeetingTime());
    					System.out.println("�|���ʧO: "+memSvc.getOneMember(dateItem.getBuyerNo()).getMemGender());
    					System.out.println("�d������: "+petSvc.getOnePet(dateItem.getPetNo()).getPetKind());
    					System.out.println("===================================");
    				}
    				/**************************** 3.�d�ߧ���,�ǳ����(Send the Success view)************/
    				req.setAttribute("listEmps_ByCompositeQuery", list); // ��Ʈw���X��list����,�s�Jrequest
    				RequestDispatcher successView = req.getRequestDispatcher("/front_end/dateitem/compositeQuery.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
    				successView.forward(req, res);

    				/*************************** ��L�i�઺���~�B�z **********************************/
    			} catch (Exception e) {
    				errorMsgs.add(e.getMessage());
    				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/dateitem/compositeQuery.jsp");
    				failureView.forward(req, res);
    			}
    		}
    		
    		
    		// �Ӧ�googleMapQuery.jsp�ƦX�d�߬��|�ӫ~
    		if ("googleMapQuery".equals(action)) {

    			List<String> errorMsgs = new LinkedList<String>();
    			// Store this set in the request scope, in case we need to
    			// send the ErrorPage view.
    			req.setAttribute("errorMsgs", errorMsgs);
    			
    			try {

    				/*************************** 1.�N��J����ରMap **********************************/
    				String date = null;
    				try{
    					date=req.getParameter("dateMeetingTime");
    				}
    				catch(Exception e){
    					errorMsgs.add("������~");
    				}
    				/*************************** 2.�}�l�ƦX�d�� ***************************************/
    				DateItemService dateItemSvc = new DateItemService();
    				List<SDateItemVO> list = dateItemSvc.findByDate(date);
    			    
    				//�U���|��O�L�L�ݦ�list�@�h�j
    				ByteArrayOutputStream baos = new ByteArrayOutputStream();
    			    ObjectOutputStream out = new ObjectOutputStream(baos);
    			    out.writeObject(list);
    			    out.close();
    			    System.out.print(list.size());
    			    System.out.println(list.getClass().getSimpleName() +" used " + baos.toByteArray().length + " bytes");
    				


    				/**************************** 3.�d�ߧ���,�ǳ����(Send the Success view)************/
    				HttpSession session=req.getSession();
    				req.setAttribute("googleMaplist", list); // ��Ʈw���X��list����,�s�Jrequest
    				req.setAttribute("date", date);
    				session.setAttribute("result",list);
    				RequestDispatcher successView = req.getRequestDispatcher("/front_end/dateitem/gMapSearchResult.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
    				successView.forward(req, res);

    				/*************************** ��L�i�઺���~�B�z **********************************/
    			} catch (Exception e) {
    				errorMsgs.add(e.getMessage());
    				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/dateitem/googleMapQuery.jsp");
    				failureView.forward(req, res);
    			}
    		}
    		
    		
    		
    		
    		// �Ӧ�googleMapQuery.jsp�d�ߦU�\�U���|�ӫ~
    		if ("showDItemFromMap".equals(action)) {

    			List<String> errorMsgs = new LinkedList<String>();
    			// Store this set in the request scope, in case we need to
    			// send the ErrorPage view.
    			req.setAttribute("errorMsgs", errorMsgs);

    			try {

    				/*************************** 1.���o��J��� **********************************/

    			
    				HttpSession session = req.getSession();
    				List<String> list = (List<String>) session.getAttribute("dateItemNo");
    				if (req.getParameter("whichPage") == null) {
    					String dateItemNos=req.getParameter("dateItemNo");
    					list=Arrays.asList(dateItemNos.split(","));
    					session.setAttribute("dateItemNo", list);
    				}
    				
    				
    				/*************************** 2.�}�l�d�� ***************************************/
    				DateItemService dateItemSvc = new DateItemService();
    				List<DateItemVO> dlist=new ArrayList<DateItemVO>();
    				try{
    				for(String dItemNo:list){
    					Integer dNo=Integer.parseInt(dItemNo);
    					DateItemVO dIVO=dateItemSvc.findByPK(dNo);
    					dlist.add(dIVO);
    				}
    				}
    				catch(Exception e){
    					errorMsgs.add("�d�ߵo�ͤF�@�I�p���D");
    				}

    				/**************************** 3.�d�ߧ���,�ǳ����(Send the Success view)************/
    				req.setAttribute("listEmps_ByCompositeQuery", dlist); // ��Ʈw���X��list����,�s�Jrequest
    				RequestDispatcher successView = req.getRequestDispatcher("/front_end/dateitem/gMapShowDItem.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
    				successView.forward(req, res);

    				/*************************** ��L�i�઺���~�B�z **********************************/
    			} catch (Exception e) {
    				errorMsgs.add(e.getMessage());
    				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/dateitem/gMapShowDItem.jsp");
    				failureView.forward(req, res);
    			}
    		}
    		
    		
    		
    		
    		
    		
    		// �Ӧ�googleMapQuery.jsp�����|�ӫ~
    		if ("googleMapFilter".equals(action)) {
    		
    			List<String> errorMsgs = new LinkedList<String>();
    			// Store this set in the request scope, in case we need to
    			// send the ErrorPage view.
    			req.setAttribute("errorMsgs", errorMsgs);
    			try {

    				/*************************** 1.�����ШD�Ѽ�**********************************/
    				Integer memGender=null;
    				try{
    				memGender=Integer.parseInt(req.getParameter("memGender"));	
    				}
    				catch(Exception e){
    					errorMsgs.add("�d�ߵo�Ͱ��D");
    				}

    				String petKind=req.getParameter("petKind");
//    				if(petKind==null){
//    					errorMsgs.add("�d�ߵo�Ͱ��D");
//    				}
    				
    				String date=req.getParameter("date");
    		
    				
    				/*************************** 2.�}�l��� ***************************************/
    				HttpSession session=req.getSession();
    				List<SDateItemVO> list=(List<SDateItemVO>)session.getAttribute("result");


    				Predicate<SDateItemVO> predicate_gender = sDate -> (sDate.getMemGender()==0||sDate.getMemGender()==1||sDate.getMemGender()==2);
    				if(memGender==0){
    					predicate_gender = sDate -> sDate.getMemGender()==0;
    				}
    				if(memGender==1){
    					predicate_gender = sDate -> sDate.getMemGender()==1;
    				}
    				if(memGender==2){
    					predicate_gender = sDate -> sDate.getMemGender()==2;	
    				}
    				

    				Predicate<SDateItemVO> predicate_petKind = sDate -> (sDate.getPetKind().equals("��")||sDate.getPetKind().equals("��")||sDate.getPetKind().equals("��L"));
    				if(petKind.equals("��")){
    					predicate_petKind = sDate -> sDate.getPetKind().equals("��");
    				}
    				if(petKind.equals("��")){
    					predicate_petKind = sDate -> sDate.getPetKind().equals("��");
    				}
    				if(petKind.equals("��L")){
    					predicate_petKind = sDate -> sDate.getPetKind().equals("��L");	
    				}
    				
    				List<SDateItemVO> fList = 
    					     list
    					     .stream()
    					     .filter(predicate_gender)
    					     .filter(predicate_petKind)
    					     .distinct()
    					     .collect(Collectors.toList());

    				/**************************** 3.�ק粒��,�ǳ����(Send the Success view)************/
    				req.setAttribute("filterList",fList); // ��Ʈw���X��list����,�s�Jrequest
    				req.setAttribute("memGender", memGender);
    				req.setAttribute("petKind", petKind);
    				req.setAttribute("date", date);
    				RequestDispatcher successView = req.getRequestDispatcher("/front_end/dateitem/gMapSearchResult.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
    				successView.forward(req, res);

    				/*************************** ��L�i�઺���~�B�z **********************************/
    			} catch (Exception e) {
    				errorMsgs.add(e.getMessage());
    				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/dateitem/gMapSearchResult.jsp");
    				failureView.forward(req, res);
    			}
    		}    
            
            
            
            
            
	}
	
	public byte[] getByteArrayImg(Part part){
		
		ByteArrayOutputStream diaimg=null;
		try{
			java.io.InputStream in =part.getInputStream(); 
			diaimg = new ByteArrayOutputStream();
			byte[] buffer = new byte[8192];
			int i;
			while ((i = in.read(buffer)) != -1) {
				diaimg.write(buffer, 0, i);
			}
			diaimg.close();
			in.close();
			
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return diaimg.toByteArray();
	}
	
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		String filename = header.substring(header.lastIndexOf("=") + 2, header.length() - 1);
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
	
	//�NHtml5 date�ରTimestamp��Method
	
//	public Timestamp getTimestamp(String dateStr){	
//		int yyyy= Integer.parseInt((dateStr.substring(0, 4)));
//		int mm= Integer.parseInt((dateStr.substring(5, 7)));
//		int dd= Integer.parseInt((dateStr.substring(8, 10)));
//		int hh= Integer.parseInt((dateStr.substring(11, 13)));
//		int minute= Integer.parseInt((dateStr.substring(14, 16)));
//	GregorianCalendar cal = new GregorianCalendar(yyyy,mm-1,dd,hh,minute,0);
//	java.util.Date ud = cal.getTime();
//	Timestamp ts= new Timestamp(ud.getTime());
//	return ts;	
//	}
	
	public Timestamp getTimestamp(String dateStr){	
		int yyyy= Integer.parseInt((dateStr.substring(0, 4)));
		int mm= Integer.parseInt((dateStr.substring(5, 7)));
		int dd= Integer.parseInt((dateStr.substring(8, 10)));
		int hh= Integer.parseInt((dateStr.substring(11, 13)));
		int minute= Integer.parseInt((dateStr.substring(14, 16)));
	GregorianCalendar cal = new GregorianCalendar(yyyy,mm-1,dd,hh,minute,0);
	java.util.Date ud = cal.getTime();
	Timestamp ts= new Timestamp(ud.getTime());
	return ts;	
	}
			
//  �վ�Ϥ��j�p	
//	private static BufferedImage resizeImage(BufferedImage originalImage,int type){
//		BufferedImage resizedImage = new BufferedImage(150,150,type);
//		Graphics2D g = resizedImage.createGraphics();
//		g.drawImage(originalImage, 0, 0, 150, 150, null);
//		g.dispose();
//		
//		return resizedImage;
//	}
//	
}
	

