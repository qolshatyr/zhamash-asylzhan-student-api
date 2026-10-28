package com.example.studentapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StudentApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void infoContainsStudentName() throws Exception {
        mockMvc.perform(get("/api/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.student").value("Wrong Name"));
    }

    @Test
    void createStudent() throws Exception {
        String body = """
                {"firstName":"Asylzhan","lastName":"Zhamash","email":"asylzhan.zhamash@example.com","groupName":"DevOps-1"}
                """;
        mockMvc.perform(post("/api/students").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.lastName").value("Zhamash"));
    }

    @Test
    void unknownStudentReturns404() throws Exception {
        mockMvc.perform(get("/api/students/999999"))
                .andExpect(status().isNotFound());
    }
}
