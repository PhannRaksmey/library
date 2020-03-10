package com.wu.library.services;

import com.wu.library.models.Member;
import com.wu.library.repositories.admin.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public MemberService() {
    }


    public int save(Member member){
        try {
            entityManager.persist(member);
            return 1;
        }catch (Exception e){
            return 0;
        }

}

    public Member findOne(String id){
        return entityManager.find(Member.class,id);
    }
    public List<Member> getAll(){

        return this.memberRepository.getAll();
//        return entityManager.createQuery("select se from Member-Entity se",Member.class).getResultList();
    }
    public int update(Member _member, String _id){
        try {
            return this.memberRepository.update(_id,_member);
        }catch (Exception e){
            return 0;
        }
    }

    public int updatePicture(Member _member, String _id){
        try {
            return this.memberRepository.updatePicture(_id,_member);
        }
        catch (Exception e){
            return 0;
        }
    }

    public int delete (String _id){
        try{
            return this.memberRepository.delete(_id);

        }catch (Exception e){
            return 0;
        }
    }


}
