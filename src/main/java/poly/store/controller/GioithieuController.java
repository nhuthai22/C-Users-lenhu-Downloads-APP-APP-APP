package poly.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GioithieuController {

    @GetMapping("/gioithieu/gioithieu")
    public String gioithieu() {
        return "gioithieu/gioithieu"; // Trả về template trangchu.html
    }
}
