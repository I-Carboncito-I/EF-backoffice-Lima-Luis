package pe.edu.i202221574.EF_backoffice_Lima_Luis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.i202221574.EF_backoffice_Lima_Luis.dto.CarDetailDto;
import pe.edu.i202221574.EF_backoffice_Lima_Luis.dto.CarDto;
import pe.edu.i202221574.EF_backoffice_Lima_Luis.entity.Car;
import pe.edu.i202221574.EF_backoffice_Lima_Luis.repository.CarRepository;
import pe.edu.i202221574.EF_backoffice_Lima_Luis.service.FabricService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FabricServiceImpl implements FabricService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<CarDto> getAllCars() {
        return ((List<Car>) carRepository.findAll()).stream()
                .map(car -> new CarDto(
                        car.getCarId(),
                        car.getMake(),
                        car.getModel(),
                        car.getYear(),
                        car.getLicensePlate(),
                        car.getColor()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CarDto> getCarById(Integer id) {
        return carRepository.findById(id)
                .map(car -> new CarDto(
                        car.getCarId(),
                        car.getMake(),
                        car.getModel(),
                        car.getYear(),
                        car.getLicensePlate(),
                        car.getColor()
                ));
    }

    @Override
    public Optional<CarDetailDto> getCarDetailById(Integer id) {
        return carRepository.findById(id)
                .map(car -> new CarDetailDto(
                        car.getCarId(),
                        car.getMake(),
                        car.getModel(),
                        car.getYear(),
                        car.getVin(),
                        car.getLicensePlate(),
                        car.getOwnerName(),
                        car.getOwnerContact(),
                        car.getPurchaseDate(),
                        car.getMileage(),
                        car.getEngineType(),
                        car.getColor(),
                        car.getInsuranceCompany(),
                        car.getInsurancePolicyNumber(),
                        car.getRegistrationExpirationDate(),
                        car.getServiceDueDate()
                ));
    }

    @Override
    public void createCar(CarDto carDto) {
        Car car = new Car();
        car.setMake(carDto.make());
        car.setModel(carDto.model());
        car.setYear(carDto.year());
        car.setLicensePlate(carDto.licensePlate());
        car.setColor(carDto.color());
        carRepository.save(car);
    }

    @Override
    public boolean updateCar(CarDto carDto) {
        Optional<Car> optionalCar = carRepository.findById(carDto.carId());
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            car.setMake(carDto.make());
            car.setModel(carDto.model());
            car.setYear(carDto.year());
            car.setLicensePlate(carDto.licensePlate());
            car.setColor(carDto.color());
            carRepository.save(car);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCar(Integer id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
