package Model;

import java.util.ArrayList;
import java.util.List;

public class Curso implements Comparable<Curso> {
    private String nome;
    private int codigo;
    private int duracaoSemestres;
    private List<Turma> turmas;

    // ? construtor
    public Curso(String nome, int codigo, int duracaoSemestres){
        this.nome = nome;
        this.codigo = codigo;
        this.duracaoSemestres = duracaoSemestres;
        this.turmas = new ArrayList<>();
    }

    // ? getters
    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getDuracaoSemestres() {
        return duracaoSemestres;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    // ? setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDuracaoSemestres(int duracaoSemestres) {
        this.duracaoSemestres = duracaoSemestres;
    }

    // ? métodos para gerenciar turmas
    public void adicionarTurma(Turma turma) {
        this.turmas.add(turma);
    }

    public void removerTurma(int turmaId) {
        this.turmas.removeIf(t -> t.getId() == turmaId);
    }

    public Turma buscarTurma(int turmaId) {
        return this.turmas.stream()
                .filter(t -> t.getId() == turmaId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public int compareTo(Curso o) {
        return Integer.compare((this.codigo), o.codigo);
    }

    @Override
    public String toString() {
        return codigo + " - " + getNome() + " (" + turmas.size() + " turmas)";
    }
}