package space.devincoopers.portfolio.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import space.devincoopers.portfolio.mapper.UserMapper;
import space.devincoopers.portfolio.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final UserMapper userMapper;

    public UserDetailsServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);
        logger.debug("What is this User: {}", user);
            if (user == null) {
                throw new UsernameNotFoundException("User Not Found with username: " + username);
            }
        return UserDetailsImpl.build(user); // UserDetailsImpl is a custom class that implements UserDetails
    }
}
