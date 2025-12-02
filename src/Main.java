import java.util.Scanner;
import Services.*;
import Model.*;
import Exception.NaoEncontradoException;

public class Main {
    private static ServiceAluno serviceAluno = new ServiceAluno();
    private static ServiceProfessor serviceProfessor = new ServiceProfessor();
    private static ServiceDisciplina serviceDisciplina = new ServiceDisciplina();
    private static ServiceCurso serviceCurso = new ServiceCurso();
    private static ServiceTurma serviceTurma = new ServiceTurma();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = -1;
        
        // . Loop principal do programa 
        while (opcao != 0) {
            System.out.println("\n=== SISTEMA DE GESTÃO ACADÊMICA ===");
            System.out.println("1. Gerir Alunos");
            System.out.println("2. Gerir Professores");
            System.out.println("3. Gerir Disciplinas");
            System.out.println("4. Gerir Cursos");
            System.out.println("5. Gerir Turmas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            try {
                String entrada = scanner.nextLine();
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
                continue;
            }

            switch (opcao) {
                case 1: menuAluno(); break;
                case 2: menuProfessor(); break;
                case 3: menuDisciplina(); break;
                case 4: menuCurso(); break;
                case 5: menuTurma(); break;
                case 0: System.out.println("A encerrar sistema..."); break;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    // ? Menus 

    // . Alunos 
    private static void menuAluno() {
        System.out.println("\n--- GESTÃO DE ALUNOS ---");
        System.out.println("1. Cadastrar Aluno");
        System.out.println("2. Buscar Aluno");
        System.out.println("3. Atualizar Aluno");
        System.out.println("4. Remover Aluno");
        System.out.println("5. Listar Todos");
        System.out.println("6. Ver Árvore (Visual)");
        System.out.print("Opção: ");
        
        int opcao = lerInteiro();
        try {
            switch (opcao) {
                case 1:
                    System.out.println("Cadastrar (Matrícula, Nome, CPF, Tel, Email, Curso):");
                    System.out.print("Matrícula (número): "); int mat = lerInteiro();
                    System.out.print("Nome: "); String nome = scanner.nextLine();
                    System.out.print("CPF: "); String cpf = scanner.nextLine();
                    System.out.print("Telefone: "); String tel = scanner.nextLine();
                    System.out.print("Email: "); String email = scanner.nextLine();
                    System.out.print("Curso: "); String curso = scanner.nextLine();
                    serviceAluno.inserir(new Aluno(nome, cpf, tel, email, curso, mat));
                    System.out.println("Sucesso!");
                    break;
                case 2:
                    System.out.print("Digite a Matrícula: ");
                    System.out.println(serviceAluno.buscar(lerInteiro()).getNome());
                    break;
                case 3:
                    System.out.print("Digite a Matrícula para atualizar: ");
                    int matUp = lerInteiro();
                    // Buscamos primeiro para confirmar que existe
                    serviceAluno.buscar(matUp); 
                    System.out.println("Novos dados (Nome, Tel, Email, Curso):");
                    System.out.print("Nome: "); String nNome = scanner.nextLine();
                    System.out.print("Telefone: "); String nTel = scanner.nextLine();
                    System.out.print("Email: "); String nEmail = scanner.nextLine();
                    System.out.print("Curso: "); String nCurso = scanner.nextLine();
                    // Passamos um objeto temporário com os novos dados
                    serviceAluno.atualizar(matUp, new Aluno(nNome, "", nTel, nEmail, nCurso, matUp));
                    System.out.println("Atualizado!");
                    break;
                case 4:
                    System.out.print("Digite a Matrícula para remover: ");
                    serviceAluno.remover(lerInteiro());
                    System.out.println("Removido (se existia).");
                    break;
                case 5:
                    serviceAluno.listar();
                    break;
                case 6:
                    serviceAluno.exibirArvore();
                    break;
            }
        } catch (NaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    // . Professores 
    private static void menuProfessor() {
        System.out.println("\n--- GESTÃO DE PROFESSORES ---");
        System.out.println("1. Cadastrar | 2. Buscar | 3. Atualizar | 4. Remover | 5. Listar | 6. Árvore");
        int opcao = lerInteiro();
        try {
            if (opcao == 1) {
                System.out.print("ID: "); int id = lerInteiro();
                System.out.print("Nome: "); String nome = scanner.nextLine();
                System.out.print("CPF: "); String cpf = scanner.nextLine();
                System.out.print("Tel: "); String tel = scanner.nextLine();
                System.out.print("Email: "); String email = scanner.nextLine();
                System.out.print("Disciplina: "); String disc = scanner.nextLine();
                System.out.print("Salário: "); double sal = Double.parseDouble(scanner.nextLine());
                serviceProfessor.inserir(new Professor(nome, cpf, tel, email, disc, sal, id));
            } else if (opcao == 2) {
                System.out.print("ID: "); System.out.println(serviceProfessor.buscar(lerInteiro()));
            } else if (opcao == 3) {
                System.out.print("ID: "); int id = lerInteiro();
                System.out.print("Novo Nome: "); String nome = scanner.nextLine();
                System.out.print("Nova Disc: "); String disc = scanner.nextLine();
                serviceProfessor.atualizar(id, new Professor(nome, "", "", "", disc, 0.0, id));
            } else if (opcao == 4) {
                System.out.print("ID: "); serviceProfessor.remover(lerInteiro());
            } else if (opcao == 5) serviceProfessor.listar();
              else if (opcao == 6) serviceProfessor.exibirArvore();
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    // . Disciplinas 
    private static void menuDisciplina() {
        System.out.println("\n--- GESTÃO DE DISCIPLINAS ---");
        System.out.println("1. Cadastrar | 2. Buscar | 3. Atualizar | 4. Remover | 5. Listar | 6. Árvore");
        int opcao = lerInteiro();
        try {
            if (opcao == 1) {
                System.out.print("Código: "); int cod = lerInteiro();
                System.out.print("Nome: "); String nome = scanner.nextLine();
                System.out.print("Carga Horária: "); int ch = lerInteiro();
                serviceDisciplina.inserir(new Disciplina(nome, cod, ch));
            } else if (opcao == 2) {
                System.out.print("Código: "); System.out.println(serviceDisciplina.buscar(lerInteiro()));
            } else if (opcao == 3) {
                System.out.print("Código: "); int cod = lerInteiro();
                System.out.print("Novo Nome: "); String nome = scanner.nextLine();
                System.out.print("Nova CH: "); int ch = lerInteiro();
                serviceDisciplina.atualizar(cod, new Disciplina(nome, cod, ch));
            } else if (opcao == 4) {
                System.out.print("Código: "); serviceDisciplina.remover(lerInteiro());
            } else if (opcao == 5) serviceDisciplina.listar();
              else if (opcao == 6) serviceDisciplina.exibirArvore();
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    // . Cursos 
    private static void menuCurso() {
        System.out.println("\n--- GESTÃO DE CURSOS ---");
        System.out.println("1. Cadastrar | 2. Buscar | 3. Atualizar | 4. Remover | 5. Listar | 6. Árvore");
        int opcao = lerInteiro();
        try {
            if (opcao == 1) {
                System.out.print("Código: "); int cod = lerInteiro();
                System.out.print("Nome: "); String nome = scanner.nextLine();
                System.out.print("Duração (semestres): "); int dur = lerInteiro();
                serviceCurso.inserir(new Curso(nome, cod, dur));
            } else if (opcao == 2) {
                System.out.print("Código: "); System.out.println(serviceCurso.buscar(lerInteiro()));
            } else if (opcao == 3) {
                System.out.print("Código: "); int cod = lerInteiro();
                System.out.print("Novo Nome: "); String nome = scanner.nextLine();
                System.out.print("Nova Duração: "); int dur = lerInteiro();
                serviceCurso.atualizar(cod, new Curso(nome, cod, dur));
            } else if (opcao == 4) {
                System.out.print("Código: "); serviceCurso.remover(lerInteiro());
            } else if (opcao == 5) serviceCurso.listar();
              else if (opcao == 6) serviceCurso.exibirArvore();
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    // . Turmas 
    private static void menuTurma() {
        System.out.println("\n--- GESTÃO DE TURMAS ---");
        System.out.println("1. Cadastrar | 2. Buscar | 3. Atualizar | 4. Remover | 5. Listar | 6. Árvore");
        int opcao = lerInteiro();
        try {
            if (opcao == 1) {
                System.out.print("ID da Turma: "); int id = lerInteiro();
                System.out.print("Ano (ex: 2024): "); String ano = scanner.nextLine();
                System.out.print("Semestre (1 ou 2): "); int sem = lerInteiro();
                System.out.print("Cód. Disciplina: "); int codDisc = lerInteiro();
                serviceTurma.inserir(new Turma(id, ano, sem, codDisc));
            } else if (opcao == 2) {
                System.out.print("ID: "); System.out.println(serviceTurma.buscar(lerInteiro()));
            } else if (opcao == 3) {
                System.out.print("ID: "); int id = lerInteiro();
                System.out.print("Novo Ano: "); String ano = scanner.nextLine();
                System.out.print("Novo Semestre: "); int sem = lerInteiro();
                serviceTurma.atualizar(id, new Turma(id, ano, sem, 0));
            } else if (opcao == 4) {
                System.out.print("ID: "); serviceTurma.remover(lerInteiro());
            } else if (opcao == 5) serviceTurma.listar();
              else if (opcao == 6) serviceTurma.exibirArvore();
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    // ? Funções auxiliares 
    // . Evitar problemas com buffer 
    private static int lerInteiro() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}