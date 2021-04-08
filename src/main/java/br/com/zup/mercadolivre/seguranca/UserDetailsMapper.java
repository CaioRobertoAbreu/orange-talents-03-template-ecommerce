package br.com.zup.mercadolivre.seguranca;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

public interface UserDetailsMapper {

    UserDetails map(Object usuario);
}
