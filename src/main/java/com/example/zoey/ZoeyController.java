package com.example.zoey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping(path = "/field")
    @ResponseBody
    public ResponseEntity<ResponseDto> getField(@RequestBody Map<String, String> params) {
		List<Map<String, Object>> rtnData = zoeyService.getField(params);
		return ResponseUtils.createGetSuccessResponse(rtnData);
    }

}
