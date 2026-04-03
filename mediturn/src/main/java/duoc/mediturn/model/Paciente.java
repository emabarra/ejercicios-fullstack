package duoc.mediturn.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Paciente {
    @Min(value = 0,message = "El id debe ser mayor a 0")
    private int idPaciente;

    @NotBlank(message="El nombre del paciente es obligatorio")
    private String nombrePaciente;

    @NotBlank(message = "El paciente debe tener un apellido")
    private String apellidoPaciente;


    private String rutPaciente;

    @Min(value = 1930,message = "El año de nacimiento debe ser mayor o igual a 1930")
    @Max(value = 2026,message = "El año de nacimiento debe ser menor o igual a 2026")
    private int anniNacimiento;

    @NotBlank(message = "La prevision del paciente es obligatorio")
    private String previsionSalud;

    @NotBlank(message = "El correo no puede estar vacio")
    private String correo;

}// fin paciente
