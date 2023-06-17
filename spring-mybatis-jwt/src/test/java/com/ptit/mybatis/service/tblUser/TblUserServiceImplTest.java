package com.ptit.mybatis.service.tblUser;

import com.ptit.mybatis.dto.request.CreateTblUserRequest;
import com.ptit.mybatis.dto.request.UpdateTblUserRequest;
import com.ptit.mybatis.dto.response.MstGroupResponse;
import com.ptit.mybatis.dto.response.MstJapanseResponse;
import com.ptit.mybatis.dto.response.TblUserInforResponse;
import com.ptit.mybatis.entity.TblDetailUserJapan;
import com.ptit.mybatis.entity.TblUser;
import com.ptit.mybatis.exception.BusinessException;
import com.ptit.mybatis.repository.MstGroupRepository;
import com.ptit.mybatis.repository.MstJapanseRepository;
import com.ptit.mybatis.repository.TblDetailUserJapanRepository;
import com.ptit.mybatis.repository.TblUserInforRepository;
import com.ptit.mybatis.utils.BaseResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TblUserServiceImplTest {

    @InjectMocks
    private TblUserServiceImpl tblUserServiceTest;

    @Mock
    private TblUserInforRepository tblUserInforRepository;

    @Mock
    private MstGroupRepository mstGroupRepository;

    @Mock
    private MstJapanseRepository mstJapanseRepository;

    @Mock
    private TblDetailUserJapanRepository tblDetailUserJapanRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void test_get_list_user() {
        Integer groupId = 1;
        String fullName = "Tung";
        TblUserInforResponse response = new TblUserInforResponse();
        response.setGroupId(1);
        response.setFullName("Nguyen Dac Tung");
        response.setCodeLevel("N1");
        response.setBirthday("10-11-1997 00:00:00");
        response.setTotal(100);
        response.setEmail("ndactung@gmail.com");
        List<TblUserInforResponse> tblUserInforResponses = new ArrayList<>();
        tblUserInforResponses.add(response);
        Mockito.when(tblUserInforRepository.getListlUsers(ArgumentMatchers.eq(groupId), ArgumentMatchers.eq(fullName), ArgumentMatchers.any(Pageable.class)))
                .thenReturn(tblUserInforResponses);
        //run test
        List<TblUserInforResponse> responseList = tblUserServiceTest.getListlUsers(groupId, fullName, PageRequest.of(1, 2));
        //verify
        Assert.notNull(responseList);
        Assertions.assertEquals(1, responseList.get(0).getGroupId());
        Assertions.assertEquals("Nguyen Dac Tung", responseList.get(0).getFullName());
        Assertions.assertEquals("N1", responseList.get(0).getCodeLevel());
        Assertions.assertEquals("10-11-1997 00:00:00", responseList.get(0).getBirthday());
        Assertions.assertEquals(100, responseList.get(0).getTotal());
        Assertions.assertEquals("ndactung@gmail.com", responseList.get(0).getEmail());
    }

    @Test
    @DisplayName("test delete tbl user when id equals 0")
    void test_delete_tbl_user_when_id_equals_0() {
        Integer id = 0;

        BaseResponse baseResponse = tblUserServiceTest.deleteTblUser(id);

        //verify
        Assert.notNull(baseResponse);
        Assertions.assertEquals("User does not exist", baseResponse.getMessage());
    }

    @Test
    @DisplayName("test delete tbl user dose not exist")
    void test_delete_tbl_user_when_user_dose_not_exist() {
        Integer id = 100;

        BaseResponse baseResponse = tblUserServiceTest.deleteTblUser(id);

        //verify
        Assert.notNull(baseResponse);
        Assertions.assertEquals("User does not exist", baseResponse.getMessage());
    }

    @Test
    @DisplayName("test delete tbl user dose exist")
    void test_delete_tbl_user_when_user_dose_exist() {
        Integer id = 1;

        Mockito.when(tblUserInforRepository.findTblUserByUserId(id)).thenReturn(new TblUser());
        BaseResponse baseResponse = tblUserServiceTest.deleteTblUser(id);

        //verify
        Assert.notNull(baseResponse);
        Assertions.assertEquals("Delete success", baseResponse.getMessage());
    }

    @Test
    @DisplayName("test update tbl user when user dose not exist")
    void test_update_tbl_user_when_user_dose_not_exist() {
        UpdateTblUserRequest updateTblUserRequest = new UpdateTblUserRequest();
        updateTblUserRequest.setUserId(1);

        Mockito.when(tblUserInforRepository.findTblUserByUserId(updateTblUserRequest.getUserId()))
                .thenReturn(null);

        //run test
        BaseResponse response = tblUserServiceTest.updateTblUser(updateTblUserRequest);
        //verify
        Assert.notNull(response);
        Assertions.assertEquals("TblUser dont exits !", response.getMessage());
    }

    @Test
    @DisplayName("test update tbl user when email exist")
    void test_update_tbl_user_when_email_exist() {
        UpdateTblUserRequest updateTblUserRequest = new UpdateTblUserRequest();
        updateTblUserRequest.setUserId(1);
        updateTblUserRequest.setEmail("ndactung1011@gmail.com");

        TblUser tblUser = new TblUser();
        tblUser.setUserId(1);

        TblUser tblUser1 = new TblUser();
        tblUser1.setUserId(2);
        Mockito.when(tblUserInforRepository.findTblUserByUserId(updateTblUserRequest.getUserId()))
                .thenReturn(tblUser);

        Mockito.when(tblUserInforRepository.findTblUserByEmail(updateTblUserRequest.getEmail()))
                .thenReturn(tblUser1);

        //run test
        BaseResponse response = tblUserServiceTest.updateTblUser(updateTblUserRequest);
        //verify
        Assert.notNull(response);
        Assertions.assertEquals("Email already exists !", response.getMessage());
    }

    @Test
    @DisplayName("test update tbl user when group dose not exist")
    void test_update_tbl_user_when_group_dose_not_exist() {
        UpdateTblUserRequest updateTblUserRequest = new UpdateTblUserRequest();
        updateTblUserRequest.setUserId(1);
        updateTblUserRequest.setEmail("ndactung1011@gmail.com");
        updateTblUserRequest.setGroupId(1);

        TblUser tblUser = new TblUser();
        tblUser.setUserId(1);

        TblUser tblUser1 = new TblUser();
        tblUser1.setUserId(1);
        Mockito.when(tblUserInforRepository.findTblUserByUserId(updateTblUserRequest.getUserId()))
                .thenReturn(tblUser);

        Mockito.when(tblUserInforRepository.findTblUserByEmail(updateTblUserRequest.getEmail()))
                .thenReturn(tblUser1);

        Mockito.when(mstGroupRepository.getMstGroupByGroupId(updateTblUserRequest.getGroupId()))
                .thenReturn(null);

        //run test
        BaseResponse response = tblUserServiceTest.updateTblUser(updateTblUserRequest);
        //verify
        Assert.notNull(response);
        Assertions.assertEquals("Department does not exist !", response.getMessage());
    }

    @Test
    @DisplayName("test update tbl user success")
    void test_update_tbl_user_success() {
        UpdateTblUserRequest updateTblUserRequest = new UpdateTblUserRequest();
        updateTblUserRequest.setUserId(1);
        updateTblUserRequest.setEmail("ndactung1011@gmail.com");
        updateTblUserRequest.setGroupId(1);

        TblUser tblUser = new TblUser();
        tblUser.setUserId(1);

        TblUser tblUser1 = new TblUser();
        tblUser1.setUserId(1);
        Mockito.when(tblUserInforRepository.findTblUserByUserId(updateTblUserRequest.getUserId()))
                .thenReturn(tblUser);

        Mockito.when(tblUserInforRepository.findTblUserByEmail(updateTblUserRequest.getEmail()))
                .thenReturn(tblUser1);

        Mockito.when(mstGroupRepository.getMstGroupByGroupId(updateTblUserRequest.getGroupId()))
                .thenReturn(new MstGroupResponse());

        TblUser tblUser2 = new TblUser();
        tblUser2.setUserId(1);
        tblUser2.setEmail("ndactung1011@gmail.com");
        tblUser2.setGroupId(1);
        Mockito.when(modelMapper.map(updateTblUserRequest, TblUser.class))
                .thenReturn(tblUser2);

        Mockito.when(tblUserInforRepository.updateTblUser(tblUser2)).thenReturn(1);
        //run test
        BaseResponse response = tblUserServiceTest.updateTblUser(updateTblUserRequest);
        //verify
        Assert.notNull(response);
        Assertions.assertEquals("Update success", response.getMessage());
    }

    @Test
    @DisplayName("test update tbl user fail")
    void test_update_tbl_user_fail() {
        UpdateTblUserRequest updateTblUserRequest = new UpdateTblUserRequest();
        updateTblUserRequest.setUserId(1);
        updateTblUserRequest.setEmail("ndactung1011@gmail.com");
        updateTblUserRequest.setGroupId(1);

        TblUser tblUser = new TblUser();
        tblUser.setUserId(1);

        TblUser tblUser1 = new TblUser();
        tblUser1.setUserId(1);
        Mockito.when(tblUserInforRepository.findTblUserByUserId(updateTblUserRequest.getUserId()))
                .thenReturn(tblUser);

        Mockito.when(tblUserInforRepository.findTblUserByEmail(updateTblUserRequest.getEmail()))
                .thenReturn(tblUser1);

        Mockito.when(mstGroupRepository.getMstGroupByGroupId(updateTblUserRequest.getGroupId()))
                .thenReturn(new MstGroupResponse());

        TblUser tblUser2 = new TblUser();
        tblUser2.setUserId(1);
        tblUser2.setEmail("ndactung1011@gmail.com");
        tblUser2.setGroupId(1);
        Mockito.when(modelMapper.map(updateTblUserRequest, TblUser.class))
                .thenReturn(tblUser2);

        Mockito.when(tblUserInforRepository.updateTblUser(tblUser2)).thenReturn(0);
        //run test
        BaseResponse response = tblUserServiceTest.updateTblUser(updateTblUserRequest);
        //verify
        Assert.notNull(response);
        Assertions.assertEquals("Update Fail", response.getMessage());
    }

    @Test
    @DisplayName("test update tbl user success insert tbl detail user")
    void test_update_tbl_user_success_2() {
        UpdateTblUserRequest updateTblUserRequest = new UpdateTblUserRequest();
        updateTblUserRequest.setUserId(1);
        updateTblUserRequest.setEmail("ndactung1011@gmail.com");
        updateTblUserRequest.setGroupId(1);
        updateTblUserRequest.setCodeLevel("N1");

        TblUser tblUser = new TblUser();
        tblUser.setUserId(1);

        TblUser tblUser1 = new TblUser();
        tblUser1.setUserId(1);
        Mockito.when(tblUserInforRepository.findTblUserByUserId(updateTblUserRequest.getUserId()))
                .thenReturn(tblUser);

        Mockito.when(tblUserInforRepository.findTblUserByEmail(updateTblUserRequest.getEmail()))
                .thenReturn(tblUser1);

        Mockito.when(mstGroupRepository.getMstGroupByGroupId(updateTblUserRequest.getGroupId()))
                .thenReturn(new MstGroupResponse());

        Mockito.when(tblDetailUserJapanRepository.findTblDetailUserJapanByUserId(updateTblUserRequest.getUserId()))
                .thenReturn(null);

        Mockito.when(mstJapanseRepository.getMstJapaneseByCodeLevel(updateTblUserRequest.getCodeLevel()))
                .thenReturn(new MstJapanseResponse());

        TblUser tblUser2 = new TblUser();
        tblUser2.setUserId(1);
        tblUser2.setEmail("ndactung1011@gmail.com");
        tblUser2.setGroupId(1);
        Mockito.when(modelMapper.map(updateTblUserRequest, TblUser.class))
                .thenReturn(tblUser2);

        Mockito.when(tblUserInforRepository.updateTblUser(tblUser2)).thenReturn(1);
        //run test
        BaseResponse response = tblUserServiceTest.updateTblUser(updateTblUserRequest);
        //verify
        Assert.notNull(response);
        Assertions.assertEquals("Update success", response.getMessage());
    }

    @Test
    @DisplayName("test update tbl user success update tbl detail user")
    void test_update_tbl_user_success_3() {
        UpdateTblUserRequest updateTblUserRequest = new UpdateTblUserRequest();
        updateTblUserRequest.setUserId(1);
        updateTblUserRequest.setEmail("ndactung1011@gmail.com");
        updateTblUserRequest.setGroupId(1);
        updateTblUserRequest.setCodeLevel("N1");

        TblUser tblUser = new TblUser();
        tblUser.setUserId(1);

        TblUser tblUser1 = new TblUser();
        tblUser1.setUserId(1);
        Mockito.when(tblUserInforRepository.findTblUserByUserId(updateTblUserRequest.getUserId()))
                .thenReturn(tblUser);

        Mockito.when(tblUserInforRepository.findTblUserByEmail(updateTblUserRequest.getEmail()))
                .thenReturn(tblUser1);

        Mockito.when(mstGroupRepository.getMstGroupByGroupId(updateTblUserRequest.getGroupId()))
                .thenReturn(new MstGroupResponse());

        Mockito.when(tblDetailUserJapanRepository.findTblDetailUserJapanByUserId(updateTblUserRequest.getUserId()))
                .thenReturn(new TblDetailUserJapan());

        Mockito.when(mstJapanseRepository.getMstJapaneseByCodeLevel(updateTblUserRequest.getCodeLevel()))
                .thenReturn(new MstJapanseResponse());

        TblUser tblUser2 = new TblUser();
        tblUser2.setUserId(1);
        tblUser2.setEmail("ndactung1011@gmail.com");
        tblUser2.setGroupId(1);
        Mockito.when(modelMapper.map(updateTblUserRequest, TblUser.class))
                .thenReturn(tblUser2);

        Mockito.when(tblUserInforRepository.updateTblUser(tblUser2)).thenReturn(1);
        //run test
        BaseResponse response = tblUserServiceTest.updateTblUser(updateTblUserRequest);
        //verify
        Assert.notNull(response);
        Assertions.assertEquals("Update success", response.getMessage());
    }

    @Test
    @DisplayName("test update tbl user when japanse level dose not exist")
    void test_update_tbl_user_when_japanse_level_dose_not_exist() {
        UpdateTblUserRequest updateTblUserRequest = new UpdateTblUserRequest();
        updateTblUserRequest.setUserId(1);
        updateTblUserRequest.setEmail("ndactung1011@gmail.com");
        updateTblUserRequest.setGroupId(1);
        updateTblUserRequest.setCodeLevel("N1");

        TblUser tblUser = new TblUser();
        tblUser.setUserId(1);

        TblUser tblUser1 = new TblUser();
        tblUser1.setUserId(1);
        Mockito.when(tblUserInforRepository.findTblUserByUserId(updateTblUserRequest.getUserId()))
                .thenReturn(tblUser);

        Mockito.when(tblUserInforRepository.findTblUserByEmail(updateTblUserRequest.getEmail()))
                .thenReturn(tblUser1);

        Mockito.when(mstGroupRepository.getMstGroupByGroupId(updateTblUserRequest.getGroupId()))
                .thenReturn(new MstGroupResponse());

        Mockito.when(mstJapanseRepository.getMstJapaneseByCodeLevel(updateTblUserRequest.getCodeLevel()))
                .thenReturn(null);

        TblUser tblUser2 = new TblUser();
        tblUser2.setUserId(1);
        tblUser2.setEmail("ndactung1011@gmail.com");
        tblUser2.setGroupId(1);
        Mockito.when(modelMapper.map(updateTblUserRequest, TblUser.class))
                .thenReturn(tblUser2);

        Mockito.when(tblUserInforRepository.updateTblUser(tblUser2)).thenReturn(1);
        //run test
        BusinessException businessException = Assertions.assertThrows(BusinessException.class, () -> tblUserServiceTest.updateTblUser(updateTblUserRequest));
        Assert.notNull(businessException);
        Assertions.assertEquals("Japanese level dose not exist !", businessException.getMsg());
    }

    @Test
    @DisplayName("test create tbl user when email already exist")
    void test_create_tblUser_when_email_already_exist() {
        CreateTblUserRequest createTblUserRequest = new CreateTblUserRequest();
        createTblUserRequest.setEmail("ndactungp@gmail.com");

        Mockito.when(tblUserInforRepository.findTblUserByEmail(createTblUserRequest.getEmail()))
                .thenReturn(new TblUser());
        //run test
        BaseResponse response = tblUserServiceTest.createTblUser(createTblUserRequest);
        //verify
        Assert.notNull(response);
        Assertions.assertEquals("Email already exists !", response.getMessage());
    }

    @Test
    @DisplayName("test create tbl user when login name already exist")
    void test_create_tblUser_when_login_name_already_exist() {
        CreateTblUserRequest createTblUserRequest = new CreateTblUserRequest();
        createTblUserRequest.setEmail("ndactungp@gmail.com");
        createTblUserRequest.setLoginName("tung123");

        Mockito.when(tblUserInforRepository.findTblUserByEmail(createTblUserRequest.getEmail()))
                .thenReturn(null);
        Mockito.when(tblUserInforRepository.findTblUserByLoginName(createTblUserRequest.getLoginName()))
                .thenReturn(new TblUser());
        //run test
        BaseResponse response = tblUserServiceTest.createTblUser(createTblUserRequest);
        //verify
        Assert.notNull(response);
        Assertions.assertEquals("UserName already exists !", response.getMessage());
    }

    @Test
    @DisplayName("test create tbl user success when dose not code level")
    void test_create_tblUser_success_when_dose_not_code_level() {
        CreateTblUserRequest createTblUserRequest = new CreateTblUserRequest();
        createTblUserRequest.setEmail("ndactungp@gmail.com");
        createTblUserRequest.setLoginName("tung123");
        createTblUserRequest.setPassword("tung123");

        Mockito.when(tblUserInforRepository.findTblUserByEmail(createTblUserRequest.getEmail()))
                .thenReturn(null);
        Mockito.when(tblUserInforRepository.findTblUserByLoginName(createTblUserRequest.getLoginName()))
                .thenReturn(null);
        Mockito.when(mstGroupRepository.getMstGroupByGroupId(createTblUserRequest.getGroupId()))
                .thenReturn(new MstGroupResponse());

        TblUser tblUser = new TblUser();
        tblUser.setUserId(12);
        Mockito.when(modelMapper.map(createTblUserRequest, TblUser.class))
                .thenReturn(tblUser);
        //run test
        BaseResponse response = tblUserServiceTest.createTblUser(createTblUserRequest);
        //verify
        Assert.notNull(response);
        Assertions.assertEquals("Create Success", response.getMessage());
    }

    @Test
    @DisplayName("test create tbl user when japanese dose not exist")
    void test_create_tblUser_when_japanses_dose_not_exist() {
        CreateTblUserRequest createTblUserRequest = new CreateTblUserRequest();
        createTblUserRequest.setEmail("ndactungp@gmail.com");
        createTblUserRequest.setLoginName("tung123");
        createTblUserRequest.setPassword("tung123");
        createTblUserRequest.setCodeLevel("N1");

        Mockito.when(tblUserInforRepository.findTblUserByEmail(createTblUserRequest.getEmail()))
                .thenReturn(null);
        Mockito.when(tblUserInforRepository.findTblUserByLoginName(createTblUserRequest.getLoginName()))
                .thenReturn(null);
        Mockito.when(mstGroupRepository.getMstGroupByGroupId(createTblUserRequest.getGroupId()))
                .thenReturn(new MstGroupResponse());

        Mockito.when(mstJapanseRepository.getMstJapaneseByCodeLevel(createTblUserRequest.getCodeLevel()))
                .thenReturn(null);
        TblUser tblUser = new TblUser();
        tblUser.setUserId(12);
        Mockito.when(modelMapper.map(createTblUserRequest, TblUser.class))
                .thenReturn(tblUser);
        //run test
        BusinessException response = Assertions.assertThrows(BusinessException.class, () -> tblUserServiceTest.createTblUser(createTblUserRequest));
        //verify
        Assert.notNull(response);
        Assertions.assertEquals("Japanese level dose not exist !", response.getMsg());
    }

    @Test
    @DisplayName("test create tbl user success when have code level")
    void test_create_tblUser_success_when_have_code_level() {
        CreateTblUserRequest createTblUserRequest = new CreateTblUserRequest();
        createTblUserRequest.setEmail("ndactungp@gmail.com");
        createTblUserRequest.setLoginName("tung123");
        createTblUserRequest.setPassword("tung123");
        createTblUserRequest.setCodeLevel("N1");

        Mockito.when(tblUserInforRepository.findTblUserByEmail(createTblUserRequest.getEmail()))
                .thenReturn(null);
        Mockito.when(tblUserInforRepository.findTblUserByLoginName(createTblUserRequest.getLoginName()))
                .thenReturn(null);
        Mockito.when(mstGroupRepository.getMstGroupByGroupId(createTblUserRequest.getGroupId()))
                .thenReturn(new MstGroupResponse());

        Mockito.when(mstJapanseRepository.getMstJapaneseByCodeLevel(createTblUserRequest.getCodeLevel()))
                .thenReturn(new MstJapanseResponse());
        TblUser tblUser = new TblUser();
        tblUser.setUserId(12);
        Mockito.when(modelMapper.map(createTblUserRequest, TblUser.class))
                .thenReturn(tblUser);
        //run test
        BaseResponse response = tblUserServiceTest.createTblUser(createTblUserRequest);
        //verify
        Assert.notNull(response);
        Assertions.assertEquals("Create Success", response.getMessage());
    }
}
