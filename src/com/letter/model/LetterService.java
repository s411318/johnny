package com.letter.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import com.actDetial.model.ActDetial;
import com.activity.model.ActivityService;
import com.dateitem.model.DateItemVO;
import com.diamsg.model.DiaMsg;
import com.lettertype.model.LetterType;
import com.lettertype.model.LetterTypeService;
import com.member.model.Member;
import com.member.model.MemberService;
import com.order.model.Ord;

public class LetterService {
	
	private Letter_Interface dao;

	private static final int APPEAL_NUMBER = 30004;
	private static final int BUY_DATEITEM_SUCCESS_NUMBER = 30001;
	private static final int BUYER_CANCEL_DATE_NUMBER = 30002;
	private static final int REPORT_NUMBER =30003;
	private static final int ADD_ACTIVE_NUMBER = 30005;
	private static final int ACTIVITY_SUCCESS_NUMBER = 30006;
	private static final int ACTIVITY_CANCEL_NUMBER = 30007;
	private static final int SHOP_NUMBER = 30008;
	private static final int DATE_BE_BOUGHT_NUMBER = 30009;
	private static final int BE_CANCEL_BY_BUYER_NUMBER = 30010;
	private static final int SELLER_CANCEL_DATE_NUMBER = 30011;
	private static final int BE_CANCEL_BY_SELLER_NUMBER = 30012;
	
	
	public LetterService(){
		dao = new LetterDAO();
	}

	MemberService memSvc  = new MemberService();
	Member member = null;
	
	
	//-----------�H���Q���|���H------------------
	public Letter addLtrOfRep(DateItemVO dateItem){
		
		LetterType letterType = null;
		LetterTypeService ltySvc = new LetterTypeService();
		letterType = ltySvc.getOneByPrimaryKey(REPORT_NUMBER);
		
		Letter letter = new Letter();
		letter.setLetterTypeNo(letterType.getLetterTypeNo());
		letter.setMemNo(dateItem.getSellerNo());//�Q���|��
		letter.setLetterTime(new Timestamp(System.currentTimeMillis()));
		letter.setLetterState(0);
		letter.setLetterTag(0);
		
		member = memSvc.getOneMember(dateItem.getSellerNo());
		
		String text = null;
		text = member.getMemName()+" �A�n: "+letterType.getLetterTypeText();
		letter.setLetterText(text);
		dao.insert(letter);
		
		return letter;
	}
	//-----------�H���Q�ӶD���H------------------
	public Letter addLtrOfApp(DateItemVO dateItem){
		
		LetterType letterType = null;
		LetterTypeService ltySvc = new LetterTypeService();
		letterType = ltySvc.getOneByPrimaryKey(APPEAL_NUMBER);
		
		Letter letter = new Letter();
		letter.setLetterTypeNo(letterType.getLetterTypeNo());
		letter.setMemNo(dateItem.getSellerNo());//�Q�ӶD��
		letter.setLetterTime(new Timestamp(System.currentTimeMillis()));
		letter.setLetterState(0);
		letter.setLetterTag(0);
		
		member = memSvc.getOneMember(dateItem.getSellerNo());
		
		String text = null;
		text = member.getMemName()+" �A�n: "+letterType.getLetterTypeText();
		letter.setLetterText(text);
		dao.insert(letter);
		
		return letter;
	}
	
	//------------�H���ʶR���|���\���H-------------
	public Letter addLtrOfBuyDateItemSucess(DateItemVO dateItem){
		
		LetterType letterType = null;
		LetterTypeService ltySvc = new LetterTypeService();
		letterType = ltySvc.getOneByPrimaryKey(BUY_DATEITEM_SUCCESS_NUMBER);
		
		Letter letter = new Letter();
		letter.setLetterTypeNo(letterType.getLetterTypeNo());
		letter.setMemNo(dateItem.getBuyerNo());//�ʶR��
		letter.setLetterTime(new Timestamp(System.currentTimeMillis()));
		letter.setLetterState(0);
		letter.setLetterTag(0);
		
		member = memSvc.getOneMember(dateItem.getBuyerNo());
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		String text = null;
		text = member.getMemName()+" �A�n: "+letterType.getLetterTypeText()+sdFormat.format(dateItem.getDateMeetingTime());
		letter.setLetterText(text);
		dao.insert(letter);
		
		return letter;
	}
	
