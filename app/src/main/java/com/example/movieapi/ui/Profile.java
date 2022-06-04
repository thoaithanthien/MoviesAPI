package com.example.movieapi.ui;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.movieapi.R;
import com.example.movieapi.adapters.AdapterProfile;
import com.example.movieapi.response.Account;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {
    String urlRequest = "http://phamthoai123-001-site1.ctempurl.com/jsonprofile.php";
    ListView lvNhanVien;
    ArrayList<Account> arrayList;
    AdapterProfile adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        lvNhanVien = findViewById(R.id.lv_Profile);
        arrayList = new ArrayList<>();

        adapter = new AdapterProfile(this, R.layout.item_profile, arrayList);
        lvNhanVien.setAdapter(adapter);
        GetData(urlRequest);


    }



    private void GetData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                arrayList.clear();
                // Goi GetData o fuction deleteNhanVien nen no tu update lai
                // Su dung clear de xoa
                for (int i = 0 ; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        arrayList.add(new Account(
                                jsonObject.getInt("Id"),
                                jsonObject.getString("Name"),
                                jsonObject.getString("Email"),
                                jsonObject.getString("Phone"),
                                jsonObject.getString("Diachi"),
                                jsonObject.getString("Fb")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Profile.this, "Loi", Toast.LENGTH_SHORT).show();
                    }
                }

        );
        requestQueue.add(jsonArrayRequest);
    }
}