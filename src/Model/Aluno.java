package Model;

public class Aluno extends Pessoa implements Comparable<Aluno>{
    private String curso;
    private int matricula;
    private Turma turma;
    
    // ? construtor
    public Aluno(String nome, String cpf, String telefone, String email, String curso, int matricula) {
        super(nome, cpf, telefone, email);
        this.curso = curso;
        this.matricula = matricula;
        this.turma = null;
    }

    // ? getters
    public String getCurso() {
        return curso;
    }

    public int getMatricula() {
        return matricula;
    }

    public Turma getTurma() {
        return turma;
    }

    // ? setters
    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public int compareTo(Aluno other) {
        return Integer.compare(this.matricula, other.matricula);
    }

    @Override
    public String toString() {
        String turmaInfo = (turma != null) ? " - Turma: " + turma.getId() : " - Sem turma";
        return matricula + " - " + getNome() + turmaInfo;
    }
    
}