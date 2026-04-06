package duoc.productos.controller;

import duoc.productos.model.Producto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController //Permite trabajar en la web enviando datos
@RequestMapping("/api/productos") //Ruta principal
@CrossOrigin(origins = "*")

//localhost:8080/api/productos

public class ProductoController {
    //Creamos nuestro array con los datos como una BD
    private List<Producto> listaProductos = Arrays.asList(
            new Producto(1, "Notebook Lenovo", 550000),
            new Producto(2, "Mouse Logitech", 150000),
            new Producto(3, "Monitor Samsung", 120000),
            new Producto(4, "Teclado Mecanico Razer BlackWidow V3", 130000),
            new Producto(5, "Consolo Portatil XBOX ROG ALLY X", 990000),
            new Producto(6, "Disco Duro PC X300 4TB", 170000),
            new Producto(7, "Smartwatch Samsung Watch8 44mm Negro", 240000),
            new Producto(8, "Kit Starlink Mini", 200000),
            new Producto(9, "Celular S26 Ultra 512GB/12GB Negro Liberado", 1710000),
            new Producto(10, "Ipad Air 11'' Chip M3 Wi-Fi 128GB - Space Grey", 760000)
    );

    //Creamos un saludo simple de prueba
    @GetMapping("/saludos")
    public String saludoProducto(){
        return "saludo";
    }

    //Creamos listado general de productos
    @GetMapping("/listar")
    public List<Producto> listarProductos(){
        return listaProductos;
    }

    //Buscar producto por ID
    @GetMapping("/{idProducto}")
    public ResponseEntity<?> buscarProdcuto(@PathVariable int idProducto){
        for (Producto producto : listaProductos){
            if(producto.getId() == idProducto){
                return ResponseEntity.ok(producto); //Producto encontrado
            }//Fin de if
        }//Fin de ciclo for
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Producto con ID " + idProducto + " no encontrado");
    }//Fin de buscarProducto

    //Agregar un nuevo producto POST
    @PostMapping
    public ResponseEntity<?> agregarProducto(@RequestBody Producto nuevoProducto){
        listaProductos.add(nuevoProducto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Producto agregado correctamente: "+ nuevoProducto.getNombre());
    }//fin de agregarProducto

    //Actualizar datos del producto PUT
    @PutMapping("/{idProducto}")
    public ResponseEntity<?> actualizarProducto(@PathVariable int idProducto, @RequestBody Producto productoActualizado){
        for(Producto producto : listaProductos){
            if(producto.getId() == idProducto){
                producto.setNombre(productoActualizado.getNombre());
                producto.setPrecio(productoActualizado.getPrecio());
                return ResponseEntity.ok("Producto actualizado correctamente");
            }
        }//fin ciclo FOR
        return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Producto con ID "+idProducto+" no ha sido encontrado");
    }//fin actualizarProducto

    //Eliminar producto (DELETE)
    @DeleteMapping("/{idProducto}")
    public ResponseEntity<?> eliminarProducto(@PathVariable int idProducto){
        boolean eliminado = listaProductos.removeIf(producto -> producto.getId() == idProducto);

        if(eliminado){
            return ResponseEntity.ok("Producto eliminado correctamente");
        }// fin IF
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Producto con ID "+idProducto+" no ha sido encontrado");
    }//fin eliminarProducto

}//Fin de ProductoController