package Services;

import ArvoreAVL.ArvoreAVL;
import Exception.NaoEncontradoException;
import Interface.Service;
import Model.Professor;

public class ServiceProfessor implements Service<Professor> {
    private ArvoreAVL<Professor> arvore = new ArvoreAVL<>();

    @Override
    public void inserir(Professor p) {
        arvore.inserir(p);
    }

    @Override
    public void remover(int id) {
        arvore.remover(new Professor("", "", "", "", "", 0.0, id));
    }

    @Override
    public Professor buscar(int id) throws NaoEncontradoException {
        Professor dummy = new Professor("", "", "", "", "", 0.0, id);
        Professor p = arvore.buscar(dummy);
        if (p == null) throw new NaoEncontradoException("Professor não encontrado.");
        return p;
    }

    @Override
    public void atualizar(int id, Professor novosDados) throws NaoEncontradoException {
        Professor existente = buscar(id);
        existente.setNome(novosDados.getNome());
        existente.setTelefone(novosDados.getTelefone());
        existente.setEmail(novosDados.getEmail());
        existente.setDisciplina(novosDados.getDisciplina());
        existente.setSalario(novosDados.getSalario());
    }

    @Override
    public void listar() {
        System.out.println("--- Lista de Professores ---");
        for (Professor p : arvore.emOrdem()) {
            System.out.println(p);
        }
    }

    @Override
    public void exibirArvore() {
        arvore.exibirArvore();
    }
}
