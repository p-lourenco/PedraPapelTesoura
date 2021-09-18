package br.edu.ifsp.scl.sdm.pedrapapeltesoura.view;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.slider.RangeSlider;

import br.edu.ifsp.scl.sdm.pedrapapeltesoura.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsDialog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsDialog extends DialogFragment {

    public static final String TAG = "SettingsDialog";

    private RangeSlider playerRangeSlide;
    private RangeSlider roundPlayerSlide;
    private FloatingActionButton saveFb;

    public SettingsDialog() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog builder = new AlertDialog.Builder(requireContext()).create();
        Window window = builder.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        builder.show();
        return builder;
    }

    public static SettingsDialog newInstance() {
        SettingsDialog fragment = new SettingsDialog();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_settings_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        playerRangeSlide = view.findViewById(R.id.playerRangeSlider);
        roundPlayerSlide = view.findViewById(R.id.roundsRangeSlider);
        saveFb = view.findViewById(R.id.saveFb);

        playerRangeSlide.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                Toast.makeText(getContext(), String.valueOf(value), Toast.LENGTH_SHORT).show();
            }
        });

        roundPlayerSlide.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                Toast.makeText(getContext(), String.valueOf(value), Toast.LENGTH_SHORT).show();
            }
        });

        int width = getResources().getDimensionPixelSize(R.dimen.dialog_width);
        int height =  getResources().getDimensionPixelSize(R.dimen.dialog_height);
        getDialog().getWindow().setLayout(width, height);
    }
}