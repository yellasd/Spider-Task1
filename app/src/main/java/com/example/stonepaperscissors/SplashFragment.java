package com.example.stonepaperscissors;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SplashFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SplashFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    static String p1 = "", p2 = "";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SplashFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SplashFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SplashFragment newInstance(String param1, String param2) {
        SplashFragment fragment = new SplashFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SharedPreferences sp;
        EditText e1, e2, e3;
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_splash, container, false);
        final Button singlePlayer = v.findViewById(R.id.single_player);
        e1 = v.findViewById(R.id.editText);
        e2 = v.findViewById(R.id.player_1);
        e3 = v.findViewById(R.id.player_2);
        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Myapp.rounds = s.toString();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        e2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                p1 = s.toString();
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        e3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                p2 = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        singlePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Myapp.rounds.equals("") || p1.equals(""))
                    Toast.makeText(getContext(), "Please enter the required fields", Toast.LENGTH_SHORT).show();
                else {
                    SinglePlayerActivity.scorep1=0;
                    SinglePlayerActivity.scorep2=0;
                    Intent intent = new Intent(getContext(), SinglePlayerActivity.class);
                    Objects.requireNonNull(getActivity()).finish();
                    getActivity().overridePendingTransition(0, 0);
                    startActivity(intent);
                }
            }
        });
        Button twoPlayers = v.findViewById(R.id.two_players);
        twoPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Myapp.rounds.equals("") || p1.equals("") || p2.equals(""))
                    Toast.makeText(getContext(), "Please enter the required fields", Toast.LENGTH_SHORT).show();
                else {
                    TwoPlayerActivity.scorepl1=0;
                    TwoPlayerActivity.scorepl2=0;
                    Intent intent = new Intent(getContext(), TwoPlayerActivity.class);
                    Objects.requireNonNull(getActivity()).finish();
                    getActivity().overridePendingTransition(0, 0);
                    startActivity(intent);
                }
            }
        });
        return v;
    }
}
