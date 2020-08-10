package br.com.projectstages_mvc.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.projectstages_mvc.dao.AmigosDao;
import br.com.projectstages_mvc.dao.CadastroDao;
import br.com.projectstages_mvc.dao.ChatDao;
import br.com.projectstages_mvc.dao.ConfiguracoesDao;
import br.com.projectstages_mvc.dao.NotificacaoAmizadeDao;
import br.com.projectstages_mvc.dao.ParticipantesDao;
import br.com.projectstages_mvc.dao.ProjetoDao;
import br.com.projectstages_mvc.model.Chat;
import br.com.projectstages_mvc.model.Concluidos;
import br.com.projectstages_mvc.model.Configuracoes;
import br.com.projectstages_mvc.model.Desenvolvimentos;
import br.com.projectstages_mvc.model.NotificacaoAmizade;
import br.com.projectstages_mvc.model.Participantes;
import br.com.projectstages_mvc.model.Projeto;
import br.com.projectstages_mvc.model.Tarefas;
import br.com.projectstages_mvc.model.Usuario;

@Controller
@Transactional
public class HomeController {

	@Autowired
	private CadastroDao cadastroDao;

	@Autowired
	private NotificacaoAmizadeDao notificacaoDao;

	@Autowired
	private ChatDao chatDao;

	@Autowired
	private ProjetoDao projetodao;

	@Autowired
	private ConfiguracoesDao configuracoesDao;

	@Autowired
	private AmigosDao amigosDao;

	@Autowired
	private ParticipantesDao participantesDao;

