package spb.ifuture.ru.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spb.ifuture.ru.service.StatisticService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/statistics")
public class StatisticController {
    private final StatisticService statisticService;

    @GetMapping("/clean")
    public void cleanStatistic(){
        statisticService.cleanStatistic();
    }
}
