package br.com.fiap.controller;

import br.com.fiap.model.dto.HabilidadeDTO;
import br.com.fiap.model.entity.Habilidade;
import br.com.fiap.model.repository.HabilidadeRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/habilidade")
public class HabilidadeController {

    @Autowired
    private HabilidadeRepository repository;

    @GetMapping("/")
    public String home() {
        return "redirect:/habilidade";
    }

    @GetMapping("/novo")
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView("formulario");
        mv.addObject("habilidade", new Habilidade());
        mv.addObject("acao", "/habilidade");
        return mv;
    }

    @PostMapping
    public ModelAndView cadastrar(@Valid HabilidadeDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("redirect:/habilidade");
        }
        Habilidade h = new Habilidade(dto);
        repository.save(h);
        return new ModelAndView("redirect:/habilidade");
    }

    @GetMapping
    public ModelAndView consultar() {
        List<Habilidade> habilidades = repository.findAll();
        ModelAndView mv = new ModelAndView("habilidade");
        mv.addObject("habilidades", habilidades);
        return mv;
    }

    @GetMapping("/editar/{codigo}")
    public ModelAndView editar(@PathVariable Long codigo) {
        Habilidade h = repository.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("formulario");
        mv.addObject("habilidade", h);
        mv.addObject("acao", "/habilidade/atualizar/" + codigo);
        return mv;
    }

    @PostMapping("/atualizar/{codigo}")
    public ModelAndView atualizar(@PathVariable Long codigo,
                                  @Valid HabilidadeDTO dto,
                                  BindingResult result) {

        if (result.hasErrors()) {
            return new ModelAndView("redirect:/habilidade");
        }

        Habilidade existente = repository.findByCodigo(codigo);
        if (existente == null) {
            return new ModelAndView("redirect:/habilidade");
        }

        existente.setHabilidade(dto.habilidade());
        existente.setCategoria(dto.categoria());
        existente.setNivelAtual(dto.nivelAtual());
        existente.setMeta(dto.meta());
        existente.setFuncionario(dto.funcionario());
        existente.setStatus(dto.status());

        repository.save(existente);
        return new ModelAndView("redirect:/habilidade");
    }

    @GetMapping("/excluir/{codigo}")
    public ModelAndView excluir(@PathVariable Long codigo) {
        repository.deleteById(codigo);
        return new ModelAndView("redirect:/habilidade");
    }



    @GetMapping("/api")
    public List<Habilidade> listarApi() {
        return repository.findAll();
    }


    @GetMapping("/api/{codigo}")
    public Habilidade buscarPorId(@PathVariable Long codigo) {
        return repository.findByCodigo(codigo);
    }


    @PostMapping("/api")
    public Habilidade criarApi(@RequestBody @Valid HabilidadeDTO dto) {
        return repository.save(new Habilidade(dto));
    }

    @PutMapping("/api/{codigo}")
    public Habilidade atualizarApi(@PathVariable Long codigo,
                                   @RequestBody @Valid HabilidadeDTO dto) {

        Habilidade existente = repository.findByCodigo(codigo);
        if (existente == null) return null;

        existente.setHabilidade(dto.habilidade());
        existente.setCategoria(dto.categoria());
        existente.setNivelAtual(dto.nivelAtual());
        existente.setMeta(dto.meta());
        existente.setFuncionario(dto.funcionario());
        existente.setStatus(dto.status());

        return repository.save(existente);
    }

    @DeleteMapping("/api/{codigo}")
    public void excluirApi(@PathVariable Long codigo) {
        repository.deleteById(codigo);
    }
}
