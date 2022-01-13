package com.example.banquesecurity.service;

import com.example.banquesecurity.model.Client;
import com.example.banquesecurity.model.Role;

import java.util.List;

public interface ClientService {
    Client saveUser(Client client);
    Role saveRole(Role role);
    Client getUser(String username);
    List<Client> getUsers();
    void addRoleToUser(String username, String roleName);
    void deleteClient(Integer clientId);
}
