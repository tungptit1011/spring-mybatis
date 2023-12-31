/**
 * Copyright 2023 NguyenDacTung
 */
package com.ptit.mybatis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "MST_GROUP")
public class MstGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GROUP_ID")
    private Integer groupId;

    @Column(name = "GROUP_NAME")
    private String groupName;
}
