package com.example.studentapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InfoController {

    @Value("${app.student.name}")
    private String studentName;

    @Value("${app.version}")
    private String version;

    @GetMapping("/api/info")
    public Map<String, String> info() {
        return Map.of(
                "student", studentName,
                "application", "zhamash-asylzhan-student-api",
                "version", version
        );
    }
}
