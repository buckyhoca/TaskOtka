package com.example.Task2.Controller;
import com.example.Task2.Service.PageService;
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
public class PageController {

    private static final Logger logger = LoggerFactory.getLogger(PageController.class);

    @Autowired
    PageService pageService;

    @GetMapping("/user-info")
    public List<Map<String, Object>> getUserInfo(){
        logger.info("It is now executing here ");
        return pageService.getUserInfo();
    }
}
