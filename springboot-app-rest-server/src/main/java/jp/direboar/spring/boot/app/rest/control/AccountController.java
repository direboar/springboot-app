package jp.direboar.spring.boot.app.rest.control;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jp.direboar.spring.boot.app.rest.data.Account;

// validation
// https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/boot-features-validation.html

@RestController
@RequestMapping("/account/v1")
@Validated
public class AccountController {

    @GetMapping
    public Account getAccount(@PathVariable("id") String id) {
        return new Account();
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Account putAccount(@PathVariable("id") String id,
                    @Validated @NotNull @RequestBody Account account) {
        return account;
    }

}
