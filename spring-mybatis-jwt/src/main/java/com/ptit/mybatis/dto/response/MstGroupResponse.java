/**
 * Copyright 2023 NguyenDacTung
 */
package com.ptit.mybatis.dto.response;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class MstGroupResponse {
    private Integer groupId;

    private String groupName;

}
