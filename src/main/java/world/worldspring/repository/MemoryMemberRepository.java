package world.worldspring.repository;

import org.springframework.stereotype.Repository;
import world.worldspring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        // store.get(id)가 null이여도 클라이언트에서 할수 있어서 optional로 감싸서 반환한다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        // 자바 8의 람다식을 적용
        // member에서 member.getname()이 parameter로 넘어온 name이랑 같은지 보고 같은 경우에만 찾고 찾으면 반환한다.
        // 하지만 다 찾았는데도 없으면 optional로 인해 null이 포함되서 반환된다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        // store에 있는 values 즉, member들이 쭉 반환된다.
    }

    public void clearStore() {
        // Test코드에서 하나씩 test하고 끝날때마다 저장소를 다 지워주는 기능
        store.clear();
    }
}
