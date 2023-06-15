package com.team.hotel.service;

import com.team.hotel.dto.TopFiveDetails;
import com.team.hotel.pojo.Book;
import com.team.hotel.dto.TopFive;

import java.text.ParseException;
import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book deleteById(int id);
    Book updataBookInfo(Book book);
    Book addBookInfo(Book book) throws ParseException;
    Book updateBookStatus(String status,Integer id);
    List<TopFiveDetails> getTopFive();
    int getDateBookNum(String typeName,String date);
}
