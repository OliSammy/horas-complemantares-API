package com.uece.horas_complementares.service.evento;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.uece.horas_complementares.model.DTO.user.EventoDTO;
import com.uece.horas_complementares.model.Inscricao;
import com.uece.horas_complementares.model.Presenca;
import com.uece.horas_complementares.model.repository.AlunoRepository;
import com.uece.horas_complementares.model.repository.EventoRepository;
import com.uece.horas_complementares.model.repository.InscricaoRepository;
import com.uece.horas_complementares.model.repository.PresencaRepository;
import com.uece.horas_complementares.model.repository.ProfessorRepository;
import com.uece.horas_complementares.model.user.Aluno;
import com.uece.horas_complementares.security.TokenService;
import com.uece.horas_complementares.service.inscricaoService.InscricaoService;
import com.uece.horas_complementares.service.presenca.PresencaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import com.uece.horas_complementares.model.Evento;
import com.uece.horas_complementares.model.DTO.user.ProfessorEventoDTO;
import com.uece.horas_complementares.model.spec.EventoByAlunoMatricula;
import com.uece.horas_complementares.model.spec.EventoByProfessor;
import com.uece.horas_complementares.model.spec.EventoNaoInscritoPorAluno;
import com.uece.horas_complementares.model.user.Professor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EventoService {
  @Autowired
  private EventoRepository eventoRepository;

  @Autowired
  private AlunoRepository alunoRepository;

  @Autowired
  private TokenService tokenService;

  @Autowired
  private InscricaoService inscricaoService;

  @Autowired
  private PresencaRepository presencaRepository;

  @Autowired
  private InscricaoRepository inscricaoRepository;

  private static final String UPLOAD_DIR = "src/main/resources/uploads/";

  public List<Evento> listar() {
    return eventoRepository.findAll();
  }

  public Evento buscar(Long id) {
    return eventoRepository.findById(id).get();
  }

  public Evento criar(EventoDTO evento, Professor matricula, MultipartFile file) {
    Evento newEvento = new Evento();

    if (!(file.isEmpty())) {
      String caminho = this.uploadFoto(file, evento.getNome());
      newEvento.setBanner(caminho);
    }

    newEvento.setDescricao(evento.getDescricao());
    newEvento.setTipoHorasComplementares(evento.getTipoHorasComplementares());
    newEvento.setDataInicial(evento.getDataInicial());
    newEvento.setDataFinal(evento.getDataFinal());
    newEvento.setHorarioInicial(evento.getHorarioInicial());
    newEvento.setHorarioFinal(evento.getHorarioFinal());
    newEvento.setMatriculaProfessor(matricula);
    newEvento.setLimiteDedescrição(evento.getLimiteDedescrição());
    newEvento.setNome(evento.getNome());
    newEvento.setQuantidadeHorasComplementares(evento.getQuantidadeHorasComplementares());

    return eventoRepository.save(newEvento);
  }

  public String uploadFoto(MultipartFile file, String nome) {
    long tamanhoMaximo = 5 * 1024 * 1024; // 5 MB
    List<String> tiposPermitidos = Arrays.asList("image/jpeg", "image/png");

    if (file == null || file.isEmpty()) {
      throw new IllegalArgumentException("Selecione um arquivo.");
    }

    Path userUploadDir = Paths.get(UPLOAD_DIR, nome);
    try {
      if (!Files.exists(userUploadDir)) {
        Files.createDirectories(userUploadDir);
      }
    } catch (IOException e) {
      throw new RuntimeException("Erro ao criar o diretório de upload para o evento.", e);
    }

    if (file.getSize() > tamanhoMaximo) {
      throw new IllegalArgumentException("O arquivo " + file.getOriginalFilename()
          + " é muito grande. O tamanho máximo permitido é de 5 MB.");
    }

    String tipoConteudo = file.getContentType();
    if (!tiposPermitidos.contains(tipoConteudo)) {
      throw new IllegalArgumentException("Tipo de arquivo não suportado para o arquivo "
          + file.getOriginalFilename() + ". Apenas imagens JPEG e PNG são permitidas.");
    }

    try {
      String fileName = file.getOriginalFilename();
      Path filePath = userUploadDir.resolve(fileName);

      // Salva o arquivo no diretório específico do evento
      Files.write(filePath, file.getBytes());

      // Retorna o caminho do arquivo salvo
      return "/uploads/" + nome + "/" + fileName;
    } catch (IOException e) {
      throw new RuntimeException("Erro ao carregar o arquivo " + file.getOriginalFilename(), e);
    }
  }

  public Evento atualizar(Long id, Evento evento) {
    Evento eventoAtualizado = eventoRepository.findById(id).get();
    eventoAtualizado.setNome(evento.getNome());
    eventoAtualizado.setBanner(evento.getBanner());
    eventoAtualizado.setTipoHorasComplementares(evento.getTipoHorasComplementares());
    eventoAtualizado.setDataInicial(evento.getDataInicial());
    eventoAtualizado.setDataFinal(evento.getDataFinal());
    return eventoRepository.save(eventoAtualizado);
  }

  public void deletar(Long id) {
    eventoRepository.deleteById(id);
  }

  public List<Evento> getEventosByAlunoMatricula(Long alunoMatricula) {
    Specification<Evento> spec = new EventoByAlunoMatricula(alunoMatricula);
    List<Evento> eventos = eventoRepository.findAll(spec);
    return eventos;
  }

  public List<Evento> getEventosDisponiveis(Long alunoMatricula) {
    Specification<Evento> spec = new EventoNaoInscritoPorAluno(alunoMatricula);
    List<Evento> eventos = eventoRepository.findAll(spec);
    return eventos;
  }

  public List<Evento> getEventosProfessor(Long professorMatricula) {
    Specification<Evento> spec = new EventoByProfessor(professorMatricula);
    List<Evento> eventos = eventoRepository.findAll(spec);
    return eventos;
  }

  private ProfessorEventoDTO toProfessorDTO(Professor professor) {
    return new ProfessorEventoDTO(professor.getMatricula(), professor.getNome(), professor.getEmail());
  }

  public void inscreverAluno(Long idEvento, Aluno aluno) {
    Evento evento = eventoRepository.findById(idEvento)
        .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

    // Verifica se o aluno já está inscrito no evento
    Optional<Inscricao> inscricaoExistente = inscricaoRepository.findByIdEvento_IdAndAluno_Matricula(idEvento, aluno.getMatricula());
    if (inscricaoExistente.isPresent()) {
      throw new IllegalArgumentException("O aluno já está inscrito neste evento.");
    }else{

    Inscricao inscricao = new Inscricao(aluno, evento);
    inscricaoRepository.save(inscricao);

    boolean presente = false;
    Presenca presenca = new Presenca(presente, inscricao, aluno);
    presencaRepository.save(presenca);

    aluno.getInscricoes().add(inscricao);
    alunoRepository.save(aluno);
  }
}
}
