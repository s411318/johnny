package com.albumimg.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class AlbumImgTest {

	public static void main(String[] args) {
		AlbumImgJDBCDAO dao=new AlbumImgJDBCDAO();
		
		//�s�W
//		AlbumImg aImg=new AlbumImg();
//		aImg.setAlbumNo(1);
//		aImg.setImgTitle("�ۤ�a");
//		aImg.setImgDesc("���ƴy�J");
//		aImg.setImgCreatedTime(new Timestamp((new Date()).getTime()));
//		aImg.setImgModifiedTime(new Timestamp((new Date()).getTime()));
//		aImg.setImgFileName("Hello");
//		aImg.setImgExtName("png");
//		aImg.setImgFile(new byte[123465]);
//		dao.add(aImg);
//		System.out.println("=================");
		
		
		//�ק�
//		AlbumImg aImg2=new AlbumImg();
//		aImg2.setImgNo(2);
//		aImg2.setAlbumNo(1);
//		aImg2.setImgTitle("�ק�ۤ�");
//		aImg2.setImgDesc("�ק�ۤ�");
//		aImg2.setImgCreatedTime(new Timestamp((new Date()).getTime()));
//		aImg2.setImgModifiedTime(new Timestamp((new Date()).getTime()));
//		aImg2.setImgFileName("�ק�");
//		aImg2.setImgExtName("png");
//		aImg2.setImgFile(new byte[123465]);
//		dao.update(aImg2);
//		System.out.println("=================");
		
		//�R��
//		dao.delete(3);
//		System.out.println("=================");
		
		
		//�d��
//		AlbumImg aImg3=dao.findByPk(4);
//		System.out.println(aImg3.getImgNo());
//		System.out.println(aImg3.getImgNo());
//		System.out.println(aImg3.getImgTitle());
//		System.out.println(aImg3.getImgDesc());
//		System.out.println(aImg3.getImgCreatedTime());
//		System.out.println(aImg3.getImgModifiedTime());
//		System.out.println(aImg3.getImgFileName());
//		System.out.println(aImg3.getImgExtName());
//		System.out.println(aImg3.getImgFile());
		
		
		//�d����
//		List<AlbumImg> list=dao.getAll();
//		for(AlbumImg aImg4:list){
//			System.out.println(aImg4.getImgNo());
//			System.out.println(aImg4.getImgNo());
//			System.out.println(aImg4.getImgTitle());
//			System.out.println(aImg4.getImgDesc());
//			System.out.println(aImg4.getImgCreatedTime());
//			System.out.println(aImg4.getImgModifiedTime());
//			System.out.println(aImg4.getImgFileName());
//			System.out.println(aImg4.getImgExtName());
//			System.out.println(aImg4.getImgFile());
//			System.out.println("=================");
//		}

		
	}

}
