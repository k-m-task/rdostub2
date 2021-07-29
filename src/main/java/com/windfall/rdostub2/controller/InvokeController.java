package com.windfall.rdostub2.controller;

import com.windfall.rdostub2.service.InvokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvokeController {

    @Autowired
    private InvokeService invokeService;

    @PostMapping(path = "/invoke", produces = "application/json")
    public Object invoke(@RequestBody String requestObject) {
        return invokeService.invoke(requestObject);
    }

}
