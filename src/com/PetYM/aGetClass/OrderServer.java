package com.PetYM.aGetClass;


import com.PetYM.*;
import com.PetYM.Member.MemberDAO;
import com.PetYM.Pet.Pet;
import com.PetYM.Pet.PetDAO;
import com.PetYM.Restaurant.Restaurant;
import com.PetYM.Restaurant.RestaurantDAO;
import com.PetYM.puDateItem.DateItemDAO;
import com.PetYM.puDateItem.DateItemVO;
import com.PetYM.Member.Member;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.lang.reflect.Type;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;


@SuppressWarnings("serial")
@WebServlet("/Order")
public class OrderServer extends HttpServlet {
	
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	OrderVO orderVO;
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();

	}

	public void doPost(HttpServletRequest rq, HttpServletResponse rp) throws ServletException, IOException {
		MemberDAO memberDAO = new MemberDAO();
		PetDAO petDAO = new PetDAO();
		RestaurantDAO restaurantDAO = new RestaurantDAO();
		DateItemDAO dateItemDAO = new DateItemDAO();
		
		Member buyer = new Member();
		Member seller = new Member();
		Pet pet = new Pet();
		Restaurant restaurant = new Restaurant();
		DateItemVO dateItemVO = new DateItemVO();
		
		rq.setCharacterEncoding("UTF-8");
        Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//		Gson gson = new Gson();
		BufferedReader br = rq.getReader();
		StringBuffer jsonIn = new StringBuffer();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		//System.out.println("----------jsonIn-78��--------"+jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		//��android��memberId��String
		String memberId = jsonObject.get("memberId").getAsString();
		String action = jsonObject.get("action").getAsString();
		//System.out.println("----------memberId-83��--------"+memberId);
		//��o���ID
		Integer memberInteger = Integer.valueOf(memberId);
		ArrayList<DateItemVO> buyerList = dateItemDAO.findItemBuyer(memberInteger);//1
		//System.out.println("----------buyerList-87��--------"+buyerList.size());
		ArrayList<DateItemVO> sellerList = dateItemDAO.findItemSeller(memberInteger);//5
		//System.out.println("----------sellerList-89��--------"+sellerList.size());

		ArrayList<Member> sellerOfmember = new ArrayList<Member>();
		ArrayList<Member> buyerOfmember = new ArrayList<Member>();
		ArrayList<Pet> sellerPetOfmember = new ArrayList<Pet>();
		ArrayList<Pet> buyerPetOfmember = new ArrayList<Pet>();
		ArrayList<Restaurant> sellerRestaurantOfmember = new ArrayList<Restaurant>();
		ArrayList<Restaurant> buyerRestaurantOfmember = new ArrayList<Restaurant>();
		String outStr = "";
		
		//��|���O�R�a�άO��a���ɭԡA����a��ơA��a���A��a�\�U
		if("all".equals(action)){
			ArrayList<DateItemVO> AllList = dateItemDAO.findItemAll(memberInteger);
			//System.out.println("-------------AllList.size()"+AllList.size());
			
			for(DateItemVO dateItem: AllList){
				//System.out.println("------dateItem.getBuyerNo()---"+dateItem.getBuyerNo());
				//System.out.println("------dateItem.getSellerNo()---"+dateItem.getSellerNo());
				//System.out.println("-------memberInteger------------"+memberInteger);
				//System.out.println("-----------------------------"+(Integer.valueOf(dateItem.getSellerNo()).equals(Integer.valueOf(memberInteger))));
				boolean memberIsBuyer = Integer.valueOf(dateItem.getBuyerNo()).equals(Integer.valueOf(memberInteger));
				boolean memberIsSeller = Integer.valueOf(dateItem.getSellerNo()).equals(Integer.valueOf(memberInteger));
				//��ܥL�O�R�a
				if(memberIsBuyer){
					Member buyerMember = memberDAO.findByPk(memberInteger);
					Member sellerMember = memberDAO.findByPk(dateItem.getSellerNo());
					Pet sellerpet = petDAO.findByPk(dateItem.getPetNo());
					Restaurant restaurantSeller = restaurantDAO.findByPK(dateItem.getRestListNo());
					buyerOfmember.add(buyerMember);
					sellerOfmember.add(sellerMember);
					buyerPetOfmember.add(sellerpet);
					sellerRestaurantOfmember.add(restaurantSeller);
			}else if(memberIsSeller){
			
				//��ܥL�O��a
				Member buyerMember = memberDAO.findByPk(dateItem.getBuyerNo());
				Member sellerMember = memberDAO.findByPk(memberInteger);
				Pet sellerpet = petDAO.findByPk(dateItem.getPetNo());
				Restaurant restaurantSeller = restaurantDAO.findByPK(dateItem.getRestListNo());
				buyerOfmember.add(buyerMember);
				sellerOfmember.add(sellerMember);
				buyerPetOfmember.add(sellerpet);
				sellerRestaurantOfmember.add(restaurantSeller);
				
			}else{
				//System.out.println("�S�F��");
			}
				OrderVOAll orderVO = new OrderVOAll(AllList,buyerOfmember,sellerOfmember,buyerPetOfmember,sellerRestaurantOfmember);
				outStr = gson.toJson(orderVO);
		}
		}
			//System.out.println("----------notAll.equals(action)--------"+"notAll".equals(action));
		
		//��|���նR�a���ɭԡA���L����a��T
		if("notAll".equals(action)){
		for (int i=0 ; i<buyerList.size();i++){
			int sellno= buyerList.get(i).getSellerNo();
			int sellPetNo = buyerList.get(i).getPetNo();
			int sellRestNo = buyerList.get(i).getRestListNo();
			Member memberseller =memberDAO.findByPk(sellno);
			sellerOfmember.add(memberseller);//2
			Pet petSeller = petDAO.findByPk(sellPetNo);
			sellerPetOfmember.add(petSeller);//3
			Restaurant restaurantSeller = restaurantDAO.findByPK(sellRestNo);
			sellerRestaurantOfmember.add(restaurantSeller);//4
		}
		//�|���O��a�A���R�a��T�C�]�t�R�a��� �\�U�A�d���O�|���������d���A���i�঳�ܦh�d�� �ҥH�٬O�n���o��a�d���s��
		for (int i=0 ; i<sellerList.size();i++){
			int buyerNo= sellerList.get(i).getBuyerNo();
			int buyerPetNo = sellerList.get(i).getPetNo();
			int buyRestNo = sellerList.get(i).getRestListNo();
			Member memberbuyer =memberDAO.findByPk(buyerNo);
			buyerOfmember.add(memberbuyer);//6
			Pet petSeller = petDAO.findByPk(buyerPetNo);
			buyerPetOfmember.add(petSeller);//7
			Restaurant restaurantBuyer = restaurantDAO.findByPK(buyRestNo);
			sellerRestaurantOfmember.add(restaurantBuyer);//8
			}
		OrderVO orderVO = new OrderVO(buyerList, sellerOfmember, sellerPetOfmember, sellerRestaurantOfmember, sellerList, buyerOfmember, buyerPetOfmember, sellerRestaurantOfmember);
		outStr = gson.toJson(orderVO);
		}
	
		//System.out.println("outStr::::"+outStr);
	    doGet(rq, rp);
		rp.setContentType(CONTENT_TYPE);
		PrintWriter out = rp.getWriter();
		out.println(outStr);
		System.out.println(outStr);
		
	}

	@Override
	public void doGet(HttpServletRequest rq, HttpServletResponse rp) throws ServletException, IOException {
		Gson gson = new Gson();

		String outStr = gson.toJson(orderVO);
		rp.setContentType(CONTENT_TYPE);
		PrintWriter out = rp.getWriter();

		
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
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

	public static String getPictureByteArray1(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return Base64.getMimeEncoder().encodeToString((baos.toByteArray()));

	}
}
