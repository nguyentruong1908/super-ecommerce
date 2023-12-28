package com.example.authenticateservce.config;

import com.example.authenticateservce.config.authorize.CustomUserDetails;
import com.example.authenticateservce.entity.UserEntity;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            return Optional.of(customUserDetails.getUserEntity().getId());
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