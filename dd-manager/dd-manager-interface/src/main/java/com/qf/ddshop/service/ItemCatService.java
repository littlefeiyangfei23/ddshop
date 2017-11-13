package com.qf.ddshop.service;

import com.qf.ddshop.common.dto.TreeNode;

import java.util.List;

public interface ItemCatService {
    List<TreeNode> listItemCatsById(Long parentId);
}
