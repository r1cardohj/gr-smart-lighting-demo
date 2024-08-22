package org.grsl.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class SSEManageService {
    private final Map<String, SseEmitter> sseSession;

    public SSEManageService() {
        this.sseSession = new ConcurrentHashMap<>();
    }

    public SseEmitter connect(String clientId) {
        SseEmitter sseEmitter = new SseEmitter(0L);
        sseEmitter.onCompletion(() -> {
            log.info("client: {} connect is over.", clientId);
        });
        sseEmitter.onTimeout(() -> {
            log.info("client: {} connect is time out.", clientId);
        });
        sseEmitter.onError((e) -> {
            log.error("client: {} is error.", clientId);
        });

        this.sseSession.put(clientId, sseEmitter);

        return sseEmitter;
    }

    public void send(String clientId, Object data) {
        SseEmitter sseEmitter = this.sseSession.get(clientId);
        try {
            sseEmitter.send(data);
        } catch (Exception ex){
            log.error("send to client {} error: {}", clientId, ex.toString());
            this.sseSession.remove(clientId);
        }
    }

    public boolean inSession(String clientId) {
        return this.sseSession.containsKey(clientId);
    }
}
