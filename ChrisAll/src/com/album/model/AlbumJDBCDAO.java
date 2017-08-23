package com.album.model;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.albumimg.model.AlbumImg;
import com.albumimg.model.AlbumImgDAO;
import com.pet.model.Pet;
import com.pet.model.PetJDBCDAO;

public class AlbumJDBCDAO implements AlbumDAO_interface {

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "petym";
	private static final String PASSWORD = "123456";
	
	
	private static final String INSERT_STMT = "INSERT INTO ALBUM(ALBUMNO, MEMNO,ALBUMTITLE,ALBUMCREATEDTIME,AlbumModifiedTime,ALBUMSTATUS,ALBUMIMGFILE)"
			+ " VALUES(ALBUMNO_SQ.NEXTVAL,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE ALBUM SET ALBUMNO = ?, MEMNO = ?, ALBUMTITLE = ?, "
			+ "ALBUMCREATEDTIME = ?, ALBUMMODIFIEDTIME = ?, ALBUMSTATUS = ?, ALBUMIMGFILE = ? WHERE ALBUMNO =�@?";
	private static final String DELETE_ALBUM = "DELETE FROM ALBUM WHERE ALBUMNO = ?";
	private static final String DELETE_ALBUMIMG = "DELETE FROM ALBUMIMG WHERE ALBUMNO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM ALBUM WHERE ALBUMNO = ?";
	private static final String FIND_ALBUMIMGS_BY_MEMNO = "SELECT * FROM ALBUMIMG WHERE ALBUMNO = ? ORDER BY IMGNO DESC";
	private static final String GET_ALL = "SELECT * FROM ALBUM";

	
	@Override
	public void add(Album album) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt=con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, album.getMemNo());
			pstmt.setString(2, album.getAlbumTitle());
			pstmt.setTimestamp(3, album.getAlbumCreatedTime());
			pstmt.setTimestamp(4, album.getAlbumModifiedTime());
			pstmt.setInt(5,album.getAlbumStatus());
			Blob blob=con.createBlob();
			blob.setBytes(1, album.getAlbumImgFile());
			pstmt.setBlob(6, blob);
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
	public Integer add2(Album album) {
		PreparedStatement pstmt=null;
		Connection con=null;
		Integer albumNo=null;
		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			String cols[]={"ALBUMNO"};
			pstmt=con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, album.getMemNo());
			pstmt.setString(2, album.getAlbumTitle());
			pstmt.setTimestamp(3, album.getAlbumCreatedTime());
			pstmt.setTimestamp(4, album.getAlbumModifiedTime());
			pstmt.setInt(5,album.getAlbumStatus());
			Blob blob=con.createBlob();
			blob.setBytes(1, album.getAlbumImgFile());
			pstmt.setBlob(6, blob);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				albumNo = rs.getInt(1);
				System.out.println("�ۼW�D���= " + albumNo +"(��s�W���\����ï�s��)");
			} else {
				System.out.println("�����o�ۼW�D���");
			}	
			
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
		return albumNo;
		
	}
	
	
	
	@Override
	public void addWithImg(Album album, List<AlbumImg> aImgs) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// 1���]�w�� pstm.executeUpdate()���e
    		con.setAutoCommit(false);
			
    		// ���s�W�|��
			String cols[] = {"ALBUMNO"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setInt(1, album.getMemNo());
			pstmt.setString(2, album.getAlbumTitle());
			pstmt.setTimestamp(3, album.getAlbumCreatedTime());
			pstmt.setTimestamp(4, album.getAlbumModifiedTime());
			pstmt.setInt(5,album.getAlbumStatus());
			pstmt.setBytes(6, album.getAlbumImgFile());
			pstmt.executeUpdate();
			//�����������ۼW�D���
			String next_albumno = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_albumno = rs.getString(1);
				System.out.println("�ۼW�D���= " + next_albumno +"(��s�W���\����ï�s��)");
			} else {
				System.out.println("�����o�ۼW�D���");
			}
			rs.close();
			// �A�P�ɷs�W���u
			AlbumImgDAO dao = new AlbumImgDAO();
			for(AlbumImg aImg:aImgs){
			aImg.setImgNo(new Integer(next_albumno)) ;
			dao.update2(aImg,con);}
			// 2���]�w�� pstm.executeUpdate()����
			con.commit();
			con.setAutoCommit(true);
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3���]�w���exception�o�ͮɤ�catch�϶���
					System.err.print("Transaction is being ");
					System.err.println("rolled back-��-Album");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	public void update(Album album) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt=con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, album.getAlbumNo());
			pstmt.setInt(2, album.getMemNo());
			pstmt.setString(3, album.getAlbumTitle());
			pstmt.setTimestamp(4, album.getAlbumCreatedTime());
			pstmt.setTimestamp(5, album.getAlbumModifiedTime());
			pstmt.setInt(6,album.getAlbumStatus());
			Blob blob=con.createBlob();
			blob.setBytes(1, album.getAlbumImgFile());
			pstmt.setBlob(7, blob);
			pstmt.setInt(8, album.getAlbumNo());
			
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
	public void delete(int albumNo) {
		int updateCount_AlbumImgs = 0;
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			
			// 1���]�w�� pstm.executeUpdate()���e
			con.setAutoCommit(false);
			
			
			//���R�ۤ� ALBUMIMG
			pstmt=con.prepareStatement(DELETE_ALBUMIMG);
			pstmt.setInt(1, albumNo);
			updateCount_AlbumImgs=pstmt.executeUpdate();
			
			//�A�R��ï ALBUM
			pstmt=con.prepareStatement(DELETE_ALBUM);
			pstmt.setInt(1, albumNo);
			pstmt.executeUpdate();
		
			
			// 2���]�w�� pstm.executeUpdate()����
			con.commit();
			con.setAutoCommit(true);
			System.out.println("�R����ï�s��" + albumNo + "��,�@��" + updateCount_AlbumImgs
					+ "�i�ۤ��P�ɳQ�R��");
			
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
	public Album findByPk(int albumNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		Album album=null;
		
		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt=con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, albumNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				album=new Album();
				album.setAlbumNo(rs.getInt("albumNo"));
				album.setMemNo(rs.getInt("memNo"));
				album.setAlbumTitle(rs.getString("albumTitle"));
				album.setAlbumCreatedTime(rs.getTimestamp("albumCreatedTime"));
				album.setAlbumModifiedTime(rs.getTimestamp("albumModifiedTime"));
				album.setAlbumStatus(rs.getInt("albumStatus"));
				album.setAlbumImgFile(rs.getBytes("albumImgFile"));
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
		return album;
	}

	@Override
	public List<Album> getAll() {
		List<Album> albumList = new ArrayList<>();
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		
		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt=con.prepareStatement(GET_ALL);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Album album=new Album();
				album.setAlbumNo(rs.getInt("albumNo"));
				album.setMemNo(rs.getInt("memNo"));
				album.setAlbumTitle(rs.getString("albumTitle"));
				album.setAlbumCreatedTime(rs.getTimestamp("albumCreatedTime"));
				album.setAlbumModifiedTime(rs.getTimestamp("albumModifiedTime"));
				album.setAlbumStatus(rs.getInt("albumStatus"));
				album.setAlbumImgFile(rs.getBytes("albumImgFile"));
				albumList.add(album);		
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
		return albumList;
	}


	@Override
	public Set<AlbumImg> findAImgsByAlbumNo(Integer albumNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		Set<AlbumImg> set=new LinkedHashSet<AlbumImg>();
		
		try{
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt=con.prepareStatement(FIND_ALBUMIMGS_BY_MEMNO);
			pstmt.setInt(1,albumNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				AlbumImg albumImg = new AlbumImg();
				albumImg.setImgNo(rs.getInt("imgNo"));
				albumImg.setAlbumNo(rs.getInt("albumNo"));
				albumImg.setImgTitle(rs.getString("imgTitle"));
				albumImg.setImgDesc(rs.getString("imgDesc"));
				albumImg.setImgCreatedTime(rs.getTimestamp("imgCreatedTime"));
				albumImg.setImgModifiedTime(rs.getTimestamp("imgModifiedTime"));
				albumImg.setImgFileName(rs.getString("imgFileName"));
				albumImg.setImgExtName(rs.getString("imgExtName"));
				albumImg.setImgFile(rs.getBytes("imgFile"));
				set.add(albumImg);
			}
		}
		catch(ClassNotFoundException e){
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
		
		return set;
	}



}
