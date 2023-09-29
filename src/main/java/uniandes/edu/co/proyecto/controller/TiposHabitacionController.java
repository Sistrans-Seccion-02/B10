package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.TipoHabitacion;
import uniandes.edu.co.proyecto.repositorio.TipoHabitacionRepository;

@Controller
public class TiposHabitacionController{

    @Autowired
    private TipoHabitacionRepository tipoHabitacionRepository;

    @GetMapping("/tiposhabitacion")
    public String tiposHabitacion(Model model){
        model.addAttribute("tiposhabitacion", tipoHabitacionRepository.darTiposHabitacion());
        return "tiposhabitacion";
    }

    @GetMapping("/tiposhabitacion/new")
    public String tipoHabitacionForm(Model model){
        model.addAttribute("tipohabitacion", new TipoHabitacion());
        return "tipohabitacionNuevo";
    }

    @PostMapping("/tiposhabitacion/new/save")
    public String tipoHabitacionGuardar(@ModelAttribute TipoHabitacion tipoHabitacion){
        tipoHabitacionRepository.insertarTipoHabitacion(
            tipoHabitacion.getNombre(), tipoHabitacion.getCapacidad(), tipoHabitacion.getCosto());
        return "redirect:/tiposhabitacion";
    }

    //POSIBLE FUENTE DE ERROR, INTEGER Y NO LONG COMO ID
    @GetMapping("/tiposhabitacion/{nombre}/edit")
    public String tipoHabitacionEditarForm(@PathVariable("nombre") String nombre, Model model) {
        TipoHabitacion tipoHabitacion = tipoHabitacionRepository.darTipoHabitacion(nombre);
        if(tipoHabitacion != null) {
            model.addAttribute("tipohabitacion");
            return "tipohabitacionEditar";
        
        }
        else {
            return "redirect:/tiposhabitacion";
        }

    }

}