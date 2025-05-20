package com.appdatabase;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appdatabase.adapters.ContatoAdapter;
import com.appdatabase.db.DAO;
import com.appdatabase.model.Contato;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Handler handler;

    private DAO dao;
    private ContatoAdapter contatoAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Contato> contatos;
    private ArrayList<Contato> contatosMock;

    private boolean animando = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Dados mockados de referência; (inicia a lista contatosMock)
        criaDadosMockados();

        // Iniciando o handler (para o temporizador)
        handler = new Handler();

        // Obtendo acesso ao DAO e obtendo a lista de todos os dados;
        dao = DAO.getInstance(getApplicationContext());
        contatos = dao.getContatos();

        // Definindo o adapter
        contatoAdapter = new ContatoAdapter(contatos);

        // Vinculando-se ao RecyclerView do layout da activity
        recyclerView = findViewById(R.id.recyclerview);

        // Conectando o adaptador ao RecyclerView, onde os contatos devem aparecer
        recyclerView.setAdapter(contatoAdapter);

        // Define um tipo de layout a ser utilizado no recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Adicionando um listener (implementado aqui) ao adapter
        contatoAdapter.setListener(new ContatoAdapter.Listener() {
            @Override
            public void onClick(int position) {
                // Remove o contato clicado
                remover(position);
            }
        });
    }

    private void remover(int position) {
        Integer id = contatos.get(position).id;
        if (dao.delete(id)) {
            contatos.remove(position);
            // Obrigatório: informar ao adapter sobre qualquer alteração nos dados
            contatoAdapter.notifyItemRemoved(position);
            contatoAdapter.setContatos(contatos);
        }
    }

    private void adicionarContato(Contato contato, int position) {
        dao.addContato(contato);
        contatos.add(contato);
        contatoAdapter.notifyItemInserted(position);
    }

    public void clear(View view) {
        if (animando) return;
        limparConteutoTotalmente();
    }

    private void limparConteutoTotalmente() {
        if (!dao.reset()) {
            // TODO: FAZER ALGO (TALVEZ UMA MENSAGEM NA TELA)
        }
        contatos.clear();
        contatoAdapter.setContatos(contatos);
        contatoAdapter.notifyDataSetChanged();
    }

    public void addUmPorUm(View view) {
        if (animando) return;
        animando = true;
        limparConteutoTotalmente();
        iniciaTemporizadorAdd();
    }

    public void delUmPorUm(View view) {
        if (animando) return;
        animando = true;
        iniciaTemporizadorDelete();
    }

    private void iniciaTemporizadorAdd() {
        handler.post(new Runnable() {
            int count = 0;
            @Override
            public void run() {
                if (contatos.size() < contatosMock.size() - 1) {
                    Contato contato = new Contato(
                            contatosMock.get(count).id,
                            contatosMock.get(count).nome,
                            contatosMock.get(count).email,
                            contatosMock.get(count).tel
                    );
                    adicionarContato(contato, count);
                    recyclerView.scrollToPosition(contatos.size() - 1);
                    count++;
                    handler.postDelayed(this, 300);
                } else {
                    animando = false;
                    contatos = dao.getContatos();
                    contatoAdapter.setContatos(contatos);
                    contatoAdapter.notifyDataSetChanged();
                    // Isso é necessário porque os contatos estavam sendo
                    // adicionados no arraylist sem os ids (perde-se sincronia
                    // com a base de dados)
                }
            }
        });
    }

    private void iniciaTemporizadorDelete() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (contatos.size() > 0) {
                    remover(0);
                    recyclerView.scrollToPosition(0);
                    handler.postDelayed(this, 400);
                } else {
                    animando = false;
                }
            }
        });
    }

    // Cria dados mockados
    private void criaDadosMockados() {
        contatosMock = new ArrayList<>();
        // contato 1
        contatosMock.add(new Contato(null, "Fulano de Tal", "fulanodetal@email.com",
                "(71) 99999 1234"));
        // contato 2
        contatosMock.add(new Contato(null, "Sicrano", "siucrano@email.com",
                "(71) 98888 4321"));
        // contato 3
        contatosMock.add(new Contato(null, "Beltrano Jr", "beljr@email.com",
                "(71) 98786 1645"));
        // contato 4
        contatosMock.add(new Contato(null, "Senhor Dr Tiririca", "srtiririca@email.com",
                "(71) 99123 1345"));
        // contato 5
        contatosMock.add(new Contato(null, "Excelentíssimo Cidadão", "cidadao@email.com",
                "(71) 90012 1287"));
        // contato 6
        contatosMock.add(new Contato(null, "Um Amigo", "amigo@email.com",
                "(71) 9987 9978"));
        // contato 7
        contatosMock.add(new Contato(null, "ET O Extraterrestre", "et@email.com",
                "(71) 1234 4321"));
        // contato 6
        contatosMock.add(new Contato(null, "Senhor dos Anéis", "senhor@email.com",
                "(71) 3676 3676"));
        // contato 6
        contatosMock.add(new Contato(null, "Tyrion Lanister", "tyrion@email.com",
                "(71) 9857 3874"));
    }
}