package com.ptit.mybatis.dto.request;

import com.ptit.mybatis.validate.FullNameKanaFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TblUserRequest {
    private String rule;

    @FullNameKanaFormat
    private String fullNameKana;

    private String endDate;

    private String startDate;

    private Integer total;

    private String codeLevel;
}
