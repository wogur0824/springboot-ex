package world.worldspring.service;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import world.worldspring.domain.Member;

import world.worldspring.repository.MemoryMemberRepository;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    // 1. 각 테스트를 실행하기 전에 memorymemberrepository를 만든다.
    // 2. memorymemberrepository를 memberservice에 넣어주면 같은 memorymemberrepository를 사용한다.
    // DI(Dependency Injection, 의존성 주입): 외부에서 두 객체 간의 관계를 결정해주는 디자인 패턴으로,
    // 인터페이스를 사이에 둬서 클래스 레벨에서는 의존관계가 고정되지 않도록 하고 런타임 시에 관계를 다이나믹하게 주입하여 유연성을 확보하고 결합도를 낮출 수 있게 해준다.

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        // Test는 순서와 관계없이 서로 의존관계가 없이 설계되어야 한다.
        // 그래서 하나의 test가 끝날때마다 공용 데이터가 지워져야 한다.
        // Test가 하나씩 끝날때마다 저장소 안에 있는 데이터를 지워주는 기능
        memberRepository.clearStore();
    }

    /**
     * 회원가입
     */
    @Test
    void 회원가입() {

        // given(상황이 주어져서)
        Member member = new Member();
        member.setName("world");

        // when(이걸 실행 했을때)
        Long saveId = memberService.join(member);

        // then(이 결과가 나와야돼)
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외() {

        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // ==
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        // then
    }

    @Test
    void findMembers() {

    }

    @Test
    void finOne() {

    }
}