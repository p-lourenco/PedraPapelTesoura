package br.edu.ifsp.scl.sdm.pedrapapeltesoura.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GamePresenter {

    public final static String PLAYER1 = "player1";
    public final static String PLAYER2 = "player2";
    public final static String PLAYER3 = "player3";
    public final static String WINNER = "winner";

    private Map<String, Integer> mPlayersResult = new HashMap<>();

    public GamePresenter() {
    }

    public Map<String, Integer> jokenpo(int cPlayer1, int nPlayers){
        Random random = new Random();
        int cPlayer2 = random.nextInt(3);
        mPlayersResult.put(PLAYER1, cPlayer1);
        mPlayersResult.put(PLAYER2, cPlayer2);
        if (nPlayers == 2){
            mPlayersResult.put(WINNER, moveWith2(cPlayer1, cPlayer2));
            return mPlayersResult;
        }
        int cPlayer3 = random.nextInt(3);
        mPlayersResult.put(PLAYER3, cPlayer3);
        mPlayersResult.put(WINNER,moveWith3(moveWith2(cPlayer1, cPlayer2), cPlayer1, cPlayer2, cPlayer3));
        return mPlayersResult;
    }

    public int moveWith2(int c1,int c2){

        if (c1 == c2) {
            return c1;
        }
        if (c1 == Options.PAPER.option && c2 == Options.SCISSORS.option){
            return c2;
        }
        if (c1 == Options.SCISSORS.option && c2 == Options.ROCK.option){
            return c2;
        }
        if (c1 == Options.ROCK.option && c2 == Options.PAPER.option){
            return c2;
        }
        return c1;
    }

    public int moveWith3(int winner,int c1,int c2,int c3){

        if (c2 == c1 && c1 == c3) return c3;

        if (winner == c1){

            if (c1 == c3) return c3;

            if (c1 == Options.PAPER.option && c3 == Options.SCISSORS.option){
                return c3;
            }
            if (c1 == Options.SCISSORS.option && c3 == Options.ROCK.option){
                return c3;
            }
            if (c1 == Options.ROCK.option && c3 == Options.PAPER.option){
                return c3;
            }
            return c1;
        }else {

            if (c2 == c3) return c2;

            if (c2 == Options.PAPER.option && c3 == Options.SCISSORS.option){
                return c3;
            }
            if (c2 == Options.SCISSORS.option && c3 == Options.ROCK.option){
                return c3;
            }
            if (c2 == Options.ROCK.option && c3 == Options.PAPER.option){
                return c3;
            }
            return c2;
        }
    }

}
