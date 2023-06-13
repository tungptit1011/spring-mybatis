package com.ptit.mybatis.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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

    @NotNull
    private String email;

    @NotNull
    private String tel;

    @NotNull
    private String password;

    @NotNull
    private String birthday;

}
