package org.grsl.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;



@Service
@Slf4j
public class SSEManageService {

    private SseEmitter sseEmitter;


    public SseEmitter connect(String clientId) {
        if (this.inSession())
            return sseEmitter;

        sseEmitter = new SseEmitter(0L);
        sseEmitter.onCompletion(() -> {
            log.info("client: {} connect is over.", clientId);
        });
        sseEmitter.onTimeout(() -> {
            log.info("client: {} connect is time out.", clientId);
        });
        sseEmitter.onError((e) -> {
            log.error("client: {} is error.", clientId);
        });
        return sseEmitter;
    }

    public void send(Object data) {
        try {
            sseEmitter.send(data);
        } catch (Exception ex){
            log.error("send to client error: {}", ex.toString());
            sseEmitter = null;
        }
    }

    public boolean inSession() {
        return this.sseEmitter != null;
    }
}
