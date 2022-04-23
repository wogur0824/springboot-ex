package world.worldspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WorldController {

    @GetMapping("world")
    public String World(Model model) {
        model.addAttribute("data", "world!!");
        return "world"; // templates안에 있는 world라는 파일을 찾아서 world로 렌더링해라
    }

    @GetMapping("world-mvc")
    // 웹브라우저에서 요청을 하면 controller에서 viewresolver로 던져서 찾은다음 반환하게 한다.
    public String worldMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "world-template";
    }

    @GetMapping("world-string")
    @ResponseBody // http에서 header부와 body와 놔누어져 있는데 거기에서 body에 직접 넣겠다.
    public String worldString(@RequestParam("name") String name) {
        return "world " + name;
        // template엔진과 차이점은 view가 없고 문자 그대로 올라간다.
        // 이 방식은 거의 쓰이질 않는다.
    }


    // 지금 밑에 있는건 api방식이다. (JSON의 방식)
    // spring도 객체를 반환하고 resonsebody로 표기를 하면 json으로 반환하는게 default로 기본으로 되어있다.
    // 웹브라우저에서 요청을 하면 원래 템플릿엔진이면 controller에서 view로 던지지만 @responsebody 어노테이션이 붙으면 viewresolver가 아닌
    // httpmessageconverter에서 http가 요청한것이 단순 문자이면
    // stringconverter가 동작하고 객체이면 jsoncenverter가 동작해서 http응답에 반환한다.
    // 객체를 json으로 바꿔주는 라이브러리 대표적인 2가지
    // 1. jackson(spring은 jackson이 기본) 2. gson(google에서 만듬)
    @GetMapping("world-api")
    @ResponseBody
    public World worldApi(@RequestParam("name") String name) {
        World world = new World();
        world.setName(name);
        return world;
    }

    static class World {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
