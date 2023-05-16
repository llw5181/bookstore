package com.llw.dao;

import com.llw.pojo.Book;

import java.util.List;

public interface BookDao {

    /**
     * 添加book对象到DB
     *
     * @param book
     * @return 返回影响的DB的行数
     */
    public int addBook(Book book);

    /**
     * 根据Book的id从DB中删除book对象
     *
     * @param id
     * @return 返回影响的DB的行数
     */
    public int deleteBookById(Integer id);

    /**
     * 根据book的id锁定book对象修改book对象的其他属性
     *
     * @param book
     * @return 返回影响的DB的行数
     */
    public int updateBook(Book book);

    /**
     * 根据book的id从DB中查询book对象并返回
     *
     * @param id
     * @return 饭回id查询到的book对象
     */
    public Book queryBookById(Integer id);

    /**
     * 查询DB中所有book对象并存储在一个List对象中
     *
     * @return 返回存储了book对象的List
     */
    public List<Book> queryBooks();

    /**
     *
     * @return 返回book表中的总记录条数
     */
    public Integer queryForPageTotalCount();

    /**
     * @return 返回根据起始记录条数和每页记录条数查询到的分页记录
     */
    public List<Book> queryForItems(int begin, int pageSize);

    /**
     *
     * @param min
     * @param max
     * @return 返回价格区间中的总记录数
     */
    int queryForPageTotalCountByPrice(int min, int max);

    /**
     *
     * @param begin
     * @param pageSize
     * @param min
     * @param max
     * @return 返回价格区间根据limit分页后的一页Book对象
     */
    List<Book> queryForItemsByPrice(int begin, int pageSize, int min, int max);
}
