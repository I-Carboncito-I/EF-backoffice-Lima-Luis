package pe.edu.i202221574.EF_backoffice_Lima_Luis.service;

import pe.edu.i202221574.EF_backoffice_Lima_Luis.dto.CarCreateDto;
import pe.edu.i202221574.EF_backoffice_Lima_Luis.dto.CarDetailDto;
import pe.edu.i202221574.EF_backoffice_Lima_Luis.dto.CarDto;

import java.util.List;
import java.util.Optional;

public interface FabricService {

    List<CarDto> getAllCars();

    Optional<CarDto> getCarById(Integer id);

    Optional<CarDetailDto> getCarDetailById(Integer id);

    void createCar(CarCreateDto carCreateDto);

    boolean updateCar(CarDto carDto);

    boolean deleteCar(Integer id);
}

