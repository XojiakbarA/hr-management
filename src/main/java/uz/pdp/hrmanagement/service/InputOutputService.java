package uz.pdp.hrmanagement.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import uz.pdp.hrmanagement.dto.InputOutputDTO;
import uz.pdp.hrmanagement.entity.InputOutput;

public interface InputOutputService {
    Page<InputOutputDTO> getInputOutputsByUserId(Pageable pageable, UUID userId);
    InputOutputDTO input(String email);
    InputOutputDTO output(UUID id);
    InputOutput findById(UUID id);
    InputOutput save(InputOutput inputOutput);
}
