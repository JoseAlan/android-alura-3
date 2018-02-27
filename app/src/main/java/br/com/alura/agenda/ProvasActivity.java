package br.com.alura.agenda;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import br.com.alura.agenda.modelo.Prova;

public class ProvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction tx = fragmentManager.beginTransaction();
        tx.replace(R.id.frame_principal, new ListaProvasFragment());
        if(estaNoModoPaisagem()){
            tx.replace(R.id.frame_secundario, new DetalheProvasFragment());
        }
        tx.commit();


    }

    private boolean estaNoModoPaisagem() {
        return getResources().getBoolean(R.bool.modoPaisagem);
    }


    public void selecionaProva(Prova prova) {
        FragmentManager manager = getSupportFragmentManager();
        if(!estaNoModoPaisagem()){
            android.support.v4.app.FragmentTransaction tx = manager.beginTransaction();

            DetalheProvasFragment detalheFragment = new DetalheProvasFragment();
            Bundle parametros = new Bundle();
            parametros.putSerializable("prova", prova);
            detalheFragment.setArguments(parametros);

            tx.replace(R.id.frame_principal, detalheFragment );
            tx.commit();
        }else{
            DetalheProvasFragment detalheFragment =
                    (DetalheProvasFragment) manager.findFragmentById(R.id.frame_secundario);
            detalheFragment.populaCamposCom(prova);

        }



    }
}
