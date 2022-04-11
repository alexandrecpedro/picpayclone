package com.picpayclone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
// Default async execution settings occurs on background
public class AsyncExecutionSettings {

    @Bean(name = "asyncExecutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3); // core pool
        executor.setMaxPoolSize(3); // memory pool
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("AsyncThread-");
        return executor;
    }
}
