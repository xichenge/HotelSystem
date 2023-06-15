package com.team.hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team.hotel.dto.TopFive;
import com.team.hotel.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookMapper extends BaseMapper<Book> {
    @Select("select roomtype as name,count(*) as num from book  GROUP BY name ORDER BY num desc limit 5")
    List<TopFive> getTopFive();
}
