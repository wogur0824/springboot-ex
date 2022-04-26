package world.worldspring.repository;

import world.worldspring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;
    // jpa는 EntityManager로 모든걸 동작한다.
    // 결론: JPA를 사용할려면 EntityManager를 주입받아야 한다.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // persist는 영구저장하다는 뜻
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name).getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
        // JPQL query: 보통은 테이블을 대상으로 sql을 날리지만,
        // JPQL query는 객체를 대상으로 쿼리를 날린다. 그러면 Member가 sql로 번역이 된다.
    }
}
