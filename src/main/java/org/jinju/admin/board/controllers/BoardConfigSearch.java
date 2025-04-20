package org.jinju.admin.board.controllers;

import lombok.Data;
import org.jinju.global.paging.CommonSearch;

import java.util.List;

@Data
public class BoardConfigSearch extends CommonSearch {
    private List<String> bid;
}
