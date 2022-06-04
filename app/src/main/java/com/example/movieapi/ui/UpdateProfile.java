package com.example.movieapi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.movieapi.R;
import com.example.movieapi.response.Account;

import java.util.HashMap;
import java.util.Map;

public class UpdateProfile extends AppCompatActivity {
    private EditText tvID, edtName, edtEmail, edtPhone, edtDiaChi, edtFb;
    private ImageView Luu;

    String url = "http://phamthoai123-001-site1.ctempurl.com/updateprofile.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        anhXa();

        Intent intent = getIntent();
        Account account = (Account) intent.getSerializableExtra("dataAccount");

        tvID.setText(account.getPIDProfile() + "");
        edtName.setText(account.getNameProfile());
        edtEmail.setText(account.getEmailProfile());
        edtPhone.setText(account.getPhoneProfile());
        edtDiaChi.setText(account.getDiaChiProfile());
        edtFb.setText(account.getFbProfile());

    }

    private void anhXa() {
        tvID = findViewById(R.id.edt_PIDProfileUpdate);
        edtName = findViewById(R.id.edt_nameProfile);
        edtEmail = findViewById(R.id.edt_EmailProfile);
        edtPhone = findViewById(R.id.edt_PhoneProfile);
        edtDiaChi = findViewById(R.id.edt_DiaChiProfile);
        edtFb = findViewById(R.id.edt_FaceBookProfile);

        Luu = findViewById(R.id.img_Luu);
        Luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateProfile(url);
            }
        });

    }

    private void UpdateProfile(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("update data successful")) {
                    Toast.makeText(UpdateProfile.this, "Sua thanh cong", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateProfile.this, Profile.class);
                    startActivity(intent);
                } else
                    Toast.makeText(UpdateProfile.this, "Sua khong thanh cong", Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UpdateProfile.this, "Xay ra loi!!!", Toast.LENGTH_SHORT).show();
                        Log.d("AAA", "Loi!\n"+ error.toString());
                    }
                }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                // update data
                params.put("PIDProfile",tvID.getText().toString());
//                params.put("MaNV", String.valueOf(id));
                params.put("NameProfile",edtName.getText().toString().trim());
                params.put("EmailProfile",edtEmail.getText().toString().trim());
                params.put("PhoneProfile",edtPhone.getText().toString().trim());
                params.put("DiaChiProfile",edtDiaChi.getText().toString().trim());
                params.put("FbProfile",edtFb.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}