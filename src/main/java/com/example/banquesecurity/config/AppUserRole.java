package com.example.banquesecurity.config;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.example.banquesecurity.config.AppUserPermission.USER_READ;
import static com.example.banquesecurity.config.AppUserPermission.USER_WRITE;

public enum AppUserRole
{
    // Ajouter les permission de lecture et ecriture

    ADMIN(Sets.newHashSet(USER_READ,USER_WRITE)),
    USER(Sets.newHashSet(USER_READ));

    private final Set<AppUserPermission> permissions;

    AppUserRole(Set<AppUserPermission> permissions) {
        this.permissions = permissions;
    }
}


