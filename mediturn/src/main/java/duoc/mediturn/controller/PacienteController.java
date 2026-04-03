package duoc.mediturn.controller;
import duoc.mediturn.model.Paciente;
import duoc.mediturn.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/todos")
    public List<Paciente>listarPacientes(){
        return pacienteService.getPacientes();
    }

    @PostMapping
    public String agregarPaciente(@Valid @RequestBody Paciente paciente){
        pacienteService.savePaciente(paciente);
        return "Paciente Agregado Correctamente";
    }

    @PutMapping("{id}")
    public Paciente actualizarPaciente(@PathVariable int id, @Valid @RequestBody Paciente paciente){
        return pacienteService.updatePaciente(paciente);
    }

    @GetMapping("{id}")
    public Paciente buscarPacienteId(@PathVariable int id){
        return pacienteService.getIdPaciente(id);
    }

    @DeleteMapping("{id}")
    public String eliminarPaciente(@PathVariable int id){
        return pacienteService.deletePaciente(id);
    }

    @GetMapping("/total")
    public int totalPacientes(){
        return pacienteService.totalPacientes();
    }

    @GetMapping("/buscar/{rut}")
    public Paciente buscarPacienteRut(@PathVariable  String rut){
        return pacienteService.getRutPaciente(rut);
    }

}
