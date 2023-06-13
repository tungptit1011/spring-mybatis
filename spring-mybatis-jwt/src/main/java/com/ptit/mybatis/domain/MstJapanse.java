package com.ptit.mybatis.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "MST_JAPANSE")
public class MstJapanse {

    @Id
    @Column(name = "CODE_LEVEL")
    private String codeLevel;

    @Column(name = "NAME_LEVEL")
    private String nameLevel;
}
