package eic.cefet_rj.br.bibliotecaapp4;


import java.util.Date;

public class Exemplar {
    private int id;
    private int livroId;
    private int alunoId;
    private Date dataDev;
    private boolean disponivel;

    public Exemplar() {
    }

    public Exemplar(int livroId, int alunoId, Date dataDev, boolean disponivel) {
        this.livroId = livroId;
        this.alunoId = alunoId;
        this.dataDev = dataDev;
        this.disponivel = disponivel;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLivroId() {
        return livroId;
    }

    public void setLivroId(int livroId) {
        this.livroId = livroId;
    }

    public int getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }

    public Date getDataDev() {
        return dataDev;
    }

    public void setDataDev(Date dataDev) {
        this.dataDev = dataDev;
    }
}
