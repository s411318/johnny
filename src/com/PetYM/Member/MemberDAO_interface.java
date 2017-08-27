package com.PetYM.Member;

import java.util.List;
import com.PetYM.Pet.Pet;

public interface MemberDAO_interface {
	void add(Member member);
	void addWithPet(Member member,Pet pet);
	void update(Member member);
	void delete(Integer memno);
	Member findByPk(Integer memno);
	List<Pet> findPetsByMemNo(Integer memno);
	List<Member> getAll();
	Member findById(String memId);
	
}
