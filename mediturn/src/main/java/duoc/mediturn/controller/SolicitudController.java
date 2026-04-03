package duoc.mediturn.controller;

import duoc.mediturn.model.Solicitud;
import duoc.mediturn.service.SolicitudService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/v1/solicitudes")
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    @PostMapping
    public ResponseEntity<Solicitud> guardarSolicitud(@Valid @RequestBody Solicitud solicitud) {
        Solicitud nuevoSolicitud = solicitudService.saveSolicitud(solicitud);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoSolicitud);
    }

    @GetMapping("/{id}") //
    public ResponseEntity<Solicitud> obtenerSolicitud(@PathVariable int id) {
        Solicitud solicitud = solicitudService.getSolicitudPorId(id);
        return ResponseEntity.ok(solicitud);
    }

    @GetMapping
    public ResponseEntity<List<Solicitud>> listarSolicitud() {
        List<Solicitud> listaSolicitudes = solicitudService.getSolicitudes();
        return ResponseEntity.ok(listaSolicitudes);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Solicitud> actualizarSolicitud(@PathVariable int id, @Valid @RequestBody Solicitud solicitud) {
        Solicitud solicitudActualizada = solicitudService.updateSolicitud(id, solicitud);
        return ResponseEntity.ok(solicitudActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSolicitud(@PathVariable int id) {
        solicitudService.eliminarSolicitud(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ordenados/prioridad")
    public List<Solicitud> listarSolicitudesPorPrioridad() {
        return solicitudService.getSolicitudDeMayorPrioridad();
    }

}
