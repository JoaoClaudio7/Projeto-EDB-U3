package Model;

public class Professor extends Pessoa implements Comparable<Professor> {
    private String disciplina;
    private double salario;
    private int id;

    // ? construtor
    public Professor(String nome, String cpf, String telefone, String email, String disciplina, double salario, int id) {
        super(nome, cpf, telefone, email);
        this.disciplina = disciplina;
        this.salario = salario;
        this.id = id;
    }

    // ? getters
    public int getId() {
        return id;
    }

    public double getSalario(){
        return salario;
    }

    public String getDisciplina() {
        return disciplina;
    }
    // ? setters
    public void setDisciplina(String d) {
        this.disciplina = d;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public int compareTo(Professor o) {
        return Integer.compare(this.id, o.id);
    }
    
    @Override
    public String toString() {
        return "--------------------------------------------------\n" +
               "ID:          " + id + "\n" +
               "Nome:        " + getNome() + "\n" +
               "Disciplina:  " + disciplina + "\n" +
               "Salário:     R$ " + String.format("%.2f", salario) + "\n" +
               "CPF:         " + getCpf() + "\n" +
               "Telefone:    " + getTelefone() + "\n" +
               "Email:       " + getEmail() + "\n" +
               "--------------------------------------------------";
    }
}