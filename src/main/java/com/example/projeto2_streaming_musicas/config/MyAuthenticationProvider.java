package com.example.projeto2_streaming_musicas.config;

import com.example.projeto2_streaming_musicas.model.Role;
import com.example.projeto2_streaming_musicas.model.User;
import com.example.projeto2_streaming_musicas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate( Authentication authentication ) throws AuthenticationException
    {
        String htmlFormUser = authentication.getName();
        String htmlFormPassword = authentication.getCredentials()
                .toString();

        User fetchedUser = userRepository.findUserWithRoleByName( htmlFormUser )
                                         .orElse( null );

        if ( ( fetchedUser != null ) &&
                ( fetchedUser.getId() > 0 ) &&
                passwordEncoder.matches( htmlFormPassword, fetchedUser.getUserPassword() ) )
        {
            return new UsernamePasswordAuthenticationToken(
                    fetchedUser.getUserName(), null, getGrantedAuthorities( fetchedUser.getUserRole() )
            );
        } else
        {
            throw new BadCredentialsException( "Invalid credentials!" );
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities( Role role )
    {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add( new SimpleGrantedAuthority( "ROLE_" + role.getRole()
                .toUpperCase() ) );
        return grantedAuthorities;
    }

    @Override
    public boolean supports( Class<?> authentication )
    {
        return authentication.equals( UsernamePasswordAuthenticationToken.class );
    }

}
