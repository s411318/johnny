package com.orderlist.model;

import java.util.List;


public interface OrderList_interface {
	void add(OrderList ol);
	void update(OrderList ol);
	void delete(int olNo);
	OrderList findByPk(int olNo);
	List<OrderList> getAll();
	
	//�P�ɷs�W�q��P�q��M��-------------------------------------------
    public void insert2 (OrderList ordList , java.sql.Connection con);
}
