package developer.shivam.myapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private CustomAdapter adapter;
    private List<Data> data_list;
    private ProgressDialog pd;
    private static String url = "http://43.252.91.43:8984/solr/Meals/select?q=*%3A*&rows=8&wt=json&indent=true";
    public static int [] imgs ={R.mipmap.ic_ab,R.mipmap.ic_cd,R.mipmap.ic_ef,R.mipmap.ic_gh};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        data_list  = new ArrayList<>();
        new GetData().execute();
    }

    private class GetData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);
            Log.e(TAG, "Response from url: " + jsonStr);


            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONObject response = jsonObj.getJSONObject("response");
                    JSONArray docs = response.getJSONArray("docs");

                    for (int i = 0; i < docs.length(); i++) {
                        JSONObject c = docs.getJSONObject(i);

                        String i_id = c.getString("Itemid");
                        String name = c.getString("ItemName");
                        int price = c.getInt("Price");
                        JSONArray d = c.getJSONArray("Description");
                        String desc = d.getString(0);
                        //String img = c.getString("ImgUrl");
                        int j;
                        if(i >=4){
                            j=i-4;
                        }
                        else {
                            j = i;
                        }
                        int img = imgs[j];

                        Data data = new Data(i_id,name,price,desc.toString(),img);
                        data_list.add(data);
                        Log.d("Data", i_id+name+price+desc.toString()+img);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            if (pd.isShowing())
                pd.dismiss();
            LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(llm);
            //gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
            //recyclerView.setLayoutManager(gridLayoutManager);

            adapter = new CustomAdapter(getApplicationContext(),data_list);
            recyclerView.setAdapter(adapter);
        }
    }
}
