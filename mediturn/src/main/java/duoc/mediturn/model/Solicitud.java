package duoc.mediturn.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Solicitud {
    private Integer idSolicitud;

    private Integer idPaciente;


    private String rutPaciente;

    @NotNull(message = "La fecha de la solicitud es obligatoria")
    private LocalDate fechaSolicitud;

    @NotBlank(message = "El estado de la solicitud es obligatoria")
    private String estadoSolicitud;

    @NotBlank(message = "Se debe especificar la especialidad de atencion")
    private String especialidad;

    @Min(value = 1,message = "El nivel de prioridad debe ser mayor a 0 ")
    @Max(value = 100,message = "El nivel de prioridad debe ser menor a 100")
    private int nivelPrioridad;

    private String nombrePaciente;



}// fin solicitud
