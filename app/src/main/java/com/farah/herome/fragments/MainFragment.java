package com.farah.herome.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.farah.herome.R;
import com.farah.herome.activities.MainActivity;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private LinearLayout cameByAccidentButton;
    private LinearLayout geneticMutationButton;
    private LinearLayout bornWithThemButton;
    private Button choosePowersButton;

    private ImageView came_by_accident_button_checkmark;
    private ImageView genetic_mutation_button_checkmark;
    private ImageView born_with_them_button_checkmark;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MainFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
        View view  = inflater.inflate(R.layout.fragment_main, container, false);
        List<LinearLayout> buttons = new ArrayList<>();

        cameByAccidentButton = view.findViewById(R.id.came_by_accident_button);
        geneticMutationButton = view.findViewById(R.id.genetic_mutation_button);
        bornWithThemButton = view.findViewById(R.id.born_with_them_button);
        choosePowersButton = view.findViewById(R.id.choose_powers_button);

        came_by_accident_button_checkmark = view.findViewById(R.id.came_by_accident_button_checkmark);
        genetic_mutation_button_checkmark = view.findViewById(R.id.genetic_mutation_button_checkmark);
        born_with_them_button_checkmark = view.findViewById(R.id.born_with_them_button_checkmark);

        buttons.add(cameByAccidentButton);
        buttons.add(geneticMutationButton);
        buttons.add(bornWithThemButton);

        setOnClickListeners(buttons);

        choosePowersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.loadPickPowerFragment();
            }
        });

        choosePowersButton.setEnabled(false);
        choosePowersButton.getBackground().setAlpha(128);

        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.MainFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainFragmentInteractionListener) {
            mListener = (MainFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement MainFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface MainFragmentInteractionListener {
        // TODO: Update argument type and name
        void MainFragmentInteraction(Uri uri);
    }

    private void setOnClickListeners(final List<LinearLayout> buttons) {
        for (final LinearLayout button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View buttonClicked) {
                    choosePowersButton.setEnabled(true);
                    choosePowersButton.getBackground().setAlpha(255);
                    if (button == cameByAccidentButton) {
                        came_by_accident_button_checkmark.setImageResource(R.drawable.item_selected);
                        genetic_mutation_button_checkmark.setImageResource(0);
                        born_with_them_button_checkmark.setImageResource(0);
                    }
                    if (button == geneticMutationButton) {
                        came_by_accident_button_checkmark.setImageResource(0);
                        genetic_mutation_button_checkmark.setImageResource(R.drawable.item_selected);
                        born_with_them_button_checkmark.setImageResource(0);
                    }
                    if (button == bornWithThemButton) {
                        came_by_accident_button_checkmark.setImageResource(0);
                        genetic_mutation_button_checkmark.setImageResource(0);
                        born_with_them_button_checkmark.setImageResource(R.drawable.item_selected);
                    }
                }
            });
        }
    }
}
