package pe.edu.i202221574.EF_backoffice_Lima_Luis.dto;

public record CarDto(
        Integer carId,
        String make,
        String model,
        Integer year,
        String licensePlate,
        String color
) {}

