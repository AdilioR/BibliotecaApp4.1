package eic.cefet_rj.br.bibliotecaapp4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {
    Aluno aluno = null;


    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 8;

    // Database Name
    private static final String DATABASE_NAME = "DatabaseBiblioteca";

    // table names
    private static final String TABLE_ALUNO = "Aluno";
    private static final String TABLE_LIVRO = "Livro";
    private static final String TABLE_EXEMPLAR = "Exemplar";

    //KEY_ID
    private static final String KEY_ID = "id";

    // Aluno Table Columns names
    private static final String KEY_NAME = "name";
    private static final String KEY_SENHA = "senha";
    private static final String KEY_MATRICULA = "matricula";

    //Livro table columns
    private static final String KEY_TITULO = "titulo";
    private static final String KEY_AUTOR = "autor";

    //Exemplares table column
    private static final String KEY_LIVRO_ID = "livro_id";
    private static final String KEY_ALUNO_ID = "aluno_id";
    private static final String KEY_DATA_DEV = "data_dev";
    private static final String KEY_DISPONIVEL = "disponivel";


    //Criando tabelas
    private static final String CREATE_ALUNOS_TABLE = "CREATE TABLE "
            + TABLE_ALUNO + "("
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_MATRICULA + " VARCHAR UNIQUE, "
            + KEY_NAME + " TEXT, "
            + KEY_SENHA + " VARCHAR)";

    private static final String CREATE_LIVROS_TABLE = "CREATE TABLE "
            + TABLE_LIVRO + "("
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_TITULO + " VARCHAR, "
            + KEY_AUTOR + " VARCHAR)";

    private static final String CREATE_TABLE_EXEMPLAR = "CREATE TABLE "
            + TABLE_EXEMPLAR + "("
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_LIVRO_ID + " INTEGER, "
            + KEY_ALUNO_ID + " INTEGER, "
            + KEY_DATA_DEV + " DATE, "
            + KEY_DISPONIVEL + " BOOLEAN, " +
            "FOREIGN KEY (" +KEY_LIVRO_ID+ ") REFERENCES "+TABLE_LIVRO+" (id), " +
            "FOREIGN KEY (" +KEY_ALUNO_ID+ ") REFERENCES "+TABLE_ALUNO+" (id)" +
            ")";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("SQL", "Criando tabela!!!!!!!!!!!");
        db.execSQL(CREATE_ALUNOS_TABLE);
        db.execSQL(CREATE_LIVROS_TABLE);
        db.execSQL(CREATE_TABLE_EXEMPLAR);
        Log.d("SQL", "Talbelas criadas!!!!!!");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.d("SQL", "Atualizando tabela!!!!!!!!!!!!");
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALUNO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIVRO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXEMPLAR);

        // Create tables again
        onCreate(db);
        Log.d("SQL", "Tabelas atualizadas!!!!");
    }

    // Adding new aluno
    public void addAluno(Aluno aluno) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MATRICULA, aluno.getMatricula()); // Aluno Matricula
        values.put(KEY_NAME, aluno.getName()); // Aluno Name
        values.put(KEY_SENHA, aluno.getSenha()); // Aluno Senha

        // Inserting Row
        db.insert(TABLE_ALUNO, null, values);
        db.close(); // Closing database connection
    }

    // Getting single aluno

    public boolean validarLogin(String matricula, String senha) {
        Log.d("DatadaseHandler","Entrou no método validarLogin!");
        boolean validar;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.query(TABLE_ALUNO, new String[]{KEY_ID, KEY_MATRICULA,
                            KEY_NAME, KEY_SENHA}, KEY_MATRICULA + "=?",
                    new String[]{String.valueOf(matricula)}, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }

            if (cursor.getCount() > 0) {
                Log.d("DatabaseHandler", "Encontrou aluno na base de dados!");
                aluno = new Aluno(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            } else {
                Log.d("DatabaseHandler", "Matricula não encontrada na base de dados");
            }
            if (aluno.getSenha().equals(senha)) {
                Log.d("DatabaseHandler", "Matrícula e senha conferem!");
                validar = true;
            } else {
                Log.d("DatabaseHandler", "Matricula ou senha não conferem!");
                validar = false;
            }
        }finally {
            db.close();
        }
        return validar;
    }

    public void retornaAluno(){
        //testando
        //Log.d("teste getAluno", "Retornando um aluno!!!!!!");
        String log = "Id: "+ aluno.getId() +" ,Matricula: "+aluno.getMatricula()+" ,Name: " + aluno.getName() + " ,Senha: " + aluno.getSenha();
        Log.d("Retornando um aluno:", log);
    }
    /*
    public Aluno getAluno(String matricula) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ALUNO, new String[] { KEY_ID, KEY_MATRICULA,
                        KEY_NAME, KEY_SENHA }, KEY_MATRICULA + "=?",
                new String[] { String.valueOf(matricula) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Aluno aluno = new Aluno(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));

        //testando
        Log.d("teste getAluno", "Retornando um aluno!!!!!!");
        String log = "Id: "+ aluno.getId() +" ,Matricula: "+aluno.getMatricula()+" ,Name: " + aluno.getName() + " ,Senha: " + aluno.getSenha();
        Log.d("Retornando um aluno:", log);

        // return aluno


        return aluno;
    }
    */

    //TODO (atualizar metodos)
    // Getting All Alunos
    public List<Aluno> getAllContacts() {
        List<Aluno> contactList = new ArrayList<Aluno>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ALUNO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Aluno aluno = new Aluno();
                aluno.setId(Integer.parseInt(cursor.getString(0)));
                aluno.setMatricula(cursor.getString(1));
                aluno.setName(cursor.getString(2));
                aluno.setSenha(cursor.getString(3));
                // Adding aluno to list
                contactList.add(aluno);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Getting Aluno for listView Emprestados
    public List<Aluno> getListaEmprestados() {
        List<Aluno> contactList = new ArrayList<Aluno>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ALUNO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Aluno aluno = new Aluno();
                aluno.setId(Integer.parseInt(cursor.getString(0)));
                aluno.setMatricula(cursor.getString(1));
                aluno.setName(cursor.getString(2));
                aluno.setSenha(cursor.getString(3));
                // Adding aluno to list
                contactList.add(aluno);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Getting alunos Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_ALUNO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Updating single contact
    public int updateContact(Aluno aluno) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, aluno.getName());
        values.put(KEY_SENHA, aluno.getSenha());

        // updating row
        return db.update(TABLE_ALUNO, values, KEY_ID + " = ?",
                new String[]{String.valueOf(aluno.getId())});
    }

    // Deleting single contact
    public void deleteContact(Aluno aluno) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ALUNO, KEY_ID + " = ?",
                new String[]{String.valueOf(aluno.getId())});
        db.close();
    }

}
