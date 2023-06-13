package com.ptit.mybatis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "TBL_USER")
public class TblUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "GROUP_ID")
    private Integer groupId;

    @Column(name = "LOGIN_NAME")
    private String loginName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "FULL_NAME_KANA")
    private String fullNameKana;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TEL")
    private String tel;

    @Column(name = "BIRTHDAY")
    private String birthday;

    @Column(name = "RULE")
    private String rule;
}
