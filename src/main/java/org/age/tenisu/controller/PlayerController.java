package org.age.tenisu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

        @GetMapping("/player")
        public String getString() {
            return "hello player";
        }

}
