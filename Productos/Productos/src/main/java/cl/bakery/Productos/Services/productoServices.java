package cl.bakery.Productos.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.bakery.Productos.Model.producto;
import cl.bakery.Productos.Repository.productoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class productoServices {
    

    @Autowired
    private productoRepository productorepository;

    public List<producto> BuscarTodoProducto(){
        return productorepository.findAll();
    }

    public producto BuscarUnProducto(Long ID_PRODUCTO){
        return productorepository.findById(ID_PRODUCTO).get();

    }

    public producto GuardarProducto(producto producto){
        return productorepository.save(producto);

    }

    public void EliminarProducto(Long ID_PRODUCTO){
        productorepository.deleteById(ID_PRODUCTO);
    }

}


