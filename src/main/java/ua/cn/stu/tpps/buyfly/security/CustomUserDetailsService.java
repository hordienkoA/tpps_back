package ua.cn.stu.tpps.buyfly.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.cn.stu.tpps.buyfly.domain.User;
import ua.cn.stu.tpps.buyfly.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("email not found :" + email);
        }

        return new org.springframework.security.core.userdetails.User(
            email,
            passwordEncoder.encode(user.getPassword()),
            true, true, true, true,
            getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        //todo also we can set user scopes using "SCOPE_{SCOPE}"
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserRole()));

        return authorities;
    }

}
