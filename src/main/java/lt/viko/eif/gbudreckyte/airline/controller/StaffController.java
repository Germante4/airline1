package lt.viko.eif.gbudreckyte.airline.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @GetMapping("/panel")
    public String staffPanel() {
        return "STAFF AREA: You have STAFF role!";
    }
}

