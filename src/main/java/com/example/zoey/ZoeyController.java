package com.example.zoey;

import com.example.zoey.utils.MessageUtils;
import com.example.zoey.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.example.zoey.ZoeyConstants.HttpRequestType;
import static com.example.zoey.ZoeyConstants.UserStatus;

@Controller
@RequestMapping(path = "/zoey")
public class ZoeyController {

    final ZoeyService zoeyService;
    final MessageUtils messageUtils;

    @Autowired
    public ZoeyController(ZoeyService zoeyService, MessageUtils messageUtils) {
        this.zoeyService = zoeyService;
        this.messageUtils = messageUtils;
    }

    @GetMapping(path = "/enum")
    @ResponseBody
    public ResponseEntity<ResponseDto> enumTest(@RequestBody Map<String, String> params) {

        // UserStatus
        UserStatus myACT = UserStatus.ACT;
		System.out.println(myACT); // ACT
		System.out.println(UserStatus.INACT); // INACT

        // HttpRequestType
        HttpRequestType requestType = HttpRequestType.NEW;
        System.out.println("Request Type: " + requestType); // NEW
        System.out.println("HTTP Method: " + requestType.getMethod()); // POST (corresponding HTTP method)

        return null;
    }

    @GetMapping(path = "/message")
    @ResponseBody
    public ResponseEntity<ResponseDto> message(@RequestBody Map<String, String> params) {
        String message_ko = messageUtils.getMessage("RESULT_MSG_SUCCESS"); // (Request HEADER) Accept-Language : ko (or null)
        System.out.println(message_ko); // 성공
        String message_en = messageUtils.getMessage("RESULT_MSG_SUCCESS", null, Locale.ENGLISH); // Accept-Language : en
        System.out.println(message_en); // SUCCESS
        return ResponseUtils.createGetSuccessResponse(message_ko);
    }

    @GetMapping(path = "/field")
    @ResponseBody
    public ResponseEntity<ResponseDto> getField(@RequestBody Map<String, String> params) {
		List<Map<String, Object>> rtnData = zoeyService.getField(params);
		return ResponseUtils.createGetSuccessResponse(rtnData);
    }

}
