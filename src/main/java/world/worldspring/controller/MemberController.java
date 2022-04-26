package world.worldspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import world.worldspring.domain.Member;
import world.worldspring.service.MemberService;

import java.util.List;

@Controller
public class MemberController {
    // 스프링이라는 컨테이너가 컨트롤러 어노테이션이 있으면 멤버컨트롤러 객체를 생성해서 스프링에 넣어주고 스프링이 관리해준다.
    private MemberService memberService;
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

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
        // return "redirect:/";는 redirect옆에 기재된 주소로 다시 넘어간다.
        // 즉, 여기에선 "/"만 있으므로 home화면으로 넘어가게 설정한 것이다.
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
