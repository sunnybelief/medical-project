package com.yufeimen.application.secruity;

import com.yufeimen.application.model.MedicUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(MedicUser user) {
        return new JwtUser(
                user.getId(),
                user.getAccountName(),
                user.getAccountKey(),
                mapToGrantedAuthorities(asList(user.getAccountRole()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}

