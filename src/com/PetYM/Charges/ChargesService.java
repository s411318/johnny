package com.PetYM.Charges;

import java.sql.Date;
import java.util.List;

public class ChargesService {
	private Charges_interface dao;

	public ChargesService() {
		dao = new ChargesJDBCDAO();
	}

	public Charges addCharge(Integer chargeNo, Integer memNo, Integer chargeNum, Date applyTime, Integer chargeStatus,
			Integer payWay) {

		Charges charge = new Charges();

		charge.setChargeNo(chargeNo);
		charge.setMemNo(memNo);
		charge.setChargeNum(chargeNum);
		charge.setApplyTime(applyTime);
		charge.setChargeStatus(chargeStatus);
		charge.setPayWay(payWay);
		dao.add(charge);

		return charge;
	}

	
	public Charges updateCharges(Integer chargeNo, Integer memNo, Integer chargeNum, Date applyTime, Integer chargeStatus,
			Integer payWay) {

		Charges charge = new Charges();

		charge.setChargeNo(chargeNo);
		charge.setMemNo(memNo);
		charge.setChargeNum(chargeNum);
		charge.setApplyTime(applyTime);
		charge.setChargeStatus(chargeStatus);
		charge.setPayWay(payWay);
		dao.update(charge);

		return dao.findByPk(chargeNo);
	}
	
	

	public void deleteCharges(Integer chargeNo) {
		dao.delete(chargeNo);
	}

	public Charges getOneCharges(Integer chargeNo) {
		return dao.findByPk(chargeNo);
	}

	public List<Charges> getAll() {
		return dao.getAll();
	}
}
