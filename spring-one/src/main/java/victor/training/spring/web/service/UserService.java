package victor.training.spring.web.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import victor.training.spring.web.domain.User;
import victor.training.spring.web.repo.UserRepo;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService  {
    private final UserRepo userRepo;


    public long countUsers() {
        return userRepo.count();
    }

    // TODO 1 Avoid extra query - get from cache
    // TODO 2 Avoid inconsistencies - evict cache
    // Scenario: get(=2), create, get(=2) STALE!!!
    // TODO 3 Avoid inconsistencies on multiple machines - use Redis as centralized cache
    // Scenario: N1.get(=2), N2.get(=2), N1.create, N1.get(=3), N2.get(=2) STALE!!!
    // TODO 4 At restart, clean the Redis cache (with CLR)
    public void createUser() {
        userRepo.save(new User());
    }

}

