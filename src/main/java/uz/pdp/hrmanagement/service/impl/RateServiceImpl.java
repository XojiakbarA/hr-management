package uz.pdp.hrmanagement.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.hrmanagement.entity.Rate;
import uz.pdp.hrmanagement.repository.RateRepository;
import uz.pdp.hrmanagement.service.RateService;

import java.util.UUID;

@Service
public class RateServiceImpl implements RateService {
    @Autowired
    private RateRepository rateRepository;
    @Override
    public Rate findById(UUID id) {
        return rateRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Rate with id = " + id + " not found")
        );
    }

    @Override
    public Rate save(Rate rate) {
        return rateRepository.save(rate);
    }
}
