package com.bwei.oracle_coundday0421.task;

import com.bwei.oracle_coundday0421.bean.TBook;
import com.bwei.oracle_coundday0421.bean.TBookExample;
import com.bwei.oracle_coundday0421.dao.TBookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @User feifei
 * @ClassName bookTask  类名
 * @Author Administrator  作者
 * @Date 2020-04-25 11:27  时间
 * @Version 1.0 版本
 */
@Component
public class bookTask {
    @Autowired
    private TBookMapper tBookMapper;

    @Autowired
    private RedisTemplate redisTemplate;
    @Scheduled(fixedDelay = 20000)
    //@Scheduled(cron = "0 0/10 * * * ? *")
    public void updateRedisTraffic(){
        System.out.println("======执行updateRedisTraffic=====");
        //得到当前库中所有的书籍
        List<TBook> books = tBookMapper.selectByExample(new TBookExample());
        //在循环拿到书籍的id去redis中查询点击量,并且同步更新到库中
        if(books!=null && books.size()>0){
            for (TBook book : books) {
                String id = book.getId();
                //判断id在redis缓存中是否存在
                if(redisTemplate.hasKey(id)){
                    Integer count= (Integer) redisTemplate.opsForValue().get(id);
                    book.setTraffic((long)count);
                    //更新数据
                    tBookMapper.updateByPrimaryKeySelective(book);
                }

            }
        }
    }
}
