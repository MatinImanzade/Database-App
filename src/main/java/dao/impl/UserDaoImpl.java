package dao.impl;

import entity.Country;
import entity.User;
import entity.UserSkill;
import dao.inter.AbstractDAO;
import dao.inter.UserDaoInter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    private User getUser(ResultSet resultSet) throws Exception {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        String phone = resultSet.getString("phone");
        String email = resultSet.getString("email");
        int nationalityId = resultSet.getInt("nationality_id");
        int birthPlaceId = resultSet.getInt("birthplace_id");
        Date birthDate = resultSet.getDate("birthdate");
        String nationalityStr = resultSet.getString("nationality");
        String birthPlaceStr = resultSet.getString("birthplace");
        String profileDescription = resultSet.getString("profile_description");

        Country nationality = new Country(nationalityId, null, nationalityStr);
        Country birthPlace = new Country(birthPlaceId, birthPlaceStr, null);
        return new User(id, name, surname, phone, email, birthDate, nationality, birthPlace, null, profileDescription);

    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<User>();
        try ( Connection connection = connect()) {

            Statement statement = connection.createStatement();
            statement.execute(""
                    + "SELECT "
                    + "	u.*,"
                    + "	c.name as birthplace,"
                    + "	n.nationality "
                    + "	"
                    + "FROM"
                    + "	USER as u"
                    + "	LEFT JOIN country n ON u.nationality_id = n.id"
                    + "	LEFT JOIN country c ON u.birthplace_id = c.id");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                User user = getUser(resultSet);
                userList.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return userList;
    }

    @Override
    public boolean updateUser(User user) {
        try ( Connection connection = connect()) {

            PreparedStatement preparedStatement = connection.prepareStatement("update user set name = ?,surname = ?,phone = ?,email = ?,profile_description = ?,birthDate = ? where id = ?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getProfileDesription());
            preparedStatement.setDate(6, user.getBirthDate());
            
            preparedStatement.setInt(7, user.getId());

            return preparedStatement.execute();
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUser(int id) {
        try ( Connection connection = connect()) {

            Statement statement = connection.createStatement();
            return statement.execute("delete from user where id =" + id);
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public User getById(int userId) {

        User user = null;
        try ( Connection connection = connect()) {

            Statement statement = connection.createStatement();
            statement.execute(""
                    + "SELECT "
                    + "	u.*,"
                    + "	c.name as birthplace,"
                    + "	n.nationality"
                    + "	"
                    + "FROM"
                    + "	USER as u"
                    + "	LEFT JOIN country n ON u.nationality_id = n.id"
                    + "	LEFT JOIN country c ON u.birthplace_id = c.id "
                    + "where u.id = " + userId);
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                user = getUser(resultSet);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean userAdd(User user) {
        try ( Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user(name,surname,phone,email,profile_description,birthdate) values(?,?,?,?,?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getProfileDesription());
            preparedStatement.setDate(6, user.getBirthDate());
            return preparedStatement.execute();
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

}
