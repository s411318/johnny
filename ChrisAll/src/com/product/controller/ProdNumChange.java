package com.product.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.product.model.Product;


@WebServlet("/ProdNumChange")
public class ProdNumChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");  
		res.setContentType("text/html; charset=UTF-8");
		HttpSession session = req.getSession();
		Vector<Product> buylist = (Vector<Product>) session.getAttribute("shoppingcart");
		Map<String,Integer> qtyMap = (Map<String,Integer>) session.getAttribute("qtyMap");
		String[] num = req.getParameterValues("test");
		
		
		int amount = 0;

		for(int i=0;i<buylist.size();i++) {
			
			
			int numNew=Integer.parseInt(num[i]);
			
			Product prod = buylist.get(i);
			String prodName = prod.getProdName();
			System.out.println(numNew);
			
			qtyMap.remove(prodName);
			qtyMap.put(prodName,numNew);
			Product Newprod = buylist.get(i);
			
			String NewProName = Newprod.getProdName();
			amount += (Newprod.getProdPrice()*qtyMap.get(NewProName));
			
			int Newqty = qtyMap.get(NewProName);
			
//			System.out.println("�ثe�ӫ~����:"+NewProName+"�ثe���ӫ~�ƶq:"+Newqty + "�`��:" + amount);
		
	}
		session.setAttribute("amount", amount);
		if(req.getParameter("action").equals("pay")) {
			RequestDispatcher rd = req.getRequestDispatcher("/front_end/product/CheckOutConfirm.jsp");
			rd.forward(req, res);
		}if(req.getParameter("action").equals("back")) {
			RequestDispatcher rd = req.getRequestDispatcher("/front_end/product/BrowseProduct.jsp");
			rd.forward(req, res);
		}
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doGet(req, res);
	}

}
