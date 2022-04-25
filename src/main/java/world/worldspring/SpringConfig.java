package world.worldspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import world.worldspring.repository.MemberRepository;
import world.worldspring.repository.MemoryMemberRepository;
import world.worldspring.service.MemberService;

@Configuration
public class SpringConfig {

    // 멤버서비스랑 멤버리포지토리를 스프링 빈에 등록해주고 스프링 빈에 등록되어있는 멤버리포지토리를 멤버서비스에 넣어준다.
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
