package Model;

public class Professor extends Pessoa implements Comparable<Professor> {
    private String disciplina;
    private double salario;
    private int id;
    private Turma turma;

    // ? construtor
    public Professor(String nome, String cpf, String telefone, String email, String disciplina, double salario,
            int id) {
        super(nome, cpf, telefone, email);
        this.disciplina = disciplina;
        this.salario = salario;
        this.id = id;
        this.turma = null;
    }

    // ? getters
    public int getId() {
        return id;
    }

    public double getSalario() {
        return salario;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public Turma getTurma() {
        return turma;
    }

    // ? setters
    public void setDisciplina(String d) {
        this.disciplina = d;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public int compareTo(Professor o) {
        return Integer.compare(this.id, o.id);
    }

    @Override
    public String toString() {
        String turmaInfo = (turma != null) ? " - Turma: " + turma.getId() : " - Sem turma";
        return id + " - " + getNome() + turmaInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Professor))
            return false;
        Professor p = (Professor) o;
        return this.id == p.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

}