package spb.ifuture.ru.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spb.ifuture.ru.service.StatisticService;
import spb.ifuture.ru.util.AccountStatistic;

@Component
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final AccountStatistic accountStatistic;

    @Override
    public void cleanStatistic(){
        accountStatistic.getTotalOnGet().set(0);
        accountStatistic.getTotalOnAdd().set(0);
        accountStatistic.getActiveOnGet().set(0);
        accountStatistic.getActiveOnAdd().set(0);
    }
}