	// Variaveis auxiliares
	private int idDoProjeto;
	private Usuario user = new Usuario();
	private Participantes meuParticipante = new Participantes();
	private Configuracoes config = new Configuracoes();
	private Projeto projeto = new Projeto();
	private List<Tarefas> listTarefas = new ArrayList<Tarefas>();
	private List<Desenvolvimentos> listDesenvolvimentos = new ArrayList<Desenvolvimentos>();
	private List<Concluidos> listConcluidos = new ArrayList<Concluidos>();
	private List<String> listaStatusTarefas = new ArrayList<String>();
	private List<String> listaStatusDesenvolvimentos = new ArrayList<String>();
	private List<String> listaStatusConcluidos = new ArrayList<String>();

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	@Cacheable(value = "projetos")
	public ModelAndView index(@AuthenticationPrincipal Usuario usuario, @RequestParam int idProjeto) {
		ModelAndView model = new ModelAndView("projetos");
		idDoProjeto = idProjeto;
		int totalMensagens = 0;
		int quantidade = 0;
		List<String> meusAmigos = new ArrayList<String>();
		List<Usuario> listaAmigos = new ArrayList<Usuario>();
		List<NotificacaoAmizade> msgNotificacoes = new ArrayList<NotificacaoAmizade>();
		List<String> participantes = new ArrayList<String>();
		List<String> listaFuncoesParticipantes = new ArrayList<String>();
		List<Usuario> listaParticipantes = new ArrayList<Usuario>();
		List<Usuario> listaProprietarios = new ArrayList<Usuario>();
		List<Usuario> listaProprietariosDesenvolvimentos = new ArrayList<Usuario>();
		List<Usuario> listaProprietariosConcluidos = new ArrayList<Usuario>();
		List<Date> listaAjustaData = new ArrayList<Date>();
		List<Date> listaAjustaDataDesenvolvimentos = new ArrayList<Date>();
		List<Date> listaAjustaDataConcluidos = new ArrayList<Date>();
		List<Projeto> listaProjetosParticipantes = new ArrayList<Projeto>();
		List<Participantes> projetosParticipantes = new ArrayList<Participantes>();
		List<Chat> listMensagens = new ArrayList<Chat>();
		user = cadastroDao.findUsuario(usuario.getUsername());
		meusAmigos = amigosDao.listarTodosOsAmigos(usuario.getUsername());
		participantes = participantesDao.listarTodosOsPartcipantesDoProjeto(idProjeto);
		listaFuncoesParticipantes = participantesDao.listarFuncoesDosPartcipantes(idProjeto);
		meuParticipante = participantesDao.findParticipante(idDoProjeto, usuario.getUsername());
		projetosParticipantes = participantesDao.listarProjetosParticipantes(usuario.getUsername());

		// Lista a quantidade de visualizções de notificação não vista
		msgNotificacoes = notificacaoDao.listarTodasNotificacoesDoDestinatario(usuario.getUsername());
		for (int i = 0; i < msgNotificacoes.size(); i++) {
			if (msgNotificacoes.get(i).isVisualizacao() == false) {
				quantidade++;
			}
		}

		if (quantidade > 0) {
			model.addObject("qtnNotificacao", quantidade);
		}

		// Lista os amigos do usuario para serem particpantes do projeto
		for (int i = 0; i < meusAmigos.size(); i++) {
			listaAmigos.add(cadastroDao.findUsuario(meusAmigos.get(i)));
		}

		// Lista os participantes
		for (int i = 0; i < participantes.size(); i++) {
			listaParticipantes.add(cadastroDao.findUsuario(participantes.get(i)));
		}

		// Mostra o total de mensagens nao lidas.
		listMensagens = chatDao.getAllMensagens(usuario.getEmail());
		for (int j = 0; j < listMensagens.size(); j++) {
			if (listMensagens.get(j).isVisualizacao() == false
					&& listMensagens.get(j).getEmailDestinatario().equals(usuario.getEmail())) {
				totalMensagens++;
			}
		}

		if (totalMensagens > 0) {
			model.addObject("totalMensagens", totalMensagens);
		}

		config = configuracoesDao.configuracoesDoUsuario(usuario.getUsername());

		if (meuParticipante.getFuncao() == "criador") {
			projeto = projetodao.listarProjetosUsuarioPorID(usuario.getUsername(), idProjeto);
			listTarefas = projetodao.listarTarefas(projeto.getId());
			listaStatusTarefas = projetodao.listarStatusTarefas(projeto.getId());
			listDesenvolvimentos = projetodao.listarDesenvolvimentos(projeto.getId());
			listaStatusDesenvolvimentos = projetodao.listarStatusDesenvolvimentos(projeto.getId());
			listConcluidos = projetodao.listarConcluidos(projeto.getId());
			listaStatusConcluidos = projetodao.listarStatusConcluidos(projeto.getId());
		} else {
			projeto = projetodao.listarProjetosParticipantePorID(idDoProjeto);
			listTarefas = projetodao.listarTarefas(projeto.getId());
			listaStatusTarefas = projetodao.listarStatusTarefas(projeto.getId());
			listDesenvolvimentos = projetodao.listarDesenvolvimentos(projeto.getId());
			listaStatusDesenvolvimentos = projetodao.listarStatusDesenvolvimentos(projeto.getId());
			listConcluidos = projetodao.listarConcluidos(projeto.getId());
			listaStatusConcluidos = projetodao.listarStatusConcluidos(projeto.getId());
		}

		// Ajusta a data das tarefas
		for (int i = 0; i < listTarefas.size(); i++) {
			Date ajustaData = new Date(listTarefas.get(i).getPrazo().getTime());
			int dia = listTarefas.get(i).getPrazo().getDate() + 1;
			ajustaData.setDate(dia);
			listaAjustaData.add(ajustaData);
		}

		// Ajusta a data dos desenvolvimentos
		for (int i = 0; i < listDesenvolvimentos.size(); i++) {
			Date ajustaData = new Date(listDesenvolvimentos.get(i).getPrazo().getTime());
			int dia = listDesenvolvimentos.get(i).getPrazo().getDate() + 1;
			ajustaData.setDate(dia);
			listaAjustaDataDesenvolvimentos.add(ajustaData);
		}

		// Ajusta a data dos concluidos
		for (int i = 0; i < listConcluidos.size(); i++) {
			Date ajustaData = new Date(listConcluidos.get(i).getPrazo().getTime());
			int dia = listConcluidos.get(i).getPrazo().getDate() + 1;
			ajustaData.setDate(dia);
			listaAjustaDataConcluidos.add(ajustaData);
		}

		// Lista os proprietarios das tarefas
		for (int i = 0; i < listTarefas.size(); i++) {
			if (listTarefas.get(i).getEmailProprietario() != null) {
				listaProprietarios.add(cadastroDao.findUsuario(listTarefas.get(i).getEmailProprietario()));
			} else {
				listaProprietarios.add(null);
			}
		}

		// Lista os proprietarios dos Desenvolvimentos
		for (int i = 0; i < listDesenvolvimentos.size(); i++) {
			if (listDesenvolvimentos.get(i).getEmailProprietario() != null) {
				listaProprietariosDesenvolvimentos
						.add(cadastroDao.findUsuario(listDesenvolvimentos.get(i).getEmailProprietario()));
			} else {
				listaProprietariosDesenvolvimentos.add(null);
			}
		}

		// Lista os proprietarios dos Concluidos
		for (int i = 0; i < listConcluidos.size(); i++) {
			if (listConcluidos.get(i).getEmailProprietario() != null) {
				listaProprietariosConcluidos
						.add(cadastroDao.findUsuario(listConcluidos.get(i).getEmailProprietario()));
			} else {
				listaProprietariosConcluidos.add(null);
			}
		}

		for (int i = 0; i < projetosParticipantes.size(); i++) {
			System.out.println(projetosParticipantes.get(i).getFuncao());
			System.out.println(projetosParticipantes.size());
			if (projetosParticipantes.get(i).getFuncao().equals("adm")
					|| projetosParticipantes.get(i).getFuncao().equals("membro")) {
				System.out.println("passou");
				System.out.println(projetosParticipantes.get(i).getFuncao());
				listaProjetosParticipantes
						.add(projetodao.listarProjetosParticipantePorID(projetosParticipantes.get(i).getIdProjeto()));
			} else {
				System.out.println("passou null");
				listaProjetosParticipantes.add(null);
			}

		}

		model.addObject("listaProjeto", projetodao.listarTodosProjetos(usuario.getUsername()));
		model.addObject("nomeProjeto", projeto.getNome());
		model.addObject("idProjeto", idProjeto);
		model.addObject("tarefaProjeto", projeto.getTarefa());
		model.addObject("desenvolvimentoProjeto", projeto.getDesenvolvimento());
		model.addObject("concluidoProjeto", projeto.getConcluido());
		model.addObject("listaTarefas", listTarefas);
		model.addObject("listDesenvolvimentos", listDesenvolvimentos);
		model.addObject("listConcluidos", listConcluidos);
		model.addObject("usuarioAtual", user);
		model.addObject("usuarioFoto", user.getFoto());
		model.addObject("listaAmigos", listaAmigos);
		model.addObject("listaParticipantes", listaParticipantes);
		model.addObject("listaFuncoes", listaFuncoesParticipantes);
		model.addObject("listaProprietarios", listaProprietarios);
		model.addObject("listaProprietariosDesenvolvimentos", listaProprietariosDesenvolvimentos);
		model.addObject("listaProprietariosConcluidos", listaProprietariosConcluidos);
		model.addObject("listaAjustaData", listaAjustaData);
		model.addObject("listaAjustaDataDesenvolvimentos", listaAjustaDataDesenvolvimentos);
		model.addObject("listaAjustaDataConcluidos", listaAjustaDataConcluidos);
		model.addObject("minhaFuncao", meuParticipante.getFuncao());
		model.addObject("qtnParticipante", listaParticipantes.size() - 4);
		model.addObject("projetosParticipantes", listaProjetosParticipantes);
		return model;
	}

