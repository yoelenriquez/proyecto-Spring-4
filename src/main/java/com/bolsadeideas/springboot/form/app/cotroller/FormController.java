package com.bolsadeideas.springboot.form.app.cotroller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.form.app.models.domain.Usuario;

@Controller
@SessionAttributes("usuario")
public class FormController {
	
	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Juan");
		usuario.setApellido("Carlos");
		usuario.setIdentificador("123.432-L");
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Formulario usuarios");
		return "form";
	}
	
	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {
		
		
		model.addAttribute("titulo", "Mostrando usuario");
		
		if(result.hasErrors()) {
			/*Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(erro ->{
				errores.put(erro.getField(), "Error en ".concat(erro.getField()).concat(" ").concat(erro.getDefaultMessage()));
			});
			model.addAttribute("error", errores);*/
			
			return "form";
		}
		
		model.addAttribute("usuario", usuario);
		status.setComplete();
		
		return "resultado";
	}
}
