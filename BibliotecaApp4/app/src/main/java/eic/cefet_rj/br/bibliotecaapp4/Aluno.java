package eic.cefet_rj.br.bibliotecaapp4;

/**
 * Created by Arthur on 08/04/2016.
 */
public class Aluno {

    private int id;
    private String name;
    private String senha;
    private String matricula;

    //Empty constructor
    public Aluno(){

    }

    //constructor
    /*
    public Aluno (int id, String name, String matricula){
        this.setId(id);
        this.setName(name);
        this.setMatricula(matricula);
    }
    */

    //constructor
    public Aluno (String matricula, String name, String senha){
        this.setMatricula(matricula);
        this.setName(name);
        this.setSenha(senha);
    }

    //constructor

    public Aluno (int id, String matricula, String name, String senha){
        this.setId(id);
        this.setMatricula(matricula);
        this.setName(name);
        this.setSenha(senha);
    }


    /*
    //constructor
    public Aluno (String matricula, String senha){
        this.setMatricula(matricula);
        this.setSenha(senha);
    }
    */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
