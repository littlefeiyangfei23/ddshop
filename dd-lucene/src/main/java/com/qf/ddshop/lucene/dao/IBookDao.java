package com.qf.ddshop.lucene.dao;

import com.qf.ddshop.lucene.po.Book;

import java.util.List;

/**
 * 数据访问层接口
 */
public interface IBookDao {
    /**
     * 采集数据：查询所有图书
     * @return
     */
    List<Book> listBooks();
}
