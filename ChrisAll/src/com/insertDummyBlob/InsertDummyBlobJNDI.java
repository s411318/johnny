package com.insertDummyBlob;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.Member;
import com.member.model.MemberDAO;


@WebServlet("/InsertDummyBlobJNDI")
public class InsertDummyBlobJNDI extends HttpServlet {


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// 會員照片修改
		//讀取會員照片延伸附檔名	
		Set<String> memSet=new HashSet<String>();	
		System.out.println(new File("WebContent/DummyImg/member").exists());
		for (File file : new File("WebContent/DummyImg/member").listFiles()) { 
			String fileName=file.getName();
			String fileExtName=fileName.substring(fileName.lastIndexOf("."));
			System.out.println(fileExtName);
			memSet.add(fileExtName);
		}	
		
	    for (int i=5001;i<=5030;i++) { 
	    	MemberDAO dao=new MemberDAO();
	    	String fileN=String.valueOf((i-5000));
	    	
	    	File file = null;	    	
	    	for(String fileExtName:memSet){
	    		file=new File("WebContent/DummyImg/member/"+fileN+"."+fileExtName);
	    		if(file.exists()){
	    			break;
	    		}
	    	}
	    	
	    	Member member=dao.findByPk(i);
	        try {
	        	byte[] b = getPictureByteArrayNoChangeSize(file);
				member.setMemImg(b);
				dao.update(member);
			} catch (IOException e) {
	 
			}    
	    }
	    System.out.println("=============會員新增完畢================");
		
	}

	
	
	
	
	private static BufferedImage resizeImage(BufferedImage originalImage, int type,int fixed_width,int fixed_height ){
		BufferedImage resizedImage = new BufferedImage(fixed_width, fixed_height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, fixed_width, fixed_height, null);
		g.dispose();

		return resizedImage;
	    }
	
	
	public static byte[] getPictureByteArray(File file) throws IOException {
		int fixed_width=400;
		int fixed_height=300;
		BufferedImage originalImage = ImageIO.read(file);
		int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
		BufferedImage resizeImageJpg = resizeImage(originalImage, type,fixed_width,fixed_height);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write( resizeImageJpg, "jpg", baos );
		baos.flush();
		baos.close();

		return baos.toByteArray();
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
	
	
	public static byte[] getPictureByteArrayForMemberAndDateItem(File file) throws IOException {

		BufferedImage originalImage = ImageIO.read(file);
		int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
		BufferedImage resizeImageJpg = resizeImage(originalImage, type,300,300);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write( resizeImageJpg, "jpg", baos );
		baos.flush();
		baos.close();

		return baos.toByteArray();
	}
	
	
	
}
