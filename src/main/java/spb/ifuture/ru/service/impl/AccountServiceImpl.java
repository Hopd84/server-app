package spb.ifuture.ru.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import spb.ifuture.ru.exceprion.ServiceException;
import spb.ifuture.ru.persistance.Account;
import spb.ifuture.ru.repository.AccountRepository;
import spb.ifuture.ru.service.AccountService;
import spb.ifuture.ru.util.AccountStatistic;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountStatistic accountStatistic;
    private final AccountRepository accountRepository;

    @Cacheable(cacheNames = "account")
    @Override
    public Long getAmount(Integer id) {
        accountStatistic.getTotalOnGet().getAndIncrement();
        accountStatistic.getActiveOnGet().getAndIncrement();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new ServiceException("Something went wrong");
        }
        Long result = accountRepository.findById(id)
                                .orElseThrow(() -> new ServiceException("Account with id = " + id + " not found", 404))
                                .getAmount();
        accountStatistic.getActiveOnGet().getAndDecrement();
        return result;
    }

    @Transactional
    @Override
    public void addAmount(Integer id, Long value) {
        accountStatistic.getTotalOnAdd().getAndIncrement();
        accountStatistic.getActiveOnAdd().getAndIncrement();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new ServiceException("Something went wrong");
        }
        Account account = accountRepository.findById(id)
                                .orElseThrow(() -> new ServiceException("Account with id = " + id + " not found", 404));
        account.setAmount(account.getAmount() + value);
        accountRepository.save(account);
        accountStatistic.getActiveOnAdd().getAndDecrement();
    }

    @Scheduled(fixedRate = 1000)
    public void fixedRateSch(){
        log.info("totalOnGet: " + accountStatistic.getTotalOnGet());
        log.info("totalOnAdd: " + accountStatistic.getTotalOnAdd());
        log.info("activeOnGet: " + accountStatistic.getActiveOnGet());
        log.info("activeOnAdd: " + accountStatistic.getActiveOnAdd());
    }
}
