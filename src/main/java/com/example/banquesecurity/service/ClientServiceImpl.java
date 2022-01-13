package com.example.banquesecurity.service;

import com.example.banquesecurity.model.Client;
import com.example.banquesecurity.model.Role;
import com.example.banquesecurity.repositories.ClientRepository;
import com.example.banquesecurity.repositories.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService,UserDetailsService {

    private ClientRepository clientRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.clientRepository = clientRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client existedUser = clientRepository.findClientByNom(username);
        if (existedUser == null) {
            throw new UsernameNotFoundException("user not found in database");
        } else {
            log.info("user found in database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        existedUser.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getAuthority())));
        return new org.springframework.security.core.userdetails.User(existedUser.getNom(), existedUser.getPassword(), authorities);
    }

    @Override
    public Client saveUser(Client client) {
        Role role = roleRepository.findRoleByAuthority("ROLE_USER");
        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        client.getRoles().add(role);
        return clientRepository.save(client);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Client getUser(String username) {
        return clientRepository.findClientByNom(username);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        Client user = clientRepository.findClientByNom(username);
        Role role = roleRepository.findRoleByAuthority(roleName);
        user.getRoles().add(role);
    }

    @Override
    public void deleteClient(Integer clientId) {
        Optional<Client> client=clientRepository.findById(clientId);
        if(!client.isPresent()){
            throw new UsernameNotFoundException("the client with id "+ clientId + "does not exist");
        }
        clientRepository.delete(client.get());
    }

    @Override
    public List<Client> getUsers() {
        return clientRepository.findAll();
    }
}
