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
			
			//---------申訴人編號-----------------之後改成用session取
			Integer memNo  = null;
			memNo = member.getMemNo();
			
			//---------被申訴約會商品編號-----------------
			Integer dateItemNo  = null;
			dateItemNo =new Integer(req.getParameter("dateItemNo").trim());
			
			//---------新增的標題---------------------
			String appTitle = null;
			appTitle = req.getParameter("appTitle").trim();
			
			
			//---------新增的內文+加上把照片轉字串---------------------
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
			//--------申訴時間為現在-------------------
			Date appDate = new Date(System.currentTimeMillis());
			Integer appState = new Integer(0);//代表還沒有處理
			
			//---------呼叫service前來處理---------------
			DateItemAppService dipSvc = new DateItemAppService();
			dipSvc.addApp(memNo, dateItemNo, appTitle, appText, appDate, appState);
			
			//------------導回申訴頁面-----------------------
			res.sendRedirect(req.getContextPath() + "/front_end/dateitem/list_buyer_future.jsp");
			
			
			
			
		}
		
		if("updatepass".equals(action)){
			
			//---------約會商品申訴編號-----------------
			Integer appNo  = null;
			appNo =new Integer(req.getParameter("appno").trim());
			
			//---------呼叫service前來找被申訴會員---------------
			DateItemAppService dipSvc = new DateItemAppService();
			DateItemService diSvc = new DateItemService();
			Integer memNo = diSvc.findByPK(dipSvc.findByPrimaryKey(appNo).getDateItemNo()).getSellerNo();
			Date recDate = new Date(System.currentTimeMillis());
			//---------呼叫Appreprec前來紀錄-------------------
			AppRepRecService arrSvc = new AppRepRecService();
			arrSvc.addrep(memNo, recDate);
			//-----------改變申訴處理狀態--------------------------
			DateItemApp dateItemApp = dipSvc.findByPrimaryKey(appNo);
			dipSvc.updateApp(appNo, dateItemApp.getMemNo(), dateItemApp.getDateItemNo(), dateItemApp.getAppTitle(), dateItemApp.getAppText(), dateItemApp.getAppDate(), 1);
			
			//-----------寄信給被申訴者------------------------
			DateItemVO dateItem = diSvc.findByPK(dipSvc.findByPrimaryKey(appNo).getDateItemNo());
			LetterService ltrSvc = new LetterService();
			ltrSvc.addLtrOfApp(dateItem);
		}
		
		if("updatedeny".equals(action)){
			
			//---------約會商品申訴編號-----------------
			Integer appNo  = null;
			appNo =new Integer(req.getParameter("appno").trim());
			
			//-----------改變申訴處理狀態--------------------------
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