	// Salvar, Atualizar e Deletar - Tarefas
	@RequestMapping(value = "/salvar/tarefas", method = RequestMethod.POST)
	@CacheEvict(value = "projetos", allEntries = true)
	public String saveTarefas(Tarefas tarefas) {
		tarefas.setIdProjeto(projeto.getId());
		tarefas.setStatusProjeto("Nao Iniciado");
		projetodao.saveTarefas(tarefas);
		return "redirect:/home?idProjeto=" + projeto.getId() + "&nome=" + projeto.getNome();
	}

	@RequestMapping(value = "/atualizar/tarefas", method = RequestMethod.GET)
	@CacheEvict(value = "projetos", allEntries = true)
	public String atualizarTarefas(@RequestParam int idTarefa, @RequestParam String statusTarefa) {
		Tarefas auxTarefas = projetodao.foundTarefa(idTarefa);
		auxTarefas.setStatusProjeto(statusTarefa);
		projetodao.updateTarefas(auxTarefas);
		return "redirect:/home?idProjeto=" + projeto.getId() + "&nome=" + projeto.getNome();
	}

	@RequestMapping(value = "/atualizar/tarefas-proprietario", method = RequestMethod.GET)
	@CacheEvict(value = "projetos", allEntries = true)
	public String atualizarTarefasProprietario(@RequestParam int idTarefa, @RequestParam String emailProprietario) {
		Tarefas auxTarefas = projetodao.foundTarefa(idTarefa);
		auxTarefas.setEmailProprietario(emailProprietario);
		projetodao.updateTarefas(auxTarefas);
		return "redirect:/home?idProjeto=" + projeto.getId() + "&nome=" + projeto.getNome();
	}

	@RequestMapping(value = "/remove/proprietario", method = RequestMethod.GET)
	@CacheEvict(value = "projetos", allEntries = true)
	public String removeTarefasProprietario(@RequestParam int idTarefa, @RequestParam String emailProprietario) {
		Tarefas auxTarefas = projetodao.foundTarefa(idTarefa);
		auxTarefas.setEmailProprietario(emailProprietario);
		auxTarefas.setEmailProprietario(null);
		projetodao.updateTarefas(auxTarefas);
		return "redirect:/home?idProjeto=" + projeto.getId() + "&nome=" + projeto.getNome();
	}

