package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Servicio;
import uniandes.edu.co.proyecto.repositorio.ServicioRepository;



@Controller
public class ServicioController {

    @Autowired
    private ServicioRepository servicioRepository;

    @GetMapping("/servicios")
    public String servicios(Model model){
        model.addAttribute("servicios", servicioRepository.darServicios());
        return "servicios";
    }

    @GetMapping("/servicios/new")
    public String servicioForm(Model model){
        model.addAttribute("servicio", new Servicio());
        return "servicioNuevo";      
    }

    @PostMapping("/servicios/new/save")
    public String servicioGuardar(@ModelAttribute Servicio servicio){
        servicioRepository.insertarServicio(servicio.getNombre(), servicio.getDescripcion(), servicio.getCosto());
        return "redirect:/servicios";
    }

    @GetMapping("/servicios/{nombre}/edit")
    public String servicioEditarForm(@PathVariable("nombre") String nombre, Model model){
        Servicio servicio = servicioRepository.darServicio(nombre);

        if (servicio != null){
            model.addAttribute("servicio", servicio);
            return "servicioEditar";
        } else {
            return "redirect:/servicios";
        }
    }

    @PostMapping("/servicios/{nombre}/edit/save")
    public String servicioEditarGuardar(@PathVariable("nombre") String nombre, @ModelAttribute Servicio servicio){
        servicioRepository.actualizarServicio(servicio.getNombre(), servicio.getDescripcion(), servicio.getCosto());;
        return "redirect:/servicios";
    }

    @GetMapping("/servicios/{nombre}/delete")
    public String servicioEliminar(@PathVariable("nombre") String nombre){
        servicioRepository.eliminarServicio(nombre);
        return "redirect:/servicios";
    }
    
}