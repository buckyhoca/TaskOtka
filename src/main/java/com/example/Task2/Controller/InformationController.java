package com.example.Task2.Controller;
import com.example.Task2.Service.InformationService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
public class InformationController {

    private static final Logger logger = LoggerFactory.getLogger(InformationController.class);

    @Autowired
    InformationService informationService;
//Это моя конечная точка для ответа на URL-адрес пользовательской информации.
//This is my end point to response the user-info url.
    @GetMapping("/user-info")
    public List<Map<String, Object>> getUserInfo(){
        logger.info("It is now executing here ");
        return informationService.getUserInfo();
    }
}
