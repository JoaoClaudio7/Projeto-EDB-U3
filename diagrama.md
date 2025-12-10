# Diagrama de Classes do Sistema

```mermaid
classDiagram
    %% ==========================================
    %% PACOTE: VIEW (NOVO)
    %% ==========================================
    namespace View {
        class MenuHandler {
            -ServiceAluno serviceAluno$
            -ServiceProfessor serviceProfessor$
            -ServiceDisciplina serviceDisciplina$
            -ServiceCurso serviceCurso$
            -ServiceTurma serviceTurma$
            +exibirMenuPrincipal()$
            -menuAluno()$
            -menuProfessor()$
            -menuDisciplina()$
            -menuCurso()$
            -menuTurma()$
            -carregarDadosIniciais()$
        }
    }

    %% ==========================================
    %% MAIN
    %% ==========================================
    class Main {
        +main(String[] args)$
    }

    Main --> View.MenuHandler : usa

    %% ==========================================
    %% PACOTE: MODEL
    %% ==========================================
    namespace Model {
        class Pessoa {
            -String nome
            -String cpf
            -String telefone
            -String email
            +getNome() String
            +setCpf(String cpf)
            +setTelefone(String telefone)
            +setEmail(String email)
        }

        class Aluno {
            -String curso
            -int matricula
            -Turma turma
            +getMatricula() int
            +setTurma(Turma turma)
            +compareTo(Aluno o) int
        }

        class Professor {
            -String disciplina
            -double salario
            -int id
            -Turma turma
            +getId() int
            +setTurma(Turma turma)
            +compareTo(Professor o) int
        }

        class Turma {
            -int id
            -String ano
            -int semestre
            -Disciplina disciplina
            -Professor professor
            -Curso curso
            -ArvoreAVL~Aluno~ alunos
            +adicionarAluno(Aluno aluno)
            +removerAluno(int matricula)
            +setProfessor(Professor professor)
            +setCurso(Curso curso)
            +buscarAluno(int matricula) Aluno
            +compareTo(Turma o) int
        }

        class Curso {
            -String nome
            -int codigo
            -int duracaoSemestres
            -ArvoreAVL~Turma~ turmas
            +adicionarTurma(Turma turma)
            +removerTurma(int id)
            +buscarTurma(int id) Turma
            +compareTo(Curso o) int
        }

        class Disciplina {
            -String nome
            -int codigo
            -int cargaHoraria
            +compareTo(Disciplina o) int
        }
    }

    %% Herança e Relacionamentos do Model
    Pessoa <|-- Aluno
    Pessoa <|-- Professor

    Turma "0..*" o-- "1" Curso : pertence a
    Curso "1" *-- "0..*" Turma : contem
    
    Turma "0..*" --> "0..1" Disciplina : tem
    
    Turma "0..1" <--> "0..1" Professor : leciona
    
    Turma "1" <--> "0..*" Aluno : matriculado

    %% ==========================================
    %% PACOTE: ARVORE AVL (ENCAPSULADO)
    %% ==========================================
    namespace ArvoreAVL {
        class No~T~ {
            -T chave
            -No~T~ esquerda
            -No~T~ direita
            -int altura
            +getChave() T
            +setChave(T chave)
            +getEsquerda() No~T~
            +setEsquerda(No~T~ no)
            +getDireita() No~T~
            +setDireita(No~T~ no)
            +getAltura() int
        }

        class ArvoreAVL~T~ {
            -No~T~ raiz
            +inserir(T chave)
            +remover(T chave)
            +buscar(T chave) T
            +emOrdem() List~T~
            -rotacaoDireita(No y) No
            -rotacaoEsquerda(No x) No
        }
    }

    ArvoreAVL *-- No : compoe

    %% ==========================================
    %% PACOTE: SERVICES
    %% ==========================================
    namespace Interface {
        class Service~T~ {
            <<interface>>
            +inserir(T objeto)
            +remover(int id)
            +buscar(int id) T
            +atualizar(int id, T dados)
            +listar()
            +existe(int id) boolean
        }
    }

    namespace Services {
        class ServiceAluno {
            -ArvoreAVL~Aluno~ arvore
        }
        class ServiceProfessor {
            -ArvoreAVL~Professor~ arvore
        }
        class ServiceCurso {
            -ArvoreAVL~Curso~ arvore
        }
        class ServiceDisciplina {
            -ArvoreAVL~Disciplina~ arvore
        }
        class ServiceTurma {
            -ArvoreAVL~Turma~ arvore
            +vincularCurso(int idTurma, Curso curso)
            +vincularProfessor(int idTurma, Professor prof)
            +vincularDisciplina(int idTurma, Disciplina disc)
            +possuiTodos(int idTurma) boolean
        }
    }

    Service <|.. ServiceAluno
    Service <|.. ServiceProfessor
    Service <|.. ServiceCurso
    Service <|.. ServiceDisciplina
    Service <|.. ServiceTurma

    %% Dependências: View -> Services
    View.MenuHandler --> Services.ServiceAluno
    View.MenuHandler --> Services.ServiceProfessor
    View.MenuHandler --> Services.ServiceCurso
    View.MenuHandler --> Services.ServiceDisciplina
    View.MenuHandler --> Services.ServiceTurma

    %% Dependências: Services -> Models/Arvore
    ServiceAluno --> ArvoreAVL : usa
    ServiceProfessor --> ArvoreAVL : usa
    ServiceCurso --> ArvoreAVL : usa
    ServiceDisciplina --> ArvoreAVL : usa
    ServiceTurma --> ArvoreAVL : usa

    ServiceAluno ..> Model.Aluno
    ServiceProfessor ..> Model.Professor
    ServiceCurso ..> Model.Curso
    ServiceDisciplina ..> Model.Disciplina
    ServiceTurma ..> Model.Turma

    %% ==========================================
    %% UTILS
    %% ==========================================
    namespace Utils {
        class InputHandler {
            +lerInt(String msg)$ int
            +lerString(String msg)$ String
            +lerDouble(String msg)$ double
        }
    }

    View.MenuHandler ..> Utils.InputHandler : usa
```