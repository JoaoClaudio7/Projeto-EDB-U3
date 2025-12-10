package Model;

import ArvoreAVL.ArvoreAVL;

public class Curso implements Comparable<Curso> {
    private String nome;
    private int codigo;
    private int duracaoSemestres;
    private ArvoreAVL<Turma> turmas;

    public Curso(String nome, int codigo, int duracaoSemestres) {
        this.nome = nome;
        this.codigo = codigo;
        this.duracaoSemestres = duracaoSemestres;
        this.turmas = new ArvoreAVL<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getDuracaoSemestres() {
        return duracaoSemestres;
    }

    public ArvoreAVL<Turma> getTurmas (){
        return turmas;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDuracaoSemestres(int duracaoSemestres) {
        this.duracaoSemestres = duracaoSemestres;
    }

    public void adicionarTurma(Turma turma) {
        this.turmas.inserir(turma);
    }

    public void removerTurma(int turmaId) {
        this.turmas.remover(new Turma(turmaId, "", 0));
    }

    public Turma buscarTurma(int turmaId) {
        return this.turmas.buscar(new Turma(turmaId, "", 0));
    }

    @Override
    public int compareTo(Curso o) {
        return Integer.compare(this.codigo, o.codigo);
    }

    @Override
    public String toString() {
        return codigo + " - " + nome + " (" + turmas.emOrdem().size() + " turmas)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Curso)) return false;
        Curso c = (Curso) o;
        return this.codigo == c.codigo;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(codigo);
    }
}