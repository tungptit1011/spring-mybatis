/**
 * Copyright 2023 NguyenDacTung
 */
package com.ptit.mybatis.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TblUserInforResponse {
    private Integer userId;

    private Integer groupId;

    private String fullName;

    private String email;

    private String tel;

    private String birthday;

    private String groupName;

    private String nameLevel;

    private String endDate;

    private Integer total;

    private String codeLevel;
}
