package duoc.mediturn.service;

import duoc.mediturn.exception.PacienteAsociadoNoEncontradoException;
import duoc.mediturn.exception.PacienteNoEncontradoException;
import duoc.mediturn.exception.SolicitudNoEncontradaException;
import duoc.mediturn.model.Paciente;
import duoc.mediturn.model.Solicitud;
import duoc.mediturn.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private PacienteService pacienteService;

    public Solicitud saveSolicitud(Solicitud solicitud) {

        Paciente pacienteAsociado = obtenerPacienteAsociado(solicitud.getIdPaciente());


        if (pacienteAsociado == null) {
            throw new RuntimeException("Paciente no encontrado con ID: " + solicitud.getIdPaciente());
        }


        solicitud.setNombrePaciente(pacienteAsociado.getNombrePaciente());
        solicitud.setRutPaciente(pacienteAsociado.getRutPaciente());


        return solicitudRepository.guardar(solicitud);
    }

    //getSolicitudId
    public Solicitud getSolicitudPorId(int id){
        Solicitud solicitud = solicitudRepository.buscarSolicitudId(id);

        if (solicitud==null){
            throw new SolicitudNoEncontradaException("Solicitud: " + id + " no encontrada");
        }

        Paciente pacienteAsociado = obtenerPacienteAsociado(solicitud.getIdPaciente());
        solicitud.setNombrePaciente(pacienteAsociado.getNombrePaciente());
        return solicitud;
    } //fin getSolicitudId

    //getSolicitudes
    public List<Solicitud> getSolicitudes(){
        List<Solicitud> listaSolicitudes = solicitudRepository.obtenerSolicitud();

        for (Solicitud solicitud : listaSolicitudes){
            Paciente pacienteAsociado = obtenerPacienteAsociado(solicitud.getIdPaciente());
            solicitud.setNombrePaciente(pacienteAsociado.getNombrePaciente());
        }
        return listaSolicitudes;
    }//fin getSolicitudes




    //update Solicitud
    public Solicitud updateSolicitud(int id,Solicitud solicitud){
        Solicitud solicitudActual = solicitudRepository.buscarSolicitudId(id);
        if(solicitudActual == null){
            throw new SolicitudNoEncontradaException("Solicitud con ID : " + id + "no encontrada");
        }// fin if
        Paciente pacienteAsociado= obtenerPacienteAsociado(solicitud.getIdPaciente());

        Solicitud solicitudActualizada = solicitudRepository.actualizar(id,solicitud);
        solicitudActualizada.setNombrePaciente(pacienteAsociado.getNombrePaciente());
        solicitudActualizada.setRutPaciente(pacienteAsociado.getRutPaciente());

        return solicitudActualizada;

    }//






    public boolean eliminarSolicitud(int idSolicitud) {

        Solicitud solicitud = solicitudRepository.buscarSolicitudId(idSolicitud);

        if (solicitud == null) {

            throw new RuntimeException("Solicitud con ID " + idSolicitud + " no encontrada");
        }

        return solicitudRepository.eliminar(idSolicitud);
    }


    // Obtener pacientes
    private Paciente obtenerPacienteAsociado(int idPaciente) {
        try {
            return pacienteService.getIdPaciente(idPaciente);
        } catch (PacienteNoEncontradoException ex) {
            throw new PacienteAsociadoNoEncontradoException("El paciente asociado  a la solicitud no existe ");
        }

    }// fin metodo


    public List<Solicitud> getSolicitudDeMayorPrioridad() {
        return solicitudRepository.listarSolicitudesPorPrioridad();
    } // Fin Metodo



}//fin SolicitudService
