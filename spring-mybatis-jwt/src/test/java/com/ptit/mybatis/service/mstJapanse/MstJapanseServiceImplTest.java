package com.ptit.mybatis.service.mstJapanse;

import com.ptit.mybatis.dto.response.MstJapanseResponse;
import com.ptit.mybatis.repository.MstJapanseRepository;
import com.ptit.mybatis.utils.BaseResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MstJapanseServiceImplTest {

    @InjectMocks
    private MstJapanseServiceImpl mstJapanseService;

    @Mock
    private MstJapanseRepository mstJapanseRepository;

    @Test
    @DisplayName("test get list mst Japanese")
    void test_get_list_mst_Japanese() {
        final MstJapanseResponse mstJapanseResponse = new MstJapanseResponse();
        mstJapanseResponse.setCodeLevel("N1");
        mstJapanseResponse.setNameLevel("Trình độ tiếng nhật cấp 1");
        final List<MstJapanseResponse> list = new ArrayList<>();
        list.add(mstJapanseResponse);
        Mockito.when(mstJapanseRepository.getListMstJapanese(ArgumentMatchers.any(Pageable.class)))
                .thenReturn(list);

        //run test
        List<MstJapanseResponse> resultList = mstJapanseService.getListMstJapanese(PageRequest.of(1, 2));
        //verify
        Assert.notNull(list);

        Assertions.assertEquals("N1", list.get(0).getCodeLevel());
        Assertions.assertEquals("Trình độ tiếng nhật cấp 1", list.get(0).getNameLevel());
    }

    @Test
    @DisplayName("test get list mst Japanese when japanese exits")
    void test_get_mst_japanese_by_code_level_when_japanese_exits() {
        String codeLevel = "N1";
        final MstJapanseResponse mstJapanseResponse = new MstJapanseResponse();
        mstJapanseResponse.setCodeLevel("N1");
        mstJapanseResponse.setNameLevel("Trình độ tiếng nhật cấp 1");

        Mockito.when(mstJapanseRepository.getMstJapaneseByCodeLevel(codeLevel))
                .thenReturn(mstJapanseResponse);

        //run test
        BaseResponse response = mstJapanseService.getMstJapaneseByCodeLevel(codeLevel);
        //verify
        Assert.notNull(response);

        Assertions.assertEquals("N1", ((MstJapanseResponse) response.getData()).getCodeLevel());
        Assertions.assertEquals("Trình độ tiếng nhật cấp 1", ((MstJapanseResponse) response.getData()).getNameLevel());
    }

    @Test
    @DisplayName("test get list mst Japanese when japanese dose not exits")
    void test_get_mst_japanese_by_code_level_when_japanese_dose_not_exits() {
        String codeLevel = "N19";
        Mockito.when(mstJapanseRepository.getMstJapaneseByCodeLevel(codeLevel))
                .thenReturn(null);

        //run test
        BaseResponse response = mstJapanseService.getMstJapaneseByCodeLevel(codeLevel);
        //verify
        Assert.notNull(response);
        Assertions.assertEquals("Japanese level dose not exist !", response.getMessage());
    }
}
