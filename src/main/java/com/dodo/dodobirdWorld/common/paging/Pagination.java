package com.dodo.dodobirdWorld.common.paging;

import lombok.Data;

@Data
public class Pagination {
    private int currentPage; // 현재페이지
    
    private int cntPerPage; // 페이지당 출력할 페이지 갯수
    
    private int pageSize; // 화면 하단 페이지 사이즈 1~10, 10~20 20~30 ...
    
    private int totalRecordCount; // 전체 데이터 개수 
    
    private int totalPageCount; // 전체 페이지 개수
    
    private int firstPage; // 페이지 리스트의 첫 페이지 번호
    
    private int lastPage; // 페이지 리스트의 마지막 페이지 번호
    
    private int firstRecordIndex; // SQL의 조건절에 사용되는 첫 RNUM
    
    private int lastRecordIndex; // SQL의 조건절에 사용되는 마지막 RNUM
    
    private boolean hasPreviousPage;  // 이전 페이지 존재 여부
    
    private boolean hasNextPage; // 다음 페이지 존재 여부
    
 
	
}
