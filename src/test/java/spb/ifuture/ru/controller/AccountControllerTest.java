package spb.ifuture.ru.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import spb.ifuture.ru.TestDataProvider;
import spb.ifuture.ru.persistance.Account;
import spb.ifuture.ru.repository.AccountRepository;
import spb.ifuture.ru.service.AccountService;
import spb.ifuture.ru.service.impl.AccountServiceImpl;
import spb.ifuture.ru.util.AccountStatistic;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {
    private AccountService accountService;
    private AccountController accountController;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    public void init(){
        AccountStatistic accountStatistic = new AccountStatistic();

        accountService = new AccountServiceImpl(accountStatistic, accountRepository);
        accountController = new AccountController(accountService);
    }

    @Test
    void retrieveBalanceByAccountId() {
        final Account accountStub = TestDataProvider.getAccount1Stub();

        Mockito.when(accountRepository.findById(10)).thenReturn(Optional.of(accountStub));

        Assertions.assertEquals(100L, accountController.retrieveBalanceByAccountId(10));
    }

    @Test
    void updateBalanceByAccountId() {
        final Account accountStub = TestDataProvider.getAccount1Stub();

        Mockito.when(accountRepository.findById(10)).thenReturn(Optional.of(accountStub));
        accountService.addAmount(10, 100L);

        Assertions.assertNotEquals(accountStub, accountService.getAmount(10));
    }
}