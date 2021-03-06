package victor.training.spring.web.domain;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.*;

import static java.util.Collections.unmodifiableList;
import static java.util.Collections.unmodifiableSet;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private UserProfile profile;
    @ElementCollection
    private Set<Long> managedTeacherIds = new HashSet<>();
    public User() {
    }
    public User(String username, UserProfile profile, List<Long> managedTeacherIds) {
        this.username = username;
        this.profile = profile;
        this.managedTeacherIds = new HashSet<>(managedTeacherIds);
    }

    public String getUsername() {
        return username;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public Set<Long> getManagedTeacherIds() {
        return unmodifiableSet(managedTeacherIds);
    }

    public Long getId() {
        return id;
    }
}
