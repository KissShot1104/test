package test.repository;

import org.springframework.stereotype.Repository;
import test.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    //저장
    public void save(Member member) {
        em.persist(member);
    }

    //UID로 추출
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    //전부 추출
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    //이름으로 추출
    public List<Member> findByNickname(String name) {
        return em.createQuery("select m from Member m where m.nickname = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    //로그인 아이디로 추출
    public List<Member> findByLoginId(String loginId) {
        return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getResultList();
    }

}
