package pe.edu.i202221574.EF_backoffice_Lima_Luis.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.i202221574.EF_backoffice_Lima_Luis.dto.CarDto;
import pe.edu.i202221574.EF_backoffice_Lima_Luis.service.FabricService;


import java.util.List;

@Controller
@RequestMapping("/fabric")
public class FabricController {

    @Autowired
    private FabricService fabricService;

    @GetMapping("/start")
    public String start(Model model) {
        try {
            List<CarDto> cars = fabricService.getAllCars();
            model.addAttribute("cars", cars);
            model.addAttribute("error", "");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Ocurrió un error al obtener los vehículos");
        }
        return "index";
    }
}

