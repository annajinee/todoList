package com.kakaopay.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageInfo {
    private long totalCount;
    private int totalPage;
    private int pageNumber;
}
