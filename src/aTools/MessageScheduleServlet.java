package aTools;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class MessageScheduleServlet extends HttpServlet {
   
    Timer timer;
    Send se = new Send();
    
    public void init() throws ServletException {
        
    TimerTask task = new TimerTask(){ 
	      public void run() {
	        String[] tel ={"0911016785" , "0953322773"};
 	        String message = "�L�����յL�����յL�����յL�����յL�����յL�����յL�����յL�����յL�����յL�����յL�����յL�����յL�����յL�����յL�����յL�����յL�����յL�����յL�����յL������";
 	        se.sendMessage(tel , message);
	      } 
     };
        
        
    
        timer = new Timer(); 
        Calendar cal = new GregorianCalendar(2111, Calendar.NOVEMBER, 30, 0, 0, 0);
        timer.scheduleAtFixedRate(task, cal.getTime(), 24*60*60*1000);
        
        System.out.println("�w�إ�²�T�o�e�Ƶ{!");       

    }
    

    public void doGet(HttpServletRequest req, HttpServletResponse res)
                               throws ServletException, IOException {
    }                           	

    public void destroy() {
        timer.cancel();
        System.out.println("�w����²�T�o�e�Ƶ{!");
    }
    
}