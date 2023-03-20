package dao.impl;

import dao.inter.AbstractDAO;
import dao.inter.EmploymentHistoryDaoInter;
import entity.EmploymentHistory;
import entity.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class EmploymentHistoryDaoImpl extends AbstractDAO implements EmploymentHistoryDaoInter {

    private EmploymentHistory getEmploymentHistory(ResultSet resultSet) throws Exception {
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("user_id");
        String header = resultSet.getString("header");
        Date beginDate = resultSet.getDate("begin_date");
        Date endDte = resultSet.getDate("end_date");
        String jobDescription = resultSet.getString("job_description");

        EmploymentHistory employmentHistory = new EmploymentHistory(id, header, beginDate, endDte, jobDescription, new User(userId));
        return employmentHistory;
    }

    @Override
    public List<EmploymentHistory> getEmploymentHistoryByUserId(int userId) {
        List<EmploymentHistory> employmentHistoryList = new ArrayList<EmploymentHistory>();
        try ( Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select"
                    + "*"
                    + "from employment_history"
                    + "where user_id = ?" + userId);
            preparedStatement.setInt(1, userId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                EmploymentHistory employmentHistory = getEmploymentHistory(resultSet);
                employmentHistoryList.add(employmentHistory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employmentHistoryList;
    }

}
