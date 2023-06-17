/**
 * Copyright 2023 NguyenDacTung
 */
package com.ptit.mybatis.service.tblUser;

import com.ptit.mybatis.entity.TblUser;
import com.ptit.mybatis.dto.request.CreateTblUserRequest;
import com.ptit.mybatis.dto.request.UpdateTblUserRequest;
import com.ptit.mybatis.dto.response.TblUserInforResponse;
import com.ptit.mybatis.utils.BaseResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TblUserService {

    List<TblUserInforResponse> getListlUsers(Integer groupId, String fullName, Pageable pageable);

    BaseResponse updateTblUser(UpdateTblUserRequest updateTblUserRequest);

    BaseResponse createTblUser(CreateTblUserRequest tblUser);

    BaseResponse deleteTblUser(Integer userId);
}
