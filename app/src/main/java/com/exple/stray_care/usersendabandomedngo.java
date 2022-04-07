package com.exple.stray_care;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class usersendabandomedngo extends AppCompatActivity {
    TextView tvid, tvname, tvemail, tvmobile, tvaddress, tvUSERNAME, tvUSEREMAIL, tvcondition, tvlocation, tvanitype;
    String tvstringcondition, tvstringlocation, Tvstringanitype;
    int position;
    String url="https://stray-care.000webhostapp.com/usersendabandonedngodata.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersendabandomedngo);
        tvid = findViewById(R.id.txtID);
        tvname = findViewById(R.id.txtname);
        tvemail = findViewById(R.id.txtemail);
        tvmobile = findViewById(R.id.txcontact);
        tvaddress = findViewById(R.id.txtaddress);
        tvUSEREMAIL = findViewById(R.id.txtUSERNAME);
        tvUSERNAME = findViewById(R.id.txtUSEREMAIL);


        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
        tvid.setText("ID: " + userdonate.userdonateNgodata.get(position).getId());
        tvname.setText("NAME: " + userdonate.userdonateNgodata.get(position).getName());
        tvemail.setText("EMAIL: " + userdonate.userdonateNgodata.get(position).getEmail());
        tvmobile.setText("MOBILE: " + userdonate.userdonateNgodata.get(position).getMobile());
        tvaddress.setText("ADDRESS: " + userdonate.userdonateNgodata.get(position).getAddress());
        tvUSERNAME.setText("USER ID: " + USERPROFILE.id);
        tvUSEREMAIL.setText("USER NAME " + USERPROFILE.name);
    }

    public void insertabandonedanidata(View view) {

        tvanitype = findViewById(R.id.txtanitype);
        tvcondition = findViewById(R.id.textcondition);
        tvlocation = findViewById(R.id.textViewLOCATION);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");


        if (tvcondition.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Animals Condition", Toast.LENGTH_SHORT).show();
        } else if (tvanitype.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Animal type", Toast.LENGTH_SHORT).show();
        } else if (tvlocation.getText().toString().equals("")) {
            Toast.makeText(this, "Enter location", Toast.LENGTH_SHORT).show();
        } else {

            progressDialog.show();
            Tvstringanitype = tvanitype.getText().toString();
            tvstringlocation = tvlocation.getText().toString();
            tvstringcondition = tvcondition.getText().toString();

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    tvanitype.setText("");
                    tvlocation.setText("");
                    tvcondition.setText("");
                    Toast.makeText(usersendabandomedngo.this, response, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(usersendabandomedngo.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("us_id", USERPROFILE.id );
                    params.put("ngo_id", userdonate.userdonateNgodata.get(position).getId());
                    params.put("animal_type", Tvstringanitype);
                    params.put("location", tvstringlocation);
                    params.put("condition", tvstringcondition);
                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(usersendabandomedngo.this);
            requestQueue.add(request);


        }
    }
}