package main;

import dao.inter.SkillDaoInter;
import dao.inter.UserDaoInter;

public class Main {

    public static void main(String[] args) throws Exception {
        UserDaoInter userDao = Context.InstanceUserDao();
        System.out.println(userDao.getById(1));
    }
}
