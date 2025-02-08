package library;

import library.service.LoanService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DailyTaskScheduler {
    private final LoanService loanService;
    private LocalDate date = LocalDate.now();
    private LocalDate targetDate = date.plusDays(12);

    public DailyTaskScheduler(LoanService loanService) {
        this.loanService = loanService;
    }

    public void scheduleDueNotifications() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable task = ()-> {
            if (!date.isAfter(targetDate)) {
                loanService.checkForDueDates(date);
                date = date.plusDays(1);
            } else {
                scheduler.shutdown();
                System.out.println("Task completed. No more due date checks.");
            }
        };
        long initialDelay = calculateInitialDelay();
        scheduler.scheduleAtFixedRate(task, initialDelay, 10, TimeUnit.SECONDS);
    }

    private static long calculateInitialDelay() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextMidnight = now.toLocalDate().plusDays(1).atStartOfDay();
        return java.time.Duration.between(now, nextMidnight).toHours();
    }
}
