package dao.inter;

import entity.User;
import entity.UserSkill;
import java.util.List;

public interface UserDaoInter {

    public List<User> getAll();

    public boolean userAdd(User user);

    public User getById(int userId);

    public boolean updateUser(User user);

    public boolean removeUser(int id);

}
