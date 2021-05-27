package com.diplom.padding.mapper;

import com.diplom.padding.entity.app.Role;
import com.diplom.padding.entity.moodle.RoleMoodle;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public Role toApp(RoleMoodle roleMoodle) {
        return new Role(roleMoodle);
    }
}