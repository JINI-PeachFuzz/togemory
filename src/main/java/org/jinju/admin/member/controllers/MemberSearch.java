package org.jinju.admin.member.controllers;

import lombok.Data;
import org.jinju.global.paging.CommonSearch;
import org.jinju.member.constants.Authority;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
public class MemberSearch extends CommonSearch {
    private List<String> email;
    private List<Authority> authority;
    private String dateType;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate sDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate eDate;
}
