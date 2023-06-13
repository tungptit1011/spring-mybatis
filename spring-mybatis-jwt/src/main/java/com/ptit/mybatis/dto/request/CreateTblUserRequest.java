package com.ptit.mybatis.dto.request;

import com.ptit.mybatis.validate.DateFormat;
import com.ptit.mybatis.validate.EmailFormat;
import com.ptit.mybatis.validate.PassFormat;
import com.ptit.mybatis.validate.TelFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CreateTblUserRequest extends TblUserRequest {
    private Integer userId;
    @NotNull
    private Integer groupId;

    @NotNull
    private String loginName;

    @NotNull
    private String fullName;

    @EmailFormat
    private String email;

    @TelFormat
    private String tel;

    @PassFormat
    private String password;

    @DateFormat
    private String birthday;
}
