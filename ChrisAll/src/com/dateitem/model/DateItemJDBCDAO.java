package com.dateitem.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Clob;

public class DateItemJDBCDAO implements DateItemDAO_interface{

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "petym";
	private static final String PASSWORD = "123456";
	
	
	private static final String INSERT_STMT = "INSERT INTO DATEITEM(DATEITEMNO,SELLERNO,RESTLISTNO,"
			+ "DATEITEMTITLE,DATEITEMIMG,DATEITEMTEXT,DATEITEMTIME,DATEMEETINGTIME,DATEITEMLOCATE,"
			+ "DATEITEMPEOPLE,HASMATE,DATEITEMPRICE,DATEITEMSTATUS,DATEITEMSHOW,DATEITEMVIEWER,BUYERNO,"
			+ "ISQRCCHECKED,BUYERREP,SELLERREP,ISINSTANTDATE)"
			+ " VALUES(DATEITEMNO_SQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE DATEITEM SET DATEITEMNO = ?, SELLERNO = ?, RESTLISTNO = ?, "
			+ "DATEITEMTITLE = ?, DATEITEMIMG = ?, DATEITEMTEXT = ?, DATEITEMTIME = ?, DATEMEETINGTIME = ?, "
			+ "DATEITEMLOCATE = ? ,DATEITEMPEOPLE = ? , HASMATE =?, DATEITEMPRICE =? , DATEITEMSTATUS=? ,"
			+ "DATEITEMSHOW=?, DATEITEMVIEWER=?, BUYERNO=?, ISQRCCHECKED=?, BUYERREP=? , SELLERREP=? ,ISINSTANTDATE=? WHERE DATEITEMNO =�@?";
	private static final String DELETE_STMT = "DELETE FROM DATEITEM WHERE DATEITEMNO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM DATEITEM WHERE DATEITEMNO = ?";
	private static final String GET_ALL = "SELECT * FROM DATEITEM";
	
	
	
