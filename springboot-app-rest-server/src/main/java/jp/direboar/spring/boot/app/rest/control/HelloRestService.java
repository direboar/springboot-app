package jp.direboar.spring.boot.app.rest.control;

import javax.websocket.server.PathParam;

import jp.direboar.spring.boot.app.rest.data.Account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account/v1")
public class HelloRestService {

    @GetMapping
    public Account getAccount(@PathParam("id") String id) {
        return new Account();
    }

}
