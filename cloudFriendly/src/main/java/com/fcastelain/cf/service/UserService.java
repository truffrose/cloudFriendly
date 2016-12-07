package com.fcastelain.cf.service;

import com.fcastelain.cf.dao.DaoUser;
import com.fcastelain.cf.model.User;

import java.util.List;

/**
 * Created by fcastelain on 28/11/16.
 */
public enum UserService {

    INSTANCE;

    private static final DaoUser user = DaoUser.INSTANCE;

    public void delete (final long id) {
        user.delete(id);
    }

    public List<User> get() {
        return user.get();
    }

    public User getById(final long id) {
        return user.getById(id);
    }

    public long getNbEntity()  {
        return user.getNbEntity();
    }

    public User update(User obj) {
        return user.update(obj);
    }

    public User add(User obj) {
        return user.add(obj);
    }

    public User isExist(final String name) {
        return user.isExist(name);
    }

}
