package com.autolink.webSocketDemo.config;

import org.apache.shiro.SecurityUtils;
import org.apache.tomcat.util.descriptor.web.Constants;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by shileichao on 2018/5/15
 * 连接成功前连接成功后增加额外功能
 */
public class ChatHandshakeInterceptor extends HttpSessionHandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        System.out.println("Before Handshake");

        //使用userName区分WebSocketHandler，以便定向发送消息(使用shiro获取session,或是使用上面的方式)
/*        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            if (session != null) {
                String userName = (String) session.getAttribute("websocket_username");
                if(userName == null){
                    userName = "WEBSOCKET_USERNAME_IS_NULL";
                }
                attributes.put("websocket_username",userName);
            }
        }*/
        String userName = (String) SecurityUtils.getSubject().getSession().getAttribute("websocket_username");
        if (userName == null) {
            userName = "default-system";
        }
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, @Nullable Exception ex) {
        System.out.println("After Handshake");
        super.afterHandshake(request, response, wsHandler, ex);
    }
}
