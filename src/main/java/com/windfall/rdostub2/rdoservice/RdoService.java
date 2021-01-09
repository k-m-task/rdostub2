package com.windfall.rdostub2.rdoservice;

import org.springframework.stereotype.Service;

@Service
public class RdoService {
    public void say(String s) {
        System.out.println(s);
    }
}
