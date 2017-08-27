package com.dateitem.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;

import com.dateitem.model.DateItemService;
import com.dateitem.model.DateItemVO;

import java.util.*;

public class ScheduleServlet extends HttpServlet {
   
    
    Timer timer;
    int i=0;      
   
    public void init() throws ServletException {
      TimerTask task = new TimerTask(){ 
	        public void run() {
//	        	======================�⤻�p�ɤ������|�U�[======================
	        	DateItemService dSvc=new DateItemService();
	        	List<DateItemVO> list = dSvc.getAllItems();
	        	
				for(DateItemVO dateItemVO:list){
					//��C�Ӱӫ~��ثe�n�W�[���ɶ����নlong����O�_�t�Z�|�p��
					Long dateItemMeetingTime = (dateItemVO.getDateMeetingTime()).getTime();
					Long now = System.currentTimeMillis();
					Long fourHour = 60*60*4*1000l ;
					if (dateItemMeetingTime-now<fourHour){
						dateItemVO.setDateItemShow(1);
						//�۰ʤU�[�Y�N������ӫ~
					}
					}    
	        } 
      };
      timer = new Timer(); 
      Calendar cal = new GregorianCalendar(2017, Calendar.AUGUST, 12, 0, 0, 0);
      timer.scheduleAtFixedRate(task, cal.getTime(), 1*60*60*1000);
      System.out.println("�w�إ߱Ƶ{!");       
    }
    

    public void doGet(HttpServletRequest req, HttpServletResponse res)
                               throws ServletException, IOException {
    }                           	

    public void destroy() {
        timer.cancel();
        System.out.println("�w�����Ƶ{!");
    }
    
}