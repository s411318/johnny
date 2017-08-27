package com.activity.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.*;

import javax.imageio.ImageIO;
import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.activity.model.Activity;
import com.activity.model.ActivityService;
import com.letter.model.LetterService;
import com.restMember.model.RestMember;
import com.restMember.model.RestMemberService;
import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantService;



@MultipartConfig
public class ActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static byte[] getPictureByteArray(InputStream fis) throws IOException {
		
		BufferedImage originalImage = ImageIO.read(fis);
		int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
		BufferedImage resizeImageJpg = resizeImage(originalImage, type);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(resizeImageJpg, "jpg", baos);
		baos.flush();
		baos.close();
		return baos.toByteArray();
	}
	
	
	

	private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
		BufferedImage resizedImage = new BufferedImage(400, 300, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, 400, 300, null);
		g.dispose();
		
		return resizedImage;
	}

	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
       
    //Pattern.compile("[\\p{InCJKUnifiedIdeographs}]"); //�u���\����
	//Pattern.compile("[^\\x00-\\x40\\x5B-\\x60\\x7B-\\x7F]"); //�u���\�^��r���H�Τ���
    public ActivityServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("newActivity".equals(action)){
			
			List<String> activityError = new ArrayList<>();
			req.setAttribute("activityError", activityError);
			
			
			
			String restMemId = req.getParameter("restMemId");
			
			Integer actStatus = null;
			try {
				actStatus = Integer.parseInt(req.getParameter("actStatus"));
			} catch (Exception e) {
				activityError.add("���ʪ��A���~");
			}
			
			String actName = req.getParameter("actName");
			String meg = "^[a-zA-Z0-9_\u4E00-\u9FFF]{0,30}";
			
			if(actName==null || (actName.trim()).length()==0 ){
				activityError.add("�п�J���ʦW��");
			}
			else if(!actName.trim().matches(meg)){
				activityError.add("�п�J���B�^��B�Ʀr�άO_�A���׳̤j��30�r");
			}
			
			String actContent = req.getParameter("actContent");
			String meg1 = "^[a-zA-Z0-9_\u4E00-\u9FFF]";
			
			if(actContent==null||(actContent.trim()).length()==0){
				activityError.add("�п�J���ʤ��e");
			}
			
			
			java.sql.Date actDate = null;
			try {
				actDate = java.sql.Date.valueOf(req.getParameter("actDate").trim());
			} catch (Exception e) {
				actDate = new java.sql.Date(System.currentTimeMillis());
				activityError.add("�п�J���ʤ��");
			}
			
			java.sql.Date actFDate = null;
			try {
				actFDate = java.sql.Date.valueOf(req.getParameter("actFDate").trim());
			} catch (Exception e) {
				actFDate = new java.sql.Date(System.currentTimeMillis());
				activityError.add("�п�J�I����");
			}
			
			if(!actDate.after(actFDate) || actDate.equals(actFDate)){
				activityError.add("�I��������W�L���ʤ���άO�P�@��");
			}
			
			
			
			Integer actULimit = null;
			try {
				actULimit = Integer.parseInt(req.getParameter("actULimit").trim());
				if(actULimit<0){
					activityError.add("�п�J���T�W���H��");
				}
			} catch (Exception e) {
				actULimit = 0 ;
				activityError.add("�п�J���T�W���H��");
			}
			
			Integer actLLimit = null;
			try {
				actLLimit = Integer.parseInt(req.getParameter("actLLimit").trim());
				if(actLLimit<0){
					activityError.add("�п�J���T�U���H��");
				}
			} catch (Exception e) {
				actLLimit = 0 ;
				activityError.add("�п�J���T�U���H��");
			}
			
			if(actLLimit>actULimit){
				activityError.add("�U���H�Ƥ���W�V�W���H��");
			}
			
			Integer actKind = null;
			try {
				actKind = new Integer(req.getParameter("actKind").trim());
			} catch (Exception e) {
				activityError.add("���ʺ���������~");
			}
			
			String actAnotherKind = req.getParameter("actAnotherKind");
			
			
			byte[] actInitImg =null;
			Collection<Part> parts = req.getParts();
			
			try {
				for(Part part :parts){
					
					if(part.getName().equals("actInitImg") && getFileNameFromPart(part) != null
							&& part.getContentType().startsWith("image")){
						
						actInitImg = getPictureByteArray(part.getInputStream());
					}
				}
			} catch (Exception e) {
				activityError.add("�Ӥ����~");
			}
			
			
			
			
			
			
			if(!activityError.isEmpty()){
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/front_end/activity/newActivity.jsp");
				requestDispatcher.forward(req, res);
				return;
			}
			
			
			/////////////////////////�s�W����/////////////////////////////
			ActivityService activityService = new ActivityService();
			Activity activity = activityService.addActivity(restMemId, actName, actContent,
					actDate, actFDate, actStatus, actULimit, actLLimit, actKind, actAnotherKind, actInitImg);
			
			req.setAttribute("activity", activity);
			
			
			
			////////////////////////��s////////////////////////////////
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/front_end/activity/activityManagent.jsp");
			requestDispatcher.forward(req, res);
		}
		
		
		
		//////////////////////////��s/////////////////////////////////////
		else if("updateActivity".equals(action)){
			List<String> updateErr = new ArrayList<>();
			req.setAttribute("updateErr", updateErr);
			///////////////////////����/////////////////////////////////////
			
			Integer actNo = null;
			try {
				actNo = Integer.parseInt(req.getParameter("actNo").trim());
			} catch (Exception e) {
				updateErr.add("���ʽs�����~");
			}
			
			
			String restMemId = req.getParameter("restMemId");
			if((restMemId.trim()).length()==0 || restMemId==null){
				updateErr.add("�\�U�|���b�����~");
			}
			
			
			String actName = req.getParameter("actName");
			if((actName.trim()).length()==0 || actName==null){
				updateErr.add("�ж�g���T���ʦW��");
			}
			
			
			
			String actContent = req.getParameter("actContent");
			if((actContent.trim()).length()==0 || actContent==null){
				updateErr.add("�ж�g���T���ʤ��e");
			}
			
			
			
			java.sql.Date actDate = null;
			try {
				actDate = java.sql.Date.valueOf(req.getParameter("actDate").trim());
			} catch (Exception e) {
				actDate = new java.sql.Date(System.currentTimeMillis());
				updateErr.add("�ж�g���T���ʤ��");
			}
			
			java.sql.Date actFDate = null;
			try {
				actFDate = java.sql.Date.valueOf(req.getParameter("actFDate").trim());
			} catch (Exception e) {
				actFDate = new java.sql.Date(System.currentTimeMillis());
				updateErr.add("�п�J�I����");
			}
			
			if(!actDate.after(actFDate) || actDate.equals(actFDate)){
				updateErr.add("�I��������W�L���ʤ���άO�P�@��");
			}
			
			
			
			Integer actULimit = null;
			try {
				actULimit = Integer.parseInt(req.getParameter("actULimit").trim());
				if(actULimit<0){
					updateErr.add("�п�J���T�W���H��");
				}
			} catch (Exception e) {
				actULimit = 0 ;
				updateErr.add("�п�J���T�W���H��");
			}
			
			Integer actLLimit = null;
			try {
				actLLimit = Integer.parseInt(req.getParameter("actLLimit").trim());
				if(actLLimit<0){
					updateErr.add("�п�J���T�U���H��");
				}
			} catch (Exception e) {
				actLLimit = 0 ;
				updateErr.add("�п�J���T�U���H��");
			}
			
			if(actLLimit>actULimit){
				updateErr.add("�U���H�Ƥ���W�V�W���H��");
			}
			
			Integer actKind = null;
			try {
				actKind = new Integer(req.getParameter("actKind").trim());
			} catch (Exception e) {
				updateErr.add("���ʺ���������~");
			}
			
			Integer actStatus = null;
			try {
				actStatus = Integer.parseInt(req.getParameter("actStatus").trim());
			} catch (Exception e) {
				updateErr.add("���ʪ��A���~");
			}
			
			byte[] actInitImg = null;
			Collection<Part> parts = req.getParts();
			
//			Part img = req.getPart("actInitImg");
			
			try {
				for(Part part:parts){
					
					
					
					if(part.getName().equals("actInitImg") && getFileNameFromPart(part) != null
							&& part.getContentType().startsWith("image")){
						actInitImg = getPictureByteArray(part.getInputStream());
						
					}else{
						
						
						ActivityService activityService = new ActivityService();
						Activity activityImg = activityService.getOneActivity(actNo);
						req.setAttribute("activityImg", activityImg);
					}
						

					
//					if(part.getSize()==0){
//						ActivityService activityService = new ActivityService();
//						Activity activity = activityService.getOneActivity(actNo);
//						actInitImg = activity.getActInitImg();
//					}
				}
//				
//				HttpSession session = req.getSession();
//				
//				if(img.getSize() !=0){ //�p�G����s��
//					
//					BufferedInputStream bi = new BufferedInputStream(img.getInputStream());
//					byte[] actInitImg = new byte[bi.available()];
//					bi.read(actInitImg);
//					bi.close();
//					
//					Activity activity = new Activity();
//					activity.setActInitImg(actInitImg);
//					
//					
//                   // �o��n��s���
//					ActivityService activityService = new ActivityService();
//					activity = activityService.updateActivity(actNo, restMemId, actName, actContent, actDate,
//							actFDate, actStatus, actULimit, actLLimit, actKind, null, actInitImg);
//					session.removeAttribute("activity");
//					req.setAttribute("activity", activity);
//
//					
//				}else if(img.getSize() == 0){
//					
//					
//					Activity imgs = (Activity) session.getAttribute("activity"); // �qsession��VO
//					
//					byte[] actInitImg = imgs.getActInitImg();//���^�쥻����
//					
//					Activity activity = new Activity();
//					activity.setActInitImg(actInitImg);//SET
//					
//					ActivityService activityService = new ActivityService();
//					activity = activityService.updateActivity(actNo, restMemId, actName, actContent, actDate,
//							actFDate, actStatus, actULimit, actLLimit, actKind, null, actInitImg);
//					
//					session.removeAttribute("activity");//�����o��SESSION
//					req.setAttribute("activity", activity);
//				}
				
				
			} catch (Exception e) {
				
				updateErr.add("�Ӥ����~");
			}
			
			
			
			String actAnotherKind = req.getParameter("actAnotherKind");
			
			if(!updateErr.isEmpty()){
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/front_end/activity/activityManagent.jsp");
				requestDispatcher.forward(req, res);
				return;
			}
			
			
			/////////////////////////��s�������/////////////////////////////
			ActivityService activityService = new ActivityService();
			Activity activityImg = (Activity)req.getAttribute("activityImg");
			Activity activity = activityService.updateActivity(actNo, restMemId, actName, actContent, actDate,
					actFDate, actStatus, actULimit, actLLimit, actKind, actAnotherKind,
					
					(actInitImg!=null)?actInitImg:activityImg.getActInitImg());
			
			req.setAttribute("activity", activity);
			
			////////////////////////��s////////////////////////////////////
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/front_end/activity/activityManagent.jsp");
			requestDispatcher.forward(req, res);
			
		}
		
		else if("actManagement".equals(action)){
			List<String> actManaErr = new ArrayList<>();
			req.setAttribute("actManaErr", actManaErr);
			
			
			Integer actStatus = null;
			try {
				actStatus = Integer.parseInt(req.getParameter("actStatus"));
			} catch (Exception e) {
				actManaErr.add("���ʪ��A���~");
			}
			
			Integer actNo = null;
			try {
				actNo = Integer.parseInt(req.getParameter("actNo"));
			} catch (Exception e) {
				actManaErr.add("���ʽs�����~");
			}
			
			if(!actManaErr.isEmpty()){
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/back_end/activityManagement/activityManagement.jsp");
				requestDispatcher.forward(req, res);
				return;
			}
			
			////////////////////////��ݧ�s///////////////////////////////////
			ActivityService activityService = new ActivityService();
			Activity activity = activityService.updateBack(actStatus, actNo);
			req.setAttribute("activity", activity);
			
			
			/////////////////////////��s////////////////////////////////////
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/back_end/activityManagement/activityManagement.jsp");
			requestDispatcher.forward(req, res);
		}
		
		else if("CompositeQueryBackAct".equals(action)){
			
			Map<String, String[]> map = req.getParameterMap();
			
			ActivityService activityService = new ActivityService();
			List<Activity> activitieList = activityService.getAll(map);
			
			req.setAttribute("activityBackList", activitieList);
			
			req.getRequestDispatcher("/back_end/activityManagement/activityManagement.jsp").forward(req, res);
		}
		
	}

}
