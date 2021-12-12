
package com.employees.employeesservice.security;

import com.employees.employeesservice.repository.MyUserRepository;

//@Component
public class CustomAuthenticationProvider /*implements AuthenticationProvider */{
    //@Autowired
    private MyUserRepository myUserRepository;

    /*@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        MyUser myUser = myUserRepository.findByUsername(username);
        if (myUser == null) {
            throw new BadCredentialsException("Unknown user" + username);
        }
        if (!password.equals(myUser.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }
        UserDetails principal = User.builder()
                .username(myUser.getUsername())
                .password(myUser.getPassword())
                .roles(myUser.getRole())
                .build();
        return new UsernamePasswordAuthenticationToken(principal, password, principal.getAuthorities());
    }
*/
    /*@Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }*/
}






