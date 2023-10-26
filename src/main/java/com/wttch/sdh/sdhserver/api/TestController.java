package com.wttch.sdh.sdhserver.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("test")
public class TestController {


    @GetMapping("index/{name}")
    public Test test(@PathVariable("name") String name) {
        return new Test(name, 200);
    }
}

@Data
@AllArgsConstructor
class  Test {
    String name;
    Integer age;
}
