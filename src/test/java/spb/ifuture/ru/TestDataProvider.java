package spb.ifuture.ru;

import spb.ifuture.ru.persistance.Account;
import spb.ifuture.ru.util.AccountStatistic;

public class TestDataProvider {
    public static Account getAccount1Stub(){
        return Account.builder()
                      .id(10)
                      .amount(100)
                      .build();
    }

    public static AccountStatistic getAccountStatistic1Stub(){
        AccountStatistic accountStatistic = new AccountStatistic();
        accountStatistic.getTotalOnGet().getAndIncrement();
        accountStatistic.getTotalOnAdd().getAndIncrement();
        accountStatistic.getActiveOnGet().getAndIncrement();
        accountStatistic.getActiveOnAdd().getAndIncrement();

        return accountStatistic;
    }
}
