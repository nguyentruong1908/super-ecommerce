package com.example.productservice.config;

import com.example.productservice.entity.UserEntity;
import jakarta.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {

    @Resource
    UserContext userContext;

    @Override
    public Optional<Long> getCurrentAuditor() {

        try {
            return Optional.of(userContext.getId());
        } catch (Exception e) {
            return null;
        }
    }


// ------------------ Use below code for spring security --------------------------

/*class SpringSecurityAuditorAware implements AuditorAware<User> {

	public User getCurrentAuditor() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}

		return ((MyUserDetails) authentication.getPrincipal()).getUser();
	}
}*/
}