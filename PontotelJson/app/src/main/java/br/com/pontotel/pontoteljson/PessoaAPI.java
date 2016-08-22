package br.com.pontotel.pontoteljson;

import java.util.List;

import retrofit.Callback;
import retrofit.http.POST;

/**
 * Created by KawaN on 20/08/2016.
 */
public interface PessoaAPI {
    /*Retrofit get annotation with our URL
       And our method that will return us the list ob Book
    */
    @POST("/pontotel-docs/data.json")
    public void getPessoas(Callback<List<Pessoa>> response);

}
