package repository;

import domain.User;

import java.util.List;

public interface UserRepository {

    User getUserByName (String uName);
    Boolean checkPass (String uName, String pass);
    void add(User user);
    List<User> getAllUserData();
    void updatePremiumStatus (String[] uNameList, String[] checkedList);
}