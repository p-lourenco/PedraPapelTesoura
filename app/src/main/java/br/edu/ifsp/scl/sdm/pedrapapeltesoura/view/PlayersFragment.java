package br.edu.ifsp.scl.sdm.pedrapapeltesoura.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.edu.ifsp.scl.sdm.pedrapapeltesoura.R;
import br.edu.ifsp.scl.sdm.pedrapapeltesoura.databinding.FragmentPlayersBinding;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayersFragment extends Fragment implements View.OnClickListener {

    FragmentPlayersBinding fragmentPlayersBinding;


    public PlayersFragment() {
        // Required empty public constructor
    }

    public static PlayersFragment newInstance() {
        PlayersFragment fragment = new PlayersFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentPlayersBinding = FragmentPlayersBinding.inflate(getLayoutInflater());
        fragmentPlayersBinding.startButton.setOnClickListener(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return fragmentPlayersBinding.getRoot();
    }

    @Override
    public void onClick(View v) {

        final int numPlayer = fragmentPlayersBinding.twoPlayersRadioButton.isChecked() ? 2 : 3;
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setReorderingAllowed(true);
        transaction.replace(R.id.fragmentContainer, RoundFragment.newInstance(numPlayer), null);
        transaction.commit();
    }
}