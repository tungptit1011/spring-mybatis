/**
 * Copyright 2023 NguyenDacTung
 */
package com.ptit.mybatis.repository;

import com.ptit.mybatis.dto.response.TblUserInforResponse;
import com.ptit.mybatis.entity.TblUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper
public interface TblUserInforRepository {
    public TblUser findTblUserByUserId(@Param("userId") Integer userId);

    public List<TblUserInforResponse> getListlUsers(@Param("groupId") Integer groupId,
                                                    @Param("fullName") String fullName,
                                                    @Param("page") Pageable pageable);

    public TblUser findTblUserByLoginName(@Param("loginName") String loginName);

    public TblUser findTblUserByEmail(String email);

    public Integer insertTblUser(@Param("tblUser") TblUser tblUser);

    public Integer updateTblUser(@Param("tblUser") TblUser tblUser);

    public Integer deleteTblUser(@Param("userId") Integer id);
}
