package com.exple.stray_care;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.AdapterView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NGOSHOWLISTOFABANDONED extends AppCompatActivity {
    ListView listView;
    Adapterngoshowlistabandonedpet adapter;
    int position;
    String url = "https://stray-care.000webhostapp.com/LISTABANDONEDPET.php";
    public static ArrayList<ngoshowlistabandonedpetdata> ngoshowlistabandonedpet = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngoshowlistofabandoned);
        listView = findViewById(R.id.myListViewshowabandonedpetngo);

        ActionBar t=getSupportActionBar();
        t.setTitle("ABANDONED ANIMAL DETAILS");
        adapter = new Adapterngoshowlistabandonedpet(this,ngoshowlistabandonedpet );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"SEND INJURED"};
                builder.setTitle("DONATION");
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i) {
                            case 0:


                                break;




                        }
                    }
                });


                builder.create().show();


            }
        });

        retrievedata();
    }
    public void retrievedata(){

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){
                        ngoshowlistabandonedpet.clear();
                        try{

                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if(sucess.equals("1")){


                                for(int i=0;i<jsonArray.length();i++){

                                    JSONObject object = jsonArray.getJSONObject(i);


                                    String name = object.getString("animal_type");
                                    String email = object.getString("location");


                                    ngoshowlistabandonedpetdata e = new ngoshowlistabandonedpetdata(name,email);
                                    ngoshowlistabandonedpet.add(e);
                                    adapter.notifyDataSetChanged();



                                }



                            }




                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }






                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NGOSHOWLISTOFABANDONED.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("ngo_id",ngoprofile.ngoshowprofileList.get(position).getId());




                return params;

            }
        };;

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);



    }

}
class ngoshowlistabandonedpetdata {

    private String name, email;

    public ngoshowlistabandonedpetdata() {
    }

    public ngoshowlistabandonedpetdata(String name, String email) {

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

class Adapterngoshowlistabandonedpet extends ArrayAdapter<ngoshowlistabandonedpetdata> {
    Context context;
    List< ngoshowlistabandonedpetdata> arrayListNGOSHOWLISTABANDONEDPET;
    public Adapterngoshowlistabandonedpet(@NonNull Context context, List< ngoshowlistabandonedpetdata> arrayListNGOSHOWLISTABANDONEDPET) {
        super(context,R.layout.custom_list_ngoshowabandonedpet, arrayListNGOSHOWLISTABANDONEDPET);
        this.context=context;
        this.arrayListNGOSHOWLISTABANDONEDPET=arrayListNGOSHOWLISTABANDONEDPET;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_ngoshowabandonedpet,null,true);
        TextView tvId=view.findViewById(R.id.txt_name);
        TextView tvname=view.findViewById(R.id.txt_mobile);
        tvId.setText(arrayListNGOSHOWLISTABANDONEDPET.get(position).getName());
        tvname.setText(arrayListNGOSHOWLISTABANDONEDPET.get(position).getEmail());
        return view;
    }
}
