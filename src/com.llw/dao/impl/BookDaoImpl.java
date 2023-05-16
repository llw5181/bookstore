package com.llw.dao.impl;

import com.llw.dao.BookDao;
import com.llw.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(id) from t_book";
        return  ((Number)queryForSingleValue(sql)).intValue();
    }

    @Override
    public List<Book> queryForItems(int begin, int pageSize) {
        String sql = "select `id`,`name`,`price`,`author`,`sales`,`stock`,`img_path` from t_book limit ?,?" ;
        return queryForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public int queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(id) from t_book where price between ? and ?";
        return  ((Number)queryForSingleValue(sql,min,max)).intValue();
    }

    @Override
    public List<Book> queryForItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select `id`,`name`,`price`,`author`,`sales`,`stock`,`img_path` from t_book where price between ? and ? order by price asc limit ?,?" ;
        return queryForList(Book.class,sql,min,max,begin,pageSize);
    }


    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(`name`,`price`,`author`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?) ";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImg_path());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id = ?";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set name=?,price=?,author=?,sales=?,stock=?,img_path=? where id =?";
        return update(sql, book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImg_path(), book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select `id`,`name`,`price`,`author`,`sales`,`stock`,`img_path` from t_book where id=? ";
        return queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select `id`,`name`,`price`,`author`,`sales`,`stock`,`img_path` from t_book ";
        return queryForList(Book.class,sql);
    }

    @Override
    public int update(String sql, Object... args) {
        return super.update(sql, args);
    }
}
