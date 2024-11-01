package poly.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LienheController {

    @GetMapping("/lienhe/lienhe")
    public String lienhe() {
        return "lienhe/lienhe"; // Trả về template trangchu.html
    }
}
