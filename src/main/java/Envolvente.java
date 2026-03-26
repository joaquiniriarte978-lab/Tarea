import org.example.Producto;

import java.util.*;
import java.util.stream.Collectors;

public class Envolvente {
    private List<Producto> productos;

    public Envolvente() {
        this.productos = Producto.cargarProductos();
    }


    public List<String> obtenerElectronicaCara() {
        return productos.stream()
                .filter(p -> "Electronica".equalsIgnoreCase(p.getCategoria()) )
                .filter(p -> p.getPrecio() > 1000 )
                .sorted(Comparator.comparingDouble(Producto::getPrecio).reversed())
                .map(p -> p.getNombre() + "- $" + p.getPrecio())
                .collect(Collectors.toList());

    }

    public Map<String,Producto> masCaroXcategoria(){
        Map<String, Producto> productosMasCarosPorCategoria =
                productos.stream()
                        .collect(Collectors.groupingBy(
                                Producto::getCategoria,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparingDouble(Producto::getPrecio)),
                                        Optional::get
                                )
                        ));
        return productosMasCarosPorCategoria;
    }
}
