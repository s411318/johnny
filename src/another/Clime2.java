package another;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Clime2 implements Runnable{
	
	
	private static final String INSERT_REST = "INSERT INTO REST (RESTNO,RESTNAME,RESTADD,RESTLOCATE,RESTPHONE,"
				+ "RESTINTRO,RESTKIND,RESTREVIEWSTATUS,RESTLONGTITUDE,RESTLATITUDE)" + "VALUES(REST_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?)";
	
	
	
	public static void main(String[] args){
		
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String oraUrl = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "petym";
		String passwd = "123456";
		
		

		BufferedReader reader = null;
		Connection conn= null;
		PreparedStatement pstmt= null;
		
		List<String> list =  new ArrayList<String>();;
		List<String> list1=  new ArrayList<String>();;
		List<String> list2=  new ArrayList<String>();;
		
		try {
//			URL url = new URL("http://tinyurl.com/3bp8zf7");
//					
//			Document xmlDoc = Jsoup.parse(url, 3000);

			Class.forName(driver);
			conn = DriverManager.getConnection(oraUrl, userid, passwd);
			

//			FileWriter writer = new FileWriter("D://restname.csv");
//
//			Elements tr = xmlDoc.select("tr");
//			for (int i = 2; i < 85; i++) {
//				String rest = (String) tr.get(i).text();
//				writer.write(rest);
//			}
//			writer.close();
//System.out.println("�g�J����");
			reader = new BufferedReader(new FileReader("D://restname.csv"));
			String name = reader.readLine().replaceAll("[?]", "");
			String name0 = name.replace("(�۫~���s����)", "�۫~���s����-");
			String name1 = name0.replace("(�Ȧ̶����d���ͬ��])", "�Ȧ̶����d���ͬ��]-");
			String name2 = name1.replace("0931-331220", "(03)955-6783");
			String name3 = name2.replace("(", ",(");
			String name4 = name3.replace("��26", "��26��");
			String name5 = name4.replace("�G�q806", "�G�q806��");
			String name6 = name5.replace("��", "��,");
			String name7 = name6.replace("1F", "");
			String name8 = name7.replace("2F", "");
			String name9 = name8.replace("��1", "");
			String name10 = name9.replace("2��", "");
			String name11 = name10.replace("�\ �U �W �q �� �a �}", "");
			String name12 = name11.replace("�x", ",�x");
			String name13 = name12.replace("�]", ",�]");
			String name14 = name13.replace("�s��", ",�s��");
			String name15 = name14.replace("���", ",���");
			String name16 = name15.replace("�]�߿��n�ܶm���L��2�F67��", "�]�߿��n�ܶm���L��2�F66��");
			String name17 = name16.replace("�����\�U", "�������d���\�U");
			String name18 = name17.replace("�x�_�������s���U��6��", "234�s�_���éM�ϥå���306��");
			String name19 = name18.replace("(02)2861-9138", "02-2231-8882");
			
			

			FileWriter writer1 = new FileWriter("D://Trestname.csv");
			writer1.write(name19);
			writer1.close();

			String[] name20 = name19.split(",");
			
			System.out.println("CSV����");			
			
			for (int j = 0; j < name20.length; j++) {
				if (j % 3 == 0) {
					list.add(name20[j]);
				} else if (j % 3 == 1) {
					list1.add(name20[j]);
				} else {
					list2.add(name20[j]);
				}
			}
Iterator<String> restNameList = list.iterator();//�\�U�W��  
			
			Iterator<String> restAddList = list2.iterator();
			
			Iterator<String> restAddListGMap = list2.iterator();
			Iterator<String> restAddListGMap1 = list2.iterator();
			System.out.println("list2:"+list2.size());
			
			while(restAddListGMap1.hasNext()){
				System.out.println("�e�X�a�}:::"+restAddListGMap1.next());
			}
			
			System.out.println("Iterator����");
			
//			List<Double> lat = new ArrayList<>();
//			List<Double> lng = new ArrayList<>();
			
			List<Double> realLat = new ArrayList<>();
			List<Double> realLng = new ArrayList<>();
			List<String> realAdd = new ArrayList<>();

			
			
			while(restAddListGMap.hasNext()){
				
				String sKeyWord = restAddListGMap.next(); //�o�O�a�}
				URL urlFromGMap  = new URL(String.format("http://maps.googleapis.com/maps/api/geocode/json?address=%s&sensor=false&language=zh-TW", 
				URLEncoder.encode(sKeyWord, "UTF-8")));//p=%s is KeyWord in	            
				URLConnection connFromGMap = urlFromGMap.openConnection() ;
				String line;
				StringBuffer builder = new StringBuffer();
				BufferedReader readerFromGMap = new BufferedReader(new InputStreamReader(connFromGMap.getInputStream(),"utf-8"));
				
				while ((line = readerFromGMap.readLine()) != null) {
					
						builder.append(line);
						
					}
			
					JSONObject json = new JSONObject(builder.toString()); //�ഫjson�榡
				    JSONArray ja = json.getJSONArray("results");//���ojson��Array����
				   
				    
				        for (int i = 0; i < 1; i++) {
			                  
//			            lat.add(ja.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lat"));
//			            lng.add(ja.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lng"));
			            
			            realAdd.add(ja.getJSONObject(0).getString("formatted_address"));
			            System.out.println("�^�Ǧa�}:::"+ja.getJSONObject(0).getString("formatted_address"));
			            realLat.add((ja.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lat")));
			            realLng.add((ja.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lng")));
				    } 
				
			}
//			Iterator<Double> latList = lat.iterator();
//			Iterator<Double> lngList = lng.iterator();
			
			Iterator<String> realPhone =  list1.iterator();
			
			Iterator<Double> realLatList = realLat.iterator();
			Iterator<Double> realLngList = realLng.iterator();
			Iterator<String> realAddList = realAdd.iterator();
	//		Iterator<String> realAddList0 = realAdd.iterator();
			
			System.out.println("realAdd:"+realAdd.size());
			
			System.out.println("GOOGLE����");
//			for (int k = 0; k < realAdd.size(); k++) {
//			
//				System.out.print(restNameList.next()+" : ");
//				System.out.print(realAddList.next()+" : ");//�o�O�a�}
//				System.out.print(realLatList.next()+" , ");
//				System.out.println(realLngList.next());
//			}
			
			
//			for (int k = 0; k < 77; k++) {
//				pstmt = conn.prepareStatement(INSERT_REST);
//				int kindOfPet = (int) (Math.random() * 3);
//				
//				pstmt.setString(1,restNameList.next());
//				pstmt.setString(2,realAddList.next());
//				pstmt.setString(3, restAddList.next().substring(0,2) +"��");
//				pstmt.setString(4,realPhone.next());
//				pstmt.setString(5, "petRestaurantIntro"+k);
//				pstmt.setInt(6, kindOfPet);
//				pstmt.setInt(7, 0);
//				pstmt.setDouble(8, realLngList.next());
//				pstmt.setDouble(9, realLatList.next());
//				pstmt.executeUpdate();
//			}
			
			
			
			
			System.out.println("����");	
			
			
			   

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		

	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
