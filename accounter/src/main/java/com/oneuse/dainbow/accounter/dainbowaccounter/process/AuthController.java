package com.oneuse.dainbow.accounter.dainbowaccounter.process;

import com.google.common.collect.ImmutableMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
public class AuthController {

    @RequestMapping({"/user", "/me"})
    public Map<String, String> user(Principal principal) {
        return ImmutableMap.of("name", principal.getName());
    }
}
