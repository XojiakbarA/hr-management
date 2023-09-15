package uz.pdp.hrmanagement.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.hrmanagement.dto.AuthorityDTO;
import uz.pdp.hrmanagement.entity.Authority;

@Component
public class AuthorityMapper {
    public AuthorityDTO mapToAuthorityDTO(Authority authority) {
        if (authority == null) return null;

        return AuthorityDTO.builder()
                .id(authority.getId())
                .name(authority.getName())
                .createdAt(authority.getCreatedAt())
                .updatedAt(authority.getUpdatedAt())
                .build();
    }
}
