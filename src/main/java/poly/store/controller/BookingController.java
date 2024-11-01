package poly.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookingController {

    @GetMapping("/booking/trangchu")
    public String trangchu() {
        return "booking/trangchu"; // Trả về template trangchu.html
    }
   
}