	@Override
	public void add(DateItemVO dateItemVO) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt=con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, dateItemVO.getSellerNo());
			pstmt.setInt(2, dateItemVO.getRestListNo());
			pstmt.setString(3, dateItemVO.getDateItemTitle());
			Blob blob=con.createBlob();
			blob.setBytes(1, dateItemVO.getDateItemImg());
			pstmt.setBlob(4, blob);
			Clob clob=con.createClob();
			clob.setString(1, dateItemVO.getDateItemText());
			pstmt.setClob(5, clob);
			pstmt.setDate(6, dateItemVO.getDateItemTime());
			pstmt.setDate(7, dateItemVO.getDateMeetingTime());
			pstmt.setString(8, dateItemVO.getDateItemLocate());
			pstmt.setInt(9, dateItemVO.getDateItemPeople());
			pstmt.setBoolean(10, dateItemVO.getHasMate());
			pstmt.setInt(11, dateItemVO.getDateItemPrice());
			pstmt.setInt(12, dateItemVO.getDateItemStatus());
			pstmt.setInt(13, dateItemVO.getDateItemShow());
			pstmt.setInt(14, dateItemVO.getDateItemViewer());
			pstmt.setInt(15, dateItemVO.getBuyerNo());
			pstmt.setBoolean(16, dateItemVO.getIsQRCChecked());
			pstmt.setInt(17, dateItemVO.getBuyerRep());
			pstmt.setInt(18, dateItemVO.getSellerRep());
			pstmt.setBoolean(19, dateItemVO.getIsInstantDate());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(DateItemVO dateItemVO) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt=con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, dateItemVO.getDateItemNo());
			pstmt.setInt(2, dateItemVO.getSellerNo());
			pstmt.setInt(3, dateItemVO.getRestListNo());
			pstmt.setString(4, dateItemVO.getDateItemTitle());
			Blob blob=con.createBlob();
			blob.setBytes(1, dateItemVO.getDateItemImg());
			pstmt.setBlob(5, blob);
			Clob clob=con.createClob();
			clob.setString(1, dateItemVO.getDateItemText());
			pstmt.setClob(6, clob);
			pstmt.setDate(7, dateItemVO.getDateItemTime());
			pstmt.setDate(8, dateItemVO.getDateMeetingTime());
			pstmt.setString(9, dateItemVO.getDateItemLocate());
			pstmt.setInt(10, dateItemVO.getDateItemPeople());
			pstmt.setBoolean(11, dateItemVO.getHasMate());
			pstmt.setInt(12, dateItemVO.getDateItemPrice());
			pstmt.setInt(13, dateItemVO.getDateItemStatus());
			pstmt.setInt(14, dateItemVO.getDateItemShow());
			pstmt.setInt(15, dateItemVO.getDateItemViewer());
			pstmt.setInt(16, dateItemVO.getBuyerNo());
			pstmt.setBoolean(17, dateItemVO.getIsQRCChecked());
			pstmt.setInt(18, dateItemVO.getBuyerRep());
			pstmt.setInt(19, dateItemVO.getSellerRep());
			pstmt.setBoolean(20, dateItemVO.getIsInstantDate());
			pstmt.setInt(21, dateItemVO.getDateItemNo());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void delete(int dateItemNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt=con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, dateItemNo);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public DateItemVO findByPk(int dateItemNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		DateItemVO dateItem=null;
		
		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt=con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, dateItemNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				dateItem=new DateItemVO();
				dateItem.setDateItemNo(rs.getInt("dateItemNo"));
				dateItem.setSellerNo(rs.getInt("sellerNo"));
				dateItem.setRestListNo(rs.getInt("restListNo"));
				dateItem.setDateItemTitle(rs.getString("dateItemTitle"));
				dateItem.setDateItemImg(rs.getBytes("dateItemImg"));
				dateItem.setDateItemText(rs.getString("dateItemText"));
				dateItem.setDateItemTime(rs.getDate("dateItemTime"));
				dateItem.setDateMeetingTime(rs.getDate("dateMeetingTime"));
				dateItem.setDateItemLocate(rs.getString("dateItemLocate"));
				dateItem.setDateItemPeople(rs.getInt("dateItemPeople"));
				dateItem.setHasMate(rs.getBoolean("hasMate"));
				dateItem.setDateItemPrice(rs.getInt("dateItemPrice"));
				dateItem.setDateItemShow(rs.getInt("dateItemShow"));;
				dateItem.setDateItemStatus(rs.getInt("dateItemStatus"));
				dateItem.setDateItemViewer(rs.getInt("dateItemViewer"));
				dateItem.setBuyerNo(rs.getInt("buyerNo"));
				dateItem.setIsQRCChecked(rs.getBoolean("isQRCChecked"));
				dateItem.setBuyerRep(rs.getInt("buyerRep"));
				dateItem.setSellerRep(rs.getInt("sellerRep"));		
				dateItem.setIsInstantDate(rs.getBoolean("isInstantDate"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return dateItem;
	}

	@Override
	public List<DateItemVO> getAll() {
		List<DateItemVO> dateItemList = new ArrayList<>();
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		
		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt=con.prepareStatement(GET_ALL);
			rs=pstmt.executeQuery();
			while(rs.next()){
				DateItemVO dateItemVO=new DateItemVO();
				dateItemVO.setDateItemNo(rs.getInt("dateItemNo"));
				dateItemVO.setSellerNo(rs.getInt("sellerNo"));
				dateItemVO.setRestListNo(rs.getInt("restListNo"));
				dateItemVO.setDateItemTitle(rs.getString("dateItemTitle"));
				dateItemVO.setDateItemImg(rs.getBytes("dateItemImg"));
				dateItemVO.setDateItemText(rs.getString("dateItemText"));
				dateItemVO.setDateItemTime(rs.getDate("dateItemTime"));
				dateItemVO.setDateMeetingTime(rs.getDate("dateMeetingTime"));
				dateItemVO.setDateItemLocate(rs.getString("dateItemLocate"));
				dateItemVO.setDateItemPeople(rs.getInt("dateItemPeople"));
				dateItemVO.setHasMate(rs.getBoolean("hasMate"));
				dateItemVO.setDateItemPrice(rs.getInt("dateItemPrice"));
				dateItemVO.setDateItemStatus(rs.getInt("dateItemStatus"));
				dateItemVO.setBuyerNo(rs.getInt("buyerNo"));
				dateItemVO.setIsQRCChecked(rs.getBoolean("isQRCChecked"));
				dateItemVO.setBuyerRep(rs.getInt("buyerRep"));
				dateItemVO.setSellerRep(rs.getInt("SellerRep"));	
				dateItemList.add(dateItemVO);		
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return dateItemList;
	}
	
	// Main��k
	public static void main(String [ ] args) throws IOException{
		byte[] testImg = new byte[123];		
		DateItemJDBCDAO dao=new DateItemJDBCDAO();
		DateItemVO dateItemVO = new DateItemVO();
		
		

		dateItemVO.setDateItemNo(4001);
		dateItemVO.setSellerNo(5001);
		dateItemVO.setRestListNo(7001);
		dateItemVO.setDateItemTitle("�����ȫ���|�j�n");
		dateItemVO.setDateItemImg(testImg);
		dateItemVO.setDateItemText("�Ӷi��@���ȫ᪺���|�a���մ���");
		dateItemVO.setDateItemTime(java.sql.Date.valueOf("2017-07-17"));
		dateItemVO.setDateMeetingTime(java.sql.Date.valueOf("2017-07-24"));
		dateItemVO.setDateItemLocate("�x�_��");
		dateItemVO.setDateItemPeople(0);
		dateItemVO.setHasMate(false);
		dateItemVO.setDateItemPrice(1500);
		dateItemVO.setDateItemStatus(1);
		dateItemVO.setDateItemShow(0);
		dateItemVO.setDateItemViewer(1600);
		dateItemVO.setBuyerNo(5002);
		dateItemVO.setIsQRCChecked(false);
		dateItemVO.setBuyerRep(0);
		dateItemVO.setSellerRep(0);
		dateItemVO.setIsInstantDate(false);
		
		//�s�W
		dao.add(dateItemVO);		
		System.out.println("�w�s�W��1��");
		dao.add(dateItemVO);
		System.out.println("�w�s�W��2��");
		dao.add(dateItemVO);
		System.out.println("�w�s�W��3��");
		
		//�ק�
		dateItemVO.setDateItemNo(4002);
		dateItemVO.setDateItemTitle("�w�ק�w�ק�L�F");	
		dao.update(dateItemVO);
		System.out.println("�w�ק�ĤG��");
		
		//�R��
		dao.delete(4001);
		System.out.println("�w�R��4001");
		
		//�d�� by PK
		dateItemVO = dao.findByPk(4003);
		System.out.println(dateItemVO.getDateItemLocate());
		System.out.println(dateItemVO.getDateItemText());
		System.out.println(dateItemVO.getDateItemNo());
		System.out.println(dateItemVO.getDateItemPrice());
		
		//getAll
		List<DateItemVO> dateItemList = dao.getAll();
		for(DateItemVO dateItem : dateItemList){
		System.out.println(dateItem.getDateItemNo());
		System.out.println(dateItem.getDateItemTitle());
		System.out.println(dateItem.getDateItemPrice());
		System.out.println(dateItem.getSellerNo());
		System.out.println(dateItem.getDateItemShow());
		System.out.println(dateItem.getDateItemText());
		System.out.println(dateItem.getRestListNo());
		System.out.println(dateItem.getDateMeetingTime());
		System.out.println(dateItem.getDateItemTime());
		System.out.println(dateItem.getIsInstantDate());
	}
		
		
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
	
}


