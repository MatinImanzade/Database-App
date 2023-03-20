package dao.impl;

import entity.UserSkill;
import dao.inter.AbstractDAO;
import dao.inter.UserSkillDaoInter;
import entity.Skill;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {

    private UserSkill getUserSkill(ResultSet resultSet) throws Exception {
        int userId = resultSet.getInt("user_id");
        int id = resultSet.getInt("id");
        String skillName = resultSet.getString("skill_name");
        int skillId = resultSet.getInt("skill_id");
        int power = resultSet.getInt("power");

        return new UserSkill(userId, new User(id), new Skill(skillId, skillName), power);// niye gore deyisenleri obyekt kimi qeyd edirik yeniki UserSkill klasinda
    }

    @Override
    public List<UserSkill> getAllSkillbyUserId(int userId) {
        List<UserSkill> userSkillList = new ArrayList<UserSkill>();
        try ( Connection connection = connect()) {

            Statement statement = connection.createStatement();
            statement.execute("SELECT"
                    + "	u.*,"
                    + "	s.NAME AS skill_name,"
                    + "	us.power"
                    + ""
                    + "FROM"
                    + "	user_skill AS us"
                    + "	LEFT JOIN USER AS u ON u.id = us.user_id"
                    + "	LEFT JOIN skill AS s ON s.id = us.skill_id"
                    + "where u.id = " + userId);
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                UserSkill userSkill = getUserSkill(resultSet);
                userSkillList.add(userSkill);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userSkillList;
    }

}
