package spb.ifuture.ru.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import spb.ifuture.ru.TestDataProvider;
import spb.ifuture.ru.service.StatisticService;
import spb.ifuture.ru.util.AccountStatistic;

@ExtendWith(MockitoExtension.class)
class StatisticServiceImplTest {

    private AccountStatistic accountStatistic;
    private StatisticService statisticService;

    @BeforeEach
    public void init(){
        accountStatistic = new AccountStatistic();
        statisticService = new StatisticServiceImpl(accountStatistic);
    }

    @Test
    void cleanStatistic() {
        final AccountStatistic accountStatisticStub = TestDataProvider.getAccountStatistic1Stub();

        statisticService.cleanStatistic();

        Assertions.assertNotEquals(accountStatisticStub, accountStatistic);
    }
}