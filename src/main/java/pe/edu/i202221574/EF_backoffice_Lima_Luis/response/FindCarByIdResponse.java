package pe.edu.i202221574.EF_backoffice_Lima_Luis.response;

import pe.edu.i202221574.EF_backoffice_Lima_Luis.dto.CarDetailDto;

public record FindCarByIdResponse(
        String code,
        String message,
        CarDetailDto carDetail
) {}