	//------------�H���Q�R���|����a---------------
	public Letter addLtrOfDateBeBought(DateItemVO dateItem){
		
		LetterType letterType = null;
		LetterTypeService ltySvc = new LetterTypeService();
		letterType = ltySvc.getOneByPrimaryKey(DATE_BE_BOUGHT_NUMBER);
		
		Letter letter = new Letter();
		letter.setLetterTypeNo(letterType.getLetterTypeNo());
		letter.setMemNo(dateItem.getSellerNo());//�Q�ʶR��
		letter.setLetterTime(new Timestamp(System.currentTimeMillis()));
		letter.setLetterState(0);
		letter.setLetterTag(0);
		
		member = memSvc.getOneMember(dateItem.getSellerNo());
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		String text = null;
		text = member.getMemName()+" �A�n: "+letterType.getLetterTypeText()+sdFormat.format(dateItem.getDateMeetingTime());
		letter.setLetterText(text);
		dao.insert(letter);
		
		return letter;
	}
	
	//------------�R�a�������|--------------------
	public void addLtrOfBuyerCancelDate(DateItemVO dateItem){
		
		LetterType letterType = null;
		LetterTypeService ltySvc = new LetterTypeService();
		letterType = ltySvc.getOneByPrimaryKey(BUYER_CANCEL_DATE_NUMBER);
		
		Letter letter = new Letter();
		letter.setLetterTypeNo(letterType.getLetterTypeNo());
		letter.setMemNo(dateItem.getBuyerNo());//�R�a
		letter.setLetterTime(new Timestamp(System.currentTimeMillis()));
		letter.setLetterState(0);
		letter.setLetterTag(0);
		
		Member Buyer = memSvc.getOneMember(dateItem.getBuyerNo());

		String text = null;
		text = Buyer.getMemName()+" �A�n: "+letterType.getLetterTypeText();
		letter.setLetterText(text);
		dao.insert(letter);
		
		//---------�H����a�i���L�R�a�w�g�������|---------------------
		LetterType letterType1 = null;
		letterType1= ltySvc.getOneByPrimaryKey(BE_CANCEL_BY_BUYER_NUMBER);
		
		Letter letter1 = new Letter();
		letter1.setLetterTypeNo(letterType.getLetterTypeNo());
		letter1.setMemNo(dateItem.getSellerNo());//��a
		letter1.setLetterTime(new Timestamp(System.currentTimeMillis()));
		letter1.setLetterState(0);
		letter1.setLetterTag(0);
		
		Member seller = memSvc.getOneMember(dateItem.getSellerNo());
		
		String text1 = null;
//		text1 = seller.getMemName()+" �A�n: "+Buyer.getMemName()+letterType1.getLetterTypeText()+" ���s�W�[�G"+"<button id='insertAgain' class='btn btn-success'>���s�W�[</button><input type='hidden' id='itemNo' value="+dateItem.getDateItemNo()+">";
		text1 = seller.getMemName()+" �A�n: "+Buyer.getMemName()+letterType1.getLetterTypeText();
		letter1.setLetterText(text1);
		dao.insert(letter1);
		
		
	}
	//-----------------��a�������|------------------------
	public void addLtrOfSellerCancelDate(DateItemVO dateItem){
		
		LetterType letterType = null;
		LetterTypeService ltySvc = new LetterTypeService();
		letterType = ltySvc.getOneByPrimaryKey(SELLER_CANCEL_DATE_NUMBER);
		
		Letter letter = new Letter();
		letter.setLetterTypeNo(letterType.getLetterTypeNo());
		letter.setMemNo(dateItem.getSellerNo());//��a
		letter.setLetterTime(new Timestamp(System.currentTimeMillis()));
		letter.setLetterState(0);
		letter.setLetterTag(0);
		
		Member Seller = memSvc.getOneMember(dateItem.getSellerNo());
		
		String text = null;
		text = Seller+" �A�n: "+letterType.getLetterTypeText();
		letter.setLetterText(text);
		dao.insert(letter);
		
		//---------�H���R�a�i���L��a�w�g�������|---------------------
		LetterType letterType1 = null;
		letterType1= ltySvc.getOneByPrimaryKey(BE_CANCEL_BY_SELLER_NUMBER);
		
		Letter letter1 = new Letter();
		letter1.setLetterTypeNo(letterType.getLetterTypeNo());
		letter1.setMemNo(dateItem.getBuyerNo());//�R�a
		letter1.setLetterTime(new Timestamp(System.currentTimeMillis()));
		letter1.setLetterState(0);
		letter1.setLetterTag(0);
		
		Member Buyer = memSvc.getOneMember(dateItem.getBuyerNo());
		
		String text1 = null;
		text1 = Buyer+" �A�n: "+Seller+letterType1.getLetterTypeText();
		letter1.setLetterText(text1);
		dao.insert(letter1);
		
		
	}
	
