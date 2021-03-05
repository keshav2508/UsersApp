package com.example.users.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.users.Adapter.UserListAdapter;
import com.example.users.Model.UsersModel;
import com.example.users.Model.response;
import com.example.users.R;
import com.example.users.ViewModel.UsersListViewModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class UserListActivity extends AppCompatActivity implements UserListAdapter.OnNoteListener{
    private response usersModelList;
    private UserListAdapter adapter;
    protected UsersListViewModel viewModel;

    Button signout;

    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        RecyclerView recycleView = findViewById(R.id.RecyclerView);
        TextView NoResult =findViewById(R.id.NoResult);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recycleView.setLayoutManager(layoutManager);
        adapter= new UserListAdapter(this,usersModelList,this);
        recycleView.setAdapter(adapter);
        firebaseAuth = FirebaseAuth.getInstance();

        signout =(Button) findViewById(R.id.signout);
        signout.setOnClickListener(v ->{
            firebaseAuth.signOut();
            Intent i = new Intent(this, LoginRegister.class);
            startActivity(i);
            finish();
        });

        viewModel = new ViewModelProvider(this).get(UsersListViewModel.class);
        viewModel.getUserListObserver().observe(this, usersModels -> {
            if(usersModels !=null){
                usersModelList = usersModels;
                adapter.setUserList(usersModels);
            }else{
                NoResult.setVisibility(View.VISIBLE);
            }
        });

        viewModel.makeAPICall();

    }
    @Override
    public void onNoteClick(int position) {
        Intent i = new Intent(this, UserDetails.class);
        //Log.d("FindError",UsersList.getItems().get(position).getURL());
        i.putExtra("URL",usersModelList.getItems().get(position).getURL());
        i.putExtra("ImageURL",usersModelList.getItems().get(position).getImageURL());
        i.putExtra("Name",usersModelList.getItems().get(position).getLogin());

        startActivity(i);
    }
}
