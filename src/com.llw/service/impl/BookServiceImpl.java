package com.llw.service.impl;

import com.llw.dao.BookDao;
import com.llw.dao.impl.BookDaoImpl;
import com.llw.pojo.Book;
import com.llw.pojo.Page;
import com.llw.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDao bookDao =new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(Integer pageNo, Integer pageSize) {
        Page<Book> page = new Page<>();
        int pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        int PageTotal= pageTotalCount%pageSize>0 ? pageTotalCount/pageSize +1: pageTotalCount/pageSize;
        page.setPageTotal(PageTotal);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        int begin = (page.getPageNo()-1)*pageSize;
        page.setItems(bookDao.queryForItems(begin,pageSize));
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();
        int pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        page.setPageTotalCount(pageTotalCount);
        int PageTotal= pageTotalCount%pageSize>0 ? pageTotalCount/pageSize +1: pageTotalCount/pageSize;
        page.setPageTotal(PageTotal);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        int begin = (page.getPageNo()-1)*pageSize;
        page.setItems(bookDao.queryForItemsByPrice(begin,pageSize,min,max));
        return page;
    }


}
