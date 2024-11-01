package poly.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TicketController {

    @GetMapping("/datve/ticket")
    public String trangchu() {
        return "/datve/ticket"; // Trả về template trangchu.html
    }
    

}
