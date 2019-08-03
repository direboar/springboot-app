package jp.direboar.spring.boot.app.rest.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestService {

    @GetMapping("/hello")
    public String hello() {
        return "this is rest server!";
    }

}
