package bcd.lobinho;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import bcd.lobinho.model.*;
import bcd.lobinho.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@SpringBootApplication
@EnableJpaRepositories("bcd.lobinho.repository")
@EntityScan("bcd.lobinho.model")
public class LobinhoApplication {

	// Repositórios existentes
	@Autowired
	private TipoSanguineoRepository tipoSanguineoRepository;
	@Autowired
	private PessoaRepository jovemRepository;
	@Autowired
	private ResponsavelRepository responsavelRepository;
	@Autowired
	private VinculoRepository vinculoRepository;

	// Novos repositórios para listagem completa
	@Autowired
	private AcampamentoRepository acampamentoRepository;
	@Autowired
	private AreaConhecimentoRepository areaConhecimentoRepository;
	@Autowired
	private DesafioDistintivoRepository desafioDistintivoRepository;
	@Autowired
	private DesafioEspecialidadeRepository desafioEspecialidadeRepository;
	@Autowired
	private DesafioInsigniaRepository desafioInsigniaRepository;
	@Autowired
	private DistintivoRepository distintivoRepository;
	@Autowired
	private EspecialidadeRepository especialidadeRepository;
	@Autowired
	private InsigniaRepository insigniaRepository;
	@Autowired
	private ProblemaSaudeRepository problemaSaudeRepository;
	@Autowired
	private DadosSaudeRepository dadosSaudeRepository;
	@Autowired
	private DesafioDistintivoFeitoRepository desafioDistintivoFeitoRepository;
	@Autowired
	private DesafioEspecialidadeFeitoRepository desafioEspecialidadeFeitoRepository;
	@Autowired
	private DesafioInsigniaFeitoRepository desafioInsigniaFeitoRepository;
	@Autowired
	private NoiteAcampadaRepository noiteAcampadaRepository;


	public static void main(String[] args) {
		SpringApplication.run(LobinhoApplication.class, args);
		log.info("Aplicação Lobinho finalizada");
	}

	// Método para listar todos os registros de todas as tabelas
	private void listAllRecords() {
		System.out.println("\n--- LISTAGEM COMPLETA DE REGISTROS ---");

		System.out.println("\n------ Tipos Sanguíneos ------");
		tipoSanguineoRepository.findAll().forEach(System.out::println);

		System.out.println("\n------ Áreas de Conhecimento ------");
		areaConhecimentoRepository.findAll().forEach(System.out::println);

		System.out.println("\n------ Especialidades ------");
		especialidadeRepository.findAll().forEach(System.out::println);

		System.out.println("\n------ Distintivos ------");
		distintivoRepository.findAll().forEach(System.out::println);

		System.out.println("\n------ Insígnias ------");
		insigniaRepository.findAll().forEach(System.out::println);

		System.out.println("\n------ Problemas de Saúde ------");
		problemaSaudeRepository.findAll().forEach(System.out::println);

		System.out.println("\n------ Acampamentos ------");
		acampamentoRepository.findAll().forEach(System.out::println);

		System.out.println("\n------ Jovens ------");
		jovemRepository.findAll().forEach(System.out::println);

		System.out.println("\n------ Dados de Saúde ------");
		dadosSaudeRepository.findAll().forEach(System.out::println);

		System.out.println("\n------ Responsáveis ------");
		responsavelRepository.findAll().forEach(System.out::println);

		System.out.println("\n------ Vínculos ------");
		vinculoRepository.findAll().forEach(System.out::println);

		System.out.println("\n------ Desafios de Distintivos ------");
		desafioDistintivoRepository.findAll().forEach(System.out::println);

		System.out.println("\n------ Desafios de Especialidades ------");
		desafioEspecialidadeRepository.findAll().forEach(System.out::println);

		System.out.println("\n------ Desafios de Insígnias ------");
		desafioInsigniaRepository.findAll().forEach(System.out::println);

		System.out.println("\n------ Desafios de Distintivos Feitos ------");
		desafioDistintivoFeitoRepository.findAll().forEach(System.out::println);

		System.out.println("\n------ Desafios de Especialidades Feitos ------");
		desafioEspecialidadeFeitoRepository.findAll().forEach(System.out::println);

		System.out.println("\n------ Desafios de Insígnias Feitos ------");
		desafioInsigniaFeitoRepository.findAll().forEach(System.out::println);

		System.out.println("\n------ Noites Acampadas ------");
		noiteAcampadaRepository.findAll().forEach(System.out::println);
	}

