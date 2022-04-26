package world.worldspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import world.worldspring.domain.Member;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // select m from Member m where m.name = ?;
    @Override
    Optional<Member> findByName(String name);
    // C.R.U.D나 findById 등 공통적인 기능들은 JpaRepository 안에 포함되어 있어 기재를 안해도 되지만
    // 공통적이지 않는 엔티디 예를 들면 name같은 것들은 적어줘야 한다.
}
