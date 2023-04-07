package org.learning.springlamiapizzeriacrud.security;

import org.learning.springlamiapizzeriacrud.model.User;
import org.learning.springlamiapizzeriacrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DBUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByNickname(username);
        if (user.isPresent()){
            return new DBUserDetails(user.get());
        } else {
            throw new UsernameNotFoundException("User "+ username +" not found");
        }
    }
}
