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

import com.actImg.model.ActImg;
import com.actImg.model.ActImgJDBCDAO;
import com.activity.model.Activity;
import com.activity.model.ActivityJDBCDAO;
import com.ad.model.Ad;
import com.ad.model.AdJDBCDAO;
import com.album.model.Album;
import com.album.model.AlbumJDBCDAO;
import com.albumimg.model.AlbumImg;
import com.albumimg.model.AlbumImgJDBCDAO;
import com.dateitem.model.DateItemJDBCDAO;
import com.dateitem.model.DateItemVO;
import com.member.model.Member;
import com.member.model.MemberDAO;
import com.member.model.MemberJDBCDAO;
import com.pet.model.Pet;
import com.pet.model.PetJDBCDAO;
import com.product.model.Product;
import com.product.model.ProductJDBCDAO;
import com.restImg.model.RestImg;
import com.restImg.model.RestImgJDBCDAO;
import com.slide.model.Slide;
import com.slide.model.SlideJDBCDAO;

public class InsertDummyBlob {
	static int fixed_width=400;
	static int fixed_height=300;
	
	
	public static void main(String[] args) {


//		// �|���Ӥ��ק�
//		//Ū���|���Ӥ��������ɦW	
//		Set<String> memSet=new HashSet<String>();	
//		for (File file : new File("WebContent/DummyImg/member").listFiles()) { 
//			String fileName=file.getName();
//			String fileExtName=fileName.substring(fileName.lastIndexOf("."));
//			memSet.add(fileExtName);
//		}	
//		
//	    //���Ʈw���s���]�^��
//		for (int i=5001;i<=5030;i++) { 
//	    	MemberJDBCDAO dao=new MemberJDBCDAO();
//	    	//���oFile�ɦW (ex.1 2 3) �G�n�O�o����Ʀr(5000)
//	    	String fileN=String.valueOf((i-5000));
//	    	
//	    	//���ɦW���T�w�A�G��i�઺���ɦW�]�^��]���ɮצs�b����
//	    	File file = null;	    	
//	    	for(String fileExtName:memSet){
//	    		file=new File("WebContent/DummyImg/member/"+fileN+fileExtName);
//	    		if(file.exists()){
//	    			break;
//	    		}
//	    	}
//	    	
//	    	Member member=dao.findByPk(i);
//	        try {
//	        	byte[] b = getPictureByteArrayNoChangeSize(file);
//				member.setMemImg(b);
//				dao.update(member);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}    
//	    }
//	    System.out.println("=============�|���s�W����================");

	    
	    
	    
//		// �ק��d���Ӥ�
//		//Ū���d���Ӥ��������ɦW	
//		Set<String> petSet=new HashSet<String>();	
//		for (File file : new File("WebContent/DummyImg/pet").listFiles()) { 
//			String fileName=file.getName();
//			String fileExtName=fileName.substring(fileName.lastIndexOf("."));
//			petSet.add(fileExtName);
//		}	
//		
//
//	    for (int i=1001;i<=1045;i++) { 
//	    	PetJDBCDAO dao=new PetJDBCDAO();
//	    	String fileN=String.valueOf((i-1000));
//	    	
//	    	File file = null;	    	
//	    	for(String fileExtName:petSet){
//	    		file=new File("WebContent/DummyImg/pet/"+fileN+fileExtName);
//	    		if(file.exists()){
//	    			break;
//	    		}
//	    	}
//	    	
//	    	Pet pet=dao.findByPk(i);
//	        try {
//	        	byte[] b = getPictureByteArrayNoChangeSize(file);
//				pet.setPetImg(b);
//				dao.update(pet);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}    
//	    }
//	    System.out.println("=============�d���s�W����================");
//	    
//	    
//	    
//		// �ק��ï
//		//Ū����ï�Ӥ��������ɦW	
//		Set<String> albumSet=new HashSet<String>();	
//		for (File file : new File("WebContent/DummyImg/album").listFiles()) { 
//			String fileName=file.getName();
//			String fileExtName=fileName.substring(fileName.lastIndexOf("."));
//			albumSet.add(fileExtName);
//		}	
//		
//
//	    for (int i=1;i<=1;i++) { 
//	    	AlbumJDBCDAO dao=new AlbumJDBCDAO();
//	    	String fileN=String.valueOf((i-0));
//	    	
//	    	File file = null;	    	
//	    	for(String fileExtName:albumSet){
//	    		file=new File("WebContent/DummyImg/album/"+fileN+fileExtName);
//	    		if(file.exists()){
//	    			break;
//	    		}
//	    	}
//	    	
//	    	Album album=dao.findByPk(i);
//	        try {
//	        	byte[] b = getPictureByteArrayNoChangeSize(file);
//	        	album.setAlbumImgFile(b);
//				dao.update(album);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}    
//	    }
//	    System.out.println("=============��ï�s�W����================");
//	    
	    
		// �ק�ۤ�
		//Ū���d���Ӥ��������ɦW	
//		Set<String> aImgSet=new HashSet<String>();	
//		for (File file : new File("WebContent/DummyImg/albumimg").listFiles()) { 
//			String fileName=file.getName();
//			String fileExtName=fileName.substring(fileName.lastIndexOf("."));
//			aImgSet.add(fileExtName);
//		}	
//		
//
//	    for (int i=1;i<=5;i++) { 
//	    	AlbumImgJDBCDAO dao=new AlbumImgJDBCDAO();
//	    	String fileN=String.valueOf((i-0));
//	    	
//	    	File file = null;	    	
//	    	for(String fileExtName:aImgSet){
//	    		file=new File("WebContent/DummyImg/albumimg/"+fileN+fileExtName);
//	    		if(file.exists()){
//	    			break;
//	    		}
//	    	}
//	    	
//	    	AlbumImg aImg=dao.findByPk(i);
//	        try {
//	        	byte[] b = getPictureByteArrayNoChangeSize(file);
//	        	aImg.setImgFile(b);
//				dao.update(aImg);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}    
//	    }
//	    System.out.println("=============�ۤ��s�W����================");
//	    
//
//		 �ק�ӫ~�Ӥ�
//		Ū���ӫ~�Ӥ��������ɦW	
		Set<String> productSet=new HashSet<String>();	
		for (File file : new File("WebContent/DummyImg/product").listFiles()) { 
			String fileName=file.getName();
			String fileExtName=fileName.substring(fileName.lastIndexOf("."));
			productSet.add(fileExtName);
		}	
		

	    for (int i=2001;i<=2016;i++) { 
	    	ProductJDBCDAO dao=new ProductJDBCDAO();
	    	String fileN=String.valueOf((i-2000));
	    	
	    	File file = null;	    	
	    	for(String fileExtName:productSet){
	    		file=new File("WebContent/DummyImg/product/"+fileN+fileExtName);
	    		if(file.exists()){
	    			break;
	    		}
	    	}
	    	
	    	Product product=dao.findByPk(i);
	        try {
	        	byte[] b = getPictureByteArrayForMemberAndDateItem(file);
	        	product.setProdImg(b);
				dao.update(product);
			} catch (IOException e) {
				e.printStackTrace();
			}    
	    }
	    System.out.println("=============�ӫ~�s�W����================");
	     
    
	    
	}

	private static BufferedImage resizeImage(BufferedImage originalImage, int type,int fixed_width,int fixed_height ){
		BufferedImage resizedImage = new BufferedImage(fixed_width, fixed_height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, fixed_width, fixed_height, null);
		g.dispose();

		return resizedImage;
	    }
	
	
	public static byte[] getPictureByteArray(File file) throws IOException {
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
