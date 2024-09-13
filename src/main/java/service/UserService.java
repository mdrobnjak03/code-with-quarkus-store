package service;

import enums.UserStatus;
import exception.UserException;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import model.Order;
import model.User;
import org.hibernate.Hibernate;

import java.util.List;

@Dependent
public class UserService {

    @Inject
    EntityManager em;

    @Transactional
    public User createUser(User user) throws UserException {

        List<User> users = getAllUsers();

        if (users.contains(user)) {
            throw new UserException(UserStatus.EXISTS.getLabel());
        }
        return em.merge(user);
    }


    @Transactional
    public List<User> getAllUsers() {
        List<User> users = em.createNamedQuery(User.GET_ALL_USERS, User.class).getResultList();
        populateUserData(users);
        return users;
    }

    @Transactional
    public List<User> getUsersByEmail(String email) {
        List<User> users = em.createNamedQuery(User.GET_USERS_BY_EMAIL, User.class)
                .setParameter("email", email).getResultList();
        populateUserData(users);
        return users;
    }

    @Transactional
    public boolean deleteUser(Long id) {
        User user = em.find(User.class, id);

        if (user == null)
            return false;

        em.remove(user);
        return true;
    }

    private void populateUserData(List<User> users) {
        for (User user : users) {
            Hibernate.initialize(user.getOrders());

            for (Order order : user.getOrders()) {
                Hibernate.initialize(order.getProducts());
            }
        }
    }

    @Transactional
    public List<Order> getAllForUser(User user) {
        return em.createNamedQuery(Order.GET_ALL_FOR_USER, Order.class).setParameter("id", user.getId())
                .getResultList();
    }



}
