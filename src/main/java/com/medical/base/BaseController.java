package com.medical.base;

import com.medical.constants.Common;
import com.medical.dto.ResponseDTO;
import com.medical.dto.pagination.PaginateDTO;
import com.medical.dto.pagination.PaginationDTO;
import com.medical.dto.pagination.PaginationResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BaseController<T>{
    public ResponseEntity<?> resSuccess(T data) {
        Map<String, T> map = new HashMap<>();
        map.put("data", data);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(HttpStatus.OK.value(), Common.MSG_SUCCESS, map));
    }
    public ResponseEntity<?> resSuccess(List<T> data) {
        Map<String, List<T>> map = new HashMap<>();
        map.put("data", data);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(HttpStatus.OK.value(), Common.MSG_SUCCESS, map));
    }
    public ResponseEntity<?> createdSuccess(T data){
        Map<String, T> map = new HashMap<>();
        map.put("data", data);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO<>(HttpStatus.CREATED.value(), Common.MSG_CREATED_SUCCESSFUL_201, map));
    }
    public ResponseEntity<?> resListSuccess(List<?> data) {
        Map<String, List<?>> map = new HashMap<>();
        map.put("data", data);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(HttpStatus.OK.value(), Common.MSG_SUCCESS, map));
    }

    public ResponseEntity<?> resPagination(PaginateDTO<?> paginateDTO) {
        PaginationDTO<List<?>> paginationDTO = new PaginationDTO<>(paginateDTO.getPageData().getContent(),
                paginateDTO.getPagination());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PaginationResponseDTO<>(HttpStatus.OK.value(), Common.MSG_SUCCESS, paginationDTO));
    }
}
