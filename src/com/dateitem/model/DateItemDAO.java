package com.dateitem.model;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_DateItem;

import java.sql.Clob;

public class DateItemDAO implements DateItemDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petym");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO DATEITEM(DATEITEMNO,SELLERNO,RESTLISTNO,"
			+ "DATEITEMTITLE,DATEITEMIMG,DATEITEMTEXT,DATEITEMTIME,DATEMEETINGTIME,DATEITEMLOCATE,"
			+ "DATEITEMPEOPLE,HASMATE,DATEITEMPRICE,DATEITEMSTATUS,DATEITEMSHOW,DATEITEMVIEWER,BUYERNO,"
			+ "ISQRCCHECKED,BUYERREP,SELLERREP,ISINSTANTDATE,PETNO)"
			+ " VALUES(DATEITEMNO_SQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE DATEITEM SET DATEITEMNO = ?, SELLERNO = ?, RESTLISTNO = ?, "
			+ "DATEITEMTITLE = ?, DATEITEMIMG = ?, DATEITEMTEXT = ?, DATEITEMTIME = ?, DATEMEETINGTIME = ?, "
			+ "DATEITEMLOCATE = ? ,DATEITEMPEOPLE = ? , HASMATE =?, DATEITEMPRICE =? , DATEITEMSTATUS=? ,"
			+ "DATEITEMSHOW=?, DATEITEMVIEWER=?, BUYERNO=?, ISQRCCHECKED=?, BUYERREP=? , SELLERREP=?, ISINSTANTDATE=?, PETNO=? WHERE DATEITEMNO =�@?";
	private static final String UPDATE_VO = "UPDATE DATEITEM SET DATEITEMNO = ?, SELLERNO = ?, RESTLISTNO = ?, "
			+ "DATEITEMTITLE = ?, DATEITEMIMG = ?, DATEITEMTEXT = ?, DATEITEMTIME = ?, DATEMEETINGTIME = ?, "
			+ "DATEITEMLOCATE = ? ,DATEITEMPEOPLE = ? , HASMATE =?, DATEITEMPRICE =? , DATEITEMSTATUS=? ,"
			+ "DATEITEMSHOW=?, DATEITEMVIEWER=?, BUYERNO=?, ISQRCCHECKED=?, BUYERREP=? , SELLERREP=?, ISINSTANTDATE=?, PETNO=? WHERE DATEITEMNO =�@?";
	private static final String DELETE_STMT = "DELETE FROM DATEITEM WHERE DATEITEMNO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM DATEITEM WHERE DATEITEMNO = ?";
	private static final String GET_ALL = "SELECT * FROM DATEITEM";
	private static final String GET_ALL_ITEMS = " SELECT * FROM DATEITEM WHERE DATEITEMSHOW = 0 ORDER BY dateItemNo DESC";
	private static final String FINDBYBUYERFUTURE = "SELECT * FROM DATEITEM WHERE (BUYERNO = ? AND DATEITEMSTATUS =1) ";
	private static final String FINDBYBUYERHISTORY = "SELECT * FROM DATEITEM WHERE (BUYERNO = ? AND (DATEITEMSTATUS =2 OR DATEITEMSTATUS =3 ))";
	private static final String FINDBYSELLERFUTURE = "SELECT * FROM DATEITEM WHERE (SELLERNO = ? AND DATEITEMSTATUS =1)";
	private static final String FINDBYSELLERHISTORY = "SELECT * FROM DATEITEM WHERE (SELLERNO = ? AND (DATEITEMSTATUS =2 OR DATEITEMSTATUS =3 ))";
	private static final String FINDBYSELLERONSALE = "SELECT * FROM DATEITEM WHERE (SELLERNO = ? AND ( DATEITEMSTATUS = 0 and DATEITEMSHOW = 0))";
	private static final String GETALLFORCHATS = "SELECT * FROM DATEITEM WHERE (SELLERNO = ? OR BUYERNO=?) and DATEITEMSTATUS =1";
	private static final String THEOTHERMEM = "SELECT * FROM DATEITEM WHERE (SELLERNO = ? OR BUYERNO=?) and DATEITEMNO =?";
	private static final String FINDBYDATE="select * from DATEITEM D"
			+ " JOIN MEMBER M ON D.Sellerno = M.Memno"
			+ " join PET P on D.PETNO = P.PETNO "
			+ " join Rest R on D.restListNo = R.restNo "
			+ " where DATEITEMSHOW = 0 AND to_char(dateMeetingTime,'yyyy-mm-dd') = ?"
			+ "order by DATEITEMNO DESC";
	
	
	@Override
	public void add(DateItemVO dateItemVO) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(INSERT_STMT);
			System.out.println("�s�u���\");
			pstmt.setInt(1, dateItemVO.getSellerNo());
			System.out.println("1");
			pstmt.setInt(2, dateItemVO.getRestListNo());
			System.out.println("2");
			pstmt.setString(3, dateItemVO.getDateItemTitle());
			System.out.println("3");
			pstmt.setBytes(4, dateItemVO.getDateItemImg());
			System.out.println("4");
			pstmt.setString(5, dateItemVO.getDateItemText());
			System.out.println("5");
			pstmt.setTimestamp(6, dateItemVO.getDateItemTime());
			System.out.println("6");
			pstmt.setTimestamp(7, dateItemVO.getDateMeetingTime());
			System.out.println("7");
			pstmt.setString(8, dateItemVO.getDateItemLocate());
			System.out.println("8");
			pstmt.setInt(9, dateItemVO.getDateItemPeople());
			System.out.println("9");
			pstmt.setBoolean(10, dateItemVO.getHasMate());
			System.out.println("10");
			pstmt.setInt(11, dateItemVO.getDateItemPrice());
			System.out.println("11");
			pstmt.setInt(12, dateItemVO.getDateItemStatus());
			System.out.println("12");
			System.out.println(dateItemVO.getDateItemShow());
			pstmt.setInt(13, dateItemVO.getDateItemShow());
			System.out.println("13");
			pstmt.setInt(14, dateItemVO.getDateItemViewer());
			System.out.println("14");
			pstmt.setInt(15, dateItemVO.getBuyerNo());
			System.out.println("15");
			pstmt.setBoolean(16, dateItemVO.getIsQRCChecked());
			System.out.println("16");
			pstmt.setInt(17, dateItemVO.getBuyerRep());
			System.out.println("17");
			pstmt.setInt(18, dateItemVO.getSellerRep());
			System.out.println("18");
			pstmt.setBoolean(19, dateItemVO.getIsInstantDate());
			System.out.println("19");
			pstmt.setInt(20, dateItemVO.getPetNo());
			System.out.println("20");
			pstmt.executeUpdate();
			System.out.println("here");

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
			con = ds.getConnection();
			pstmt=con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, dateItemVO.getDateItemNo());
			pstmt.setInt(2, dateItemVO.getSellerNo());
			pstmt.setInt(3, dateItemVO.getRestListNo());
			pstmt.setString(4, dateItemVO.getDateItemTitle());
			pstmt.setBytes(5, dateItemVO.getDateItemImg());
			pstmt.setString(6, dateItemVO.getDateItemText());
			pstmt.setTimestamp(7, dateItemVO.getDateItemTime());
			pstmt.setTimestamp(8, dateItemVO.getDateMeetingTime());
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
			pstmt.setInt(21, dateItemVO.getPetNo());
			pstmt.setInt(22, dateItemVO.getDateItemNo());
			pstmt.executeUpdate();

		}  catch (SQLException e) {
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
	public void updateByVO(DateItemVO dateItemVO) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(UPDATE_VO);
			pstmt.setInt(1, dateItemVO.getDateItemNo());
			pstmt.setInt(2, dateItemVO.getSellerNo());
			pstmt.setInt(3, dateItemVO.getRestListNo());
			pstmt.setString(4, dateItemVO.getDateItemTitle());
			pstmt.setBytes(5, dateItemVO.getDateItemImg());
			pstmt.setString(6, dateItemVO.getDateItemText());
			pstmt.setTimestamp(7, dateItemVO.getDateItemTime());
			pstmt.setTimestamp(8, dateItemVO.getDateMeetingTime());
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
			pstmt.setInt(21, dateItemVO.getPetNo());
			pstmt.setInt(22, dateItemVO.getDateItemNo());
			pstmt.executeUpdate();

		}  catch (SQLException e) {
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
			con = ds.getConnection();
			pstmt=con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, dateItemNo);
			pstmt.executeUpdate();

		}  catch (SQLException e) {
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
		DateItemVO dateItemVO=null;
		
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, dateItemNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				dateItemVO=new DateItemVO();
				
				dateItemVO.setDateItemNo(rs.getInt("dateItemNo"));
				dateItemVO.setSellerNo(rs.getInt("sellerNo"));
				dateItemVO.setRestListNo(rs.getInt("restListNo"));
				dateItemVO.setDateItemTitle(rs.getString("dateItemTitle"));
				dateItemVO.setDateItemImg(rs.getBytes("dateItemImg"));
				dateItemVO.setDateItemText(rs.getString("dateItemText"));
				dateItemVO.setDateItemTime(rs.getTimestamp("dateItemTime"));
				dateItemVO.setDateMeetingTime(rs.getTimestamp("dateMeetingTime"));
				dateItemVO.setDateItemLocate(rs.getString("dateItemLocate"));
				dateItemVO.setDateItemPeople(rs.getInt("dateItemPeople"));
				dateItemVO.setHasMate(rs.getBoolean("hasMate"));
				dateItemVO.setDateItemPrice(rs.getInt("dateItemPrice"));
				dateItemVO.setDateItemStatus(rs.getInt("dateItemStatus"));
				dateItemVO.setDateItemShow(rs.getInt("dateItemShow"));
				dateItemVO.setDateItemViewer(rs.getInt("dateItemShow"));
				dateItemVO.setBuyerNo(rs.getInt("buyerNo"));
				dateItemVO.setIsQRCChecked(rs.getBoolean("isQRCChecked"));
				dateItemVO.setBuyerRep(rs.getInt("buyerRep"));
				dateItemVO.setSellerRep(rs.getInt("SellerRep"));
				dateItemVO.setIsInstantDate(rs.getBoolean("isInstantDate"));
				dateItemVO.setPetNo(rs.getInt("petNo"));
				
			}
			
		}  catch (SQLException e) {
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
		return dateItemVO;
	}
	//�C�X�w�������or����listed by�R�a	
		@Override
		public List<DateItemVO> findByBuyer_history(int buyerNo) {
			List<DateItemVO> dateItemList = new ArrayList<>();
			PreparedStatement pstmt=null;
			Connection con=null;
			ResultSet rs=null;
			
			try {
				con = ds.getConnection();
				pstmt=con.prepareStatement(FINDBYBUYERHISTORY);
				pstmt.setInt(1, buyerNo);
				rs=pstmt.executeQuery();
				while(rs.next()){
					DateItemVO dateItemVO=new DateItemVO();
					dateItemVO.setDateItemNo(rs.getInt("dateItemNo"));
					dateItemVO.setSellerNo(rs.getInt("sellerNo"));
					dateItemVO.setRestListNo(rs.getInt("restListNo"));
					dateItemVO.setDateItemTitle(rs.getString("dateItemTitle"));
					dateItemVO.setDateItemImg(rs.getBytes("dateItemImg"));
					dateItemVO.setDateItemText(rs.getString("dateItemText"));
					dateItemVO.setDateItemTime(rs.getTimestamp("dateItemTime"));
					dateItemVO.setDateMeetingTime(rs.getTimestamp("dateMeetingTime"));
					dateItemVO.setDateItemLocate(rs.getString("dateItemLocate"));
					dateItemVO.setDateItemPeople(rs.getInt("dateItemPeople"));
					dateItemVO.setHasMate(rs.getBoolean("hasMate"));
					dateItemVO.setDateItemPrice(rs.getInt("dateItemPrice"));
					dateItemVO.setDateItemStatus(rs.getInt("dateItemStatus"));
					dateItemVO.setDateItemShow(rs.getInt("dateItemShow"));
					dateItemVO.setDateItemViewer(rs.getInt("dateItemShow"));
					dateItemVO.setBuyerNo(rs.getInt("buyerNo"));
					dateItemVO.setIsQRCChecked(rs.getBoolean("isQRCChecked"));
					dateItemVO.setBuyerRep(rs.getInt("buyerRep"));
					dateItemVO.setSellerRep(rs.getInt("SellerRep"));
					dateItemVO.setIsInstantDate(rs.getBoolean("isInstantDate"));
					dateItemVO.setPetNo(rs.getInt("petNo"));
					dateItemList.add(dateItemVO);	
					
				}
				
			}  catch (SQLException e) {
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
			System.out.println("point1"+dateItemList.size());
			return dateItemList;
		}
		
	
	
//�C�X�٨S����������w�X�⪺�ӫ~listed by�R�a	
	@Override
	public List<DateItemVO> findByBuyer_future(int buyerNo) {
		List<DateItemVO> dateItemList = new ArrayList<>();
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(FINDBYBUYERFUTURE);
			pstmt.setInt(1, buyerNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				DateItemVO dateItemVO=new DateItemVO();
				dateItemVO.setDateItemNo(rs.getInt("dateItemNo"));
				dateItemVO.setSellerNo(rs.getInt("sellerNo"));
				dateItemVO.setRestListNo(rs.getInt("restListNo"));
				dateItemVO.setDateItemTitle(rs.getString("dateItemTitle"));
				dateItemVO.setDateItemImg(rs.getBytes("dateItemImg"));
				dateItemVO.setDateItemText(rs.getString("dateItemText"));
				dateItemVO.setDateItemTime(rs.getTimestamp("dateItemTime"));
				dateItemVO.setDateMeetingTime(rs.getTimestamp("dateMeetingTime"));
				dateItemVO.setDateItemLocate(rs.getString("dateItemLocate"));
				dateItemVO.setDateItemPeople(rs.getInt("dateItemPeople"));
				dateItemVO.setHasMate(rs.getBoolean("hasMate"));
				dateItemVO.setDateItemPrice(rs.getInt("dateItemPrice"));
				dateItemVO.setDateItemStatus(rs.getInt("dateItemStatus"));
				dateItemVO.setDateItemShow(rs.getInt("dateItemShow"));
				dateItemVO.setDateItemViewer(rs.getInt("dateItemShow"));
				dateItemVO.setBuyerNo(rs.getInt("buyerNo"));
				dateItemVO.setIsQRCChecked(rs.getBoolean("isQRCChecked"));
				dateItemVO.setBuyerRep(rs.getInt("buyerRep"));
				dateItemVO.setSellerRep(rs.getInt("SellerRep"));
				dateItemVO.setIsInstantDate(rs.getBoolean("isInstantDate"));
				dateItemVO.setPetNo(rs.getInt("petNo"));
				dateItemList.add(dateItemVO);	
				
			}
			
		}  catch (SQLException e) {
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
		System.out.println("point1"+dateItemList.size());
		return dateItemList;
	}
	
	

	@Override
	public List<DateItemVO> getAll() {
		List<DateItemVO> dateItemList = new ArrayList<>();
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		
		try {
			con = ds.getConnection();
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
				dateItemVO.setDateItemTime(rs.getTimestamp("dateItemTime"));
				dateItemVO.setDateMeetingTime(rs.getTimestamp("dateMeetingTime"));
				dateItemVO.setDateItemLocate(rs.getString("dateItemLocate"));
				dateItemVO.setDateItemPeople(rs.getInt("dateItemPeople"));
				dateItemVO.setHasMate(rs.getBoolean("hasMate"));
				dateItemVO.setDateItemPrice(rs.getInt("dateItemPrice"));
				dateItemVO.setDateItemStatus(rs.getInt("dateItemStatus"));
				dateItemVO.setDateItemShow(rs.getInt("dateItemShow"));
				dateItemVO.setDateItemViewer(rs.getInt("dateItemShow"));
				dateItemVO.setBuyerNo(rs.getInt("buyerNo"));
				dateItemVO.setIsQRCChecked(rs.getBoolean("isQRCChecked"));
				dateItemVO.setBuyerRep(rs.getInt("buyerRep"));
				dateItemVO.setSellerRep(rs.getInt("SellerRep"));
				dateItemVO.setIsInstantDate(rs.getBoolean("isInstantDate"));
				dateItemVO.setPetNo(rs.getInt("petNo"));
				dateItemList.add(dateItemVO);		
			}
			
		}  catch (SQLException e) {
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
	
	//getAllItems�Oshow�X�Ҧ��W�[���A=0���ӫ~

	public List<DateItemVO> getAllItems() {
		List<DateItemVO> dateItemList = new ArrayList<>();
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(GET_ALL_ITEMS);
			rs=pstmt.executeQuery();
			while(rs.next()){
				DateItemVO dateItemVO=new DateItemVO();
				dateItemVO.setDateItemNo(rs.getInt("dateItemNo"));
				dateItemVO.setSellerNo(rs.getInt("sellerNo"));
				dateItemVO.setRestListNo(rs.getInt("restListNo"));
				dateItemVO.setDateItemTitle(rs.getString("dateItemTitle"));
				dateItemVO.setDateItemImg(rs.getBytes("dateItemImg"));
				dateItemVO.setDateItemText(rs.getString("dateItemText"));
				dateItemVO.setDateItemTime(rs.getTimestamp("dateItemTime"));
				dateItemVO.setDateMeetingTime(rs.getTimestamp("dateMeetingTime"));
				dateItemVO.setDateItemLocate(rs.getString("dateItemLocate"));
				dateItemVO.setDateItemPeople(rs.getInt("dateItemPeople"));
				dateItemVO.setHasMate(rs.getBoolean("hasMate"));
				dateItemVO.setDateItemPrice(rs.getInt("dateItemPrice"));
				dateItemVO.setDateItemStatus(rs.getInt("dateItemStatus"));
				dateItemVO.setDateItemShow(rs.getInt("dateItemShow"));
				dateItemVO.setDateItemViewer(rs.getInt("dateItemShow"));
				dateItemVO.setBuyerNo(rs.getInt("buyerNo"));
				dateItemVO.setIsQRCChecked(rs.getBoolean("isQRCChecked"));
				dateItemVO.setBuyerRep(rs.getInt("buyerRep"));
				dateItemVO.setSellerRep(rs.getInt("SellerRep"));
				dateItemVO.setIsInstantDate(rs.getBoolean("isInstantDate"));
				dateItemVO.setPetNo(rs.getInt("petNo"));
				dateItemList.add(dateItemVO);		
			}
			
		}  catch (SQLException e) {
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

	@Override
	public List<DateItemVO> findBySeller_future(int sellerNo) {
		List<DateItemVO> dateItemList = new ArrayList<>();
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(FINDBYSELLERFUTURE);
			pstmt.setInt(1, sellerNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				DateItemVO dateItemVO=new DateItemVO();
				dateItemVO.setDateItemNo(rs.getInt("dateItemNo"));
				dateItemVO.setSellerNo(rs.getInt("sellerNo"));
				dateItemVO.setRestListNo(rs.getInt("restListNo"));
				dateItemVO.setDateItemTitle(rs.getString("dateItemTitle"));
				dateItemVO.setDateItemImg(rs.getBytes("dateItemImg"));
				dateItemVO.setDateItemText(rs.getString("dateItemText"));
				dateItemVO.setDateItemTime(rs.getTimestamp("dateItemTime"));
				dateItemVO.setDateMeetingTime(rs.getTimestamp("dateMeetingTime"));
				dateItemVO.setDateItemLocate(rs.getString("dateItemLocate"));
				dateItemVO.setDateItemPeople(rs.getInt("dateItemPeople"));
				dateItemVO.setHasMate(rs.getBoolean("hasMate"));
				dateItemVO.setDateItemPrice(rs.getInt("dateItemPrice"));
				dateItemVO.setDateItemStatus(rs.getInt("dateItemStatus"));
				dateItemVO.setDateItemShow(rs.getInt("dateItemShow"));
				dateItemVO.setDateItemViewer(rs.getInt("dateItemShow"));
				dateItemVO.setBuyerNo(rs.getInt("buyerNo"));
				dateItemVO.setIsQRCChecked(rs.getBoolean("isQRCChecked"));
				dateItemVO.setBuyerRep(rs.getInt("buyerRep"));
				dateItemVO.setSellerRep(rs.getInt("SellerRep"));
				dateItemVO.setIsInstantDate(rs.getBoolean("isInstantDate"));
				dateItemVO.setPetNo(rs.getInt("petNo"));
				dateItemList.add(dateItemVO);	
				
			}
			
		}  catch (SQLException e) {
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
	
	@Override
	public List<DateItemVO> findBySeller_history(int sellerNo) {
		List<DateItemVO> dateItemList = new ArrayList<>();
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(FINDBYSELLERHISTORY);
			pstmt.setInt(1, sellerNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				DateItemVO dateItemVO=new DateItemVO();
				dateItemVO.setDateItemNo(rs.getInt("dateItemNo"));
				dateItemVO.setSellerNo(rs.getInt("sellerNo"));
				dateItemVO.setRestListNo(rs.getInt("restListNo"));
				dateItemVO.setDateItemTitle(rs.getString("dateItemTitle"));
				dateItemVO.setDateItemImg(rs.getBytes("dateItemImg"));
				dateItemVO.setDateItemText(rs.getString("dateItemText"));
				dateItemVO.setDateItemTime(rs.getTimestamp("dateItemTime"));
				dateItemVO.setDateMeetingTime(rs.getTimestamp("dateMeetingTime"));
				dateItemVO.setDateItemLocate(rs.getString("dateItemLocate"));
				dateItemVO.setDateItemPeople(rs.getInt("dateItemPeople"));
				dateItemVO.setHasMate(rs.getBoolean("hasMate"));
				dateItemVO.setDateItemPrice(rs.getInt("dateItemPrice"));
				dateItemVO.setDateItemStatus(rs.getInt("dateItemStatus"));
				dateItemVO.setDateItemShow(rs.getInt("dateItemShow"));
				dateItemVO.setDateItemViewer(rs.getInt("dateItemShow"));
				dateItemVO.setBuyerNo(rs.getInt("buyerNo"));
				dateItemVO.setIsQRCChecked(rs.getBoolean("isQRCChecked"));
				dateItemVO.setBuyerRep(rs.getInt("buyerRep"));
				dateItemVO.setSellerRep(rs.getInt("SellerRep"));
				dateItemVO.setIsInstantDate(rs.getBoolean("isInstantDate"));
				dateItemVO.setPetNo(rs.getInt("petNo"));
				dateItemList.add(dateItemVO);	
				
			}
			
		}  catch (SQLException e) {
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
	
	@Override
	public List<DateItemVO> findBySeller_onsale(int sellerNo) {
		List<DateItemVO> dateItemList = new ArrayList<>();
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(FINDBYSELLERONSALE);
			pstmt.setInt(1, sellerNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				DateItemVO dateItemVO=new DateItemVO();
				dateItemVO.setDateItemNo(rs.getInt("dateItemNo"));
				dateItemVO.setSellerNo(rs.getInt("sellerNo"));
				dateItemVO.setRestListNo(rs.getInt("restListNo"));
				dateItemVO.setDateItemTitle(rs.getString("dateItemTitle"));
				dateItemVO.setDateItemImg(rs.getBytes("dateItemImg"));
				dateItemVO.setDateItemText(rs.getString("dateItemText"));
				dateItemVO.setDateItemTime(rs.getTimestamp("dateItemTime"));
				dateItemVO.setDateMeetingTime(rs.getTimestamp("dateMeetingTime"));
				dateItemVO.setDateItemLocate(rs.getString("dateItemLocate"));
				dateItemVO.setDateItemPeople(rs.getInt("dateItemPeople"));
				dateItemVO.setHasMate(rs.getBoolean("hasMate"));
				dateItemVO.setDateItemPrice(rs.getInt("dateItemPrice"));
				dateItemVO.setDateItemStatus(rs.getInt("dateItemStatus"));
				dateItemVO.setDateItemShow(rs.getInt("dateItemShow"));
				dateItemVO.setDateItemViewer(rs.getInt("dateItemShow"));
				dateItemVO.setBuyerNo(rs.getInt("buyerNo"));
				dateItemVO.setIsQRCChecked(rs.getBoolean("isQRCChecked"));
				dateItemVO.setBuyerRep(rs.getInt("buyerRep"));
				dateItemVO.setSellerRep(rs.getInt("SellerRep"));
				dateItemVO.setIsInstantDate(rs.getBoolean("isInstantDate"));
				dateItemVO.setPetNo(rs.getInt("petNo"));
				dateItemList.add(dateItemVO);	
				
			}
			
		}  catch (SQLException e) {
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
	
	@Override
	public Integer findTheOtherMem(int memNo, int dateItemNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		DateItemVO dateItemVO=new DateItemVO();
		
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(THEOTHERMEM);
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, memNo);
			pstmt.setInt(3, dateItemNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				dateItemVO.setDateItemNo(rs.getInt("dateItemNo"));
				dateItemVO.setSellerNo(rs.getInt("sellerNo"));
				dateItemVO.setRestListNo(rs.getInt("restListNo"));
				dateItemVO.setDateItemTitle(rs.getString("dateItemTitle"));
				dateItemVO.setDateItemImg(rs.getBytes("dateItemImg"));
				dateItemVO.setDateItemText(rs.getString("dateItemText"));
				dateItemVO.setDateItemTime(rs.getTimestamp("dateItemTime"));
				dateItemVO.setDateMeetingTime(rs.getTimestamp("dateMeetingTime"));
				dateItemVO.setDateItemLocate(rs.getString("dateItemLocate"));
				dateItemVO.setDateItemPeople(rs.getInt("dateItemPeople"));
				dateItemVO.setHasMate(rs.getBoolean("hasMate"));
				dateItemVO.setDateItemPrice(rs.getInt("dateItemPrice"));
				dateItemVO.setDateItemStatus(rs.getInt("dateItemStatus"));
				dateItemVO.setDateItemShow(rs.getInt("dateItemShow"));
				dateItemVO.setDateItemViewer(rs.getInt("dateItemShow"));
				dateItemVO.setBuyerNo(rs.getInt("buyerNo"));
				dateItemVO.setIsQRCChecked(rs.getBoolean("isQRCChecked"));
				dateItemVO.setBuyerRep(rs.getInt("buyerRep"));
				dateItemVO.setSellerRep(rs.getInt("SellerRep"));
				dateItemVO.setIsInstantDate(rs.getBoolean("isInstantDate"));
				dateItemVO.setPetNo(rs.getInt("petNo"));
				
			}
			
		}  catch (SQLException e) {
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
	
		if(dateItemVO.getBuyerNo()==memNo){
			return dateItemVO.getSellerNo();
		}else{
			return dateItemVO.getBuyerNo();
		}
		
	}
	
	@Override
	public List<DateItemVO> getAllForChats(int memNo) {
		List<DateItemVO> dateItemList = new ArrayList<>();
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(GETALLFORCHATS);
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, memNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				DateItemVO dateItemVO=new DateItemVO();
				dateItemVO.setDateItemNo(rs.getInt("dateItemNo"));
				dateItemVO.setSellerNo(rs.getInt("sellerNo"));
				dateItemVO.setRestListNo(rs.getInt("restListNo"));
				dateItemVO.setDateItemTitle(rs.getString("dateItemTitle"));
				dateItemVO.setDateItemImg(rs.getBytes("dateItemImg"));
				dateItemVO.setDateItemText(rs.getString("dateItemText"));
				dateItemVO.setDateItemTime(rs.getTimestamp("dateItemTime"));
				dateItemVO.setDateMeetingTime(rs.getTimestamp("dateMeetingTime"));
				dateItemVO.setDateItemLocate(rs.getString("dateItemLocate"));
				dateItemVO.setDateItemPeople(rs.getInt("dateItemPeople"));
				dateItemVO.setHasMate(rs.getBoolean("hasMate"));
				dateItemVO.setDateItemPrice(rs.getInt("dateItemPrice"));
				dateItemVO.setDateItemStatus(rs.getInt("dateItemStatus"));
				dateItemVO.setDateItemShow(rs.getInt("dateItemShow"));
				dateItemVO.setDateItemViewer(rs.getInt("dateItemShow"));
				dateItemVO.setBuyerNo(rs.getInt("buyerNo"));
				dateItemVO.setIsQRCChecked(rs.getBoolean("isQRCChecked"));
				dateItemVO.setBuyerRep(rs.getInt("buyerRep"));
				dateItemVO.setSellerRep(rs.getInt("SellerRep"));
				dateItemVO.setIsInstantDate(rs.getBoolean("isInstantDate"));
				dateItemVO.setPetNo(rs.getInt("petNo"));
				dateItemList.add(dateItemVO);	
				
			}
			
		}  catch (SQLException e) {
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
	
	@Override
	public List<DateItemVO> getAll(Map<String, String[]> map) {
		List<DateItemVO> list = new ArrayList<DateItemVO>();
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from MEMBER M"
					+ " JOIN DATEITEM D ON M.Memno = D.Sellerno "
					+ " join PET P on D.PETNO = P.PETNO "
					+ " where DATEITEMSHOW = 0 "
		          + jdbcUtil_CompositeQuery_DateItem.get_WhereCondition(map)
		          + "order by DATEITEMNO";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("����finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				DateItemVO dateItemVO=new DateItemVO();
				dateItemVO.setDateItemNo(rs.getInt("dateItemNo"));
				dateItemVO.setSellerNo(rs.getInt("sellerNo"));
				dateItemVO.setRestListNo(rs.getInt("restListNo"));
				dateItemVO.setDateItemTitle(rs.getString("dateItemTitle"));
				dateItemVO.setDateItemImg(rs.getBytes("dateItemImg"));
				dateItemVO.setDateItemText(rs.getString("dateItemText"));
				dateItemVO.setDateItemTime(rs.getTimestamp("dateItemTime"));
				dateItemVO.setDateMeetingTime(rs.getTimestamp("dateMeetingTime"));
				dateItemVO.setDateItemLocate(rs.getString("dateItemLocate"));
				dateItemVO.setDateItemPeople(rs.getInt("dateItemPeople"));
				dateItemVO.setHasMate(rs.getBoolean("hasMate"));
				dateItemVO.setDateItemPrice(rs.getInt("dateItemPrice"));
				dateItemVO.setDateItemStatus(rs.getInt("dateItemStatus"));
				dateItemVO.setDateItemShow(rs.getInt("dateItemShow"));
				dateItemVO.setDateItemViewer(rs.getInt("dateItemShow"));
				dateItemVO.setBuyerNo(rs.getInt("buyerNo"));
				dateItemVO.setIsQRCChecked(rs.getBoolean("isQRCChecked"));
				dateItemVO.setBuyerRep(rs.getInt("buyerRep"));
				dateItemVO.setSellerRep(rs.getInt("SellerRep"));
				dateItemVO.setIsInstantDate(rs.getBoolean("isInstantDate"));
				dateItemVO.setPetNo(rs.getInt("petNo"));
				list.add(dateItemVO);	
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
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
		return list;
	}

	@Override
	public List<SDateItemVO> findByDate(String date) {
		List<SDateItemVO> dateItemList = new ArrayList<>();
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(FINDBYDATE);
			pstmt.setString(1, date);
			rs=pstmt.executeQuery();
			while(rs.next()){
				SDateItemVO dateItemVO=new SDateItemVO();
				dateItemVO.setDateItemNo(rs.getInt("dateItemNo"));
				dateItemVO.setSellerNo(rs.getInt("sellerNo"));
				dateItemVO.setRestListNo(rs.getInt("restListNo"));
				dateItemVO.setDateMeetingTime(rs.getTimestamp("dateMeetingTime"));
				dateItemVO.setDateItemLocate(rs.getString("dateItemLocate"));
				dateItemVO.setDateItemPeople(rs.getInt("dateItemPeople"));
				dateItemVO.setHasMate(rs.getBoolean("hasMate"));
				dateItemVO.setDateItemPrice(rs.getInt("dateItemPrice"));
				dateItemVO.setDateItemStatus(rs.getInt("dateItemStatus"));
				dateItemVO.setDateItemShow(rs.getInt("dateItemShow"));
				dateItemVO.setDateItemViewer(rs.getInt("dateItemShow"));
				dateItemVO.setIsInstantDate(rs.getBoolean("isInstantDate"));
				dateItemVO.setPetNo(rs.getInt("petNo"));
				dateItemVO.setMemGender(rs.getInt("memGender"));
				dateItemVO.setMemId(rs.getString("memId"));
				dateItemVO.setMemSname(rs.getString("memSname"));
				dateItemVO.setPetGender(rs.getInt("petGender"));
				dateItemVO.setPetKind(rs.getString("petKind"));
				dateItemVO.setPetName(rs.getString("petName"));
				dateItemVO.setPetSpecies(rs.getString("petSpecies"));
				dateItemVO.setRestLatitude(rs.getDouble("restLatitude"));
				dateItemVO.setRestLongtitude(rs.getDouble("restLongtitude"));
				dateItemVO.setRestName(rs.getString("restName"));
				dateItemVO.setRestAdd(rs.getString("restAdd"));
				dateItemVO.setRestLocate(rs.getString("restLocate"));
				dateItemVO.setRestPhone(rs.getString("restPhone"));
				dateItemList.add(dateItemVO);		
			}
			
		}  catch (SQLException e) {
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

	@Override
	public List<DateItemVO> getAllWithoutImg() {
		List<DateItemVO> dateItemList = new ArrayList<>();
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(GET_ALL_ITEMS);
			rs=pstmt.executeQuery();
			while(rs.next()){
				DateItemVO dateItemVO=new DateItemVO();
				dateItemVO.setDateItemNo(rs.getInt("dateItemNo"));
				dateItemVO.setSellerNo(rs.getInt("sellerNo"));
				dateItemVO.setRestListNo(rs.getInt("restListNo"));
				dateItemVO.setDateItemTitle(rs.getString("dateItemTitle"));
//				dateItemVO.setDateItemImg(rs.getBytes("dateItemImg"));
				dateItemVO.setDateItemText(rs.getString("dateItemText"));
				dateItemVO.setDateItemTime(rs.getTimestamp("dateItemTime"));
				dateItemVO.setDateMeetingTime(rs.getTimestamp("dateMeetingTime"));
				dateItemVO.setDateItemLocate(rs.getString("dateItemLocate"));
				dateItemVO.setDateItemPeople(rs.getInt("dateItemPeople"));
				dateItemVO.setHasMate(rs.getBoolean("hasMate"));
				dateItemVO.setDateItemPrice(rs.getInt("dateItemPrice"));
				dateItemVO.setDateItemStatus(rs.getInt("dateItemStatus"));
				dateItemVO.setDateItemShow(rs.getInt("dateItemShow"));
				dateItemVO.setDateItemViewer(rs.getInt("dateItemShow"));
				dateItemVO.setBuyerNo(rs.getInt("buyerNo"));
				dateItemVO.setIsQRCChecked(rs.getBoolean("isQRCChecked"));
				dateItemVO.setBuyerRep(rs.getInt("buyerRep"));
				dateItemVO.setSellerRep(rs.getInt("SellerRep"));
				dateItemVO.setIsInstantDate(rs.getBoolean("isInstantDate"));
				dateItemVO.setPetNo(rs.getInt("petNo"));
				dateItemList.add(dateItemVO);		
			}
			
		}  catch (SQLException e) {
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
}
