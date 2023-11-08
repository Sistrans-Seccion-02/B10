package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Habitacion;
import uniandes.edu.co.proyecto.repositorio.HabitacionRepository;
import uniandes.edu.co.proyecto.repositorio.TipoHabitacionRepository;

@Controller
public class HabitacionController {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private TipoHabitacionRepository tipoHabitacionRepository;

    @GetMapping("/habitaciones")
    public String habitaciones(Model model, String RF){
        if( RF == null || RF.equals(""))
        {
            long startTime = System.currentTimeMillis();
            model.addAttribute("habitaciones", habitacionRepository.darHabitaciones());
            long endTime = System.currentTimeMillis();
            System.out.println("That took " + (endTime - startTime) + " milliseconds");
        }
        else if (RF.equals("11.3"))
        {
            long startTime = System.currentTimeMillis();
            model.addAttribute("habitaciones", habitacionRepository.darHabitacionesMasOcupadasSemana());
            long endTime = System.currentTimeMillis();
            System.out.println("That took " + (endTime - startTime) + " milliseconds");
        }
        else if (RF.equals("11.4"))
        {
            long startTime = System.currentTimeMillis();
            model.addAttribute("habitaciones", habitacionRepository.darHabitacionesMenosOcupadasSemana());
            long endTime = System.currentTimeMillis();
            System.out.println("That took " + (endTime - startTime) + " milliseconds");
        }
        else
        {
            long startTime = System.currentTimeMillis();
            model.addAttribute("servicios", habitacionRepository.darHabitaciones());
            long endTime = System.currentTimeMillis();
            System.out.println("That took " + (endTime - startTime) + " milliseconds");
        }
        return "habitaciones";
    }

    @GetMapping("/habitaciones/new")
    public String habitacionForm(Model model){
        model.addAttribute("habitacion", new Habitacion());
        model.addAttribute("tipos", tipoHabitacionRepository.darTiposHabitacion());
        return "habitacionNuevo";
    }

    @PostMapping("/habitaciones/new/save")
    public String habitacionGuardar(@ModelAttribute Habitacion habitacion){
        habitacionRepository.insertarHabitacion(
            habitacion.getNumero(), habitacion.getTipoHabitacion().getNombre());
        return "redirect:/habitaciones";
    }

    //POSIBLE FUENTE DE ERROR, INTEGER Y NO LONG COMO ID
    @GetMapping("/habitaciones/{numero}/edit")
    public String habitacionEditarForm(@PathVariable("numero") Integer numero, Model model) {
        Habitacion habitacion = habitacionRepository.darHabitacion(numero);
        if(habitacion != null) {
            model.addAttribute("habitacion", habitacion);
            model.addAttribute("tipos", tipoHabitacionRepository.darTiposHabitacion());
            return "habitacionEditar";
        
        }
        else {
            return "redirect:/habitaciones";
        }

    }

    //POSIBLE FUENTE DE ERROR, INTEGER Y NO LONG COMO ID
    @PostMapping("/habitaciones/{numero}/edit/save")
    public String habitacionEditarGuardar(@PathVariable("numero") Integer numero, @ModelAttribute Habitacion habitacion){
        habitacionRepository.actualizarHabitacion(numero, habitacion.getTipoHabitacion().getNombre());
        return "redirect:/habitaciones";
    }


    //POSIBLE FUENTE DE ERROR, INTEGER Y NO LONG COMO ID
    @GetMapping("/habitaciones/{numero}/delete")
    public String habitacionBorrar(@PathVariable("numero") Integer numero) {
        habitacionRepository.eliminarHabitacion(numero);
        return "redirect:/habitaciones";
    }
    
}
