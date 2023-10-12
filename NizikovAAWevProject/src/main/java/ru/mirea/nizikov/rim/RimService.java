package ru.mirea.nizikov.rim;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface RimService {
    @GET("/api/episode")
    Call<DTO> getSeries(@Query("page") int num);
}