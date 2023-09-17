package uz.pdp.hrmanagement.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.hrmanagement.dto.RateDTO;
import uz.pdp.hrmanagement.entity.Rate;

@Component
public class RateMapper {
    public RateDTO mapToRateDTO(Rate rate) {
        if (rate == null) return null;

        return RateDTO.builder()
                .id(rate.getId())
                .grade(rate.getGrade())
                .value(rate.getGrade().getValue())
                .build();
    }
}
