package library;

import library.service.LoanService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DailyTaskScheduler {
    private final LoanService loanService;

    public DailyTaskScheduler(LoanService loanService) {
        this.loanService = loanService;
    }

    public void scheduleDueNotifications() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable task = loanService::checkForDueDates;
        long initialDelay = calculateInitialDelay();
        scheduler.scheduleAtFixedRate(task, initialDelay, 24, TimeUnit.HOURS);
    }

    private static long calculateInitialDelay() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextMidnight = now.toLocalDate().plusDays(1).atStartOfDay();
        return java.time.Duration.between(now, nextMidnight).toHours();
    }
}
