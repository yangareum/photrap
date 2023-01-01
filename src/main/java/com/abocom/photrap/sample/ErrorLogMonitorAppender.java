package com.abocom.photrap.sample;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.springframework.stereotype.Component;

@Component
public class ErrorLogMonitorAppender extends AppenderBase<ILoggingEvent> {

    @Override
    protected void append(ILoggingEvent event) {
        if (event != null
                && event.getMessage() != null
                && Level.ERROR.equals(event.getLevel())
        )
        {
            //여기서 관리자에게 메일을 보내거나 특정 처리하는 코드를 작성
        }
    }
}
