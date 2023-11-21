package com.example.makesurest.companyselection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.makesurest.MainActivity;
import com.example.makesurest.R;
import com.example.makesurest.adapter.CompanyListAdapter;
import com.example.makesurest.databinding.ActivityCompanySelectionBinding;
import com.example.makesurest.login.LoginActivity;
import com.example.makesurest.model.ResponseCompanyListModel;

import java.util.ArrayList;

public class CompanySelection extends AppCompatActivity {

    private CompanyViewModel userViewModel;
    CompanyListAdapter adapter;
    RecyclerView recyclerView;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCompanySelectionBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_company_selection);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        btn = findViewById(R.id.AddDispatch);

        adapter = new CompanyListAdapter();
        recyclerView.setAdapter(adapter);

        userViewModel = ViewModelProviders.of(this).get(CompanyViewModel.class);
        adapter.setOnItemClickListener(user -> {

            Toast.makeText(getApplicationContext(),"You clicked : "+user.getCompanyName(), Toast.LENGTH_SHORT).show();

            String company = user.getCompanyName();

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(CompanySelection.this, MainActivity.class);
                    i.putExtra("company",company);
                    i.putExtra("gtinCompany",user.getGtinCompanyCode());
                    i.putExtra("gtinCounty",user.getGtinCountryCode());
                    i.putExtra("companyId",user.getCompanyId());
                    startActivity(i);
                }
            });

        });

        getData();

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent i = new Intent(CompanySelection.this, MainActivity.class);
//                startActivity(i);
////                i.putExtra("companyName",)
//
//            }
//        });

    }

    private void getData() {
        userViewModel.getAllUsers().observe(this, userList -> {
            adapter.setUserList((ArrayList<ResponseCompanyListModel>) userList);

//            System.out.println("userList"+userList);



        });
    }
}