package com.oneuse.dainbow.accounter.dainbowaccounter.process;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.UUID;

@RestController
public class TempController {

    @GetMapping("/test")
    public String test() {
        return String.format("%s: %s", ZonedDateTime.now(), UUID.randomUUID().toString());
    }
}
