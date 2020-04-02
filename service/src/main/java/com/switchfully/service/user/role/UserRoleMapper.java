package com.switchfully.service.user.role;

import com.switchfully.domain.user.feature.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserRoleMapper {
    public UserRole toUserRole(UserRoleDto userRoleDto) {
        return UserRole.valueOf(userRoleDto.name());
    }

    public UserRoleDto toDto(UserRole userRole) {
        return UserRoleDto.valueOf(userRole.name());
    }
}