	@RequestMapping(value = "/delete/tarefas", method = RequestMethod.GET)
	@CacheEvict(value = "projetos", allEntries = true)
	public String deleteTarefas(@RequestParam int idTarefa) {
		projetodao.deletarTarefa(idTarefa);
		return "redirect:/home?idProjeto=" + projeto.getId() + "&nome=" + projeto.getNome();
	}

	@RequestMapping(value = "/atualizar/tarefas-concluidos", method = RequestMethod.GET)
	@CacheEvict(value = "projetos", allEntries = true)
	public String updateTarefaConcluido(@RequestParam int idTarefa, @RequestParam String statusTarefa) {
		Tarefas auxTarefas = projetodao.foundTarefa(idTarefa);
		Concluidos auxConcluidos = new Concluidos(auxTarefas.getId());
		auxTarefas.setStatusProjeto(statusTarefa);
		auxConcluidos.setIdProjeto(auxTarefas.getIdProjeto());
		auxConcluidos.setNome(auxTarefas.getNome());
		auxConcluidos.setEmailProprietario(auxTarefas.getEmailProprietario());
		auxConcluidos.setStatusProjeto(auxTarefas.getStatusProjeto());
		auxConcluidos.setPrazo(auxTarefas.getPrazo());
		projetodao.saveConcluidos(auxConcluidos);
		projetodao.deletarTarefa(idTarefa);
		return "redirect:/home?idProjeto=" + projeto.getId() + "&nome=" + projeto.getNome();
	}

	// Salvar, atualizar, deletar - Desenvolvimentos
	@RequestMapping(value = "/salvar/desenvolvimentos", method = RequestMethod.GET)
	@CacheEvict(value = "projetos", allEntries = true)
	public String saveDesenvolvimentos(@RequestParam int idTarefa, @RequestParam String statusTarefa) {
		Tarefas auxTarefas = projetodao.foundTarefa(idTarefa);
		Desenvolvimentos auxDesenvolvimentos = new Desenvolvimentos(auxTarefas.getId());
		auxTarefas.setStatusProjeto(statusTarefa);
		auxDesenvolvimentos.setIdProjeto(auxTarefas.getIdProjeto());
		auxDesenvolvimentos.setNome(auxTarefas.getNome());
		auxDesenvolvimentos.setEmailProprietario(auxTarefas.getEmailProprietario());
		auxDesenvolvimentos.setStatusProjeto(auxTarefas.getStatusProjeto());
		auxDesenvolvimentos.setPrazo(auxTarefas.getPrazo());
		projetodao.saveDesenvolvimentos(auxDesenvolvimentos);
		projetodao.deletarTarefa(idTarefa);
		return "redirect:/home?idProjeto=" + projeto.getId() + "&nome=" + projeto.getNome();
	}

	@RequestMapping(value = "/atualizar/desenvolvimentos", method = RequestMethod.GET)
	@CacheEvict(value = "projetos", allEntries = true)
	public String atualizarDesenvolvimentos(@RequestParam int idTarefa, @RequestParam String statusTarefa) {
		Desenvolvimentos auxDesenvolvimentos = projetodao.foundDesenvolvimentos(idTarefa);
		auxDesenvolvimentos.setStatusProjeto(statusTarefa);
		projetodao.updateDesenvolvimentos(auxDesenvolvimentos);
		return "redirect:/home?idProjeto=" + projeto.getId() + "&nome=" + projeto.getNome();
	}

	@RequestMapping(value = "/atualizar/desenvolvimentos-proprietario", method = RequestMethod.GET)
	@CacheEvict(value = "projetos", allEntries = true)
	public String atualizarDesenvolvimentosProprietario(@RequestParam int idTarefa,
			@RequestParam String emailProprietario) {
		Desenvolvimentos auxDesenvolvimentos = projetodao.foundDesenvolvimentos(idTarefa);
		auxDesenvolvimentos.setEmailProprietario(emailProprietario);
		projetodao.updateDesenvolvimentos(auxDesenvolvimentos);
		return "redirect:/home?idProjeto=" + projeto.getId() + "&nome=" + projeto.getNome();
	}

	@RequestMapping(value = "/remove/proprietario-desenvolvimentos", method = RequestMethod.GET)
	@CacheEvict(value = "projetos", allEntries = true)
	public String removeDesenvolvimentosProprietario(@RequestParam int idTarefa,
			@RequestParam String emailProprietario) {
		Desenvolvimentos auxDesenvolvimentos = projetodao.foundDesenvolvimentos(idTarefa);
		auxDesenvolvimentos.setEmailProprietario(emailProprietario);
		auxDesenvolvimentos.setEmailProprietario(null);
		projetodao.updateDesenvolvimentos(auxDesenvolvimentos);
		return "redirect:/home?idProjeto=" + projeto.getId() + "&nome=" + projeto.getNome();
	}

