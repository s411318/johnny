package ��Ʈw���إ�;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dateitem.model.DateItemService;
import com.member.model.MemberService;
import com.pet.model.Pet;
import com.pet.model.PetService;
import com.restaurant.model.RestaurantJDBCDAO;
import com.restaurant.model.RestaurantService;


@WebServlet("/DateItemFakeData2")
public class DateItemFakeData8 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MemberService memSvc=new MemberService();
		
		Map<Integer,List<Integer>> pMember=new HashMap<Integer,List<Integer>>();
		for(int i=5001;i<=5015;i++){
			List<Pet> pets=memSvc.getPetsByMemNo(i);
			List<Integer> list=new ArrayList<Integer>();
			for(Pet pet:pets){
				list.add(pet.getPetNo());
			}
			pMember.put(i, list);
		}
		

		
		RestaurantService restSvc=new RestaurantService ();
		Map<Integer,String> rest=new HashMap<Integer,String>();
		for(int i=7006;i<=7080;i++){
			String loc=restSvc.getOneRest(i).getRestLocate();
			rest.put(i, loc);
		}
		
	
		
		List<String> dText=new ArrayList<String>();
		dText.add("Hey�� �˷R���A�̡A�̪�n���� �n�@�}�l�S����A�̳ܪM�@�ءB�U�ȯ��F�C��۳o���R���I�R�A�N�o�˧ڭ̶}�l�F���n���@�ѱ����C�C���h�A�N���F�o�f���H�n�F�A�ѫG�F�A�ڭ̥i�H�ɱ������A�F�C");
		dText.add("Hi~�̪�Ѯ�S�O�n�A�b�o���n���Ѯ�� �̾A�X��n�B�͡A�p�G�A�@�N���ڤ@�_�Y�y���A�|�O�b�������L���@�ѤF");
		dText.add("���o���A�j�a�n�ڡA�ڳ̪�W�R�ݤ��꦳�H�����A�γ\�ڭ̥i�H��Y����ᤤ�꦳�H���᪺�A");
		dText.add("�A�b�ݧڶܡA�S���N�O�p�A�٤��֨ӦY����ӪB�͡A�ڮa�i�R���d�����b���ۧA�O");
		dText.add("�ڮa���d���̪�ܩt��O�A�o���b���ۧA��A���}");
		dText.add("�ڳ̪�b�Чڮa�����ǲ�Hibernate�A�o�w�g�}�l�|�زպA�פF�A�γ\�ڭ̥i�H��Y�����Java��");
		dText.add("�C�s�B���B�A��N�O�@�D���R�������A���B���զ⪺�p�өб��M�䤤�A�S�p���i�F���R�����ܥ@�ɡA���H�ѫo�u�@���h�ҡA�ߤ����дo�C");
		dText.add("�K�ѡA���R�ۡA�}�۹�񪺪޾�X�X�w�g�i�H���۶ø����~�֡A�b�Ǧ⪺�K�C�W�ͥͦa���ۡB�A��ۡB�A���ӲM�յ�");
		dText.add("���G���F�L���A�L�F�s�A�B�L�F�;��P�٦����A���F�Ʀܤ����ٺU�ͤ@���έ��λ��A����E�ʺ���");
		dText.add("���ۤj�𪺨Ií�A�z�۲D�n�F���ۺ��H�B�K���ƹs�����ջP�d��");
		dText.add("���ۭ���]�ʡ^���s�b�A�����a�Ө�o�ӥ@�ɤW�B�����a�֦��ͩR�B�����a�Q��m�_�o�Ӧa����");
		
		
		List<String> dTitle=new ArrayList<String>();
		dTitle.add("�ڭ��ۭ��ۻ��S�ƧA���N�۫H�F�O");
		dTitle.add("�֨Ӥ@�_���a");
		dTitle.add("Yo this is your best chance");
		dTitle.add("�Ѯ�W�n�� ���Ѥ@�_�Y�Ӷ��a");
		dTitle.add("�ڷQ�p�F�A�A�̴H�N���Ѯ��");
		dTitle.add("�ڤ��򳣤��n�A�u�n�A");
		dTitle.add("�o�e���Ӭ��ڤ�����");
		dTitle.add("�I�����H���A�������H�W");
		dTitle.add("�����ӤH�w�g���O��");
		dTitle.add("�@����[���A�u�R�ìy��");
		
		
		List<java.sql.Timestamp> dateTime=new ArrayList<java.sql.Timestamp>();
		GregorianCalendar  gCalender1=new GregorianCalendar(2017,8,20,9,00,00);
		GregorianCalendar  gCalender2=new GregorianCalendar(2017,8,22,12,30,00);
		GregorianCalendar  gCalender3=new GregorianCalendar(2017,8,25,18,00,00);
		dateTime.add(new java.sql.Timestamp(gCalender1.getTimeInMillis()));
		dateTime.add(new java.sql.Timestamp(gCalender2.getTimeInMillis()));
		dateTime.add(new java.sql.Timestamp(gCalender3.getTimeInMillis()));
		
		
		List<java.sql.Timestamp> meetTime=new ArrayList<java.sql.Timestamp>();
		GregorianCalendar  gCalenderb1=new GregorianCalendar(2017,8,26,9,00,00);
		GregorianCalendar  gCalenderb2=new GregorianCalendar(2017,8,26,12,30,00);
		GregorianCalendar  gCalenderb3=new GregorianCalendar(2017,8,36,18,30,00);
		GregorianCalendar  gCalendera1=new GregorianCalendar(2017,8,28,9,00,00);
		GregorianCalendar  gCalendera2=new GregorianCalendar(2017,8,28,12,30,00);
		GregorianCalendar  gCalendera3=new GregorianCalendar(2017,8,28,18,30,00);
		GregorianCalendar  gCalendern1=new GregorianCalendar(2017,8,30,9,00,00);
		GregorianCalendar  gCalendern2=new GregorianCalendar(2017,8,30,12,30,00);
		GregorianCalendar  gCalendern3=new GregorianCalendar(2017,8,30,18,30,00);		
		meetTime.add(new java.sql.Timestamp(gCalenderb1.getTimeInMillis()));
		meetTime.add(new java.sql.Timestamp(gCalenderb2.getTimeInMillis()));
		meetTime.add(new java.sql.Timestamp(gCalenderb3.getTimeInMillis()));
		meetTime.add(new java.sql.Timestamp(gCalendera1.getTimeInMillis()));
		meetTime.add(new java.sql.Timestamp(gCalendera2.getTimeInMillis()));
		meetTime.add(new java.sql.Timestamp(gCalendera3.getTimeInMillis()));
		meetTime.add(new java.sql.Timestamp(gCalendern1.getTimeInMillis()));
		meetTime.add(new java.sql.Timestamp(gCalendern2.getTimeInMillis()));
		meetTime.add(new java.sql.Timestamp(gCalendern3.getTimeInMillis()));
		
		
		//�����d���Ϥ��d�n ��bmap �קK�b�j��z�@���hdb�d��
		PetService petSvc=new PetService();
		Map<Integer,byte[]> petImg=new HashMap<Integer,byte[]>();
		List<Pet> pets = petSvc.getAll();
		for(Pet pet:pets){
			petImg.put(pet.getPetNo(), pet.getPetImg());
		}
		
		
