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
import com.member.model.MemberJDBCDAO;
import com.pet.model.Pet;
import com.pet.model.PetJDBCDAO;
import com.product.model.Product;
import com.product.model.ProductJDBCDAO;
import com.restImg.model.RestImg;
import com.restImg.model.RestImgJDBCDAO;
import com.slide.model.Slide;
import com.slide.model.SlideJDBCDAO;

public class InsertDummyBlob2 {
	static int fixed_width=400;
	static int fixed_height=300;
	
	public static void main(String[] args) {

	// �\�U�|���Ӥ��ק�
	int r = 7001;
    for (File file : new File("WebContent/DummyImg/restImg").listFiles()) { 
    	RestImgJDBCDAO restImgJDBCDAO=new RestImgJDBCDAO();
        RestImg restImg=restImgJDBCDAO.findByPK(r++);
        try {
			byte[] b = getPictureByteArray(file);
			restImg.setRestImg(b);
			restImgJDBCDAO.update(restImg);
		} catch (IOException e) {
			e.printStackTrace();
		}    
    }
    System.out.println("=============�\�U�|���Ӥ��s�W����================");

    
    // �o�_���ʷӤ��ק�
    int j = 8001;
    for (File file : new File("WebContent/DummyImg/activityInitImg").listFiles()) { 
    	ActivityJDBCDAO activityDAO=new ActivityJDBCDAO();
    	Activity activity=activityDAO.findByPK(j++);
        try {
			byte[] b = getPictureByteArray(file);
			activity.setActInitImg(b);
			activityDAO.update(activity);
		} catch (IOException e) {
			e.printStackTrace();
		}    
    }
    System.out.println("=============�o�_���ʷӤ��s�W����================");


 // ���ʷӤ��ק�
    int k = 8001;
    for (File file : new File("WebContent/DummyImg/actImg").listFiles()) { 
    	ActImgJDBCDAO actImgJDBCDAO=new ActImgJDBCDAO();
    	ActImg actImg=actImgJDBCDAO.findByPK(k++);
        try {
			byte[] b = getPictureByteArray(file);
			actImg.setActImg(b);
			actImgJDBCDAO.update(actImg);
		} catch (IOException e) {
			e.printStackTrace();
		}    
    }
    System.out.println("=============���ʷӤ��s�W����================");
    
    
//	// �s�i�Ϥ��ק�
//	int aa = 1;
//	for (File file : new File("WebContent/DummyImg/ad").listFiles()) {
//		AdJDBCDAO dao = new AdJDBCDAO();
//		Ad ad = dao.findByPrimaryKey(aa++);
//		try {
//			byte[] b = getPictureByteArray(file);
//			ad.setAdImg(b);
//			dao.update(ad);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	System.out.println("=============�s�i�Ϥ��ק粒�F================");

	// �ۿO���ק�
	int s = 1;
	for (File file : new File("WebContent/DummyImg/slide").listFiles()) {
		SlideJDBCDAO dao = new SlideJDBCDAO();
		Slide slide = dao.findByPrimaryKey(s++);
		try {
			byte[] b = getPictureByteArray(file);
			slide.setSlideImg(b);
			dao.update(slide);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	System.out.println("=============�ۿO���ק粒�F================");
        
    
	// �ק���|�ӫ~�Ӥ�
	//Ū�����|�ӫ~�Ӥ��������ɦW	
	Set<String> dateSet=new HashSet<String>();	
	for (File file : new File("WebContent/DummyImg/dateitem").listFiles()) { 
		String fileName=file.getName();
		String fileExtName=fileName.substring(fileName.lastIndexOf("."));
		dateSet.add(fileExtName);
	}	
	

    for (int i=4001;i<=4015;i++) { 
    	DateItemJDBCDAO dao=new DateItemJDBCDAO();
    	String fileN=String.valueOf((i-4000));
    	
    	File file = null;	    	
    	for(String fileExtName:dateSet){
    		file=new File("WebContent/DummyImg/dateitem/"+fileN+fileExtName);
    		if(file.exists()){
    			break;
    		}
    	}
    	
    	DateItemVO date=dao.findByPk(i);
        try {
        	byte[] b = getPictureByteArrayNoChangeSize(file);
        	date.setDateItemImg(b);
			dao.update(date);
		} catch (IOException e) {
			e.printStackTrace();
		}    
    }
    System.out.println("=============���|�ӫ~�s�W����================");
    
    
    
    
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
	
}
