package org.grsl.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
@Slf4j
public class SSEManageService {

    private Map<String, SseEmitter> sseSession;

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

    public void send(Object data) {
        ArrayList<String> disableConnects = new ArrayList<>();
        this.sseSession.forEach((clientId, sseEmitter) -> {
            try {
                sseEmitter.send(data);
            } catch (Exception e) {
                log.error("client: {} is error.", clientId);
                disableConnects.add(clientId);
            }
        });
        disableConnects.forEach((elem) -> this.sseSession.remove(elem));
    }

    public boolean isSessionEmpty() {
        return this.sseSession.isEmpty();
    }
}
