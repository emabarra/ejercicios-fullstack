package duoc.mediturn.repository;


import duoc.mediturn.model.Paciente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PacienteRepository {

   
    private List<Paciente>listaPaciente = new ArrayList<>();

    public PacienteRepository(){
        listaPaciente.add(new Paciente(1,"Armando","Cachas","11.240.124-1",1969,"Fonasa","ArmandoCcachas10569@gmail.com"));
        listaPaciente.add(new Paciente(2,"Camila","Guzman","18.256.240-1",1985,"Isapre","CamilaGuzman54@gmail.com"));
        listaPaciente.add(new Paciente(3,"Juan Pablo","Morales Pino","15.342.890-1",1985,"Fonasa","j.morales@gmail.com"));
        listaPaciente.add(new Paciente(4,"María Elena","Salazar Soto","8.432.119-K",1952,"Isalud","m.salazar52@gmail.com"));
        listaPaciente.add(new Paciente(5,"Carlos","Venegas Jara","18.990.231-5",1994,"Banmédica","c.venegas.j@outlook.com"));
        listaPaciente.add(new Paciente(6,"Lucía","Carmona Ruiz","21.003.442-8",2002,"Fonasa","lucia.carmona@uandes.cl"));
        listaPaciente.add(new Paciente(7,"Ricardo","Díaz","12.677.304-2",1974,"Colmena","r_oyarzun@servicios.cl"));
        listaPaciente.add(new Paciente(8,"Sofía","Valenzuela Paz","25.441.009-3",2015,"Consalud","sofia.v.paz@familia.cl"));
        listaPaciente.add(new Paciente(9,"Valentina","Rojas Mery","19.432.110-6",1997,"Fonasa","vale.rojas@gmail.com"));
        listaPaciente.add(new Paciente(10,"Beatriz","Lagos Silva","9.882.331-0",1961,"Isapre Esencial","b.lagos.silva@empresa.cl"));
        listaPaciente.add(new Paciente(11,"Fernanda","Alarcón Reyes","17.223.456-7",1990,"Fonasa","f.alarcon@gmail.com"));
        listaPaciente.add(new Paciente(12,"Constanza","Valdés Ugarte","8.229.440-K",1990,"Isalud","cony.valdes@gmail.com"));
        listaPaciente.add(new Paciente(13,"Florencia","Ibanez Castro","26.332.119-2",2020,"Banmedica","flo.ibanez@familia.cl"));
    }// fin constructor paciente repository

    // Mostrar Pacientes
    public List<Paciente>obtenerPacientes(){
        return listaPaciente;
    }

    // Mostrar Cantidad de pacientes

    public int totalPacientes(){
        return listaPaciente.size();
    }

    //Agregar Paciente

    public Paciente agregar(Paciente pac){
        listaPaciente.add(pac);
        return pac;
    }

    // Buscar Paciente por id
    public Paciente buscarPorId(int id){
        for(Paciente paciente : listaPaciente){
            if(paciente.getIdPaciente()==id){
                return paciente;
            }//Fin if
        }//Fin for
        return null;
    }//fin metodo

    // EliminarPaciente

    public void eliminarPaciente(int id){
        listaPaciente.removeIf(x->x.getIdPaciente()==id);
    }

    // Actualizar Paciente

    public Paciente actualizar(Paciente pac) {
        int idPaciente = 0;
        int idPosicion = 0;

        for (int i = 0; i < listaPaciente.size(); i++) {
            if (listaPaciente.get(i).getIdPaciente() == pac.getIdPaciente()) {
                idPaciente = pac.getIdPaciente();
                idPosicion = i;
            } // fin IF actualización
        } // fin FOR actualización

        Paciente paciente1 = new Paciente();
        paciente1.setIdPaciente(idPaciente);
        paciente1.setRutPaciente(pac.getRutPaciente());
        paciente1.setAnniNacimiento(pac.getAnniNacimiento());
        paciente1.setNombrePaciente(pac.getNombrePaciente());
        paciente1.setApellidoPaciente(pac.getApellidoPaciente());
        paciente1.setCorreo(pac.getCorreo());
        paciente1.setPrevisionSalud(pac.getPrevisionSalud());

        listaPaciente.set(idPosicion, paciente1);
        return paciente1;
    } // FIN

    // Metodo Buscar Paciente por Rut
    public Paciente buscarPacientePorRut(String rut) {
        for (Paciente paciente : listaPaciente) {
            if (paciente.getRutPaciente().equals(rut)) {
                return paciente;
            } // fin IF
        } // fin FOR
        return null;
    } // fin metodo












}// fin clase
