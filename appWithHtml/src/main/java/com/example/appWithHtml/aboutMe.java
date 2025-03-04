package com.example.appWithHtml;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class aboutMe {
    @GetMapping("/info")
    public String getPage(){
        return "info";
    }
}
