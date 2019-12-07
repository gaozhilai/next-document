package com.gzl.next.document.controller.websocket;

import com.gzl.next.document.pojo.vo.DrawingBoardVO;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/drawing")
    public String pushDrawing(@RequestBody DrawingBoardVO param) throws IOException {
        WebSocketServer.sendInfo(param.getBase64Data(), null);
        return "success";
    }
}
