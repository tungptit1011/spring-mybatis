package com.ptit.mybatis.service.mstGroup;

import com.ptit.mybatis.dto.response.MstGroupResponse;
import com.ptit.mybatis.repository.MstGroupRepository;
import com.ptit.mybatis.utils.BaseResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MstGroupServiceImplTest {

    @InjectMocks
    private MstGroupServiceImpl mstGroupService;

    @Mock
    private MstGroupRepository mstGroupRepository;

    @Test
    @DisplayName("test get list mst group")
    void test_get_list_mst_group() {
        MstGroupResponse mstGroupResponse = new MstGroupResponse();
        mstGroupResponse.setGroupId(1);
        mstGroupResponse.setGroupName("Phòng phát triển số 1");
        List<MstGroupResponse> list = new ArrayList<>();
        list.add(mstGroupResponse);
        when(mstGroupRepository.getListMstGroup(any(Pageable.class))).thenReturn(list);

        //run test
        List<MstGroupResponse> listMstGroup = mstGroupService.getListMstGroup(PageRequest.of(1, 5));

        //verify
        Assert.notNull(listMstGroup);
        Assertions.assertEquals(1, listMstGroup.get(0).getGroupId());
        Assertions.assertEquals("Phòng phát triển số 1", listMstGroup.get(0).getGroupName());
    }

    @Test
    @DisplayName("test get mst group by group id when id exits")
    void test_get_mst_group_by_group_id_when_id_exits() {
        Integer id = 1;
        MstGroupResponse mstGroupResponse = new MstGroupResponse();
        mstGroupResponse.setGroupId(1);
        mstGroupResponse.setGroupName("Phòng phát triển số 1");
        when(mstGroupRepository.getMstGroupByGroupId(id))
                .thenReturn(mstGroupResponse);

        //run test
        BaseResponse baseResponse = mstGroupService.getMstGroupByGroupId(id);

        //verify
        Assert.notNull(baseResponse);
        Assertions.assertEquals("Phòng phát triển số 1", ((MstGroupResponse) baseResponse.getData()).getGroupName());
        Assertions.assertEquals(1, ((MstGroupResponse) baseResponse.getData()).getGroupId());
    }

    @Test
    @DisplayName("test get mst group by group id when id dose not exits")
    void test_get_mst_group_by_group_id_when_id_dose_not_exits() {
        Integer id = 9;
        when(mstGroupRepository.getMstGroupByGroupId(id))
                .thenReturn(null);

        //run test
        BaseResponse baseResponse = mstGroupService.getMstGroupByGroupId(id);

        //verify
        Assert.notNull(baseResponse);
        Assert.isNull(baseResponse.getData());
        Assertions.assertEquals("Department does not exist !", baseResponse.getMessage());
    }
}
