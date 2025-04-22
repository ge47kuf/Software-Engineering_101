package de.tum.in.ase.eist;

import java.util.Objects;

public class UserObjectTuple {
    private User user;
    private Object object;

    public UserObjectTuple(User user, Object object) {
        this.user = user;
        this.object = object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserObjectTuple that = (UserObjectTuple) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(object, that.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, object);
    }

    public User getUser(){
        return this.user;
    }

    public Object getObject() {
        return this.object;
    }
}
