package br.edu.ifsp.scl.sdm.pedrapapeltesoura.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import br.edu.ifsp.scl.sdm.pedrapapeltesoura.R;

public class MainActivity extends AppCompatActivity {


    private int mCurrentFragment = 0;
    public final String CURRENT_FRAG = "currentFrag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null){
            mCurrentFragment = savedInstanceState.getInt(CURRENT_FRAG,0);
        }

        if (mCurrentFragment == 0){

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setReorderingAllowed(true);
            transaction.replace(R.id.fragmentContainer, PlayersFragment.newInstance(), null);
            transaction.commit();
            mCurrentFragment = 1;
        }
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_FRAG,mCurrentFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.gameSettings:
                SettingsDialog settingsDialog = new SettingsDialog();
                settingsDialog.show(getSupportFragmentManager(),SettingsDialog.TAG);return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}