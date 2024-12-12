package pe.edu.i202221574.EF_backoffice_Lima_Luis.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.i202221574.EF_backoffice_Lima_Luis.dto.CarCreateDto;
import pe.edu.i202221574.EF_backoffice_Lima_Luis.dto.CarDetailDto;
import pe.edu.i202221574.EF_backoffice_Lima_Luis.dto.CarDto;
import pe.edu.i202221574.EF_backoffice_Lima_Luis.response.*;
import pe.edu.i202221574.EF_backoffice_Lima_Luis.service.FabricService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fabric")
public class FabricApi {

    @Autowired
    private FabricService fabricService;

    @GetMapping("/all")
    public FindCarsResponse findCars(@RequestParam(value = "id", defaultValue = "0") String id) {
        try {
            if (Integer.parseInt(id) > 0) {
                Optional<CarDto> optional = fabricService.getCarById(Integer.parseInt(id));
                if (optional.isPresent()) {
                    CarDto carDto = optional.get();
                    return new FindCarsResponse("01", "", List.of(carDto));
                } else {
                    return new FindCarsResponse("02", "No se encontró el auto", null);
                }
            } else {
                List<CarDto> cars = fabricService.getAllCars();
                if (!cars.isEmpty()) {
                    return new FindCarsResponse("01", "", cars);
                } else {
                    return new FindCarsResponse("03", "Lista de coches no encontrada", cars);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new FindCarsResponse("99", "Servicio no encontrado", null);
        }
    }

    @GetMapping("/detail")
    public FindCarByIdResponse findCarById(@RequestParam(value = "id", defaultValue = "0") String id) {
        try {
            if (Integer.parseInt(id) > 0) {
                Optional<CarDetailDto> optional = fabricService.getCarDetailById(Integer.parseInt(id));
                if (optional.isPresent()) {
                    CarDetailDto carDetailDto = optional.get();
                    return new FindCarByIdResponse("01", "", carDetailDto);
                } else {
                    return new FindCarByIdResponse("02", "Coche no encontrado", null);
                }
            } else {
                return new FindCarByIdResponse("03", "Parámetro no encontrado", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new FindCarByIdResponse("99", "Servicio no encontrado", null);
        }
    }


    @PostMapping("/create")
    public CreateCarResponse createCar(@RequestBody CarCreateDto carCreateDto) {
        try {
            fabricService.createCar(carCreateDto);
            return new CreateCarResponse("01", "Coche creado con éxito");
        } catch (Exception e) {
            e.printStackTrace();
            return new CreateCarResponse("99", "Servicio no encontrado");
        }
    }



    @PostMapping("/update")
    public UpdateCarResponse updateCar(@RequestBody CarDto carDto) {
        try {
            if (fabricService.updateCar(carDto)) {
                return new UpdateCarResponse("01", "Coche actualizado con éxito");
            } else {
                return new UpdateCarResponse("02", "Coche no encontrado");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new UpdateCarResponse("99", "Coche no encontrado");
        }
    }

    @DeleteMapping("/delete")
    public DeleteCarResponse deleteCar(@RequestParam(value = "id", defaultValue = "0") String id) {
        try {
            if (fabricService.deleteCar(Integer.parseInt(id))) {
                return new DeleteCarResponse("01", "Coche eliminado con éxito");
            } else {
                return new DeleteCarResponse("02", "Coche no encontrado");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new DeleteCarResponse("99", "Servicio no encontrado");
        }
    }
}
