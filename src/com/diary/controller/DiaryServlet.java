package com.diary.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.diary.model.Diary;
import com.diary.model.DiaryService;
import com.member.model.Member;

@MultipartConfig(fileSizeThreshold =500* 1024 * 1024, maxFileSize = 500 * 1024 * 1024, maxRequestSize = 5 * 500 * 1024 * 1024)
public class DiaryServlet extends HttpServlet{
	
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
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			

			//----------------�P�_�W�٬O�_���ŭ�-------------------
			String diaName = null;
			try{
				diaName = req.getParameter("dianame").trim();
				if(diaName.isEmpty()){
					errorMsgs.add("�п�J�o��W��!");
				}
			}catch(NullPointerException e){
				e.printStackTrace(System.err);	
			}
			//----------------�P�_����O�_���ŭ�-------------------
			String diaText = null;
			try{
				diaText = req.getParameter("diatext").trim();
				if(diaText.equals("")){			//�j���̭����Onull���r�ӬO""
					errorMsgs.add("�п�J����!");
				}
			}catch(Exception ne){
				ne.printStackTrace(System.err);
			}
			//---------------�P�_�Ϥ����榡----------------------
			byte[] diaImg= null;
			String diaImgExtName = null;
			
			Part part = req.getPart("diaimg");
			diaImgExtName =  part.getContentType().substring(0,5);
			
			if(!part.equals("")){
				if(part.getContentType().substring(0,5).equals("image")||part.getContentType().substring(0,5).equals("video")){
					diaImg = getByteArrayImg(part);
				}
			}
//				GregorianCalendar speday = new GregorianCalendar(2011,12,3,23,49,22);
//				Timestamp diaCreTime =new Timestamp(speday.getTimeInMillis());
				
				Timestamp diaCreTime =new Timestamp(System.currentTimeMillis());
				
				Timestamp diaModTime =null;
				Integer diaState = 0; //indicate appearance
				
//				Diary diary = new Diary();
//				diary.setMemNo(member.getMemNo());
//				diary.setDiaName(diaName);
//				diary.setDiaCreTime(diaCreTime);
//				diary.setDiaModTime(diaModTime);
//				diary.setDiaText(diaText);
//				diary.setDiaImg(diaImg);
//				diary.setDiaState(diaState);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
	
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/diary/mydiary.jsp");
					failureView.forward(req, res);
					
					return;
				}
				
				DiaryService dsv =new DiaryService();
				dsv.addDia(member.getMemNo(), diaName, diaText, diaImg, diaCreTime, diaModTime, diaState, diaImgExtName);
				
				res.sendRedirect(req.getContextPath() + "/front_end/diary/mydiary.jsp");
				
		}
		
		if ("update".equals(action)) { // �Ӧ�jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				Integer diaNo = Integer.valueOf(req.getParameter("diano"));
				//----------------�P�_�W�٬O�_���ŭ�-------------------
				String diaName = null;
				try{
					diaName = req.getParameter("dianame").trim();
					if(diaName.isEmpty()){
						errorMsgs.add("�п�J�o��W��!");
					}
				}catch(NullPointerException e){
					e.printStackTrace(System.err);	
				}
				//----------------�P�_����O�_���ŭ�-------------------
				String diaText = null;
				
				try{
					diaText = req.getParameter("diatext").trim();
					if(diaText.equals("")){			//�j���̭����Onull���r�ӬO""
						errorMsgs.add("�п�J����!");
					}
					
				}catch(Exception ne){
					ne.printStackTrace(System.err);
				}
				//---------------�P�_�Ϥ�----------------------
				byte[] diaImg= null;	
				String diaImgExtName = null;
				try{
					Part part = req.getPart("diaimg");
					diaImgExtName =  part.getContentType().substring(0,5);
						if(part.getContentType().substring(0,5).equals("image")||part.getContentType().substring(0,5).equals("video") ){
							diaImg = getByteArrayImg(part);
							
						}else if(part.getSize()!=0){
							//�榡���~
							errorMsgs.add("���D�Ϥ��榡!");
						}else {
							//�P�_�p�G�S�W�Ƿs���N�u���ª�
							DiaryService diaSvc = new DiaryService();
							Diary diaryOld = diaSvc.getOneDia(diaNo);
							diaImg = diaryOld.getDiaImg();
							
						}
			
				}catch(Exception e){
					e.printStackTrace(System.err);					
				}
					
					Timestamp diaModTime = new Timestamp(System.currentTimeMillis());;
					Integer diaState = 0; //indicate appearance

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/diary/updateDiary.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				DiaryService diaSvc = new DiaryService();
				Diary diary = diaSvc.updateDia(member.getMemNo(), diaName, diaText, diaImg, diaModTime, diaState, diaNo, diaImgExtName);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("diary", diary); // ��Ʈwupdate���\��,���T����diary����,�s�Jreq
				String url = "/front_end/diary/mydiary.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���: "+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/diary/updateDiary.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�mydiary.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer diano = Integer.valueOf(req.getParameter("diano"));
				
				/***************************2.�}�l�d�߸��****************************************/
				DiaryService diaSvc = new DiaryService();
				Diary diary = diaSvc.getOneDia(diano); 
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("diary", diary);         // ��Ʈw���X��diary����,�s�Jreq
				String url = "/front_end/diary/updateDiary.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/diary/mydiary.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer diaNo = new Integer(req.getParameter("diano"));
				
				/***************************2.�}�l�R�����***************************************/
				DiaryService diaSvc = new DiaryService();
				diaSvc.deleteDia(diaNo);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front_end/diary/mydiary.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/diary/mydiary.jsp");
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
	

}
