package com.autolink.webSocketDemo.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * Created by shileichao on 2018/5/15.
 */
@ServerEndpoint(value="/demo")
@Component
@Slf4j
public class WebSocketController {


    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    static Map<String, Object> sessionMap = new Hashtable<String, Object>();

    /**
     * 广播给所有人
     *
     * @param message
     **/
    public static void broadcastAll(String message) {
        Set<Map.Entry<String, Object>> set = sessionMap.entrySet();

        Set<String> keySet = sessionMap.keySet();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    }


    /**
     * 连接建立成功调用的方法
     */
    @OnOpen

    public void onOpen(Session session) {
        sessionMap.put(session.getId(), session);
        log.info("connect to webSocket success....");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        try {
            this.session = session;
            log.info("sessionId:" + session.getId() + " connect to webSocket close");
            sessionMap.remove(session.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.info("sessionId为:" + session.getId() + "connect to webSocket error");
        sessionMap.remove(session.getId());
        error.printStackTrace();
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        this.session = session;
        log.info("receive message from "+session.getId()+"["+message+"]");

    }
}
