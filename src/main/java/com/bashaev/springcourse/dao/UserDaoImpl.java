package com.bashaev.springcourse.dao;

import com.bashaev.springcourse.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Рустам
 */

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext()
    private EntityManager entityManager;


    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void editUser(User updateUser) {
        entityManager.merge(updateUser);
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.createQuery("delete from User where id=: id")
                .setParameter("id", id)
                .executeUpdate();

    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("From User").getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }
}
