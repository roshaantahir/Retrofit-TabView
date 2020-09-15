package com.tvacstudio.tabs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DelPostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DelPostFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View view;
    TextView deletedPost;
    Button MapButton;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DelPostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DelPostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DelPostFragment newInstance(String param1, String param2) {
        DelPostFragment fragment = new DelPostFragment();
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
        view =inflater.inflate(R.layout.fragment_del_post, container, false);
        MapButton = view.findViewById(R.id.MapButton);
        // Inflate the layout for this fragment

        MyInterface myInterface = APIClient.getClient().create(MyInterface.class);
        myInterface.deletePost(5).enqueue(new Callback<Void>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    deletedPost = view.findViewById(R.id.deletedPost);
                    deletedPost.setText("Response code = " + response.code());
                    deletedPost.append("\n"+"Post Deleted successfully");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

        MapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity();
            }
        });
        return view;
    }

    public void nextActivity(){
        Intent intent = new Intent(getActivity(), MapActivity.class);
        startActivity(intent);
    }
}