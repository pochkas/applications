package org.example.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public interface PageCreation {
    default Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    default PageRequest pageRequest(int page, int size, String[] sort) {
        Sort direction = new Sort(getSortDirection(sort[1]), sort[0]);
        return PageRequest.of(page, size, direction);
    }
}
