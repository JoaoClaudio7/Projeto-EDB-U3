package Model;

import ArvoreAVL.ArvoreAVL;

public class Turma implements Comparable<Turma> {
    private int id;
    private String ano;
    private int semestre;
    private Disciplina disciplina;
    private Professor professor;
    private Curso curso; 
    private ArvoreAVL<Aluno> alunos;

    // construtor
    public Turma(int id, String ano, int semestre) {
        this.id = id;
        this.ano = ano;
        this.semestre = semestre;
        this.disciplina = null;
        this.professor = null;
        this.curso = null;
        this.alunos = new ArvoreAVL<>();
    }

    // getters
    public int getId() {
        return id;
    }

    public String getAno() {
        return ano;
    }

    public int getSemestre() {
        return semestre;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public Professor getProfessor() {
        return professor;
    }

    public Curso getCurso() {
        return curso;
    }

    public ArvoreAVL<Aluno> getAlunos() {
        return alunos;
    }

    // setters
    public void setAno(String ano) {
        this.ano = ano;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
        if (professor != null)
            professor.setTurma(this);
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
        if (curso != null)
            curso.adicionarTurma(this);
    }

    // gestão de alunos
    public void adicionarAluno(Aluno aluno) {
        this.alunos.inserir(aluno);
        aluno.setTurma(this);
    }

    public void removerAluno(int matricula) {
        for (Aluno a : alunos.emOrdem()) {
            if (a.getMatricula() == matricula) {
                a.setTurma(null);
                alunos.remover(a);
                return;
            }
        }
    }

    public Aluno buscarAluno(int matricula) {
        for (Aluno a : alunos.emOrdem()) {
            if (a.getMatricula() == matricula)
                return a;
        }
        return null;
    }

    @Override
    public int compareTo(Turma o) {
        return Integer.compare(this.id, o.id);
    }

    @Override
    public String toString() {
        String disciplinaInfo = (disciplina != null) ? disciplina.getNome() : "Sem disciplina";
        String professorInfo = (professor != null) ? professor.getNome() : "Sem professor";
        String cursoInfo = (curso != null) ? curso.getNome() : "Sem curso";

        return "Turma " + id + " (" + ano + "/" + semestre + ") - " + disciplinaInfo +
                " - Prof: " + professorInfo +
                " - Curso: " + cursoInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Turma))
            return false;
        return this.id == ((Turma) o).id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
