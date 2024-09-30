package com.example.zoey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import static com.example.zoey.ZoeyConstants.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/zoey")
public class ZoeyController {

    final ZoeyService zoeyService;

    @Autowired
    public ZoeyController(ZoeyService zoeyService) {
        this.zoeyService = zoeyService;
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

    @GetMapping(path = "/field")
    @ResponseBody
    public ResponseEntity<ResponseDto> getField(@RequestBody Map<String, String> params) {
		List<Map<String, Object>> rtnData = zoeyService.getField(params);
		return ResponseUtils.createGetSuccessResponse(rtnData);
    }

}
