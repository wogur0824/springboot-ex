package world.worldspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import world.worldspring.aop.TimeTraceAop;
import world.worldspring.repository.JdbcTemplateMemberRepository;
import world.worldspring.repository.JpaMemberRepository;
import world.worldspring.repository.MemberRepository;
import world.worldspring.repository.MemoryMemberRepository;
import world.worldspring.service.MemberService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.lang.management.ThreadInfo;

@Configuration
public class SpringConfig {

    // JDBC
//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    // JPA
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    // Spring Data JPA
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 멤버서비스랑 멤버리포지토리를 스프링 빈에 등록해주고 스프링 빈에 등록되어있는 멤버리포지토리를 멤버서비스에 넣어준다.
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    // AOP를 스프링 빈에 등록할려면 그냥 @Coponent 어노테이션을 직접 적어줘도 되지만
    // 이렇게 SpringConfig에 @Bean 어노테이션을 적어 추가해줘도 된다.
//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }

//    @Bean
//    public MemberRepository memberRepository() {
//      return new MemoryMemberRepository();
//      return new JdbcTemplateMemberRepository(dataSource);
//      return new JpaMemberRepository(em);
//    }
}
