package com.maeinghome.thread.contorller;

import com.maeinghome.util.api.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
    @GetMapping("task/1")
    public R taskOne(String param) {
        return R.success("");
    }
}
