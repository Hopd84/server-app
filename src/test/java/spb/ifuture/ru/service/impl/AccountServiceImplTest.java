package spb.ifuture.ru.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import spb.ifuture.ru.TestDataProvider;
import spb.ifuture.ru.exceprion.ServiceException;
import spb.ifuture.ru.persistance.Account;
import spb.ifuture.ru.repository.AccountRepository;
import spb.ifuture.ru.service.AccountService;
import spb.ifuture.ru.util.AccountStatistic;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    private AccountService accountService;
    private AccountStatistic accountStatistic;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    public void init(){
        accountStatistic = new AccountStatistic();
        accountService = new AccountServiceImpl(accountStatistic, accountRepository);
    }

    @Test
    void getAmountForCorrectId() {
        final Account accountStub = TestDataProvider.getAccount1Stub();

        Mockito.when(accountRepository.findById(10)).thenReturn(Optional.of(accountStub));

        Assertions.assertEquals(100L, accountService.getAmount(10));
    }

    @Test
    void getAmountForIncorrectId() {
        Mockito.when(accountRepository.findById(-10)).thenReturn(Optional.empty());

        Assertions.assertThrows(ServiceException.class, () -> accountService.getAmount(-10));
    }

    @Test
    void addAmountSimple() {
        final Account accountStub = TestDataProvider.getAccount1Stub();

        Mockito.when(accountRepository.findById(10)).thenReturn(Optional.of(accountStub));

        Assertions.assertDoesNotThrow(() -> accountService.addAmount(10, 100L));
    }

    @Test
    void checkTotalStatisticFromAddAmount() {
        final Account accountStub = TestDataProvider.getAccount1Stub();
        final int startCount = accountStatistic.getTotalOnAdd().get();

        Mockito.when(accountRepository.findById(10)).thenReturn(Optional.of(accountStub));
        accountService.addAmount(10, 100L);

        Assertions.assertNotEquals(startCount, accountStatistic.getTotalOnAdd().get());
    }

    @Test
    void checkStatisticFromAddAmountUntilMethodExecuteTime() {
        final Account accountStub = TestDataProvider.getAccount1Stub();
        final int startCount = accountStatistic.getActiveOnAdd().get();

        Mockito.when(accountRepository.findById(10)).thenReturn(Optional.of(accountStub));
        accountService.addAmount(10, 100L);

        Assertions.assertEquals(startCount, accountStatistic.getActiveOnAdd().get());
    }

    @Test
    void checkTotalStatisticFromGetAmount() {
        final Account accountStub = TestDataProvider.getAccount1Stub();
        final int startCount = accountStatistic.getTotalOnGet().get();

        Mockito.when(accountRepository.findById(10)).thenReturn(Optional.of(accountStub));
        accountService.getAmount(10);

        Assertions.assertNotEquals(startCount, accountStatistic.getTotalOnGet().get());
    }

    @Test
    void checkStatisticFromGetAmountUntilMethodExecuteTime() {
        final Account accountStub = TestDataProvider.getAccount1Stub();
        final int startCount = accountStatistic.getActiveOnGet().get();

        Mockito.when(accountRepository.findById(10)).thenReturn(Optional.of(accountStub));
        accountService.getAmount(10);

        Assertions.assertEquals(startCount, accountStatistic.getActiveOnGet().get());
    }
}