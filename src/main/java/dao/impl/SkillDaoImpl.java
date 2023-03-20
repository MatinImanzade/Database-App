package dao.impl;

import dao.inter.AbstractDAO;
import dao.inter.SkillDaoInter;
import entity.Skill;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {

    private Skill getSkill(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        Skill skill = new Skill(id, name);
        return skill;
    }

    @Override
    public List<Skill> getAllSkill() {
        List<Skill> skillList = new ArrayList<Skill>();
        try ( Connection connection = connect()) {
            Statement statement = connection.createStatement();
            statement.execute("select * from skill");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
               Skill skill = getSkill(resultSet);
               skillList.add(skill);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return skillList;
    }

}
