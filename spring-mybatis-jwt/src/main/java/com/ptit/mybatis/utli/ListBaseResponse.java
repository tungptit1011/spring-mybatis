package com.ptit.mybatis.utli;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Getter
@Setter
@ToString
public class ListBaseResponse<T> extends Meta {

    private List<T> data;

    private ListBaseResponse.Pagination pagination;

    public ListBaseResponse(PageImpl<T> data) {
        this.data = data.getContent();
        this.pagination = new ListBaseResponse.Pagination();
        pagination.setPageSize(data.getSize());
        pagination.setPageNumber(data.getNumber());
        pagination.setTotal(data.getTotalElements());
        pagination.setTotalPage(data.getTotalPages());
        code = ConstantStatus.OK;
        message = "Success";
    }

    public ListBaseResponse(List<T> data) {
        this.data = data;
        code = ConstantStatus.OK;
        message = "Success";
    }

    public ListBaseResponse(Meta meta) {
        code = meta.getCode();
        message = meta.getMessage();
    }

    @Data
    static class Pagination {
        private int pageSize;
        private int pageNumber;
        private int totalPage;
        private long total;
    }
}
