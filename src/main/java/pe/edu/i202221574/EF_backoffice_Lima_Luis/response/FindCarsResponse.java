package pe.edu.i202221574.EF_backoffice_Lima_Luis.response;

import pe.edu.i202221574.EF_backoffice_Lima_Luis.dto.CarDto;

import java.util.List;

public record FindCarsResponse(
        String code,
        String message,
        List<CarDto> cars
) {}

