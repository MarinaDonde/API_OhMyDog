package com.gft.ohMyDog.configs;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.gft.ohMyDog.entities.Atendimento;
import com.gft.ohMyDog.entities.Cachorro;
import com.gft.ohMyDog.entities.Cliente;
import com.gft.ohMyDog.entities.Endereco;
import com.gft.ohMyDog.entities.Perfil;
import com.gft.ohMyDog.entities.Usuario;
import com.gft.ohMyDog.entities.Veterinario;
import com.gft.ohMyDog.enums.Genero;
import com.gft.ohMyDog.enums.TipoPessoa;
import com.gft.ohMyDog.repositories.AtendimentoRepository;
import com.gft.ohMyDog.repositories.CachorroRepository;
import com.gft.ohMyDog.repositories.ClienteRepository;
import com.gft.ohMyDog.repositories.EnderecoRepository;
import com.gft.ohMyDog.repositories.PerfilRepository;
import com.gft.ohMyDog.repositories.UsuarioRepository;
import com.gft.ohMyDog.repositories.VeterinarioRepository;

@Component
@Transactional
public class DbInitializer implements CommandLineRunner{	
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CachorroRepository cachorroRepository;
	
	@Autowired
	private VeterinarioRepository veterinarioRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private AtendimentoRepository atendimentoRepository;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "778.086.180-55");	
		Cliente cli2 = new Cliente(null, "Fernanda Barros", "fernanda@gmail.com", "469.072.670-12");
		Cliente cli3 = new Cliente(null, "João Carvalho", "joao@gmail.com", "634.629.120-38");
		Cliente cli4 = new Cliente(null, "Antônio Mendes", "antonio@gmail.com", "413.401.740-81");
		Cliente cli5 = new Cliente(null, "Carolina Santos", "carol@gmail.com", "993.188.910-16");
		Cliente cli6 = new Cliente(null, "Ricardo de Paula", "ricardo@gmail.com", "612.156.600-06");
		Cliente cli7 = new Cliente(null, "Patricia Carneiro", "pati@gmail.com", "740.445.410-78");
		Cliente cli8 = new Cliente(null, "Luna Yoshimi", "luna@gmail.com", "793.517.480-65");
		Cliente cli9 = new Cliente(null, "Marcos Fernandes", "mf@gmail.com", "699.054.300-08");

		cli1.getTelefones().addAll(Arrays.asList("33578585"));
		cli2.getTelefones().addAll(Arrays.asList("33556644", "987654320"));
		cli3.getTelefones().addAll(Arrays.asList("32454588"));
		cli4.getTelefones().addAll(Arrays.asList("31478596", "995523366"));
		cli5.getTelefones().addAll(Arrays.asList("33258856", "987414123"));
		cli6.getTelefones().addAll(Arrays.asList("34747123", "985858521"));
		cli7.getTelefones().addAll(Arrays.asList("33235689", "35335555", "974411121"));
		cli8.getTelefones().addAll(Arrays.asList("32121236"));
		cli9.getTelefones().addAll(Arrays.asList("34693254"));
	
		if (clienteRepository.findAll().isEmpty()) {
			clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5, cli6, cli7, cli8, cli9));
		}
			
		Veterinario vet1 = new Veterinario(null, "Roberta Mendes", "roberta@gmail.com", TipoPessoa.CNPJ, "18.320.471/0001-42", "Oftalmologia");
		Veterinario vet2 = new Veterinario(null, "Fábio Marcos", "fb@gmail.com", TipoPessoa.CPF, "866.566.740-70", "Dermatologia");
		Veterinario vet3 = new Veterinario(null, "Rogério Rodrigues", "rogerio@gmail.com", TipoPessoa.CPF, "064.600.750-50", "Cirurgia Geral");
		Veterinario vet4 = new Veterinario(null, "Aline Pereira", "aline@gmail.com", TipoPessoa.CNPJ, "42.473.681/0001-55", "Ortopedia");
		Veterinario vet5 = new Veterinario(null, "Julia Kunz", "julia@gmail.com", TipoPessoa.CPF, "355.672.010-87", "Clínico Geral");		
		
		vet1.getTelefones().addAll(Arrays.asList("985855666"));
		vet2.getTelefones().addAll(Arrays.asList("998721213", "987458841"));
		vet3.getTelefones().addAll(Arrays.asList("985523588"));
		vet4.getTelefones().addAll(Arrays.asList("989655412", "999898989"));
		vet5.getTelefones().addAll(Arrays.asList("989898365", "978585821"));
	
		if (veterinarioRepository.findAll().isEmpty()) {
			veterinarioRepository.saveAll(Arrays.asList(vet1, vet2, vet3, vet4, vet5));
		}
		
		Cachorro dog01 = new Cachorro(null, "Kia", "Shitzu", Genero.FEMEA, "8kg", sdf.parse("23/08/2019"), cli1);		
		Cachorro dog02 = new Cachorro(null, "Campeão", "Collie", Genero.MACHO, "28kg", sdf.parse("15/06/2018"), cli2);
		Cachorro dog03 = new Cachorro(null, "Thor", "Golden Retriever", Genero.MACHO, "29kg", sdf.parse("10/08/2019"), cli2);
		Cachorro dog04 = new Cachorro(null, "Mel", "Poodle", Genero.FEMEA, "5kg", sdf.parse("25/09/2020"), cli3);
		Cachorro dog05 = new Cachorro(null, "Mia", "Poodle", Genero.FEMEA, "6kg", sdf.parse("13/05/2017"), cli3);
		Cachorro dog06 = new Cachorro(null, "Malu", "Poodle", Genero.FEMEA, "4kg", sdf.parse("11/03/2021"), cli3);
		Cachorro dog07 = new Cachorro(null, "Beethoven", "Maltês", Genero.MACHO, "7kg", sdf.parse("07/10/2018"), cli4);
		Cachorro dog08 = new Cachorro(null, "Charlie", "Buldogue", Genero.MACHO, "18kg", sdf.parse("13/12/2017"), cli5);
		Cachorro dog09 = new Cachorro(null, "Sol", "Doberman", Genero.FEMEA, "30kg", sdf.parse("23/12/2015"), cli6);
		Cachorro dog10 = new Cachorro(null, "Juju", "PitBull", Genero.FEMEA, "35kg", sdf.parse("31/07/2016"), cli6);
		Cachorro dog11 = new Cachorro(null, "Amora", "SRD", Genero.FEMEA, "12kg", sdf.parse("25/01/2020"), cli7);
		Cachorro dog12 = new Cachorro(null, "Pepe", "Lhasa Apso", Genero.MACHO, "7kg", sdf.parse("03/06/2021"), cli8);
		Cachorro dog13 = new Cachorro(null, "Lupita", "Cavalier", Genero.FEMEA, "6kg", sdf.parse("05/06/2018"), cli8);
		Cachorro dog14 = new Cachorro(null, "Pancho", "Pug", Genero.MACHO, "9kg", sdf.parse("22/11/2017"), cli9);

		if (cachorroRepository.findAll().isEmpty()) {
			cachorroRepository.saveAll(Arrays.asList(dog01, dog02, dog03, dog04, dog05, dog06, dog07, dog08, dog09, dog10, dog11, dog12, dog13, dog14));
		}
				
		Endereco end01 = new Endereco(null, "92224-448", "Rua Flores", "Jardim", "Porto Alegre", "RS", "300", cli1, null);
		Endereco end02 = new Endereco(null, "92000-222", "Travessa São José", "São João", "Porto Alegre", "RS", "54a", cli2, null);
		Endereco end03 = new Endereco(null, "92223-450", "Rua da Pedra", "Jardim", "Porto Alegre", "RS", "1255", cli3, null);
		Endereco end04 = new Endereco(null, "92225-470", "Rua Guia Lopes", "São João", "Porto Alegre", "RS", "1200", cli4, null);
		Endereco end05 = new Endereco(null, "92227-445", "Rua Bento Gonçalves",  "JSão João", "Porto Alegre", "RS", "550", cli5, null);
		Endereco end06 = new Endereco(null, "92222-413", "Av Marcílio", "São João", "Porto Alegre","RS", "888 apto 405", cli6, null);
		Endereco end07 = new Endereco(null, "92221-441", "Rua Florinda", "Jardim", "Porto Alegre","RS", "30", cli7, null);
		Endereco end08 = new Endereco(null, "92223-423", "Rua Chaves", "Jardim", "Porto Alegre","RS", "747", cli8, null);
		Endereco end09 = new Endereco(null, "92224-474", "Rua Sapiranga", "São João", "Porto Alegre","RS", "989", cli9, null);		
		
		Endereco end10 = new Endereco(null, "92544-200", "Rua Esteio", "São João", "Porto Alegre","RS", "15",null, vet1);
		Endereco end11 = new Endereco(null, "92711-212", "Rua Ivoti", "Jardim", "Porto Alegre","RS", "7450", null, vet2);
		Endereco end12 = new Endereco(null, "92874-347", "Av Lima", "Jardim", "Porto Alegre","RS", "320 apto 200", null, vet3);
		Endereco end13 = new Endereco(null, "92632-574", "Rua de Sá", "São João", "Porto Alegre","RS", "544", null, vet4);
		Endereco end14 = new Endereco(null, "92858-669", "Rua Lagoa", "São João", "Porto Alegre","RS", "600", null, vet5);
		
		if (enderecoRepository.findAll().isEmpty()) {
			enderecoRepository.saveAll(Arrays.asList(end01, end02, end03, end04, end05, end06, end07, end08, end09, end10, end11, end12, end13, end14));
		}
								
		Atendimento atend01 = new Atendimento(null, sdf.parse("25/07/2022"), "5,2kg", true, "usar a pomada tal de 12 em 12h", "Cachorro apresentando muita coceira e irritações na pele", vet2, cli3, dog04);
		Atendimento atend02 = new Atendimento(null, sdf.parse("25/07/2022"), "11,5kg", true, "Remédio para dor, de 8 em 8h", "Cachorro apresentou fratura na pata direita dianteira e teve a pata imobilizada", vet4, cli7, dog11);
		Atendimento atend03 = new Atendimento(null, sdf.parse("25/07/2022"), "9.1kg", true, "Consulta de rotina - vacinação", "Vacinas aplicadas: Antirrabica e polivalente", vet1, cli9, dog14);

		if (atendimentoRepository.findAll().isEmpty()) {
			atendimentoRepository.saveAll(Arrays.asList(atend01, atend02, atend03));
		}
	
		Perfil p1 = new Perfil(null, "admin");
		Perfil p2 = new Perfil(null, "usuario");

		if (perfilRepository.findAll().isEmpty()) {
			perfilRepository.saveAll(Arrays.asList(p1, p2));
		}
		
		Usuario u1 = new Usuario(null, "admin@gft.com", new BCryptPasswordEncoder().encode("Gft@1234"), p1);
		Usuario u2 = new Usuario(null, "usuario@gft.com", new BCryptPasswordEncoder().encode("Gft@1234"), p2);

		if (usuarioRepository.findAll().isEmpty()) {
			usuarioRepository.saveAll(Arrays.asList(u1, u2));
		}		
	}
}
