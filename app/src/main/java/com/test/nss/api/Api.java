package com.test.nss.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Api {

    @POST("/api/token/login")
    @FormUrlEncoded
    Call<ResponseBody> login(
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("/diary/api/campdetails")
    Call<ResponseBody> getCampDetails(@Header("Authorization") String token);

    @GET("/diary/api/PoDetials/")
    Call<ResponseBody> getPoData(@Header("Authorization") String token);

    @GET("/diary/api/totalhours/")
    Call<ResponseBody> getWorkDetailsFirstFrag(@Header("Workhours") String token);

    @GET("/diary/api/totalhours/")
    Call<ResponseBody> getWorkDetailsSecoundFrag(@Header("Workhours") String token);

    @POST("/diary/api/selfRegistration/")
    @FormUrlEncoded
    Call<ResponseBody> signup(
            @Field("State") String state,
            @Field("DateOfRegistration") String dof,
            @Field("FirstName") String f_name,
            @Field("FatherName") String fath_name,
            @Field("MotherName") String mom_name,
            @Field("LastName") String last_name,
            @Field("VEC") String vec,
            @Field("Email") String email,
            @Field("CollegeName") String college_name,
            @Field("Contact") String contact
    );
    @GET("/diary/api/selfRegistration/")
    Call<ResponseBody> getHelpData(
            @Field("Email") String email,
            @Field("Contact") String contact



    );

    @GET("/diary/api/collegenames/")
    Call<ResponseBody> getClgList();

    @POST("/api/token/logout/")
    Call<Void> delToken(@Header("Authorization") String token);

    @GET("/diary/api/campactivitylist/")
    Call<ResponseBody> getCampList(@Header("Authorization") String token);

    @POST("diary/api/campactivities/")
    @FormUrlEncoded
    Call<ResponseBody> sendCampDetail(
            @Header("Authorization") String token,
            @Field("CollegeName") String clgName,
            @Field("CampActivityDescription") String desc,
            @Field("Day") int day,
            @Field("VEC") String vec,
            @Field("State") String state,
            @Field("CampActivityTitle") String campTitle
    );

    @GET("/diary/api/campactivities/")
    Call<ResponseBody> getCampActListAll(@Header("Authorization") String token);

    @GET("/diary/api/allactivites/")
    Call<ResponseBody> getActList(@Header("Authorization") String token);
}