	@RequestMapping(value = "/delete/desenvolvimentos", method = RequestMethod.GET)
	@CacheEvict(value = "projetos", allEntries = true)
	public String deleteDesenvolvimentos(@RequestParam int idTarefa) {
		projetodao.deletarDesenvolvimentos(idTarefa);
		return "redirect:/home?idProjeto=" + projeto.getId() + "&nome=" + projeto.getNome();
	}

	@RequestMapping(value = "/atualiza/tarefas-desenvolvimentos", method = RequestMethod.GET)
	@CacheEvict(value = "projetos", allEntries = true)
	public String saveDesenvolvimentosParaTarefas(@RequestParam int idTarefa, @RequestParam String statusTarefa) {
		Desenvolvimentos auxDesenvolvimentos = projetodao.foundDesenvolvimentos(idTarefa);
		Tarefas auxTarefas = new Tarefas();
		auxDesenvolvimentos.setStatusProjeto(statusTarefa);
		auxTarefas.setIdProjeto(auxDesenvolvimentos.getIdProjeto());
		auxTarefas.setNome(auxDesenvolvimentos.getNome());
		auxTarefas.setEmailProprietario(auxDesenvolvimentos.getEmailProprietario());
		auxTarefas.setStatusProjeto(auxDesenvolvimentos.getStatusProjeto());
		auxTarefas.setPrazo(auxDesenvolvimentos.getPrazo());
		projetodao.saveTarefas(auxTarefas);
		projetodao.deletarDesenvolvimentos(idTarefa);
		return "redirect:/home?idProjeto=" + projeto.getId() + "&nome=" + projeto.getNome();
	}

	// Salvar, atualizar, deletar - Concluidos
	@RequestMapping(value = "/salvar/concluidos", method = RequestMethod.GET)
	@CacheEvict(value = "projetos", allEntries = true)
	public String saveConcluidos(@RequestParam int idTarefa, @RequestParam String statusTarefa) {
		Desenvolvimentos auxDesenvolvimentos = projetodao.foundDesenvolvimentos(idTarefa);
		Concluidos auxConcluidos = new Concluidos(auxDesenvolvimentos.getId());
		auxDesenvolvimentos.setStatusProjeto(statusTarefa);
		auxConcluidos.setIdProjeto(auxDesenvolvimentos.getIdProjeto());
		auxConcluidos.setNome(auxDesenvolvimentos.getNome());
		auxConcluidos.setEmailProprietario(auxDesenvolvimentos.getEmailProprietario());
		auxConcluidos.setStatusProjeto(auxDesenvolvimentos.getStatusProjeto());
		auxConcluidos.setPrazo(auxDesenvolvimentos.getPrazo());
		projetodao.saveConcluidos(auxConcluidos);
		projetodao.deletarDesenvolvimentos(idTarefa);
		return "redirect:/home?idProjeto=" + projeto.getId() + "&nome=" + projeto.getNome();
	}

	@RequestMapping(value = "/atualizar/concluidos", method = RequestMethod.GET)
	@CacheEvict(value = "projetos", allEntries = true)
	public String atualizarConcluidos(@RequestParam int idTarefa, @RequestParam String statusTarefa) {
		Concluidos auxConcluidos = projetodao.foundConcluidos(idTarefa);
		auxConcluidos.setStatusProjeto(statusTarefa);
		projetodao.updateConcluidos(auxConcluidos);
		return "redirect:/home?idProjeto=" + projeto.getId() + "&nome=" + projeto.getNome();
	}
	
	@RequestMapping(value = "/atualizar/concluidos-proprietario", method = RequestMethod.GET)
	@CacheEvict(value = "projetos", allEntries = true)
	public String atualizarConcluidosProprietario(@RequestParam int idTarefa, @RequestParam String emailProprietario) {
		Concluidos auxConcluidos = projetodao.foundConcluidos(idTarefa);
		auxConcluidos.setEmailProprietario(emailProprietario);
		projetodao.updateConcluidos(auxConcluidos);
		return "redirect:/home?idProjeto=" + projeto.getId() + "&nome=" + projeto.getNome();
	}
	
