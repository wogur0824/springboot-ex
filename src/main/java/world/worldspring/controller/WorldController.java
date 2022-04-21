package world.worldspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorldController {

    @GetMapping("world")
    public String World(Model model) {
        model.addAttribute("data", "world!!");
        return "world"; // templates안에 있는 world라는 파일을 찾아서 world로 렌더링해라
    }
}
