package com.ptit.mybatis.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "TBL_DETAIL_USER_JAPAN")
public class TblDetailUserJapan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DETAIL_USER_JAPAN_ID")
    private Integer detailUserJapanId;

    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "CODE_LEVEL")
    private String codeLevel;

    @Column(name = "START_DATE")
    private String startDate;

    @Column(name = "END_DATE")
    private String endDate;

    @Column(name = "TOTAL")
    private Integer total;
}
