package com.example.banquesecurity.controller;

import com.example.banquesecurity.model.Client;
import com.example.banquesecurity.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/UserManagement")
public class UserManagmentController
{
    private ClientService clientService;

    @Autowired
    public UserManagmentController(ClientService clientService) {
        this.clientService = clientService;
    }

    public List<Client> clients = new ArrayList<Client>(Arrays.asList(
         new Client(1, "anouar", "bourja"),
         new Client(1, "mohamed amine", "benaicha")
    ));

    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:read,user:write')")
    public List<Client> getAllClients() {
        System.out.println("getAllClients");
        return clientService.getUsers();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public void addNewclient(@RequestBody Client client) {
        clientService.saveUser(client);
        System.out.println("registerNewClient");
        System.out.println(client);
    }

    @DeleteMapping(path = "{clientId}")
    @PreAuthorize("hasAuthority('user:write')")
    public void deleteClient(@PathVariable("clientId") Integer clientId) {
        clientService.deleteClient(clientId);
        System.out.println("deleteClient");
        System.out.println(clientId);
    }

    @PutMapping(path = "{clientId}")
    @PreAuthorize("hasAuthority('user:write')")
    public void updateClient(@PathVariable("clientId") Integer clientId, @RequestBody Client client) {
        System.out.println("updateClient");
        System.out.println(String.format("%s %s", clientId, client));
    }

}