	// Método para consultar jovem por CPF e seus dados relacionados
	private void consultJovemByCpf(String cpf) {
		System.out.println("\n--- CONSULTA POR CPF: " + cpf + " ---");
		Optional<Pessoa> jovemOptional = jovemRepository.findByCpf(cpf);

		if (jovemOptional.isPresent()) {
			Pessoa jovem = jovemOptional.get();
			System.out.println("Jovem encontrado: " + jovem);

			// Dados de Saúde
			Optional<DadosSaude> dadosSaude = dadosSaudeRepository.findByPessoa(jovem);
			dadosSaude.ifPresent(ds -> System.out.println("  Dados de Saúde: " + ds + " (Problema: " + ds.getProblemaSaude() + ")"));

			// Vínculos
			List<Vinculo> vinculos = vinculoRepository.findByPessoa(jovem);
			if (!vinculos.isEmpty()) {
				System.out.println("  Vínculos:");
				vinculos.forEach(v -> System.out.println("    - " + v + " (Responsável: " + v.getResponsavel().getNome() + ")"));
			}

			// Desafios de Distintivos Feitos
			List<DesafioDistintivoFeito> ddfs = desafioDistintivoFeitoRepository.findByPessoa(jovem);
			if (!ddfs.isEmpty()) {
				System.out.println("  Desafios de Distintivos Feitos:");
				ddfs.forEach(ddf -> System.out.println("    - " + ddf + " (Desafio: " + ddf.getDesafioDistintivo().getDescricao() + ")"));
			}

			// Desafios de Especialidades Feitos
			List<DesafioEspecialidadeFeito> defs = desafioEspecialidadeFeitoRepository.findByPessoa(jovem);
			if (!defs.isEmpty()) {
				System.out.println("  Desafios de Especialidades Feitos:");
				defs.forEach(def -> System.out.println("    - " + def + " (Desafio: " + def.getDesafioEspecialidade().getDescricao() + ")"));
			}

			// Desafios de Insígnias Feitos
			List<DesafioInsigniaFeito> difs = desafioInsigniaFeitoRepository.findByPessoa(jovem);
			if (!difs.isEmpty()) {
				System.out.println("  Desafios de Insígnias Feitos:");
				difs.forEach(dif -> System.out.println("    - " + dif + " (Desafio: " + dif.getDesafioInsignia().getNome() + ")"));
			}

			// Noites Acampadas
			List<NoiteAcampada> nas = noiteAcampadaRepository.findByPessoa(jovem);
			if (!nas.isEmpty()) {
				System.out.println("  Noites Acampadas:");
				nas.forEach(na -> System.out.println("    - " + na + " (Acampamento: " + na.getAcampamento().getNome() + ")"));
			}

		} else {
			System.out.println("Jovem com CPF " + cpf + " não encontrado.");
		}
	}

	@Bean
	public CommandLineRunner runApp() {
		return (args) -> {
			try {
				log.info("Iniciando aplicação Lobinho - Modo Completo!");

				// 1. Listar todos os registros de todas as tabelas
				this.listAllRecords();

				// 2. Consultar jovens específicos por CPF
				this.consultJovemByCpf("131.131.131-13"); // Taylor Swift
				this.consultJovemByCpf("111.333.131-13"); // Betty
				this.consultJovemByCpf("999.999.999-99"); // CPF que não existe

			} catch (Exception e) {
				log.error("Erro na execução da aplicação Lobinho: ", e);
			}
		};
	}
}
