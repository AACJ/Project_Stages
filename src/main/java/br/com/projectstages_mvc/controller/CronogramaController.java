package br.com.projectstages_mvc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
public class CronogramaController {

	@Autowired
	private CadastroDao cadastroDao;

	@Autowired
	private NotificacaoAmizadeDao notificacaoDao;

	@Autowired
	private ChatDao chatDao;

	@Autowired
	private ProjetoDao projetodao;

	@Autowired
	private ParticipantesDao participantesDao;

	@Autowired
	private ConfiguracoesDao configuracoesDao;

	private Usuario user = new Usuario();
	private Configuracoes config = new Configuracoes();
	private GregorianCalendar c = new GregorianCalendar();

	@RequestMapping("/cronograma")
	@Cacheable(value = "cronogramas")
	public ModelAndView cronograma(@AuthenticationPrincipal Usuario usuario) {
		ModelAndView model = new ModelAndView("cronograma");
		Date dataAtual = new Date();
		int totalMensagens = 0;
		int quantidade = 0;
		List<NotificacaoAmizade> msgNotificacoes = new ArrayList<NotificacaoAmizade>();
		List<Chat> listMensagens = new ArrayList<Chat>();
		List<Projeto> listaProjetosParticipantes = new ArrayList<Projeto>();
		List<Tarefas> listTarefas = new ArrayList<Tarefas>();
		List<Desenvolvimentos> listDesenvolvimentos = new ArrayList<Desenvolvimentos>();
		List<Concluidos> listConcluidos = new ArrayList<Concluidos>();
		List<Participantes> projetosParticipantes = new ArrayList<Participantes>();
		List<Projeto> listaProjetosFavoritos = new ArrayList<Projeto>();
		List<Tarefas> listaTarefasHoje = new ArrayList<Tarefas>();
		List<Desenvolvimentos> listaDesenvolvimentosHoje = new ArrayList<Desenvolvimentos>();
		List<Tarefas> listaTarefasMes = new ArrayList<Tarefas>();
		List<Desenvolvimentos> listaDesenvolvimentosMes = new ArrayList<Desenvolvimentos>();
		List<Concluidos> listaConcluidosMes = new ArrayList<Concluidos>();
		List<Tarefas> listaTarefasSemana = new ArrayList<Tarefas>();
		List<Desenvolvimentos> listaDesenvolvimentosSemana = new ArrayList<Desenvolvimentos>();
		List<Date> listaAjustaData = new ArrayList<Date>();
		List<Date> listaAjustaDataDesenvolvimentos = new ArrayList<Date>();
		List<Date> listaAjustaDataConcluidos = new ArrayList<Date>();
		List<java.sql.Date> listaAjustaDataTarefasHoje = new ArrayList<java.sql.Date>();
		List<java.sql.Date> listaAjustaDataDesenvolvimentosHoje = new ArrayList<java.sql.Date>();
		List<java.sql.Date> listaAjustaDataTarefasMes = new ArrayList<java.sql.Date>();
		List<java.sql.Date> listaAjustaDataDesenvolvimentosMes = new ArrayList<java.sql.Date>();
		List<java.sql.Date> listaAjustaDataTarefasSemana = new ArrayList<java.sql.Date>();
		List<java.sql.Date> listaAjustaDataDesenvolvimentosSemana = new ArrayList<java.sql.Date>();
		List<java.sql.Date> listaAjustaDataConcluidosMes = new ArrayList<java.sql.Date>();
		List<Usuario> listaProprietarios = new ArrayList<Usuario>();
		List<Usuario> listaProprietariosDesenvolvimentos = new ArrayList<Usuario>();
		List<Usuario> listaProprietariosMes = new ArrayList<Usuario>();
		List<Usuario> listaProprietariosDesenvolvimentosMes = new ArrayList<Usuario>();
		List<Usuario> listaProprietariosConcluidosMes = new ArrayList<Usuario>();
		List<Usuario> listaProprietariosSemana = new ArrayList<Usuario>();
		List<Usuario> listaProprietariosDesenvolvimentosSemana = new ArrayList<Usuario>();
		List<Usuario> listaTodosUsuarios = new ArrayList<Usuario>();
		List<String> listaNomeTarefas = new ArrayList<String>();
		List<String> listaNomeTarefasMes = new ArrayList<String>();
		List<String> listaNomeTarefasSemana = new ArrayList<String>();
		List<String> listaNomeDesenvolvimentos = new ArrayList<String>();
		List<String> listaNomeDesenvolvimentosMes = new ArrayList<String>();
		List<String> listaNomeDesenvolvimentosSemana = new ArrayList<String>();
		List<String> listaNomeConcluidosMes = new ArrayList<String>();
		user = cadastroDao.findUsuario(usuario.getUsername());
		projetosParticipantes = participantesDao.listarProjetosParticipantes(usuario.getUsername());
		msgNotificacoes = notificacaoDao.listarTodasNotificacoesDoDestinatario(usuario.getUsername());
		listaTodosUsuarios = cadastroDao.getAllUsers();
		for (int i = 0; i < msgNotificacoes.size(); i++) {
			if (msgNotificacoes.get(i).isVisualizacao() == false) {
				quantidade++;
			}
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

		for (int i = 0; i < projetosParticipantes.size(); i++) {
			listaProjetosParticipantes
					.add(projetodao.listarProjetosParticipantePorID(projetosParticipantes.get(i).getIdProjeto()));
		}

		for (int i = 0; i < projetosParticipantes.size(); i++) {
			if (projetosParticipantes.get(i).isProjetoFavorito()) {
				listaProjetosFavoritos
						.add(projetodao.listarProjetosParticipantePorID(projetosParticipantes.get(i).getIdProjeto()));
			}
		}

		if (quantidade > 0) {
			model.addObject("qtnNotificacao", quantidade);
		}
	
		// Tarefas de hoje

		// Listar todas as tarefas de todos os projetos
		for (int i = 0; i < listaProjetosParticipantes.size(); i++) {
			listTarefas = projetodao.listarTarefas(listaProjetosParticipantes.get(i).getId());
		}

		// Ajusta as datas de todas as tarefas
		for (int i = 0; i < listTarefas.size(); i++) {
			Date ajustaData = new Date(listTarefas.get(i).getPrazo().getTime());
			int dia = listTarefas.get(i).getPrazo().getDate() + 1;
			ajustaData.setDate(dia);
			listaAjustaData.add(ajustaData);
		}

		// Lista todas as atividades de hoje
		for (int i = 0; i < listaAjustaData.size(); i++) {
			if (listaAjustaData.get(i).getDate() == dataAtual.getDate()
					&& listaAjustaData.get(i).getMonth() == dataAtual.getMonth()
					&& listaAjustaData.get(i).getYear() == dataAtual.getYear()) {
				listaTarefasHoje.add(listTarefas.get(i));
			}
		}

		// Ajusta a data das tarefas de hoje
		for (int i = 0; i < listaTarefasHoje.size(); i++) {
			java.sql.Date ajustaData = new java.sql.Date(listaTarefasHoje.get(i).getPrazo().getTime());
			int dia = listaTarefasHoje.get(i).getPrazo().getDate() + 1;
			ajustaData.setDate(dia);
			listaAjustaDataTarefasHoje.add(ajustaData);
		}

		// Lista os proprietarios das tarefa
		for (int i = 0; i < listaTarefasHoje.size(); i++) {
			if (listaTarefasHoje.get(i).getEmailProprietario() != null) {
				for (int j = 0; j < listaTodosUsuarios.size(); j++) {
					if (listaTarefasHoje.get(i).getEmailProprietario().equals(listaTodosUsuarios.get(j).getEmail())) {
						listaProprietarios.add(listaTodosUsuarios.get(j));
					}
				}
			} else {
				listaProprietarios.add(null);
			}
		}

		// Lista todos os nomes das tarefas
		for (int i = 0; i < listaProjetosParticipantes.size(); i++) {
			for (int j = 0; j < listaTarefasHoje.size(); j++) {
				if (listaProjetosParticipantes.get(i).getId() == listaTarefasHoje.get(j).getIdProjeto()) {
					listaNomeTarefas.add(listaProjetosParticipantes.get(i).getNome());
				}
			}
		}

		// Listar todos desenvolvimentos de todos os projetos
		for (int i = 0; i < listaProjetosParticipantes.size(); i++) {
			listDesenvolvimentos = projetodao.listarDesenvolvimentos(listaProjetosParticipantes.get(i).getId());
		}

		// Ajusta as datas de todas os desenvolvimentos
		for (int i = 0; i < listDesenvolvimentos.size(); i++) {
			Date ajustaData = new Date(listDesenvolvimentos.get(i).getPrazo().getTime());
			int dia = listDesenvolvimentos.get(i).getPrazo().getDate() + 1;
			ajustaData.setDate(dia);
			listaAjustaDataDesenvolvimentos.add(ajustaData);
		}

		// Lista todas as atividades de hoje
		for (int i = 0; i < listaAjustaDataDesenvolvimentos.size(); i++) {
			if (listaAjustaDataDesenvolvimentos.get(i).getDate() == dataAtual.getDate()
					&& listaAjustaDataDesenvolvimentos.get(i).getMonth() == dataAtual.getMonth()
					&& listaAjustaDataDesenvolvimentos.get(i).getYear() == dataAtual.getYear()) {
				listaDesenvolvimentosHoje.add(listDesenvolvimentos.get(i));
			}
		}

		// Ajusta a data dos desenvolvimentos de hoje
		for (int i = 0; i < listaDesenvolvimentosHoje.size(); i++) {
			java.sql.Date ajustaData = new java.sql.Date(listaDesenvolvimentosHoje.get(i).getPrazo().getTime());
			int dia = listaDesenvolvimentosHoje.get(i).getPrazo().getDate() + 1;
			ajustaData.setDate(dia);
			listaAjustaDataDesenvolvimentosHoje.add(ajustaData);
		}

		// Lista os proprietarios dos desenvolvimentos
		for (int i = 0; i < listaDesenvolvimentosHoje.size(); i++) {
			if (listaDesenvolvimentosHoje.get(i).getEmailProprietario() != null) {
				for (int j = 0; j < listaTodosUsuarios.size(); j++) {
					if (listaDesenvolvimentosHoje.get(i).getEmailProprietario()
							.equals(listaTodosUsuarios.get(j).getEmail())) {
						listaProprietariosDesenvolvimentos.add(listaTodosUsuarios.get(j));
					}
				}
			} else {
				listaProprietariosDesenvolvimentos.add(null);
			}
		}

		// Lista todos os nomes das Desenvolvimentos
		for (int i = 0; i < listaProjetosParticipantes.size(); i++) {
			for (int j = 0; j < listaDesenvolvimentosHoje.size(); j++) {
				if (listaProjetosParticipantes.get(i).getId() == listaDesenvolvimentosHoje.get(j).getIdProjeto()) {
					listaNomeDesenvolvimentos.add(listaProjetosParticipantes.get(i).getNome());
				}
			}
		}

		// Tarefas desse mês

		// Lista todas as atividades desse mes
		for (int i = 0; i < listaAjustaData.size(); i++) {
			if (listaAjustaData.get(i).getMonth() == dataAtual.getMonth()
					&& listaAjustaData.get(i).getYear() == dataAtual.getYear()) {
				listaTarefasMes.add(listTarefas.get(i));
			}
		}

		// Lista todos os nomes das tarefas desse mes
		for (int i = 0; i < listaProjetosParticipantes.size(); i++) {
			for (int j = 0; j < listaTarefasMes.size(); j++) {
				if (listaProjetosParticipantes.get(i).getId() == listaTarefasMes.get(j).getIdProjeto()) {
					listaNomeTarefasMes.add(listaProjetosParticipantes.get(i).getNome());
				}
			}
		}

		// Lista todos os proprietarios das tarefas desse mes
		for (int i = 0; i < listaTarefasMes.size(); i++) {
			if (listaTarefasMes.get(i).getEmailProprietario() != null) {
				for (int j = 0; j < listaTodosUsuarios.size(); j++) {
					if (listaTarefasMes.get(i).getEmailProprietario().equals(listaTodosUsuarios.get(j).getEmail())) {
						listaProprietariosMes.add(listaTodosUsuarios.get(j));
					}
				}
			} else {
				listaProprietariosMes.add(null);
			}
		}

		// Ajusta a data das tarefas de mes
		for (int i = 0; i < listaTarefasMes.size(); i++) {
			java.sql.Date ajustaData = new java.sql.Date(listaTarefasMes.get(i).getPrazo().getTime());
			int dia = listaTarefasMes.get(i).getPrazo().getDate() + 1;
			ajustaData.setDate(dia);
			listaAjustaDataTarefasMes.add(ajustaData);
		}

		// Lista todas as atividades desse mes
		for (int i = 0; i < listaAjustaDataDesenvolvimentos.size(); i++) {
			if (listaAjustaDataDesenvolvimentos.get(i).getMonth() == dataAtual.getMonth()
					&& listaAjustaDataDesenvolvimentos.get(i).getYear() == dataAtual.getYear()) {
				listaDesenvolvimentosMes.add(listDesenvolvimentos.get(i));
			}
		}

		// Lista todos os nomes das Desenvolvimentos Mes
		for (int i = 0; i < listaProjetosParticipantes.size(); i++) {
			for (int j = 0; j < listaDesenvolvimentosMes.size(); j++) {
				if (listaProjetosParticipantes.get(i).getId() == listaDesenvolvimentosMes.get(j).getIdProjeto()) {
					listaNomeDesenvolvimentosMes.add(listaProjetosParticipantes.get(i).getNome());
				}
			}
		}

		// Lista os proprietarios dos desenvolvimentos desse mes
		for (int i = 0; i < listaDesenvolvimentosMes.size(); i++) {
			if (listaDesenvolvimentosMes.get(i).getEmailProprietario() != null) {
				for (int j = 0; j < listaTodosUsuarios.size(); j++) {
					if (listaDesenvolvimentosMes.get(i).getEmailProprietario()
							.equals(listaTodosUsuarios.get(j).getEmail())) {
						listaProprietariosDesenvolvimentosMes.add(listaTodosUsuarios.get(j));
					}
				}
			} else {
				listaProprietariosDesenvolvimentosMes.add(null);
			}
		}

		// Ajusta as datas de todas os desenvolvimentos
		for (int i = 0; i < listaDesenvolvimentosMes.size(); i++) {
			java.sql.Date ajustaData = new java.sql.Date(listaDesenvolvimentosMes.get(i).getPrazo().getTime());
			int dia = listaDesenvolvimentosMes.get(i).getPrazo().getDate() + 1;
			ajustaData.setDate(dia);
			listaAjustaDataDesenvolvimentosMes.add(ajustaData);
		}

		// Tarefas dessa semana
		c.setTime(dataAtual);
		// Lista todas as atividades dessa semana
		for (int i = 0; i < listaAjustaData.size(); i++) {
			for (int j = 1; j <= 7; j++) {
				c.set(c.DAY_OF_WEEK, j);
				Date dateDiasSemanas = c.getTime();
				if (listaAjustaData.get(i).getDate() == dateDiasSemanas.getDate()
						&& listaAjustaData.get(i).getMonth() == dateDiasSemanas.getMonth()
						&& listaAjustaData.get(i).getYear() == dateDiasSemanas.getYear()) {
					listaTarefasSemana.add(listTarefas.get(i));
				}
			}
		}

		// Lista todos os nomes das tarefas dessa semana
		for (int i = 0; i < listaProjetosParticipantes.size(); i++) {
			for (int j = 0; j < listaTarefasSemana.size(); j++) {
				if (listaProjetosParticipantes.get(i).getId() == listaTarefasSemana.get(j).getIdProjeto()) {
					listaNomeTarefasSemana.add(listaProjetosParticipantes.get(i).getNome());
				}
			}
		}

		// Ajusta a data das tarefas de semana
		for (int i = 0; i < listaTarefasSemana.size(); i++) {
			java.sql.Date ajustaData = new java.sql.Date(listaTarefasSemana.get(i).getPrazo().getTime());
			int dia = listaTarefasSemana.get(i).getPrazo().getDate() + 1;
			ajustaData.setDate(dia);
			listaAjustaDataTarefasSemana.add(ajustaData);
		}

		// Lista todos os proprietarios das tarefas dessa semana
		for (int i = 0; i < listaTarefasSemana.size(); i++) {
			if (listaTarefasSemana.get(i).getEmailProprietario() != null) {
				for (int j = 0; j < listaTodosUsuarios.size(); j++) {
					if (listaTarefasSemana.get(i).getEmailProprietario().equals(listaTodosUsuarios.get(j).getEmail())) {
						listaProprietariosSemana.add(listaTodosUsuarios.get(j));
					}
				}
			} else {
				listaProprietariosSemana.add(null);
			}
		}

		// Lista todas as atividades dessa semana
		for (int i = 0; i < listaAjustaDataDesenvolvimentos.size(); i++) {
			for (int j = 1; j <= 7; j++) {
				c.set(c.DAY_OF_WEEK, j);
				Date dateDiasSemanas = c.getTime();
				if (listaAjustaDataDesenvolvimentos.get(i).getDate() == dateDiasSemanas.getDate()
						&& listaAjustaDataDesenvolvimentos.get(i).getMonth() == dateDiasSemanas.getMonth()
						&& listaAjustaDataDesenvolvimentos.get(i).getYear() == dateDiasSemanas.getYear()) {
					listaDesenvolvimentosSemana.add(listDesenvolvimentos.get(i));
				}
			}
		}

		// Lista todos os nomes das Desenvolvimentos Semana
		for (int i = 0; i < listaProjetosParticipantes.size(); i++) {
			for (int j = 0; j < listaDesenvolvimentosSemana.size(); j++) {
				if (listaProjetosParticipantes.get(i).getId() == listaDesenvolvimentosSemana.get(j).getIdProjeto()) {
					listaNomeDesenvolvimentosSemana.add(listaProjetosParticipantes.get(i).getNome());
				}
			}
		}

		// Ajusta as datas de todas os desenvolvimentos
		for (int i = 0; i < listaDesenvolvimentosSemana.size(); i++) {
			java.sql.Date ajustaData = new java.sql.Date(listaDesenvolvimentosSemana.get(i).getPrazo().getTime());
			int dia = listaDesenvolvimentosSemana.get(i).getPrazo().getDate() + 1;
			ajustaData.setDate(dia);
			listaAjustaDataDesenvolvimentosSemana.add(ajustaData);
		}

		// Lista os proprietarios dos desenvolvimentos dessa semana
		for (int i = 0; i < listaDesenvolvimentosSemana.size(); i++) {
			if (listaDesenvolvimentosSemana.get(i).getEmailProprietario() != null) {
				for (int j = 0; j < listaTodosUsuarios.size(); j++) {
					if (listaDesenvolvimentosSemana.get(i).getEmailProprietario()
							.equals(listaTodosUsuarios.get(j).getEmail())) {
						listaProprietariosDesenvolvimentosSemana.add(listaTodosUsuarios.get(j));
					}
				}
			} else {
				listaProprietariosDesenvolvimentosSemana.add(null);
			}
		}

		// Tarefas Concluidas

		// Listar todas as tarefas de todos os concluidos
		for (int i = 0; i < listaProjetosParticipantes.size(); i++) {
			listConcluidos = projetodao.listarConcluidos(listaProjetosParticipantes.get(i).getId());
		}

		// Ajusta as datas de todos os concluidos
		for (int i = 0; i < listConcluidos.size(); i++) {
			Date ajustaData = new Date(listConcluidos.get(i).getPrazo().getTime());
			int dia = listConcluidos.get(i).getPrazo().getDate() + 1;
			ajustaData.setDate(dia);
			listaAjustaDataConcluidos.add(ajustaData);
		}

		// Lista todas as atividades desse mes
		for (int i = 0; i < listaAjustaDataConcluidos.size(); i++) {
			if (listaAjustaDataConcluidos.get(i).getMonth() == dataAtual.getMonth()
					&& listaAjustaDataConcluidos.get(i).getYear() == dataAtual.getYear()) {
				listaConcluidosMes.add(listConcluidos.get(i));
			}
		}

		// Lista todos os nomes dos Concluidos Mes
		for (int i = 0; i < listaProjetosParticipantes.size(); i++) {
			for (int j = 0; j < listaConcluidosMes.size(); j++) {
				if (listaProjetosParticipantes.get(i).getId() == listaConcluidosMes.get(j).getIdProjeto()) {
					listaNomeConcluidosMes.add(listaProjetosParticipantes.get(i).getNome());
				}
			}
		}

		// Ajusta a data dos concluidos de mes
		for (int i = 0; i < listaConcluidosMes.size(); i++) {
			java.sql.Date ajustaData = new java.sql.Date(listaConcluidosMes.get(i).getPrazo().getTime());
			int dia = listaConcluidosMes.get(i).getPrazo().getDate() + 1;
			ajustaData.setDate(dia);
			listaAjustaDataConcluidosMes.add(ajustaData);
		}

		// Lista os proprietarios dos concluidos desse mes
		for (int i = 0; i < listaConcluidosMes.size(); i++) {
			if (listaConcluidosMes.get(i).getEmailProprietario() != null) {
				for (int j = 0; j < listaTodosUsuarios.size(); j++) {
					if (listaConcluidosMes.get(i).getEmailProprietario().equals(listaTodosUsuarios.get(j).getEmail())) {
						listaProprietariosConcluidosMes.add(listaTodosUsuarios.get(j));
					}
				}
			} else {
				listaProprietariosConcluidosMes.add(null);
			}
		}

		config = configuracoesDao.configuracoesDoUsuario(usuario.getUsername());
		model.addObject("listaProjeto", projetodao.listarTodosProjetos(usuario.getUsername()));
		model.addObject("usuarioFoto", user.getFoto());
		model.addObject("projetosParticipantes", listaProjetosParticipantes);
		model.addObject("projetosFavoritos", listaProjetosFavoritos);
		model.addObject("listaTarefasHoje", listaTarefasHoje);
		model.addObject("listaDesenvolvimentosHoje", listaDesenvolvimentosHoje);
		model.addObject("listaTarefasMes", listaTarefasMes);
		model.addObject("listaDesenvolvimentosMes", listaDesenvolvimentosMes);
		model.addObject("listaConcluidosMes", listaConcluidosMes);
		model.addObject("listaTarefasSemana", listaTarefasSemana);
		model.addObject("listaDesenvolvimentosSemana", listaDesenvolvimentosSemana);
		model.addObject("dataHoje", listaAjustaDataTarefasHoje);
		model.addObject("dataDesenvolvimentosHoje", listaAjustaDataDesenvolvimentosHoje);
		model.addObject("dataMes", listaAjustaDataTarefasMes);
		model.addObject("dataDesenvolvimentosMes", listaAjustaDataDesenvolvimentosMes);
		model.addObject("dataConcluidosMes", listaAjustaDataConcluidosMes);
		model.addObject("dataSemana", listaAjustaDataTarefasSemana);
		model.addObject("dataDesenvolvimentosSemana", listaAjustaDataDesenvolvimentosSemana);
		model.addObject("listaProprietarios", listaProprietarios);
		model.addObject("listaProprietariosDesenvolvimentos", listaProprietariosDesenvolvimentos);
		model.addObject("listaProprietariosMes", listaProprietariosMes);
		model.addObject("listaProprietariosDesenvolvimentosMes", listaProprietariosDesenvolvimentosMes);
		model.addObject("listaProprietariosConcluidosMes", listaProprietariosConcluidosMes);
		model.addObject("listaProprietariosSemana", listaProprietariosSemana);
		model.addObject("listaProprietariosDesenvolvimentosSemana", listaProprietariosDesenvolvimentosSemana);
		model.addObject("listaNomesTarefas", listaNomeTarefas);
		model.addObject("listaNomeDesenvolvimentos", listaNomeDesenvolvimentos);
		model.addObject("listaNomesTarefasMes", listaNomeTarefasMes);
		model.addObject("listaNomeDesenvolvimentosMes", listaNomeDesenvolvimentosMes);
		model.addObject("listaNomesTarefasSemana", listaNomeTarefasSemana);
		model.addObject("listaNomeDesenvolvimentosSemana", listaNomeDesenvolvimentosSemana);
		model.addObject("listaNomesConcluidosMes", listaNomeConcluidosMes);
		return model;
	}

	// Retorna o status do Usuario
	@RequestMapping("/retorna/status-usuario/cronograma")
	@CacheEvict(value = "cronogramas", allEntries = true)
	@ResponseBody
	public String getStatusUsuario(@AuthenticationPrincipal Usuario usuario) {
		user = cadastroDao.findUsuario(usuario.getUsername());
		return user.getStatusUsuario();
	}

	@RequestMapping("/retorna/modo-noturno/cronograma")
	@CacheEvict(value = "cronogramas", allEntries = true)
	@ResponseBody
	public boolean getModoNotuno() {
		return config.isModoNoturno();
	}
}