/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.inter;

import entity.UserSkill;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface UserSkillDaoInter {
     public List<UserSkill> getAllSkillbyUserId(int userId);
     
}
