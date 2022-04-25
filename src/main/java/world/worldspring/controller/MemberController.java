package world.worldspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import world.worldspring.service.MemberService;

@Controller
public class MemberController {
    // 스프링이라는 컨테이너가 컨트롤러 어노테이션이 있으면 멤버컨트롤러 객체를 생성해서 스프링에 넣어주고 스프링이 관리해준다.
    private final MemberService memberService;

    @Autowired // 멤버컨트롤러가 생성이 될때 스프링 빈에 등록되어있는 멤버서비스 객체를 넣어준다. (의존성 주입:DI)
    // 생성자에 @autowired가 되어있으면 멤버서비스를
    // 스프링이 스프링 컨테이너에 있는 멤버서비스를 가져다 연결시켜준다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
