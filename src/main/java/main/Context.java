/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import dao.impl.EmploymentHistoryDaoImpl;
import dao.impl.SkillDaoImpl;
import dao.impl.UserDaoImpl;
import dao.impl.UserSkillDaoImpl;
import dao.inter.EmploymentHistoryDaoInter;
import dao.inter.SkillDaoInter;
import dao.inter.UserDaoInter;
import dao.inter.UserSkillDaoInter;

/**
 *
 * @author ASUS
 */
public class Context {

    public static UserDaoInter InstanceUserDao() {
        return new UserDaoImpl();
    }

    public static UserSkillDaoInter InstanceUserSkillDao() {
        return new UserSkillDaoImpl();
    }

    public static EmploymentHistoryDaoInter InstanceEmploymentHistoryDao() {
        return new EmploymentHistoryDaoImpl();
    }
    
     public static SkillDaoInter InstanceSkillDao() {
        return new SkillDaoImpl();
    }
}
