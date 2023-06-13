package com.ptit.mybatis.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateTblUserRequest extends TblUserRequest {
    @NotNull
    private Integer userId;

    private Integer groupId;

    private String loginName;

    private String fullName;

    private String email;

    private String tel;

    private String birthday;
}
