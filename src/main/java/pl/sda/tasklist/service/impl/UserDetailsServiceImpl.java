package pl.sda.tasklist.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sda.tasklist.dao.UserRepository;
import pl.sda.tasklist.model.UserEntity;
import pl.sda.tasklist.model.UserRoleEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Qualifier("userDetailsImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<UserEntity> byUserName = userRepository.findUserWithRolesByUserName(userName);

        UserEntity userEntity = byUserName.orElseThrow(() ->
                new UsernameNotFoundException("Not found user with name: " + userName));

        return new User(userEntity.getUserName(), userEntity.getPassword(), grantedAuthorities(userEntity.getRoles()));
    }

    private List<GrantedAuthority> grantedAuthorities(Set<UserRoleEntity> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRoleEntity role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authorities;
    }
}