	//-----------�H���[�J���ʪ��H------------------
	public Letter addLtrOfAttendActivity(ActDetial actDetail){
			
		LetterType letterType = null;
		LetterTypeService ltySvc = new LetterTypeService();
		letterType = ltySvc.getOneByPrimaryKey(ADD_ACTIVE_NUMBER);
			
		Letter letter = new Letter();
		letter.setLetterTypeNo(letterType.getLetterTypeNo());
		letter.setMemNo(actDetail.getMemNo());//�ѥ[���ʪ��H
		letter.setLetterTime(new Timestamp(System.currentTimeMillis()));
		letter.setLetterState(0);
		letter.setLetterTag(0);
			
		member = memSvc.getOneMember(actDetail.getMemNo());
		ActivityService actSvc = new ActivityService();
		Date date = actSvc.getOneActivity(actDetail.getActNo()).getActDate();
			
		String text = null;
		text = member.getMemName()+" �A�n: "+letterType.getLetterTypeText()+date.toString();
		letter.setLetterText(text);
		dao.insert(letter);
			
		return letter;
	}
		//-----------�H�����ʽT�w�|��[�J���ʪ��H------------------
	public Letter addLtrOfActivityHeldSuccess(ActDetial actDetail){
			
		LetterType letterType = null;
		LetterTypeService ltySvc = new LetterTypeService();
		letterType = ltySvc.getOneByPrimaryKey(ACTIVITY_SUCCESS_NUMBER);
			
		Letter letter = new Letter();
		letter.setLetterTypeNo(letterType.getLetterTypeNo());
		letter.setMemNo(actDetail.getMemNo());//�ѥ[���ʪ��H
		letter.setLetterTime(new Timestamp(System.currentTimeMillis()));
		letter.setLetterState(0);
		letter.setLetterTag(0);
			
		member = memSvc.getOneMember(actDetail.getMemNo());
		ActivityService actSvc = new ActivityService();
		Date date = actSvc.getOneActivity(actDetail.getActNo()).getActDate();
			
		String text = null;
		text = member.getMemName()+" �A�n: "+letterType.getLetterTypeText()+date.toString();
		letter.setLetterText(text);
		dao.insert(letter);
			
		return letter;
	}
		//-----------�H�����ʨ����[�J���ʪ��H------------------
	public Letter addLtrOfActivityCancel(ActDetial actDetail){
			
		LetterType letterType = null;
		LetterTypeService ltySvc = new LetterTypeService();
		letterType = ltySvc.getOneByPrimaryKey(ACTIVITY_CANCEL_NUMBER);
			
		Letter letter = new Letter();
		letter.setLetterTypeNo(letterType.getLetterTypeNo());
		letter.setMemNo(actDetail.getMemNo());//�ѥ[���ʪ��H
		letter.setLetterTime(new Timestamp(System.currentTimeMillis()));			
		letter.setLetterState(0);
		letter.setLetterTag(0);
			
		member = memSvc.getOneMember(actDetail.getMemNo());
			
		String text = null;
		text = member.getMemName()+" �A�n: "+letterType.getLetterTypeText();
		letter.setLetterText(text);
		dao.insert(letter);
			
		return letter;
	}
	
	//------------�H���ӫ��ʶR�ӫ~���|��-----------------------
	public Letter addLtrOfOrdDelivery(Ord ord){
		
		LetterType letterType = null;
		LetterTypeService ltySvc = new LetterTypeService();
		letterType = ltySvc.getOneByPrimaryKey(SHOP_NUMBER);
		
		Letter letter = new Letter();
		letter.setLetterTypeNo(letterType.getLetterTypeNo());
		letter.setMemNo(ord.getMemNo());//�R�ӫ��ӫ~���H
		letter.setLetterTime(new Timestamp(System.currentTimeMillis()));			
		letter.setLetterState(0);
		letter.setLetterTag(0);
		
		member = memSvc.getOneMember(ord.getMemNo());
		
		String text = null;
		text = member.getMemName()+" �A�n: "+letterType.getLetterTypeText()+ord.getOrdProduct();
		letter.setLetterText(text);
		dao.insert(letter);
		
		return letter;
	}
		
	
	
	public List<Letter> getAll()	{
		return dao.getAll();
	}
	
	public List<Letter> getOneMem(Integer memNo){
		return dao.getOneMemLtrs(memNo);
	}
	
	public List<Letter> getTagLtrs(Integer memNo){
		return dao.getTagLtrs(memNo);
	}
	
	public List<Letter> getNotReadLtrs(Integer memNo){
		return dao.getNotReadLtrs(memNo);
	}
	
	public Letter getOneByPrimary(Integer letterNo){
		return dao.findByPrimaryKey(letterNo);
	}
	
	public Letter update(Integer letterNo,Integer letterTypeNo, Integer memNo, Timestamp letterTime, Integer letterState,Integer letterTag,String letterText){
		
		Letter letter = new Letter();
		letter.setLetterNo(letterNo);
		letter.setLetterState(letterState);
		letter.setLetterTag(letterTag);
		letter.setLetterText(letterText);
		letter.setLetterTime(letterTime);
		letter.setLetterTypeNo(letterTypeNo);
		letter.setMemNo(memNo);
		dao.update(letter);
		
		return letter;
	}
}
