package com.exple.stray_care;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class USERPROFILE extends AppCompatActivity {
    ListView listView;
    Adaptertusershowprofile adapter;

    String url = "https://stray-care.000webhostapp.com/userdatashowprofile.php";
    public static ArrayList<usershowprofiledata> usershowprofileList = new ArrayList<>();

    Button v1, v2;
    TextView t;
    String emaill;
    String pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        listView = findViewById(R.id.myListSHOWPROFILE);
        adapter = new Adaptertusershowprofile(this,usershowprofileList);
        listView.setAdapter(adapter);
        Intent i = getIntent();
        emaill = i.getStringExtra("email");
        pass = i.getStringExtra("pass");
        v1 = findViewById(R.id.userprofiledonatebutton);


        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), userdonate.class);
                startActivity(i);

            }
        });
        v2 = findViewById(R.id.USERSENDINJUREDBUTTON);

        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), usersendinjuredpetdoc.class);
                startActivity(i);

            }
        });
        retrievedata();
    }


    public void retrievedata() {

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        usershowprofileList.clear();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if (sucess.equals("1")) {


                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);


                                    String name = object.getString("name");
                                    String email = object.getString("email");


                                    usershowprofiledata e = new usershowprofiledata(name, email);
                                    usershowprofileList.add(e);
                                    adapter.notifyDataSetChanged();


                                }


                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(USERPROFILE.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("email",emaill);
                params.put("mobile",pass);



                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }

    class usershowprofiledata {
        private String name, email, mobile;

        public usershowprofiledata() {
        }

        public usershowprofiledata(String name, String email) {
            this.name = name;
            this.email = email;

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

    }

    class Adaptertusershowprofile extends ArrayAdapter<usershowprofiledata> {
        Context context;
        List<usershowprofiledata> arrayListprofiledata;

        public Adaptertusershowprofile(@NonNull Context context, List<usershowprofiledata> arrayListprofiledata) {
            super(context, R.layout.custom_list_itemusershowprofile, usershowprofileList);
            this.context = context;
            this.arrayListprofiledata = arrayListprofiledata;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_itemusershowprofile, null, true);
            TextView tvId = view.findViewById(R.id.txt_name);
            TextView tvname = view.findViewById(R.id.txt_mobile);
            tvId.setText(arrayListprofiledata.get(position).getEmail());
            tvname.setText(arrayListprofiledata.get(position).getName());
            return view;
        }
    }
}