package world.worldspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import world.worldspring.service.MemberService;

@Controller
public class MemberController {
    // 스프링이라는 컨테이너가 컨트롤러 어노테이션이 있으면 멤버컨트롤러 객체를 생성해서 스프링에 넣어주고 스프링이 관리해준다.
    private final MemberService memberService;
    // @Autowired private MemberService memberService;
    // DI의 두번째 방법: 필드 주입(생성자를 뺴고 필드에다가 하는 주입)
    // 필드 주입은 바꿀 수 있는 방법이 없어서 별로 좋은 방법은 아니다.

//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }
    // DI의 세번째 방법: setter 주입(생성은 생성대로 되고 setter는 나중에 호출이 되서 멤버서비스가 들어오게 된다.)
    // 단점: 누군가가 멤버컨트롤러를 호출했을 때 public으로 열려 있어야 한다.
    // 그래서 다른 개발자가 변경을 할수 있어서 나중에 혹시나 바꾸게 되면 문제가 생기게 된다.

    @Autowired
    // DI의 첫번째 방법: 생성자 주입(생성자를 통해 멤버서비스가 멤버컨트롤러에 주입)
    // 멤버컨트롤러가 생성이 될때 스프링 빈에 등록되어있는 멤버서비스 객체를 넣어준다. (의존성 주입:DI)
    // 생성자에 @autowired가 되어있으면 멤버서비스를
    // 스프링이 스프링 컨테이너에 있는 멤버서비스를 가져다 연결시켜준다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
