package com.ptit.mybatis.controller;

import com.ptit.mybatis.utils.ConstantUrl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TblUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String BASE_URL = ConstantUrl.V1_API + "/tbl-user/";

    @Test
    @DisplayName("test api get list users")
    void test_get_list_tbl_users() throws Exception {
        Integer groupId = 1;
        String fullName = "Mai";

        mockMvc.perform(get(BASE_URL + "list")
                        .param("groupId", "1")
                        .param("fullName", fullName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data[0].userId").value(50))
                .andExpect(jsonPath("$.data[0].groupId").value(1))
                .andExpect(jsonPath("$.data[0].email").value("huong@gmail.com"))
                .andExpect(jsonPath("$.data[0].tel").value("123456789"))
                .andExpect(jsonPath("$.data[0].birthday").value("1983-07-08 00:00:00"))
                .andExpect(jsonPath("$.data[0].groupName").value("Phòng phát triển số 1"));
    }
}
