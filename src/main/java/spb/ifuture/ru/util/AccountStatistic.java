package spb.ifuture.ru.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@Getter
@NoArgsConstructor
public class AccountStatistic {
    private final AtomicInteger totalOnGet = new AtomicInteger(0);
    private final AtomicInteger totalOnAdd = new AtomicInteger(0);
    private final AtomicInteger activeOnGet = new AtomicInteger(0);
    private final AtomicInteger activeOnAdd = new AtomicInteger(0);
}
