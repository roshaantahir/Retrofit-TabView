package com.tvacstudio.tabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdatesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdatesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView updatedPost;
    View view;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UpdatesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TravelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdatesFragment newInstance(String param1, String param2) {
        UpdatesFragment fragment = new UpdatesFragment();
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
        view =  inflater.inflate(R.layout.updates_fragment, container, false);
        // Inflate the layout for this fragment
        Post updatepost = new Post(1,null, "This is the description of updated Post");
        MyInterface myInterface = APIClient.getClient().create(MyInterface.class);
        myInterface.patchPost(5,updatepost).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    updatedPost = view.findViewById(R.id.updatedPost);
                    updatedPost.setText(String.valueOf(response.code()));
                    showCreatedPost(response.body());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void showCreatedPost(Post post) {
        updatedPost = view.findViewById(R.id.updatedPost);
        updatedPost.append("\n" + "user id : " + post.getUserId() + "\n");
        updatedPost.append("id : " + post.getId() + "\n");
        updatedPost.append("tittle : " + post.getTitle() + "\n");
        updatedPost.append("body : " + post.getBody() + "\n");
    }
}