	@RequestMapping(value = "/remove/proprietario-concluidos", method = RequestMethod.GET)
	@CacheEvict(value = "projetos", allEntries = true)
	public String removeConcluidosProprietario(@RequestParam int idTarefa,
			@RequestParam String emailProprietario) {
		Concluidos auxConcluidos = projetodao.foundConcluidos(idTarefa);
		auxConcluidos.setEmailProprietario(emailProprietario);
		auxConcluidos.setEmailProprietario(null);
		projetodao.updateConcluidos(auxConcluidos);
		return "redirect:/home?idProjeto=" + projeto.getId() + "&nome=" + projeto.getNome();
	}

	@RequestMapping(value = "/atualiza/concluidos-desenvolvimentos", method = RequestMethod.GET)
	@CacheEvict(value = "projetos", allEntries = true)
	public String updateConcluidosDesenvolvimentos(@RequestParam int idTarefa, @RequestParam String statusTarefa) {
		Concluidos auxConcluidos = projetodao.foundConcluidos(idTarefa);
		Desenvolvimentos auxDesenvolvimentos = new Desenvolvimentos(auxConcluidos.getId());
		auxConcluidos.setStatusProjeto(statusTarefa);
		auxDesenvolvimentos.setIdProjeto(auxConcluidos.getIdProjeto());
		auxDesenvolvimentos.setNome(auxConcluidos.getNome());
		auxDesenvolvimentos.setEmailProprietario(auxConcluidos.getEmailProprietario());
		auxDesenvolvimentos.setStatusProjeto(auxConcluidos.getStatusProjeto());
		auxDesenvolvimentos.setPrazo(auxConcluidos.getPrazo());
		projetodao.saveDesenvolvimentos(auxDesenvolvimentos);
		projetodao.deletarConcluidos(idTarefa);
		return "redirect:/home?idProjeto=" + projeto.getId() + "&nome=" + projeto.getNome();
	}

	@RequestMapping(value = "/atualiza/concluidos-tarefas", method = RequestMethod.GET)
	@CacheEvict(value = "projetos", allEntries = true)
	public String updateConcluidosTarefas(@RequestParam int idTarefa, @RequestParam String statusTarefa) {
		Concluidos auxConcluidos = projetodao.foundConcluidos(idTarefa);
		Tarefas auxTarefas = new Tarefas();
		auxConcluidos.setStatusProjeto(statusTarefa);
		auxTarefas.setIdProjeto(auxConcluidos.getIdProjeto());
		auxTarefas.setNome(auxConcluidos.getNome());
		auxTarefas.setEmailProprietario(auxConcluidos.getEmailProprietario());
		auxTarefas.setStatusProjeto(auxConcluidos.getStatusProjeto());
		auxTarefas.setPrazo(auxConcluidos.getPrazo());
		projetodao.saveTarefas(auxTarefas);
		projetodao.deletarConcluidos(idTarefa);
		return "redirect:/home?idProjeto=" + projeto.getId() + "&nome=" + projeto.getNome();
	}

	@RequestMapping(value = "/delete/concluidos", method = RequestMethod.GET)
	@CacheEvict(value = "projetos", allEntries = true)
	public String deleteConcluidos(@RequestParam int idTarefa) {
		projetodao.deletarConcluidos(idTarefa);
		return "redirect:/home?idProjeto=" + projeto.getId() + "&nome=" + projeto.getNome();
	}

	// Cadastrar Novos Projetos
	@RequestMapping(value = "/salvar/novo-projeto", method = RequestMethod.POST)
	@CacheEvict(value = "projetos", allEntries = true)
	public String cadastroProjeto(@AuthenticationPrincipal Usuario usuario, Projeto novoProjeto) {
		novoProjeto.setEmailUsuario(usuario.getUsername());
		projetodao.save(novoProjeto);

		Participantes participante = new Participantes();
		participante.setEmailParticipante(usuario.getUsername());
		participante.setIdProjeto(novoProjeto.getId());
		participante.setFuncao("criador");
		participantesDao.save(participante);

		return "redirect:/home?idProjeto=" + novoProjeto.getId() + "&nome=" + novoProjeto.getNome();
	}

	// Recebe e Retorna o status do Usuario
	@RequestMapping("/recebe/status-usuario")
	@CacheEvict(value = "projetos", allEntries = true)
	@ResponseBody
	public String setAndGetStatusUsuario(@AuthenticationPrincipal Usuario usuario, HttpServletRequest request) {
		String status = request.getParameter("status");
		Usuario userUpdate = cadastroDao.findUsuario(usuario.getUsername());
		userUpdate.setStatusUsuario(status);
		cadastroDao.update(userUpdate);
		return userUpdate.getStatusUsuario();
	}

