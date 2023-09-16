package uz.pdp.hrmanagement.service;

import uz.pdp.hrmanagement.entity.Rate;

import java.util.UUID;

public interface RateService {
    Rate findById(UUID id);
    Rate save(Rate rate);
}
