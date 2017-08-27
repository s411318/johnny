package com.product.model;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.order.model.Ord;

public class ProductDAO implements Product_interface{
	private static DataSource ds;


	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petym");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO PRODUCT(PRODNO,PRODNAME,PRODPRICE,PRODIMG,PRODDESCPT,PRODADD,PRODOUT,PRODSTATE,PRODTYPE)"+ "VALUES(PRODNO_SQ.NEXTVAL,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE PRODUCT SET PRODNO = ?, PRODNAME = ?, PRODPRICE = ?,PRODIMG = ?,PRODDESCPT = ?,PRODADD = ?,PRODOUT = ?,PRODSTATE = ?,PRODTYPE = ? "
			+ "WHERE PRODNO =�@?";
	private static final String GET_ALL_BYTYPE = "SELECT * FROM PRODUCT WHERE PRODTYPE = ? AND PRODSTATE = ?";
	private static final String DELETE_STMT = "DELETE FROM PRODUCT WHERE PRODNO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM PRODUCT WHERE PRODNO = ?";
	private static final String GET_ALL = "SELECT * FROM PRODUCT WHERE PRODSTATE = ?";
	@Override
	public void add(Product prod) {
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, prod.getProdName());
			pstmt.setInt(2, prod.getProdPrice());
			Blob blob = con.createBlob();
			blob.setBytes(1, prod.getProdImg());
			pstmt.setBlob(3, blob);
			pstmt.setString(4, prod.getProdDescpt());
			pstmt.setDate(5, prod.getProdAdd());
			pstmt.setDate(6, prod.getProdOut());
			pstmt.setInt(7, prod.getProdState());
			pstmt.setString(8, prod.getProdType());
			pstmt.executeUpdate();

			
		} catch (Exception se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(Product prod) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, prod.getProdNo());
			pstmt.setString(2, prod.getProdName());
			pstmt.setInt(3, prod.getProdPrice());
			Blob blob = con.createBlob();
			blob.setBytes(1, prod.getProdImg());
			pstmt.setBlob(4, blob);
			pstmt.setString(5, prod.getProdDescpt());
			pstmt.setDate(6, prod.getProdAdd());
			pstmt.setDate(7, prod.getProdOut());
			pstmt.setInt(8, prod.getProdState());
			pstmt.setString(9, prod.getProdType());
			pstmt.setInt(10, prod.getProdNo());
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
	public void delete(int prodNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, prodNo);
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
	public Product findByPk(int prodNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		Product product=null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, prodNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				product=new Product();
				product.setProdNo(rs.getInt("prodNo"));
				product.setProdName(rs.getString("prodName"));
				product.setProdPrice(rs.getInt("prodPrice"));
				product.setProdImg(rs.getBytes("prodImg"));
				product.setProdDescpt(rs.getString("prodDescpt"));
				product.setProdAdd(rs.getDate("prodAdd"));
				product.setProdOut(rs.getDate("prodOut"));
				product.setProdState(rs.getInt("prodState"));
				product.setProdType(rs.getString("prodType"));
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
		return product;
	}

	@Override
	public List<Product> getAll() {
		List<Product> productList = new ArrayList<>();
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			pstmt.setInt(1, 0);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Product product=new Product();
				product.setProdNo(rs.getInt("prodNo"));
				product.setProdName(rs.getString("prodName"));
				product.setProdPrice(rs.getInt("prodPrice"));
				product.setProdImg(rs.getBytes("prodImg"));
				product.setProdDescpt(rs.getString("prodDescpt"));
				product.setProdAdd(rs.getDate("prodAdd"));
				product.setProdOut(rs.getDate("prodOut"));
				product.setProdState(rs.getInt("prodState"));
				product.setProdType(rs.getString("prodType"));
				productList.add(product);		
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
		return productList;
	}

	@Override
	public List<Product> getAllByName(String name) {
		List<Product> productList = new ArrayList<>();
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		
		try {
			con = ds.getConnection();
			String SQL = "select * from product where prodname like '%" + name + "%'";
			pstmt=con.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Product product=new Product();
				product.setProdNo(rs.getInt("prodNo"));
				product.setProdName(rs.getString("prodName"));
				product.setProdPrice(rs.getInt("prodPrice"));
				product.setProdImg(rs.getBytes("prodImg"));
				product.setProdDescpt(rs.getString("prodDescpt"));
				product.setProdAdd(rs.getDate("prodAdd"));
				product.setProdOut(rs.getDate("prodOut"));
				product.setProdState(rs.getInt("prodState"));
				product.setProdType(rs.getString("prodType"));
				productList.add(product);		
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
		return productList;
	}

	@Override
	public List<Product> getAllByType(String type) {
		List<Product> productList = new ArrayList<>();
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(GET_ALL_BYTYPE);
			pstmt.setString(1, type);
			pstmt.setInt(2, 0);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Product product=new Product();
				product.setProdNo(rs.getInt("prodNo"));
				product.setProdName(rs.getString("prodName"));
				product.setProdPrice(rs.getInt("prodPrice"));
				product.setProdImg(rs.getBytes("prodImg"));
				product.setProdDescpt(rs.getString("prodDescpt"));
				product.setProdAdd(rs.getDate("prodAdd"));
				product.setProdOut(rs.getDate("prodOut"));
				product.setProdState(rs.getInt("prodState"));
				product.setProdType(rs.getString("prodType"));
				productList.add(product);		
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
		return productList;
	}
	}


