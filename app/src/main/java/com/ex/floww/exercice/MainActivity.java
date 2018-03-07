package com.ex.floww.exercice;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    CustomAdapter adapter;
    AsyncInterface delegate;
    static final String URLSTREAM = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);
        Jsonbuilder();

        final SwipeRefreshLayout swiperefresh = findViewById(R.id.swiperefresh);

        // crete a listener for the SwipeRefreshLayout
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Jsonbuilder();
                swiperefresh.setRefreshing(false);
            }
        });
    }

    void setTitleToBar(String title) {
        // Set the title of the Action Bar
        setTitle(title);
    }

    ExObject ConstructObject(JsonObject object) {
        // Create Gson Object
        Gson gson = new Gson();

        // Set the Json Object in the custom object
        ExObject exObject = gson.fromJson(object, ExObject.class);

        return exObject;
    }

    void ConstructList(ExObject exObject) {
        // Create the adapter with the elements of the Json
        adapter = new CustomAdapter(exObject.getRows(), getApplicationContext());

        // link the adapter to the listview
        listView.setAdapter(adapter);

        // notify the adapter if changes
        adapter.notifyDataSetChanged();
    }

    /**
     * Constuct the View with the given Json
     * @param jsonObject Object given by the Json
     */

    void ConstructView(JsonObject jsonObject) {

        ExObject exObject = ConstructObject(jsonObject);

        setTitleToBar(exObject.getTitle());

        ConstructList(exObject);
    }

    /**
     * Fetch the Json from the URL with Retrofit
     */
     void Jsonbuilder ()
    {
        // Construct the Retrofit Object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLSTREAM)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create the Retrofit Interface
        AsyncInterface api = retrofit.create(AsyncInterface.class);

        // Call of the Interface method
        Call<JsonObject> jsonCall= api.getJson();

        jsonCall.enqueue(new Callback<JsonObject>(){
            @Override
            public void onResponse(@NonNull Call<JsonObject> objectCall, @NonNull Response<JsonObject> objectResponse){

                // Put the response into a JsonObject
                JsonObject jsonObject = objectResponse.body();

                // Call the construction of the view once the data is fetched into a JsonObject
                ConstructView(jsonObject);
            }

            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {

            }
        });
    }
}
