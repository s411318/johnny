package com.emp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.emp.model.Emp;
import com.emp.model.EmpService;
import com.member.model.Member;


@WebServlet("/back_end/emp/EmpRegister.do")
public class EmpRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		List<String> errorMsgs = new LinkedList<String>();
		
		
		
		try {
			/***************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
			
			String empName = req.getParameter("empName");
			if (empName == null || empName.trim().isEmpty()) {
				errorMsgs.add("�ж�g�m�W");
			}
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (!empName.trim().matches(enameReg)) {
				errorMsgs.add("���u�m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
			}

			
			String empId = req.getParameter("empId");
			if (empId == null || empId.trim().isEmpty()) {
				errorMsgs.add("�ж�g�b��");
			}
			String empIdReg = "^[(a-zA-Z0-9_)]{2,10}$";
			if (!empId.trim().matches(empIdReg)) {
				errorMsgs.add("���u�b��: �u��O�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
			}
			
			
			String empJob=req.getParameter("empJob");
			

			java.sql.Date empHireDate = null;
			try {
				empHireDate = java.sql.Date.valueOf(req.getParameter("empHireDate"));
			} catch (IllegalArgumentException e) {
				errorMsgs.add("����榡���~");
			}
			
			String empEmail = req.getParameter("empEmail");
			if (!empEmail.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
				errorMsgs.add("�п�J���T��Email�H�c");
			}

			System.out.println("qweqwe");
			String[] empAuthb=req.getParameterValues("empAuth");
			List<Integer> empAuthNos=new ArrayList<Integer>( );
			for(String eAuth:empAuthb){
				empAuthNos.add(Integer.parseInt(eAuth));
				System.out.println(eAuth);
			};
			Emp empf=new Emp();
			empf.setEmpName(empName);
			empf.setEmpId(empId);
			empf.setEmpJob(empJob);
			empf.setEmpHireDate(empHireDate);
			empf.setEmpEmail(empEmail);

			System.out.println("aaaaaaaaaaa");
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher dispatcher = req.getRequestDispatcher("/back_end/emp/empRegister.jsp");
				req.setAttribute("errorMsgs", errorMsgs);
				req.setAttribute("emp", empf);
				dispatcher.forward(req, res);
				return;
			}

			/*************************** 2.�}�l�ק��� *****************************************/
			 
			
			/********************** �K�X�B�z**************************/
			//�üƲ��ͶýX
			Integer pwd=(int) ((Math.random()*10)*10000000+(Math.random()*1000000));
			
			//Email�H�o �N�ק�
			System.out.println(pwd);
			
			//�[�K�s�Jdb �N�ק� 
			String  empPwd =String.valueOf((int)((pwd*3)+67));
			
			Emp emp=new Emp();
			emp.setEmpName(empName);
			emp.setEmpId(empId);
			emp.setEmpJob(empJob);
			emp.setEmpPwd(empPwd);
			emp.setEmpHireDate(empHireDate);
			emp.setEmpEmail(empEmail);
			EmpService empSvc=new EmpService();
			empSvc.addEmpWithAuth(empName, empJob, empId, empPwd, 0, empHireDate, empEmail,empAuthNos );
		
			

			/**************************** 3.�ק粒��,�ǳ����(Send the Success view)*************/
			Emp nemp=empSvc.getEmpById(empId);
			HttpSession session=req.getSession();
			session.setAttribute("emp", nemp);
			session.setAttribute("auth", empAuthNos);
			res.sendRedirect(req.getContextPath()+"/back_end/index_backend.jsp");

			
		
		} catch (Exception e) {
			System.out.println("error");
		}
		
	}

}
