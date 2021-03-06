package com.example.testeapp.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {TodosRoom.class}, version = 1)
public abstract class TodosDatabase extends RoomDatabase {

    private static TodosDatabase instance;

    public abstract TodosDao todosDao();

    public static synchronized TodosDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TodosDatabase.class, "todos_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private TodosDao todosDao;

        private PopulateDbAsyncTask(TodosDatabase db) {
            todosDao = db.todosDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            todosDao.insert(new TodosRoom("Como apagar tudo?", "Dar um click longo no botão da lixeira", false, false));
            todosDao.insert(new TodosRoom("Como checkar?", "Clique no item que deseja checkar", false, false));
            todosDao.insert(new TodosRoom("Como apagar um item?", "Clicar no botão da lixeira e selecionar o item a ser removido", false, false));
            todosDao.insert(new TodosRoom("Como alterar um item?", "Dar um click longo no item a ser alterado", false, false));
            todosDao.insert(new TodosRoom("Como criar um item?", "Clicar no botão de + para criar uma nova tarefa", false, false));

            return null;
        }
    }
}
