package duoc.mediturn.repository;

import duoc.mediturn.model.Solicitud;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SolicitudRepository {
    private List<Solicitud> listaSolicitudes = new ArrayList<>();
    private int ultimoId=0;

    // Guardar
    public Solicitud guardar(Solicitud solicitud){
        ultimoId++;
        solicitud.setIdSolicitud(ultimoId);
        listaSolicitudes.add(solicitud);
        return solicitud;
    }

    // Buscar Solicitud por id
    public Solicitud buscarSolicitudId(int id){
        for(Solicitud solicitud:listaSolicitudes){
            if(solicitud.getIdSolicitud()==id){
                return solicitud;
            }// fin if
        }// fin for
        return null;
    }



    // Obtener Solicitudes

    public List<Solicitud >obtenerSolicitud(){
        return listaSolicitudes;
    }


    //Actualizar

    public Solicitud actualizar(int id,Solicitud solicitudActualizado){
        for(int i = 0;  i < listaSolicitudes.size(); i++){
            if(listaSolicitudes.get(i).getIdSolicitud()==id){
                solicitudActualizado.setIdSolicitud(id);
                listaSolicitudes.set(i,solicitudActualizado);
                return solicitudActualizado;
            }// fin if

        }// fin for
        return null;
    }

        // Eliminar
        public boolean eliminar(int id) {

            return listaSolicitudes.removeIf(s -> s.getIdSolicitud() == id);
        }

    public List<Solicitud> listarSolicitudesPorPrioridad() {
        List<Solicitud> solicitudesOrdenadas = new ArrayList<>(listaSolicitudes);

        for (int i = 0; i < solicitudesOrdenadas.size() - 1; i++) {
            for (int j = i + 1; j < solicitudesOrdenadas.size(); j++) {
                if (solicitudesOrdenadas.get(i).getNivelPrioridad() > solicitudesOrdenadas.get(j).getNivelPrioridad()) {
                    Solicitud temporal = solicitudesOrdenadas.get(i);
                    solicitudesOrdenadas.set(i, solicitudesOrdenadas.get(j));
                    solicitudesOrdenadas.set(j, temporal);
                } // fin IF
            } // fin FOR
        } // fin FOR

        return solicitudesOrdenadas;
    } // fin metodo





}// fin clase
