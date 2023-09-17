package uz.pdp.hrmanagement.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import uz.pdp.hrmanagement.dto.InputOutputDTO;
import uz.pdp.hrmanagement.entity.InputOutput;
import uz.pdp.hrmanagement.entity.User;
import uz.pdp.hrmanagement.mapper.InputOutputMapper;
import uz.pdp.hrmanagement.repository.InputOutputRepository;
import uz.pdp.hrmanagement.service.InputOutputService;
import uz.pdp.hrmanagement.service.UserService;

@Service
public class InputOutputServiceImpl implements InputOutputService {
    @Autowired
    private InputOutputRepository inputOutputRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private InputOutputMapper inputOutputMapper;

    @Override
    public Page<InputOutputDTO> getInputOutputsByUserId(Pageable pageable, UUID userId) {
        return inputOutputRepository.findAllByUserId(pageable, userId).map(io -> inputOutputMapper.mapToInputOutputDTO(io));
    }

    @Override
    public InputOutputDTO input(String email) {
        User user = userService.findByEmail(email);

        InputOutput inputOutput = new InputOutput();

        inputOutput.setUser(user);
        inputOutput.setVisitedAt(new Date());

        return inputOutputMapper.mapToInputOutputDTO(save(inputOutput));
    }

    @Override
    public InputOutputDTO output(UUID id) {
        InputOutput inputOutput = findById(id);

        inputOutput.setLeftAt(new Date());

        return inputOutputMapper.mapToInputOutputDTO(save(inputOutput));
    }

    @Override
    public InputOutput findById(UUID id) {
        return inputOutputRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("InputOutput with id = " + id + " not found")
        );
    }

    @Override
    public InputOutput save(InputOutput inputOutput) {
        return inputOutputRepository.save(inputOutput);
    }
}
