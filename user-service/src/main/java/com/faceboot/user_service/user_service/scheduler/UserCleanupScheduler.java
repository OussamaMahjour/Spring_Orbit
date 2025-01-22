package com.faceboot.user_service.user_service.scheduler;

import com.faceboot.user_service.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCleanupScheduler {

    private final UserService userService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void cleanupSoftDeletedUsers() {
        userService.deleteUsersPermanentlyAfter30Days();
    }
}
