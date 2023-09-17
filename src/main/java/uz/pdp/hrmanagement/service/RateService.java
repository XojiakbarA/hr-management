package uz.pdp.hrmanagement.service;

import uz.pdp.hrmanagement.entity.Rate;

import java.util.List;
import java.util.UUID;

public interface RateService {
    List<Rate> findAll();
    Rate findById(UUID id);
    Rate save(Rate rate);
}
