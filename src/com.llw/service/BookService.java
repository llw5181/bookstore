package com.llw.service;

import com.llw.pojo.Book;
import com.llw.pojo.Page;

import java.util.List;

public interface BookService {

    /**
     * 添加图书
     * @param book
     */
    public void addBook(Book book);

    /**
     * 删除图书
     * @param id
     */
    public void deleteBookById(Integer id);

    /**
     * 修改图书
     * @param book
     */
    public void updateBook(Book book);

    /**
     * 根据图书id检索数据库对应的图书对象并返回
     * @param id
     * @return 根据id查询到的book对象
     */
    public Book queryBookById(Integer id);

    /**
     *
     * @return 查询book表中所有的book对象并封装在list中
     */
    public List<Book> queryBooks();

    /**
     * 根据客户端传递过来的当前页码和每页显示条数返回Page对象
     * @return
     */
    public Page<Book> page(Integer pageNo,Integer pageSize);

    /**
     * 返回根据价格区间排序的的Book集合
     * @param pageNo
     * @param pageSize
     * @param min
     * @param max
     * @return 返回根据价格区间排序的的Book集合
     */
    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
