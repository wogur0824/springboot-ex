package world.worldspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import world.worldspring.domain.Member;
import world.worldspring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

// JPA를 사용할려면 주위해야 할 것이 항상 Transation이 있어야 한다.
@Transactional
public class MemberService {

    // 회원 서비스를 만들려면 회원 리포지토리가 꼭 필요하다.
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member) {

        // 메소드 호출 시간 측정
//        long start = System.currentTimeMillis();

//        try {
            validateDuplicateMember(member); // 중복 회원 검증
            // 가입
            memberRepository.save(member);
            return member.getId();
//        } finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - start;
//            System.out.println("join = " + timeMs + "ms");
//        }
    }

    // 같은 이름이 있는 중복 회원X
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
    // == ->

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    public List<Member> findMembers() {
//        long start = System.currentTimeMillis();
//        try {
            return memberRepository.findAll();
//        } finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - start;
//            System.out.println("findMembers  = " + timeMs + "ms");
//        }
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
