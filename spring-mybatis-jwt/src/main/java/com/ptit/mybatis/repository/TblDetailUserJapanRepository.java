package com.ptit.mybatis.repository;

import com.ptit.mybatis.entity.TblDetailUserJapan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TblDetailUserJapanRepository {
    public Integer insertTblDetailUserJapan(@Param("tblDetailUserJapan") TblDetailUserJapan tblDetailUserJapan);

    public Integer updateTblDetailUserJapan(@Param("tblDetailUserJapan") TblDetailUserJapan tblDetailUserJapan);

    public Integer deleteTblDetailUserJapan(@Param("userId") Integer userId);

    public TblDetailUserJapan findTblDetailUserJapanByUserId(@Param("userId") Integer userId);
}
