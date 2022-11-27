package spb.ifuture.ru.controller;

import spb.ifuture.ru.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{id}")
    public Long retrieveBalanceByAccountId(@PathVariable("id") Integer id){
        return accountService.getAmount(id);
    }

    @PutMapping("/{id}")
    public void updateBalanceByAccountId(@PathVariable("id") Integer id, @RequestParam Long value){
        accountService.addAmount(id, value);
    }
}
