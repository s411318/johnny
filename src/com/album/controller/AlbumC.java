package com.album.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.album.model.Album;
import com.album.model.AlbumService;
import com.albumimg.model.AlbumImg;
import com.albumimg.model.AlbumImgService;
import com.member.model.Member;

@WebServlet("/front_end/album/Album.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024
		* 1024)
public class AlbumC extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("member");
		AlbumService albumSvc = new AlbumService();
		AlbumImgService aImgSvc=new AlbumImgService();
		String action = req.getParameter("action");

		

		//�s�W��ï
		if ("createAlbum".equals(action)) {
			List<AlbumImg> aImgs = new LinkedList<AlbumImg>();
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());

			String albumTitle = req.getParameter("albumTitle");

			Collection<Part> parts = req.getParts();
			for (Part part : parts) {
				if (getFileNameFromPart(part) != null && part.getContentType() != null) {
					AlbumImg aImg = new AlbumImg();
					aImg.setImgTitle("�����Ӥ��s�W�I�y�z�a");
					aImg.setImgDesc("�����Ӥ��s�W�I�y�z�a");
					aImg.setImgCreatedTime(currentTime);
					aImg.setImgModifiedTime(currentTime);
					aImg.setImgFileName(getFileNameFromPart(part));
					aImg.setImgExtName(part.getContentType());
					aImg.setImgFile(getPictureByteArray(part.getInputStream()));
					aImgs.add(aImg);
				}
			}

			albumSvc.addAlbumWithImg(member.getMemNo(), albumTitle, currentTime, currentTime, 0,
					aImgs.get(0).getImgFile(), aImgs);

			res.sendRedirect(req.getContextPath() + "/front_end/album/albumShow.jsp");

		}
		
		
		
		
		//�ק�ʭ�
		if ("changeCover".equals(action)) {
			
			/****************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
			Integer albumNo=null;
			try{
				albumNo=Integer.parseInt(req.getParameter("albumNo"));
			}
			catch(Exception e){
				////�N�g////
			}
			
			Integer imgNo=null;
			try{
				imgNo=Integer.parseInt(req.getParameter("imgNo"));
			}
			catch(Exception e){
				//////�N�g///////
			}
			
			
			
			/**************************** 2.�ק粒��,�ǳ����(Send the Success view)*************/
	

			AlbumImg aImg=aImgSvc.getOneAlbumImg(imgNo);
			Album album=albumSvc.getOneAlbum(albumNo);
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			albumSvc.updateAlbum(albumNo, album.getMemNo(), album.getAlbumTitle(), album.getAlbumCreatedTime(), currentTime, 0, aImg.getImgFile());
	
			
			
			/**************************** 3.�ק粒��,�ǳ����(Send the Success view)*************/
			RequestDispatcher successView = req.getRequestDispatcher("/front_end/album/albumShow.jsp?albumNo="+albumNo);

			successView.forward(req, res);
			
		}
		
		
		//�R���ʭ�
		if ("deleteImg".equals(action)) {
			
			/****************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
			Integer albumNo=null;
			try{
				albumNo=Integer.parseInt(req.getParameter("albumNo"));
			}
			catch(Exception e){
				////�N�g////
			}
			
			Integer imgNo=null;
			try{
				imgNo=Integer.parseInt(req.getParameter("imgNo"));
			}
			catch(Exception e){
				//////�N�g///////
			}
			
			System.out.print(imgNo);
			
			/**************************** 2.�ק粒��,�ǳ����(Send the Success view)*************/
	
			aImgSvc.deleteAlbumImg(imgNo);

	
			
			
			/**************************** 3.�ק粒��,�ǳ����(Send the Success view)*************/
			RequestDispatcher successView = req.getRequestDispatcher("/front_end/album/aImgShow.jsp?albumNo="+albumNo);

			successView.forward(req, res);
			
		}
		
		//�s��ۤ�
		if ("updateImg".equals(action)) {
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
	
			/****************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
			Integer albumNo=null;
			try{
				albumNo=Integer.parseInt(req.getParameter("albumNo"));
			}
			catch(Exception e){
				errorMsgs.put("imgNo", "��ï�N�����~");
			}
			
			Integer imgNo=null;
			try{
				imgNo=Integer.parseInt(req.getParameter("imgNo"));
			}
			catch(Exception e){
				errorMsgs.put("imgNo", "�Ӥ��N�����~");
			}
			
			//�n���]�i�H���L���� �S���Y
			String imgTitle=req.getParameter("imgTitle");
			if (imgTitle == null || imgTitle.trim().isEmpty()) {
				errorMsgs.put("imgTitle", imgTitle);
			}
			
			//�n���]�i�H���L���� �S���Y
			String imgDesc=req.getParameter("imgDesc");
			if (imgDesc == null || imgDesc.trim().isEmpty()) {
				errorMsgs.put("imgDesc", imgDesc);
			}
			

			if(!errorMsgs.isEmpty()){
				RequestDispatcher failView = req.getRequestDispatcher("/front_end/album/aImgShow.jsp?albumNo="+albumNo);
				failView.forward(req, res);
				return;
			}

			
			/**************************** 2.�ק粒��,�ǳ����(Send the Success view)*************/
	

			AlbumImg aImg=aImgSvc.getOneAlbumImg(imgNo);
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			aImgSvc.updateAlbumImg(imgNo, albumNo, imgTitle, imgDesc, aImg.getImgCreatedTime(), currentTime, aImg.getImgFileName(), aImg.getImgExtName(), aImg.getImgFile());



			
			/**************************** 3.�ק粒��,�ǳ����(Send the Success view)*************/
			RequestDispatcher successView = req.getRequestDispatcher("/front_end/album/aImgShow.jsp?albumNo="+albumNo);

			successView.forward(req, res);
			
		}
		
		
		
		
		
		//�s���ï
		if ("updateAlbum".equals(action)) {
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			/****************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
			Integer albumNo=null;
			try{
				albumNo=Integer.parseInt(req.getParameter("albumNo"));
			}
			catch(Exception e){
				errorMsgs.put("imgNo", "��ï�N�����~");
			}
			
			
			//�n���]�i�H���L���� �S���Y
			String albumTitle=req.getParameter("albumTitle");
			if (albumTitle == null || albumTitle.trim().isEmpty()) {
				errorMsgs.put("imgTitle", albumTitle);
			}
			


			if(!errorMsgs.isEmpty()){
				RequestDispatcher successView = req.getRequestDispatcher("/front_end/album/albumShow.jsp");
				successView.forward(req, res);
				return;
			}

			
			/**************************** 2.�ק粒��,�ǳ����(Send the Success view)*************/
	

			Album album=albumSvc.getOneAlbum(albumNo);
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			albumSvc.updateAlbum(albumNo, album.getMemNo(), albumTitle, album.getAlbumCreatedTime(),currentTime,0, album.getAlbumImgFile());
			


			
			/**************************** 3.�ק粒��,�ǳ����(Send the Success view)*************/
			RequestDispatcher successView = req.getRequestDispatcher("/front_end/album/albumShow.jsp");

			successView.forward(req, res);
			
		}
		
		
		//�R����ï
		if ("deleteAlbum".equals(action)) {
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			/****************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
			Integer albumNo=null;
			try{
				albumNo=Integer.parseInt(req.getParameter("albumNo"));
			}
			catch(Exception e){
				errorMsgs.put("imgNo", "��ï�N�����~");
			}
			
			


			//���٨S�����~�B�z map�٨S�e�^�h

			
			/**************************** 2.�ק粒��,�ǳ����(Send the Success view)*************/
	

			Album album=albumSvc.getOneAlbum(albumNo);
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			albumSvc.updateAlbum(albumNo, album.getMemNo(), album.getAlbumTitle(), album.getAlbumCreatedTime(),currentTime,1, album.getAlbumImgFile());
			


			
			/**************************** 3.�ק粒��,�ǳ����(Send the Success view)*************/
			RequestDispatcher successView = req.getRequestDispatcher("/front_end/album/albumShow.jsp");

			successView.forward(req, res);
			
		}
		
		
		
		
		

	}

	public static byte[] getPictureByteArray(InputStream fis) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}

	
	
	
	private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
		BufferedImage resizedImage = new BufferedImage(400, 300, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, 400, 300, null);
		g.dispose();

		return resizedImage;
	}

	
	
	public static byte[] getPictureByteArrayNoChangeSize(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}
	
	
	
	
	
	
	
	
	
	
	// ���X�W�Ǫ��ɮצW�� (�]��API������method,�ҥH�����ۦ漶�g)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

}
