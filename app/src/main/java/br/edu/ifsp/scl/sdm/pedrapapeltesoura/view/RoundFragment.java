package br.edu.ifsp.scl.sdm.pedrapapeltesoura.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import br.edu.ifsp.scl.sdm.pedrapapeltesoura.R;
import br.edu.ifsp.scl.sdm.pedrapapeltesoura.databinding.FragmentMoveBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RoundFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RoundFragment extends Fragment implements View.OnClickListener {


    public static final String NUMPLAYERS = "numplayers";
    public static final String NUMROUNDS = "numrounds";


    private FragmentMoveBinding fragmentMoveBinding;

    private GamePresenter gamePresenter;

    private int mNumplayers;
    private int mNumRounds;



    private Map<String, Integer> mPlayersResult = new HashMap<>();

    public RoundFragment() {
        // Required empty public constructor
    }

    public static RoundFragment newInstance(int mNumplayers) {
        RoundFragment fragment = new RoundFragment();
        Bundle args = new Bundle();
        args.putInt(NUMPLAYERS,mNumplayers);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNumplayers = getArguments().getInt(NUMPLAYERS);
        }
        gamePresenter = new GamePresenter();

        fragmentMoveBinding = FragmentMoveBinding.inflate(getLayoutInflater());

        if (mNumplayers == 2){
            fragmentMoveBinding.player3ImageView.setVisibility(View.GONE);
        }

        fragmentMoveBinding.rockFB.setOnClickListener(this);
        fragmentMoveBinding.paperFB.setOnClickListener(this);
        fragmentMoveBinding.scissorsFB.setOnClickListener(this);
        fragmentMoveBinding.restartButton.setOnClickListener(this);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return fragmentMoveBinding.getRoot();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.scissorsFB:
                check(Options.SCISSORS.option);
                break;
            case R.id.paperFB:
                check(Options.PAPER.option);
                break;
            case R.id.rockFB:
                check(Options.ROCK.option);
                break;
            case R.id.restartButton:
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.fragmentContainer, RoundFragment.newInstance(mNumplayers), null);
                transaction.commit();
        }
    }

    private void check(int cPlayer1){

        if (mPlayersResult != null && !mPlayersResult.isEmpty()){

            for (Map.Entry<String, Integer> player: mPlayersResult.entrySet()){
                if (player.getValue() == mPlayersResult.get(GamePresenter.WINNER).intValue()){

                    if (!player.getKey().equals(GamePresenter.PLAYER1)){
                        fragmentMoveBinding.moveLayout.setVisibility(View.GONE);
                        fragmentMoveBinding.scissorsFB.setVisibility(View.GONE);
                        fragmentMoveBinding.paperFB.setVisibility(View.GONE);
                        fragmentMoveBinding.rockFB.setVisibility(View.GONE);
                        fragmentMoveBinding.restartButton.setVisibility(View.VISIBLE);
                        fragmentMoveBinding.moveResultTextView.setText(R.string.game_over);
                    }
                    if (!player.getKey().equals(GamePresenter.PLAYER2)){
                        fragmentMoveBinding.player2ImageView.setVisibility(View.GONE);
                        mNumplayers -= 1;
                    }
                    if (!player.getKey().equals(GamePresenter.PLAYER3)){
                        fragmentMoveBinding.player3ImageView.setVisibility(View.GONE);
                        mNumplayers -= 1;
                    }
                }
            }
            mPlayersResult.clear();
        }

        mPlayersResult = gamePresenter.jokenpo(cPlayer1, mNumplayers);

        for (Map.Entry<String, Integer> player : mPlayersResult.entrySet()) {

            if (player.getKey().equals(GamePresenter.PLAYER1)){

                if (player.getValue() == Options.PAPER.option){
                    fragmentMoveBinding.player1ImageView.setImageResource(R.mipmap.paper);
                }
                if (player.getValue() == Options.ROCK.option){
                    fragmentMoveBinding.player1ImageView.setImageResource(R.mipmap.stone);
                }
                if (player.getValue() == Options.SCISSORS.option){
                    fragmentMoveBinding.player1ImageView.setImageResource(R.mipmap.scissors);
                }
            }

            if (player.getKey().equals(GamePresenter.PLAYER2)){

                if (player.getValue() == Options.PAPER.option){
                    fragmentMoveBinding.player2ImageView.setImageResource(R.mipmap.paper);
                }
                if (player.getValue() == Options.ROCK.option){
                    fragmentMoveBinding.player2ImageView.setImageResource(R.mipmap.stone);
                }
                if (player.getValue() == Options.SCISSORS.option){
                    fragmentMoveBinding.player2ImageView.setImageResource(R.mipmap.scissors);
                }
            }

            if (player.getKey().equals(GamePresenter.PLAYER3)){

                if (player.getValue() == Options.PAPER.option){
                    fragmentMoveBinding.player3ImageView.setImageResource(R.mipmap.paper);
                }
                if (player.getValue() == Options.ROCK.option){
                    fragmentMoveBinding.player3ImageView.setImageResource(R.mipmap.stone);
                }
                if (player.getValue() == Options.SCISSORS.option){
                    fragmentMoveBinding.player3ImageView.setImageResource(R.mipmap.scissors);
                }
            }
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }
}