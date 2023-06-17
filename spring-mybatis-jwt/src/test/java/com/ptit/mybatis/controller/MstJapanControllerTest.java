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
public class MstJapanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String BASE_URL = ConstantUrl.V1_API + "/mst-japan/";

    @Test
    @DisplayName("test api get list mst japan")
    void test_api_get_list_mst_japan() throws Exception {
        mockMvc.perform(get(BASE_URL + "list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data[0].codeLevel").value("N1"))
                .andExpect(jsonPath("$.data[0].nameLevel").value("Trình độ tiếng nhật cấp 1"))
                .andExpect(jsonPath("$.data[1].codeLevel").value("N2"))
                .andExpect(jsonPath("$.data[1].nameLevel").value("Trình độ tiếng nhật cấp 2"))
                .andExpect(jsonPath("$.data[2].codeLevel").value("N3"))
                .andExpect(jsonPath("$.data[2].nameLevel").value("Trình độ tiếng nhật cấp 3"))
                .andExpect(jsonPath("$.data[3].codeLevel").value("N4"))
                .andExpect(jsonPath("$.data[3].nameLevel").value("Trình độ tiếng nhật cấp 4"))
                .andExpect(jsonPath("$.data[4].codeLevel").value("N5"))
                .andExpect(jsonPath("$.data[4].nameLevel").value("Trình độ tiếng nhật cấp 5"));
    }

    @Test
    @DisplayName("test get mst japanese by code level when japanse level exist")
    void test_get_mst_japanese_by_code_level_when_japanse_level_exist() throws Exception {
        mockMvc.perform(get(BASE_URL + "N1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data.codeLevel").value("N1"))
                .andExpect(jsonPath("$.data.nameLevel").value("Trình độ tiếng nhật cấp 1"));
    }

    @Test
    @DisplayName("test get mst japanese by code level when japanse level dose not exist")
    void test_get_mst_japanese_by_code_level_when_japanse_level_dose_not_exist() throws Exception {
        mockMvc.perform(get(BASE_URL + "N9")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("message").value("Japanese level dose not exist !"));
    }
}
