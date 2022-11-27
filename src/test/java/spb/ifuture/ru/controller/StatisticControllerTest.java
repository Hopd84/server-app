package spb.ifuture.ru.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import spb.ifuture.ru.TestDataProvider;
import spb.ifuture.ru.service.StatisticService;
import spb.ifuture.ru.service.impl.StatisticServiceImpl;
import spb.ifuture.ru.util.AccountStatistic;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StatisticControllerTest {
    private AccountStatistic accountStatistic;
    private StatisticService statisticService;
    private StatisticController statisticController;

    @BeforeEach
    public void init(){
        accountStatistic = new AccountStatistic();
        statisticService = new StatisticServiceImpl(accountStatistic);
        statisticController = new StatisticController(statisticService);
    }

    @Test
    void cleanStatistic() {
        final AccountStatistic accountStatisticStub = TestDataProvider.getAccountStatistic1Stub();

        statisticController.cleanStatistic();

        Assertions.assertNotEquals(accountStatisticStub, accountStatistic);
    }
}