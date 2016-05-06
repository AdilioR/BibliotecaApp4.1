package eic.cefet_rj.br.bibliotecaapp4;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final Context context = this;
    final DatabaseHandler db = new DatabaseHandler(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btLogin = (Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(onClickLogin());
    }
    private View.OnClickListener onClickLogin() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tLogin = (TextView) findViewById(R.id.tLogin);
                TextView tSenha = (TextView) findViewById(R.id.tSenha);
                String login = tLogin.getText().toString();
                String senha = tSenha.getText().toString();

                if (db.validarLogin(login, senha)) {
                    alert("Bem vindo!");
                    setContentView(R.layout.activity_home);
                } else {
                    alert("Login ou senha incorreto");
                }
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


        /**
         * CRUD Operations - Testes
         * */
        // Inserting Alunos
        //Log.d("Insert: ", "Inserting ..");
        //db.addAluno(new Aluno("0000000WEB" , "Ravi", "123"));
        //db.addAluno(new Aluno("0000001WEB" , "Srinivas", "123"));
        //db.addAluno(new Aluno("0000002WEB" , "Tommy", "123"));
        //db.addAluno(new Aluno("0000003WEB" , "Karthik", "123"));

        // Reading all Alunos (teste - log)
        /*
        Log.d("Reading: ", "Reading all alunos..");

        List<Aluno> aluno = db.getAllContacts();

        for (Aluno cn : aluno) {
            String log = "Id: "+cn.getId()+" ,Matricula: "+cn.getMatricula()+" ,Name: " + cn.getName() + " ,Senha: " + cn.getSenha();
            // Writing Alunos to log
            Log.d("Name: ", log);
        }
        */

        //Teste
        // Reading single Aluno by name Ravi (teste - log)
        //db.getAuno("0000004WEB");


    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

}
