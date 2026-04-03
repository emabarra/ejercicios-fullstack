package duoc.mediturn.service;

import duoc.mediturn.exception.PacienteNoEncontradoException;
import duoc.mediturn.exception.RutDuplicadoException;
import duoc.mediturn.model.Paciente;
import duoc.mediturn.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    //get pacientes
    public List<Paciente> getPacientes(){
        return pacienteRepository.obtenerPacientes();
    }//fin get pacientes

    //save pacientes
    public Paciente savePaciente(Paciente paciente){
        Paciente pacienteExistente = pacienteRepository.buscarPacientePorRut(paciente.getRutPaciente());

        if(pacienteExistente != null){
            throw new RutDuplicadoException("Ya existe un paciente con ese RUT");
        }
        return pacienteRepository.agregar(paciente);
    } //fin save pacientes

    //get Paciente por id
    public Paciente getIdPaciente(int id) {
        Paciente paciente = pacienteRepository.buscarPorId(id);
        if (paciente == null){
            throw new PacienteNoEncontradoException("Paciente con id: " + id + " no encontrado");
        }
        return paciente;
    } //fin get paciente id

    //delete paciente
    public String deletePaciente(int id ){
        Paciente paciente = pacienteRepository.buscarPorId(id);
        if (paciente == null){
            throw new PacienteNoEncontradoException("Paciente con id: " + id + " no encontrado");
        }
        pacienteRepository.eliminarPaciente(id);
        return "Paciente Eliminado Correctamente";
    }// fin delete paciente

    //total pacientes
    public int totalPacientes(){
        return pacienteRepository.totalPacientes();
    }//total pacientes


    // Update Paciente
    public Paciente updatePaciente(Paciente paciente) {
        Paciente pacienteActual = pacienteRepository.buscarPorId(paciente.getIdPaciente());

        if (pacienteActual == null) {
            throw new PacienteNoEncontradoException("Paciente con ID : " + paciente.getIdPaciente() + " no encontrado.");
        } // Fin If
        return pacienteRepository.actualizar(paciente);
    } // Fin metodo



    // Paciente por rut
    public Paciente getRutPaciente(String rut) {
        Paciente paciente = pacienteRepository.buscarPacientePorRut(rut);
        if (paciente == null){
            throw new PacienteNoEncontradoException("Paciente con id: " + rut + " no encontrado");
        }
        return paciente;
    } //fin get paciente id

}