	// Retorna o status do Usuario
	@RequestMapping("/retorna/status-usuario/home")
	@CacheEvict(value = "projetos", allEntries = true)
	@ResponseBody
	public String getStatusUsuario(@AuthenticationPrincipal Usuario usuario) {
		user = cadastroDao.findUsuario(usuario.getUsername());
		return user.getStatusUsuario();
	}

	// Retorna o modo noturno
	@RequestMapping("/retorna/modo-noturno/home")
	@CacheEvict(value = "projetos", allEntries = true)
	@ResponseBody
	public boolean getModoNotuno() {
		return config.isModoNoturno();
	}

	// Retorna os status das desenvolvimentos
	@RequestMapping("/retorna/projeto-status-desenvolvimentos")
	@CacheEvict(value = "projetos", allEntries = true)
	@ResponseBody
	public List<String> getProjetoStatusDesenvolvimentos() {
		return listaStatusDesenvolvimentos;
	}

	// Retorna os status dos tarefas
	@RequestMapping("/retorna/projeto-status-tarefa")
	@CacheEvict(value = "projetos", allEntries = true)
	@ResponseBody
	public List<String> getProjetoStatusTarefa() {
		return listaStatusTarefas;
	}

	// Retorna os status dos concluidos
	@RequestMapping("/retorna/projeto-status-concluidos")
	@CacheEvict(value = "projetos", allEntries = true)
	@ResponseBody
	public List<String> getProjetoStatusConcluidos() {
		return listaStatusConcluidos;
	}

	// Retorna pesquisa da lista de amigos para serem participantes
	@RequestMapping("/retorna/pesquisa-amigos-projeto")
	@CacheEvict(value = "projetos", allEntries = true)
	@ResponseBody
	public List<Integer> getPesquisaUsuario(@AuthenticationPrincipal Usuario usuario, HttpServletRequest request) {
		List<Integer> ids = new ArrayList<Integer>();
		List<String> meusAmigos = new ArrayList<String>();
		List<Usuario> listaAmigos = new ArrayList<Usuario>();
		String userName = request.getParameter("nome");
		List<Usuario> listUsuario = cadastroDao.pesquisarUsuarioNome(userName);
		meusAmigos = amigosDao.listarTodosOsAmigos(usuario.getUsername());
		for (int i = 0; i < meusAmigos.size(); i++) {
			listaAmigos.add(cadastroDao.findUsuario(meusAmigos.get(i)));
		}

		for (int j = 0; j < listaAmigos.size(); j++) {
			for (int i = 0; i < listUsuario.size(); i++) {
				if (listUsuario.get(i).getEmail() == listaAmigos.get(j).getEmail()) {
					ids.add(listUsuario.get(i).getId());
				}
			}
		}

		return ids;
	}

	// Retorna todos os amigos que podem ser participantes
	@RequestMapping("/clear/pesquisa-amigos-projeto")
	@CacheEvict(value = "projetos", allEntries = true)
	@ResponseBody
	public List<Integer> clearPesquisaUsuario(@AuthenticationPrincipal Usuario usuario) {
		List<Integer> ids = new ArrayList<Integer>();
		List<String> meusAmigos = new ArrayList<String>();
		List<Usuario> listaAmigos = new ArrayList<Usuario>();
		List<Usuario> listUsers = new ArrayList<Usuario>();
		listUsers = cadastroDao.getAllUsers();
		meusAmigos = amigosDao.listarTodosOsAmigos(usuario.getUsername());
		for (int i = 0; i < meusAmigos.size(); i++) {
			listaAmigos.add(cadastroDao.findUsuario(meusAmigos.get(i)));
		}

		for (int j = 0; j < listaAmigos.size(); j++) {
			for (int i = 0; i < listUsers.size(); i++) {
				if (listUsers.get(i).getEmail() == listaAmigos.get(j).getEmail()) {
					ids.add(listUsers.get(i).getId());
				}
			}
		}
		return ids;
	}

	// Salvar, atualizar e deletar participantes
	@RequestMapping("/salvar/participante")
	@CacheEvict(value = "projetos", allEntries = true)
	@ResponseBody
	public void saveParticipantes(HttpServletRequest request) {
		Participantes participante = new Participantes();
		String email = request.getParameter("email");
		participante.setEmailParticipante(email);
		participante.setIdProjeto(idDoProjeto);
		participante.setFuncao("membro");
		participantesDao.save(participante);
	}

	@RequestMapping("/remove/participante")
	@CacheEvict(value = "projetos", allEntries = true)
	@ResponseBody
	public void deleteParticipantes(HttpServletRequest request) {
		Participantes participante = new Participantes();
		String email = request.getParameter("email");
		participante = participantesDao.findParticipante(idDoProjeto, email);
		participantesDao.delete(participante);
	}

