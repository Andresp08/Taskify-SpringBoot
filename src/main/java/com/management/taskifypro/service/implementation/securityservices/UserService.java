package com.management.taskifypro.service.implementation.securityservices;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.taskifypro.model.entity.Role;
import com.management.taskifypro.model.entity.User;
import com.management.taskifypro.model.enums.ERole;
import com.management.taskifypro.model.request.SignupRequest;
import com.management.taskifypro.repository.RoleRepository;
import com.management.taskifypro.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User not found with username ", username)
        ));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList())
        );
    }

    public User createNewUser(SignupRequest signupRequestDto) {
        User user = new User();

        user.setUsername(signupRequestDto.getUsername());
        user.setEmail(signupRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
        Collection<Role> roles = convertToRoles(signupRequestDto.getRoles());
        user.setRoles(roles);
        return userRepository.save(user);
    }

    private Collection<Role> convertToRoles(Collection<ERole> eRoles) {
    return eRoles.stream()
            .map(eRole -> roleRepository.findByName(eRole))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());
}

    
}
