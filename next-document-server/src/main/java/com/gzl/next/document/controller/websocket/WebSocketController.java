package com.gzl.next.document.controller.websocket;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Author: GaoZhilai
 * Date: 2019/9/30
 * Time: 9:04
 * Description: No Description
 */
@RestController
@RequestMapping("/ws")
public class WebSocketController {
    @GetMapping("/socket/push/{sid}/{message}")
    public String pushMessage(@PathVariable String sid, @PathVariable String message) throws IOException {
        WebSocketServer.sendInfo(message, sid);
        return "success";
    }
}
