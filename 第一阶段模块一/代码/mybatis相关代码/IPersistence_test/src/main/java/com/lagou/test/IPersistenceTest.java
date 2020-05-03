package com.lagou.test;

import com.lagou.dao.IUserDao;
import com.lagou.io.Resources;
import com.lagou.pojo.User;
import com.lagou.sqlSession.SqlSession;
import com.lagou.sqlSession.SqlSessionFactory;
import com.lagou.sqlSession.SqlSessionFactoryBuilder;
import com.lagou.utils.DbUtils;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class IPersistenceTest {


    @Test
    public void test() throws Exception {
        DbUtils.initData();
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //调用
        User user = new User();
        user.setId(15);
        user.setName("User2");
      /*  User user2 = sqlSession.selectOne("user.selectOne", user);

        System.out.println(user2);*/

       /* List<User> users = sqlSession.selectList("user.selectList");
        for (User user1 : users) {
            System.out.println(user1);
        }*/

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        System.out.println("修改之前");
        List<User> all = userDao.findAll();
        for (User user1 : all) {
            System.out.println(user1);
        }
        System.out.println("修改之后 设置名称为user2");
        userDao.update(user);
        List<User> all2 = userDao.findAll();
        for (User user1 : all2) {
            System.out.println(user1);
        }
        System.out.println("删除id=15之后");
        userDao.delete(user);
        List<User> all3 = userDao.findAll();
        if (all3 ==null||all3.isEmpty()){
            System.out.println("查询没有数据");
        }






    }



}
