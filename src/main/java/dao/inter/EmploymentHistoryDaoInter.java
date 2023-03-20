package dao.inter;

import entity.EmploymentHistory;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface EmploymentHistoryDaoInter {

    public List<EmploymentHistory> getEmploymentHistoryByUserId(int userId);
}
