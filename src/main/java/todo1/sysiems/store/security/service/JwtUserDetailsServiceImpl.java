package todo1.sysiems.store.security.service;

import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import todo1.sysiems.store.security.JwtUserFactory;
import todo1.sysiems.store.security.model.User;
import todo1.sysiems.store.security.repository.UserRepository;


@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService,UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
    }

    @Override
    public User createUser(User user) {
        
        user.setEnabled(true);
        user.setLastPasswordResetDate(Date.from(ZonedDateTime.now().toInstant()));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

}