	@RequestMapping("/tornar/adm")
	@CacheEvict(value = "projetos", allEntries = true)
	@ResponseBody
	public void updateFuncaoAdm(HttpServletRequest request) {
		Participantes participante = new Participantes();
		String email = request.getParameter("email");
		participante = participantesDao.findParticipante(idDoProjeto, email);
		participante.setFuncao("adm");
		participantesDao.update(participante);
	}

	@RequestMapping("/retirar/adm")
	@CacheEvict(value = "projetos", allEntries = true)
	@ResponseBody
	public void updateFuncaoMembro(HttpServletRequest request) {
		Participantes participante = new Participantes();
		String email = request.getParameter("email");
		participante = participantesDao.findParticipante(idDoProjeto, email);
		participante.setFuncao("membro");
		participantesDao.update(participante);
	}

	@RequestMapping("/verificacao/adm")
	@CacheEvict(value = "projetos", allEntries = true)
	@ResponseBody
	public List<String> verificacaoFuncaoAdm(HttpServletRequest request) {
		String funcao = request.getParameter("funcao");
		List<String> listEmailsAdm = participantesDao.pesquisarPorFuncao(idDoProjeto, funcao);
		return listEmailsAdm;
	}

	@RequestMapping("/retorna/minha-funcao")
	@CacheEvict(value = "projetos", allEntries = true)
	@ResponseBody
	public String getMinhaFuncao() {
		return meuParticipante.getFuncao();
	}

	// Retorna todos os participantes do projeto
	@RequestMapping("/retorna/participantes")
	@CacheEvict(value = "projetos", allEntries = true)
	@ResponseBody
	public List<String> getAllPartcipantesDoProjeto() {
		List<String> participantes = participantesDao.listarTodosOsPartcipantesDoProjeto(idDoProjeto);
		return participantes;
	}

	// Retorna todos os participantes do projeto
	@RequestMapping("/retorna/ids-participantes-projeto")
	@CacheEvict(value = "projetos", allEntries = true)
	@ResponseBody
	public List<Integer> getIDSPartcipantesDoProjeto() {
		List<Integer> ids = new ArrayList<Integer>();
		List<Usuario> listaParticipantes = new ArrayList<Usuario>();
		List<String> participantes = participantesDao.listarTodosOsPartcipantesDoProjeto(idDoProjeto);

		for (int i = 0; i < participantes.size(); i++) {
			listaParticipantes.add(cadastroDao.findUsuario(participantes.get(i)));
		}

		for (int i = 0; i < listaParticipantes.size(); i++) {
			ids.add(listaParticipantes.get(i).getId());
		}

		return ids;
	}

	@RequestMapping("/retorna/ids-proprietarios")
	@CacheEvict(value = "projetos", allEntries = true)
	@ResponseBody
	public List<Integer> getIDSProprietarios() {
		List<Integer> ids = new ArrayList<Integer>();
		List<Usuario> listaParticipantes = new ArrayList<Usuario>();
		List<String> participantes = participantesDao.listarTodosOsPartcipantesDoProjeto(idDoProjeto);

		for (int i = 0; i < participantes.size(); i++) {
			listaParticipantes.add(cadastroDao.findUsuario(participantes.get(i)));
		}

		for (int i = 0; i < listaParticipantes.size(); i++) {
			ids.add(listaParticipantes.get(i).getId());
		}

		return ids;
	}

	// Pesuquisa os participantes que estão no projeto.
	@RequestMapping("/retorna/pesquisa-participantes-projeto")
	@CacheEvict(value = "projetos", allEntries = true)
	@ResponseBody
	public List<Integer> getPesquisaPartcipantesDoProjeto(@AuthenticationPrincipal Usuario usuario,
			HttpServletRequest request) {
		List<Integer> ids = new ArrayList<Integer>();
		List<String> participantes = participantesDao.listarTodosOsPartcipantesDoProjeto(idDoProjeto);
		List<Usuario> listaParticipantes = new ArrayList<Usuario>();
		String userName = request.getParameter("nome");
		List<Usuario> listUsuario = cadastroDao.pesquisarUsuarioNome(userName);

		for (int i = 0; i < participantes.size(); i++) {
			listaParticipantes.add(cadastroDao.findUsuario(participantes.get(i)));
		}

		for (int i = 0; i < listaParticipantes.size(); i++) {
			for (int j = 0; j < listUsuario.size(); j++) {
				if (listaParticipantes.get(i).getEmail() == listUsuario.get(j).getEmail()) {
					ids.add(listaParticipantes.get(i).getId());
				}
			}
		}

		return ids;
	}
}
