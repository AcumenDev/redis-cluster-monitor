package com.acumen.redis.cluster.monitor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vstalmakov on 21.09.16.
 */
@RestController
public class IndexController {

    @RequestMapping(value = "/eeeeeeee", method = RequestMethod.GET)
    public Object index() {
        return "redirect:/webapp/index.htm";
    }



}
