package br.com.pontotel.pontoteljson;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    //Root URL of our web service
    public static final String ROOT_URL = "https://s3-sa-east-1.amazonaws.com/";


    //List view to show data
    private ListView listView ;

    //List of type books this list will store type Book which is our data model
    private List<Pessoa> pessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing the listview
        listView = (ListView) findViewById(R.id.listViewPessoas);

        //Calling the method that will fetch data
        getPessoas();

        //Setting onItemClickListener to listview
       // listView.setOnItemClickListener(this);
    }

    private void getPessoas(){
        //While the app fetched data we are displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Fetching Data","Please wait...",false,false);

        //Creating a rest adapter
            RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

        //Creating an object of our api interface
        PessoaAPI api = adapter.create(PessoaAPI.class);

        //Defining the method
        api.getPessoas(new Callback<List<Pessoa>>() {
            @Override
            public void success(List<Pessoa> list, Response response) {
                //Dismissing the loading progressbar
                loading.dismiss();

                //Storing the data in our list
                pessoas = list;

                //Calling a method to show the list
                showList();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    //Our method to show list
    private void showList(){
        //String array to store all the book names
        String[] items = new String[pessoas.size()];

        //Traversing through the whole list to get all the names
        for(int i=0; i<pessoas.size(); i++){
            //Storing names to string array
            items[i] = pessoas.get(i).getId();
            items[i] = pessoas.get(i).getName();
           items[i] = pessoas.get(i).getPwd();


        }

        //Creating an array adapter for list view
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.simple_list,items);


        listView.setAdapter(adapter);
    }


}
