package world.worldspring.repository;

import world.worldspring.domain.Member;

import java.util.List;
import java.util.Optional;

// 회원객체를 저장하는 저장소
public interface MemberRepository {
    Member save(Member member); // 회원을 저장하면 저장된 id가 저장소에 저장되는 기능
    // Optional은 자바 8에 들어간 기능 중에 하나로 findByid나 findByname에 값이 null인 경우에
    // null을 그냥 반환하지 않고 optinal를 감싸서 반환한다.
    Optional<Member> findById(Long id); // 방금 만든 id를 찾는 기능
    Optional<Member> findByName(String name); // 방금 만든 name을 찾는 기능
    List<Member> findAll(); // 지금까지 저장된 모든 리스트를 반환해주는 기능

}
