package com.submem.model;

import java.util.List;


public interface SubMem_Interface {
	
	public void insert(SubMem subMem);//�s�W�l�ܷ|��
	public void update(SubMem subMem);
	public void delete(SubMem subMem);//�R���ӷ|���Ұl�ܪ��Y�@�d��
	public List<SubMem> findByPrimaryKey(Integer actSubMemNo);//�q�X�ӷ|���Ұl�ܩҦ����d��
	public List<SubMem> getAll();
	
}
