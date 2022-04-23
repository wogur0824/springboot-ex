package world.worldspring.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import world.worldspring.domain.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        // Test는 순서와 관계없이 서로 의존관계가 없이 설계되어야 한다.
        // 그래서 하나의 test가 끝날때마다 공용 데이터가 지워져야 한다.
        // Test가 하나씩 끝날때마다 저장소 안에 있는 데이터를 지워주는 기능
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("world!!!");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // optional에서 값을 꺼낼때는 get()을 이용해서 꺼내기(test에선 괜찮음)
//        1. System.out.println("result = " + (result == member));

//        Assertions.assertEquals(member, result);
        // 2. 저장되었던 member가 find했던 result와 같은지를 비교하는 기능

        assertThat(member).isEqualTo(result);
        // 3. 2번의 방법보다 더 쉽고 간편하게 한 기능
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spirng2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findByAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
