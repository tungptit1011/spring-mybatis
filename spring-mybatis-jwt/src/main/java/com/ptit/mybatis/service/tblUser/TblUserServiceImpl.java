package com.ptit.mybatis.service.tblUser;

import com.ptit.mybatis.dto.request.CreateTblUserRequest;
import com.ptit.mybatis.dto.request.TblUserRequest;
import com.ptit.mybatis.dto.request.UpdateTblUserRequest;
import com.ptit.mybatis.dto.response.TblUserInforResponse;
import com.ptit.mybatis.entity.TblDetailUserJapan;
import com.ptit.mybatis.entity.TblUser;
import com.ptit.mybatis.repository.MstGroupRepository;
import com.ptit.mybatis.repository.MstJapanseRepository;
import com.ptit.mybatis.repository.TblDetailUserJapanRepository;
import com.ptit.mybatis.repository.TblUserInforRepository;
import com.ptit.mybatis.utils.BaseResponse;
import com.ptit.mybatis.utils.Meta;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class TblUserServiceImpl implements TblUserService {

    @Autowired
    private TblUserInforRepository tblUserRepository;

    @Autowired
    private MstGroupRepository mstGroupRepository;

    @Autowired
    private MstJapanseRepository mstJapanseRepository;

    @Autowired
    private TblDetailUserJapanRepository tblDetailUserJapanRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public TblUser findTblUserByLoginName(String loginName) {
        return tblUserRepository.findTblUserByLoginName(loginName);
    }

    @Override
    public List<TblUserInforResponse> getListlUsers(Integer groupId, String fullName, Pageable pageable) {
        return tblUserRepository.getListlUsers(groupId, fullName, pageable);
    }

    @Override
    public BaseResponse deleteTblUser(Integer userId) {
        log.info("Delete User by: {}", userId);
        if (userId == 0) {
            return new BaseResponse(new Meta("200", "User does not exist"));
        }
        if (tblUserRepository.findTblUserByUserId(userId) == null) {
            return new BaseResponse(new Meta("200", "User does not exist"), "userID: " + userId);
        }
        tblUserRepository.deleteTblUser(userId);
        return new BaseResponse(new Meta("200", "Delete success"), "userID: " + userId);
    }

    @Override
    public BaseResponse updateTblUser(UpdateTblUserRequest tblUserRequest) {
        log.info("Update User: {}", tblUserRequest.toString());
        if (tblUserRepository.findTblUserByUserId(tblUserRequest.getUserId()) == null) {
            return new BaseResponse(new Meta("200", "TblUser dont exits !"), tblUserRequest);
        }

        if (!StringUtils.isEmpty(tblUserRequest.getEmail())) {
            TblUser tblUserResponse = tblUserRepository.findTblUserByEmail(tblUserRequest.getEmail());
            if (tblUserResponse != null && tblUserResponse.getUserId() != tblUserRequest.getUserId())
                return new BaseResponse(new Meta("200", "Email already exists !"), tblUserRequest);
        }

        if (tblUserRequest.getGroupId() != null && mstGroupRepository.getMstGroupByGroupId(tblUserRequest.getGroupId()) == null) {
            return new BaseResponse(new Meta("200", "Department does not exist !"), tblUserRequest);
        }

        TblDetailUserJapan tblDetailUserJapanInDB = tblDetailUserJapanRepository.findTblDetailUserJapanByUserId(tblUserRequest.getUserId());
        TblDetailUserJapan tblDetailUserJapanRequest = convertTblDetailUserJapan(tblUserRequest);
        if (!StringUtils.isEmpty(tblUserRequest.getCodeLevel())) {
            if (mstJapanseRepository.getMstJapaneseByCodeLevel(tblUserRequest.getCodeLevel()) == null) {
                return new BaseResponse(new Meta("200", "Japanese level dose not exist !"), tblUserRequest);
            }
            if (tblDetailUserJapanInDB == null) {
                tblDetailUserJapanRepository.insertTblDetailUserJapan(tblDetailUserJapanRequest);
            } else if (!checkStatusTblDetailUserJapan(tblDetailUserJapanInDB, tblUserRequest)) {
                tblDetailUserJapanRepository.updateTblDetailUserJapan(tblDetailUserJapanRequest);
            }
        } else {
            tblDetailUserJapanRepository.deleteTblDetailUserJapan(tblUserRequest.getUserId());
        }
        if (tblUserRepository.updateTblUser(modelMapper.map(tblUserRequest, TblUser.class)) == 1) {
            return new BaseResponse(new Meta("200", "Update success"), tblUserRequest);
        }
        return new BaseResponse(new Meta("200", "Update Fail"));
    }

    @Override
    public BaseResponse createTblUser(CreateTblUserRequest tblUserRequest) {
        log.info("Create User: {}", tblUserRequest.toString());
        if (tblUserRepository.findTblUserByEmail(tblUserRequest.getEmail()) != null) {
            return new BaseResponse(new Meta("200", "Email already exists !"));
        }
        if (tblUserRepository.findTblUserByLoginName(tblUserRequest.getLoginName()) != null) {
            return new BaseResponse(new Meta("200", "UserName already exists !"));
        }
        tblUserRequest.setPassword(passwordEncoder.encode(tblUserRequest.getPassword()));
        TblUser tblUser = modelMapper.map(tblUserRequest, TblUser.class);
        tblUserRepository.insertTblUser(tblUser);
        tblUserRequest.setUserId(tblUser.getUserId());
        if (tblUserRequest.getCodeLevel() != null) {
            if (mstJapanseRepository.getMstJapaneseByCodeLevel(tblUserRequest.getCodeLevel()) == null) {
                return new BaseResponse(new Meta("200", "Japanese level dose not exist !"), tblUserRequest);
            }
            TblDetailUserJapan tblDetailUserJapan = convertTblDetailUserJapan(tblUserRequest);
            tblDetailUserJapanRepository.insertTblDetailUserJapan(tblDetailUserJapan);
        }
        return new BaseResponse(new Meta("200", "Create Success"));
    }

    private TblDetailUserJapan convertTblDetailUserJapan(TblUserRequest tblUserRequest) {
        TblDetailUserJapan tblDetailUserJapan = new TblDetailUserJapan();
        if ("com.ptit.mybatis.dto.request.UpdateTblUserRequest".equalsIgnoreCase(tblUserRequest.getClass().getName())) {
            log.info("tblUserRequest is UpdateTblUserRequest");
            UpdateTblUserRequest updateTblUserRequest = (UpdateTblUserRequest) tblUserRequest;
            tblDetailUserJapan.setUserId(updateTblUserRequest.getUserId());
            tblDetailUserJapan.setTotal(updateTblUserRequest.getTotal());
            tblDetailUserJapan.setStartDate(updateTblUserRequest.getStartDate());
            tblDetailUserJapan.setEndDate(updateTblUserRequest.getEndDate());
            tblDetailUserJapan.setCodeLevel(updateTblUserRequest.getCodeLevel());
        } else if ("com.ptit.mybatis.dto.request.CreateTblUserRequest".equalsIgnoreCase(tblUserRequest.getClass().getName())) {
            log.info("tblUserRequest is CreateTblUserRequest");
            CreateTblUserRequest createTblUserRequest = (CreateTblUserRequest) tblUserRequest;
            tblDetailUserJapan.setUserId(createTblUserRequest.getUserId());
            tblDetailUserJapan.setTotal(createTblUserRequest.getTotal());
            tblDetailUserJapan.setStartDate(createTblUserRequest.getStartDate());
            tblDetailUserJapan.setEndDate(createTblUserRequest.getEndDate());
            tblDetailUserJapan.setCodeLevel(createTblUserRequest.getCodeLevel());
        }
        return tblDetailUserJapan;
    }

    private Boolean checkStatusTblDetailUserJapan(TblDetailUserJapan tblDetailUserJapan, UpdateTblUserRequest tblUserRequest) {
        if (tblDetailUserJapan.getCodeLevel() != tblUserRequest.getCodeLevel()) {
            log.info("Different level, previous level: {}, next level: {} ", tblDetailUserJapan.getCodeLevel(), tblUserRequest.getCodeLevel());
            return false;
        } else if (tblDetailUserJapan.getTotal() != tblUserRequest.getTotal()) {
            log.info("Different total, previous total: {}, next total: {} ", tblDetailUserJapan.getTotal(), tblUserRequest.getTotal());
            return false;
        } else if (tblDetailUserJapan.getStartDate() != tblUserRequest.getStartDate()) {
            log.info("Different start date, previous date: {}, next date: {} ", tblDetailUserJapan.getStartDate(), tblUserRequest.getStartDate());
            return false;
        } else if (tblDetailUserJapan.getEndDate() != tblUserRequest.getEndDate()) {
            log.info("Different end date, previous date: {}, next date: {} ", tblDetailUserJapan.getEndDate(), tblUserRequest.getEndDate());
            return false;
        }
        log.info("Japanese no change");
        return true;
    }
}
