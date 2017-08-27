package com.dateitemapp.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.appreprec.model.AppRepRecService;
import com.dateitem.model.DateItemService;
import com.dateitem.model.DateItemVO;
import com.dateitemapp.model.DateItemApp;
import com.dateitemapp.model.DateItemAppService;
import com.letter.model.LetterService;
import com.member.model.Member;


@MultipartConfig(fileSizeThreshold =500* 1024 * 1024, maxFileSize = 500 * 1024 * 1024, maxRequestSize = 5 * 500 * 1024 * 1024)
public class DateItemAppServlet extends HttpServlet{
	
	static int fixed_width=400;
	static int fixed_height=300;
	
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
			
			//---------�ӶD�H�s��-----------------����令��session��
			Integer memNo  = null;
			memNo = member.getMemNo();
			
			//---------�Q�ӶD���|�ӫ~�s��-----------------
			Integer dateItemNo  = null;
			dateItemNo =new Integer(req.getParameter("dateItemNo").trim());
			
			//---------�s�W�����D---------------------
			String appTitle = null;
			appTitle = req.getParameter("appTitle").trim();
			
			
			//---------�s�W������+�[�W��Ӥ���r��---------------------
			String appText = null;
			Base64.Encoder bs64 = Base64.getEncoder();
			byte[] appImg= null;			
			String appImgtoString = null;
			Part part = null;
			part = req.getPart("appImage");
			
			System.out.println(part);
			
			
			if(!part.equals("")){
				if(part.getContentType().substring(0,5).equals("image")){
					appImg = getByteArrayImg(part);
					appImgtoString = bs64.encodeToString(appImg);
				}
			}
			
			if(appImgtoString.equals("")){
				appText = req.getParameter("appText").trim();
			}else{
				appText = req.getParameter("appText").trim()+"<br><img src=\"data:image/jpg;base64,"+appImgtoString+"\">";
			}
				System.out.println(appText);
			//--------�ӶD�ɶ����{�b-------------------
			Date appDate = new Date(System.currentTimeMillis());
			Integer appState = new Integer(0);//�N���٨S���B�z
			
			//---------�I�sservice�e�ӳB�z---------------
			DateItemAppService dipSvc = new DateItemAppService();
			dipSvc.addApp(memNo, dateItemNo, appTitle, appText, appDate, appState);
			
			//------------�ɦ^�ӶD����-----------------------
			res.sendRedirect(req.getContextPath() + "/front_end/dateitem/list_buyer_future.jsp");
			
			
			
			
		}
		
		if("updatepass".equals(action)){
			
			//---------���|�ӫ~�ӶD�s��-----------------
			Integer appNo  = null;
			appNo =new Integer(req.getParameter("appno").trim());
			
			//---------�I�sservice�e�ӧ�Q�ӶD�|��---------------
			DateItemAppService dipSvc = new DateItemAppService();
			DateItemService diSvc = new DateItemService();
			Integer memNo = diSvc.findByPK(dipSvc.findByPrimaryKey(appNo).getDateItemNo()).getSellerNo();
			Date recDate = new Date(System.currentTimeMillis());
			//---------�I�sAppreprec�e�Ӭ���-------------------
			AppRepRecService arrSvc = new AppRepRecService();
			arrSvc.addrep(memNo, recDate);
			//-----------���ܥӶD�B�z���A--------------------------
			DateItemApp dateItemApp = dipSvc.findByPrimaryKey(appNo);
			dipSvc.updateApp(appNo, dateItemApp.getMemNo(), dateItemApp.getDateItemNo(), dateItemApp.getAppTitle(), dateItemApp.getAppText(), dateItemApp.getAppDate(), 1);
			
			//-----------�H�H���Q�ӶD��------------------------
			DateItemVO dateItem = diSvc.findByPK(dipSvc.findByPrimaryKey(appNo).getDateItemNo());
			LetterService ltrSvc = new LetterService();
			ltrSvc.addLtrOfApp(dateItem);
		}
		
		if("updatedeny".equals(action)){
			
			//---------���|�ӫ~�ӶD�s��-----------------
			Integer appNo  = null;
			appNo =new Integer(req.getParameter("appno").trim());
			
			//-----------���ܥӶD�B�z���A--------------------------
			DateItemAppService dipSvc = new DateItemAppService();
			DateItemApp dateItemApp = dipSvc.findByPrimaryKey(appNo);
			dipSvc.updateApp(appNo, dateItemApp.getMemNo(), dateItemApp.getDateItemNo(), dateItemApp.getAppTitle(), dateItemApp.getAppText(), dateItemApp.getAppDate(), 1);
			
		}
		
		
		
	}
	
//	public byte[] getByteArrayImg(Part part){
//			
//			ByteArrayOutputStream diaimg=null;
//			try{
//				java.io.InputStream in =part.getInputStream(); 
//				diaimg = new ByteArrayOutputStream();
//				byte[] buffer = new byte[8192];
//				int i;
//				while ((i = in.read(buffer)) != -1) {
//					diaimg.write(buffer, 0, i);
//				}
//				diaimg.close();
//				in.close();	
//			}catch(IOException e){
//				e.printStackTrace();
//			}
//			
//			return diaimg.toByteArray();
//		}
	
	private static BufferedImage resizeImage(BufferedImage originalImage, int type){
		BufferedImage resizedImage = null;
		Graphics2D g = null;
		
		if(originalImage.getWidth()>originalImage.getHeight()){
			resizedImage = new BufferedImage(fixed_width, fixed_height, type);
			g = resizedImage.createGraphics();
			g.drawImage(originalImage, 0, 0, fixed_width, fixed_height, null);
		}
		else{
			resizedImage = new BufferedImage(fixed_height, fixed_width, type);
			g = resizedImage.createGraphics();
			g.drawImage(originalImage, 0, 0, fixed_height, fixed_width, null);
		}
		g.dispose();

		return resizedImage;
	    }
	
	public byte[] getByteArrayImg(Part part){
		
		ByteArrayOutputStream diaImg = null;
		try{
			java.io.InputStream in =part.getInputStream();			
			BufferedImage originalImage = ImageIO.read(in);
			int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
			BufferedImage resizeImageJpg = resizeImage(originalImage, type);
			diaImg = new ByteArrayOutputStream();
			ImageIO.write( resizeImageJpg, "jpg", diaImg );
			diaImg.flush();
			diaImg.close();
			
		}catch(IOException ie){
			ie.getMessage();
		}
		
		return diaImg.toByteArray();
	}
	
	
	
	
}
