package users.user;

import users.Person;

import java.util.Objects;
import java.util.UUID;

public class User extends Person {
    private UUID userId;
    private String oneTimePassword;

    public User(String firstName, String lastName) {
        super(firstName, lastName);
        this.userId = UUID.randomUUID();
    }

    public User(String firstName, String lastName, UUID userId) {
        super(firstName, lastName);
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getOneTimePassword() {
        return oneTimePassword;
    }

    public void setOneTimePassword(String oneTimePassword) {
        this.oneTimePassword = oneTimePassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return userId != null ? userId.hashCode() : 0;
    }
}
