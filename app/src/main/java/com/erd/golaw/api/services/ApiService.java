package com.erd.golaw.api.services;

import com.erd.golaw.model.Advokat;
import com.erd.golaw.model.AdvokatResponse;
import com.erd.golaw.model.Login;
import com.erd.golaw.model.Pelayanan;
import com.erd.golaw.model.PelayananResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by ILM on 6/9/2016.
 */
public interface ApiService {
    //GET List Data Advokat
    @GET("students")
    Call<AdvokatResponse> getAdvokat(@Header("Authorization") String auth);

    //POST Login Advokat
    @FormUrlEncoded
    @POST("studentlogin")
    Call<Login> advokatAuth(@Field("username") String user,
                            @Field("password") String pass,
                            @Header("Authorization") String auth);

    //POST Create Advokat
    @FormUrlEncoded
    @POST("createstudent")
    Call<Advokat> newAdvokat(@Field("name") String name,
                             @Field("username") String user,
                             @Field("password") String pass,
                             @Field("description") String desc,
                             @Header("Authorization") String auth);

    /**
    *---------------------------DIBAWAH INI BELOM JALAN---------------------------------------------
    **/

    //GET list Services/Pelayanan
    @GET("pelayanan")
    Call<PelayananResponse> getPelayanan(@Header("Authorization") String auth);

    @POST("createpelayanan")
    Call<Pelayanan> addPelayanan (@Body Pelayanan pelayanan,
                                  @Header("Authorization") String auth);

    @PUT("updatepelayanan")
    Call<Pelayanan> updatePelayanan (@Body Pelayanan pelayanan,
                                     @Header("Authorization") String auth);

}
