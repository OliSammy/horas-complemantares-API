 package com.uece.horas_complementares.controller;

 import com.uece.horas_complementares.model.HoraComplementar;
import com.uece.horas_complementares.service.horasComplementares.HorasComplementaresService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.ResponseEntity;
 import org.springframework.security.access.method.P;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.PostMapping;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RestController;
 import org.springframework.web.bind.annotation.DeleteMapping;
 import org.springframework.web.bind.annotation.RequestBody;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.PutMapping;
 import java.util.List;

 @RestController
 @RequestMapping("/horas-complementares") // Classe que controla as rotas relacionadas às horas complementares
 public class HorasComplementaresController {
    
     @Autowired
     private HorasComplementaresService horasComplementaresService; //falta implementar

     @PutMapping("/alunos/{alunoId}/evento/{eventoId}")
        public ResponseEntity<?> adicionarHorasComplementares(@PathVariable Long alunoId, @PathVariable Long eventoId) {
            horasComplementaresService.adicionarHorasComplementares(alunoId, eventoId);
            return ResponseEntity.ok().build();
        }

        @GetMapping("/alunos/{matricula}")
        public ResponseEntity<?> listar(@PathVariable Long matricula) {
            HoraComplementar hrComp = horasComplementaresService.listar(matricula);
            System.out.println(hrComp);
            return ResponseEntity.ok().body(hrComp);
        }


//     @GetMapping("/{id}")
//     public HorasComplementares buscar(@PathVariable Long id) {
//         return horasComplementaresService.buscar(id);
//     }
//
//     @GetMapping("/alunos/{alunoId}")
//     public List<HorasComplementares> listar(@PathVariable Long alunoId) {
//         return horasComplementaresService.listar(alunoId);
//     }
//
//     @GetMapping("/alunos/{alunoId}/{alunoId/categoria/passiveisDeAproveitamento}")
//     public List<HorasComplementares> listarPassiveisDeAproveitamento(@PathVariable Long alunoId) {
//         return horasComplementaresService.listarPassiveisDeAproveitamento(alunoId);
//     }
//
//     @GetMapping("/curso/{cursoId}")
//     public List<HorasComplementares> listarPorCurso(@PathVariable Long cursoId) {
//         return horasComplementaresService.listarPorCurso(cursoId);
//     }
//
//     @PostMapping
//     public HorasComplementares criar(@RequestBody HorasComplementares horasComplementares) {
//         return horasComplementaresService.criar(horasComplementares);
//     }
//
//     @PutMapping("/{id}")
//     public HorasComplementares atualizar(@PathVariable Long id, @RequestBody HorasComplementares horasComplementares) {
//         return horasComplementaresService.atualizar(id, horasComplementares);
//     }
//
//     @DeleteMapping("/{id}")

 }
