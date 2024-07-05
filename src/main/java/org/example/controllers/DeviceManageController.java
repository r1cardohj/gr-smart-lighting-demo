package org.example.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/device")
public class DeviceManageController {

    @GetMapping("/")
    public void getDevice() {

    }

    @PostMapping("/add")
    public void addDevice() {

    }

    @PostMapping("/upd")
    public void updDevice() {

    }

    @PostMapping("/del")
    public void delDevice() {

    }

}
