package com.study.edu.controll;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.study.edu.annotation.CatAnnotation;
import com.study.edu.entity.SexNum;
import com.study.edu.entity.User;
import com.study.edu.mapper.StuMapper;
import com.study.edu.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2018/3/14.
 */
@RestController
public class HelloController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StuMapper stuMapper;
    

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    @CatAnnotation
    public String hello(HttpServletResponse response) throws IOException {
        User user = null;
        Transaction t = Cat.newTransaction("URL","hello");
        Cat.logEvent("Method_hello","hello");
        try{
        	System.out.println(stuMapper.selectByPrimaryKey(1));
            t.setStatus(Transaction.SUCCESS);
        }catch (Exception e){
            t.setStatus(e);
            Cat.logError(new Exception(e.toString()));
        }finally {
            t.complete();
        }
        return "hello world!";
    }


    @RequestMapping(value = "/query")
    @ResponseBody
    @CatAnnotation
    public String query(@RequestParam String name){
        Transaction t = Cat.newTransaction("URL","query");
        Cat.logEvent("Method_query","query");
        try{
            userMapper.getAll();
            System.out.println(userMapper.getAll());
            t.setStatus(Transaction.SUCCESS);
        }catch (Exception e){
            t.setStatus(e);
            Cat.logError(new Exception(e.toString()));
        }finally {
            t.complete();
        }
        return "你好 :"+name;
    }

    @RequestMapping(value = "/insert",method = RequestMethod.GET)
    @ResponseBody
    public String insert(){
        try{
            userMapper.insert(new User("trancati","bcv", SexNum.MAN));
        }catch (Exception  e){
            return "FAIUT";
        }
        return "OK!";
    }
}
