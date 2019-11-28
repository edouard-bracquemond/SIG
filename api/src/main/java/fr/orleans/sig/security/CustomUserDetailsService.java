package fr.orleans.sig.security;

import fr.orleans.sig.exception.ResourceNotFoundException;
import fr.orleans.sig.model.user.User;
import fr.orleans.sig.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    private Map<Long, User> inMemoryUsers = new HashMap<Long, User>();

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        // Let people login with either username or email
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
        );

        inMemoryUsers.put(user.getId(), user);

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {

        //Pour eviter les requetes databases à chaque envoi de token.
        User user = inMemoryUsers.get(id);

        if (user!=null){
            return UserPrincipal.create(user);
        }else {
            user = userRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("User", "id", id)
            );
            inMemoryUsers.put(user.getId(), user);
            return UserPrincipal.create(user);
        }
    }
}