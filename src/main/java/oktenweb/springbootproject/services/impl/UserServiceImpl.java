package oktenweb.springbootproject.services.impl;

import oktenweb.springbootproject.dao.UserDAO;
import oktenweb.springbootproject.models.User;
import oktenweb.springbootproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public void save(User user) {
        if (user != null) userDAO.save(user);

    }

    @Override
    public List<User> findAll() {
        //....
        return null;
    }

    @Override
    public User findOneById(Integer id) {
        //......
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.findByUsername(username);
    }
}
