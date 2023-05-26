package jpa.dao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {




        @GetMapping("/hello")
        public String sayHello() {
            return "Elizabeth";
        }

        @GetMapping ("/goodbye")
        public String sayGoodbye(){
            return"goodbye";
        }
    }

