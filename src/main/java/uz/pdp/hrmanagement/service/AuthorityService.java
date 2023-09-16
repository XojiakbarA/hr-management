package uz.pdp.hrmanagement.service;

import uz.pdp.hrmanagement.entity.Authority;
import uz.pdp.hrmanagement.entity.enums.Role;

import java.util.UUID;

public interface AuthorityService {
    Authority findById(UUID id);
    Authority findByName(Role name);
    Authority save(Authority authority);
}
