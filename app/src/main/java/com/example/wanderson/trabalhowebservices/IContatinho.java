package com.example.wanderson.trabalhowebservices;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IContatinho {

    @GET("contatinhos/")
    Call<List<Contatinho>> searchContatinho();

//    @GET("contatinhos/{id}")
//    Call<List<Contatinho>> buscarContatinho(@Query("id") int id);

    @POST("contatinhos/")
    Call<Contatinho> addContatinho(@Body Contatinho contatinho);

    @PUT("contatinhos/")
    Call<Contatinho> updateContatinho(@Body Contatinho contatinho);

    @DELETE("contatinhos/{id}")
    Call<Contatinho> deleteContatinho(@Path("id") int id);


}
