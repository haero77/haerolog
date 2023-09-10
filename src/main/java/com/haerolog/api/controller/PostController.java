package com.haerolog.api.controller;

import com.haerolog.api.request.PostCreateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
public class PostController {

    @PostMapping("/posts")
    public Map<String, String> post(
            @RequestBody @Valid PostCreateRequest params
    ) {
        return Map.of();
    }

}