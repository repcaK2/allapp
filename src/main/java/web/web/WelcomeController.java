package web.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.List;


@Controller
public class WelcomeController {

    @GetMapping("/allapp")
    public String allapp(){
        return "allapp";
    }

    @GetMapping("/calculator")
    public String form(){
        return "calculator";
    }

    @PostMapping("/answer")
    public String answer(@RequestParam("var1")int var1, @RequestParam("var2")int var2,
                             @RequestParam("operation")String operation, Model model) {
        int result;
        if(operation.equals("add")){
            result = var1 + var2;
        } else if (operation.equals("subtract")) {
            result = var1 - var2;
        } else if (operation.equals("divide")) {
            result = var1 / var2;
        } else{
            result = var1 * var2;
        }
        model.addAttribute("result", result);
        return "answer";
    }
}