//		List<String> dDate=new ArrayList<String>();
//		dDate.add("20170820");
//		dDate.add("20170821");
//		dDate.add("20170815");
//
//		List<String> mDate=new ArrayList<String>();
//		mDate.add("20170830");
//		mDate.add("20170825");
//		mDate.add("20170822");
//		
//		List<String> dTime=new ArrayList<String>();
//		dTime.add("20:30:20");
//		dTime.add("12:30:20");
//		dTime.add("09:30:20");
//		
//		
//		List<String> mTime=new ArrayList<String>();
//		mTime.add("20:30:20");
//		mTime.add("12:30:20");
//		mTime.add("09:30:20");
		
		
		DateItemService dateSvc=new DateItemService();
		
		
		Random rand = new Random();
		for(int i=0;i<100;i++){
			Integer pMemberRand=rand.nextInt(15)+5001;
			Integer restRand=rand.nextInt(75)+7006;
			Integer titleRand=rand.nextInt(dTitle.size());
			Integer textRand=rand.nextInt(dText.size());
			Integer dTimeR=rand.nextInt(dateTime.size());
			Integer mTimeR=rand.nextInt(meetTime.size());
			Integer mPeopleR=rand.nextInt(5);
			Boolean[] hasmate=new Boolean[]{true,false};
			Boolean hasMateR=hasmate[rand.nextInt(2)];
			Integer priceR=(rand.nextInt(15)+1)*100;
			Integer statusR=0;
			Integer showR=0;
			Integer viewerR=rand.nextInt(1000);
//			Integer buyerR=rand.nextInt(30)+5001;
			Integer buyerR=5100;
			Boolean[] qrCode=new Boolean[]{true,false};
			Boolean qrCodeR=qrCode[rand.nextInt(2)];
			Integer buyerRepR=0;
			Integer sellerrRepR=0;
			Boolean[] instant=new Boolean[]{true,false};
			Boolean instantR=instant[rand.nextInt(2)];
			//�̷ӥD�H�����d�����ƨ�random
			Integer petNo=pMember.get(pMemberRand).get(rand.nextInt(pMember.get(pMemberRand).size()));
			byte[] dateItemImg= petImg.get(petNo);
			
			
			

//			//�U���o�ӬO�n�Q���|�����|�ӫ~
//			if(i==98){
//				dateSvc.addDateItem(pMemberRand, restRand, "�ڤ��c�굦�|�j", dateItemImg, dText.get(textRand),
//						dateTime.get(dTimeR), meetTime.get(mTimeR), 
//						rest.get(restRand), mPeopleR, hasMateR, priceR, statusR, showR, viewerR, buyerR, 
//						qrCodeR, buyerRepR, sellerrRepR, instantR, petNo);
//				break;
//			}
			
//			//�U���o�ӬO�n�ӶQ�R���_�����|�ӫ~
//			if(i==97){
//				dateSvc.addDateItem(pMemberRand, restRand, dTitle.get(titleRand), dateItemImg, dText.get(textRand),
//						dateTime.get(dTimeR), meetTime.get(mTimeR), 
//						rest.get(restRand), mPeopleR, hasMateR, new Integer(9999), statusR, showR, viewerR, buyerR, 
//						qrCodeR, buyerRepR, sellerrRepR, instantR, petNo);
//				break;
//			}
//			
//			
//			//�U���o�ӬO�n�ӶQ�R���_�����|�ӫ~
//			if(i==96){
//				dateSvc.addDateItem(pMemberRand, restRand, dTitle.get(titleRand), dateItemImg, dText.get(textRand),
//						dateTime.get(dTimeR), meetTime.get(mTimeR), 
//						rest.get(restRand), mPeopleR, hasMateR, new Integer(9999), statusR, showR, viewerR, buyerR, 
//						qrCodeR, buyerRepR, sellerrRepR, instantR, petNo);
//				break;
//			}
//			
		
			dateSvc.addDateItem(pMemberRand, restRand, dTitle.get(titleRand), dateItemImg, dText.get(textRand),
					dateTime.get(dTimeR), meetTime.get(mTimeR), 
					rest.get(restRand), mPeopleR, hasMateR, priceR, statusR, showR, viewerR, buyerR, 
					qrCodeR, buyerRepR, sellerrRepR, instantR, petNo);
			

//			String sql="INSERT INTO DATEITEM values (DATEITEMNO_SQ.NEXTVAL,"+(pMemberRand).toString()+","+restRand+",\'"+dTitle.get(titleRand)+"\'"
//					+",EMPTY_BLOB(),'"+dText.get(textRand)+"',TO_DATE('"+dDate.get(dDateR)+" "+dTime.get(dTimeR)+"\',\'YYYYMMDD HH24:MI:SS'),TO_DATE('"
//					+mDate.get(mDateR)+" "+mTime.get(mTimeR)+"\',\'YYYYMMDD HH24:MI:SS'),'"+rest.get(restRand)+"',"+mPeopleR+","+hasMateR+","+priceR+","
//					+statusR+","+showR+","+viewerR+","+buyerR+","+qrCodeR+","+buyerRepR+","+sellerrRepR+","+instantR+","+petNo+");";
//			System.out.println(sql); 
			
			
			
			
		}
		
		
		
		dateSvc.addDateItem(5014, 7040, "�ڤ��c�굦�|�j", petImg.get(1041), "��˧ڴN�Oı�o�ڶW�j",
				new java.sql.Timestamp(gCalender1.getTimeInMillis()), new java.sql.Timestamp(gCalendern3.getTimeInMillis()), 
				rest.get(7040), 0, false, new Integer(1100), 0, 0, 100, 5100, 
				false, 0, 0, false, 1041);
		
		
		dateSvc.addDateItem(5011, 7040, "��code�֤F�N�Ӹ�i�R���d�����ӷ|�a", petImg.get(1033), "�C�s�B���B�A��N�O�@�D���R�������A���B���զ⪺�p�өб��M�䤤�A�S�p���i�F���R�����ܥ@�ɡA���H�ѫo�u�@���h�ҡA�ߤ����дo�C",
				new java.sql.Timestamp(gCalender1.getTimeInMillis()), new java.sql.Timestamp(gCalendern3.getTimeInMillis()), 
				rest.get(7040), 0, false, new Integer(9999), 0, 0, 100, 5100, 
				false, 0, 0, false, 1033);
		
		
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
