package com.ptit.mybatis.controller;

import com.ptit.mybatis.utils.ConstantUrl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MstGroupControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private final String BASE_URL = ConstantUrl.V1_API + "/mst-group/";

    @Test
    @DisplayName("test api get list mst group")
    void test_api_get_list_mst_group() throws Exception {
        mockMvc.perform(get(BASE_URL + "list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data[0].groupId").value("1"))
                .andExpect(jsonPath("$.data[0].groupName").value("Phòng phát triển số 1"))
                .andExpect(jsonPath("$.data[1].groupId").value("2"))
                .andExpect(jsonPath("$.data[1].groupName").value("Phòng phát triển số 2"))
                .andExpect(jsonPath("$.data[2].groupId").value("3"))
                .andExpect(jsonPath("$.data[2].groupName").value("Phòng phát triển số 3"))
                .andExpect(jsonPath("$.data[3].groupId").value("4"))
                .andExpect(jsonPath("$.data[3].groupName").value("Phòng phát triển số 4"))
                .andExpect(jsonPath("$.data[4].groupId").value("5"))
                .andExpect(jsonPath("$.data[4].groupName").value("Phòng phát triển số 5"))
        ;
    }

    @Test
    @DisplayName("test api get mst_group by groupid when groupId equal 1")
    void test_api_get_mst_group_by_groupid_when_groupId_equal_1() throws Exception {
        String groupId = "1";
        mockMvc.perform(get(BASE_URL + groupId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data.groupId").value("1"))
                .andExpect(jsonPath("$.data.groupName").value("Phòng phát triển số 1"));
    }

    @Test
    @DisplayName("test api get mst_group by groupid when groupId is characters")
    void test_api_get_mst_group_by_groupid_when_groupId_is_characters() throws Exception {
        String groupId = "aaaa";
        mockMvc.perform(get(BASE_URL + groupId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.message").value("Department does not exist !"));
    }

    @Test
    @DisplayName("test api get mst_group by groupid when groupId dose not exist")
    void test_api_get_mst_group_by_groupid_when_groupId_dose_not_exist() throws Exception {
        String groupId = "20";
        mockMvc.perform(get(BASE_URL + groupId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.message").value("Department does not exist !"));
    }
}
