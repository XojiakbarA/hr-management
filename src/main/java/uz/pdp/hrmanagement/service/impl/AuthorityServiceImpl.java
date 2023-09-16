package uz.pdp.hrmanagement.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.hrmanagement.entity.Authority;
import uz.pdp.hrmanagement.entity.enums.Role;
import uz.pdp.hrmanagement.repository.AuthorityRepository;
import uz.pdp.hrmanagement.service.AuthorityService;

import java.util.UUID;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;
    @Override
    public Authority findById(UUID id) {
        return authorityRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Authority with id = " + id + " not found")
        );
    }

    @Override
    public Authority findByName(Role name) {
        return authorityRepository.findByName(name).orElseThrow(
                () -> new EntityNotFoundException("Authority with name = " + name + " not found")
        );
    }

    @Override
    public Authority save(Authority authority) {
        return authorityRepository.save(authority);
    }
}
