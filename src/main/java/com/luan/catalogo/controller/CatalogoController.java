package com.luan.catalogo.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.luan.catalogo.model.Musica;
import com.luan.catalogo.service.CatalogoService;

@Controller
public class CatalogoController {

	@Autowired
	CatalogoService service;

	@GetMapping("/")
	public String index() {
		return "redirect:/musicas";
	}

	@GetMapping("/musicas")
	public ModelAndView getMusicas() {
		ModelAndView modelAndView = new ModelAndView("musicas");
		List<Musica> musicas = service.findAll();
		modelAndView.addObject("musicas", musicas);
		return modelAndView;
	}

	@GetMapping("/musicas/{id}")
	public ModelAndView getDetalhes(@PathVariable("id") long id) {
		ModelAndView modelAndView = new ModelAndView("detalhes");
		Musica musica = service.findById(id);
		modelAndView.addObject("musica", musica);
		return modelAndView;
	}

	@GetMapping("/addMusica")
	public String getMusicaForm() {
		return "musicaForm";
	}

	@PostMapping("/addMusica")
	public String salvaMusica(@Valid Musica musica, BindingResult resul, RedirectAttributes attributes) {
		if (resul.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Campos obrigatórios não foram preenchidos!");
			return "redirect:/addMusica";
		}
		musica.setData(LocalDate.now());
		service.save(musica);
		return "redirect:/musicas";
	}

	@GetMapping("/editaMusica/{id}")
	public ModelAndView editaMusica(@PathVariable("id") long id) {
		ModelAndView modelAndView = new ModelAndView("editar");
		Musica musica = service.findById(id);
		modelAndView.addObject("musica", musica);
		return modelAndView;
	}

	@PostMapping("/editaMusica/{id}")
	public String editaMusica(@Valid Musica musica, BindingResult resul, RedirectAttributes attributes) {
		if (resul.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Campos obrigatórios não foram preenchidos!");
			return "redirect:/addMusica";
		}
		musica.setData(LocalDate.now());
		service.update(musica);
		return "redirect:/musicas";
	}

	@GetMapping("/deleteMusica/{id}")
	public String deletaMusica(@PathVariable("id") long id) {
		service.excluir(id);
		return "redirect:/musicas";
	}

}
