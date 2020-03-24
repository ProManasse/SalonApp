package com.example.barberbookapp.api;

import com.example.barberbookapp.domain.Account;
import com.example.barberbookapp.domain.Barber;
import com.example.barberbookapp.domain.Client;
import com.example.barberbookapp.domain.Owner;
import com.example.barberbookapp.domain.Salon;
import java.util.ArrayList;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    @POST ("addClient")
    Call<String> bookBarber(@Body Client client);

    @POST ("updateSalon")
    Call<ResponseBody> updateSalon(@Body Salon salon);

    @GET("listClients/{salon_id}")
    Call<ArrayList<Client>> getClients(@Path("salon_id") String salon_id);

    @GET("listSalons")
    Call<ArrayList<Salon>> getSalons();

    @GET("listOwners")
    Call<ArrayList<Owner>> getOwners();

    @GET("listBarbers/{salon_no}")
    Call<ArrayList<Barber>> getBarbersBySalon(@Path("salon_no") String salon_no);

    @GET("listAccounts")
    Call<ArrayList<Account>> getAccounts();

    @POST ("updateSalon")
    Call<ResponseBody> updateBarber(@Body Salon salon);

    @PUT("updateClient/{cid}")
    Call<String> updateClient(@Path("cid") String cid);

    @GET("getSalon/{salon_ow}")
    Call<ArrayList<Salon>> getSalon(@Path("salon_ow") String own_id);

    @GET("getNearSalons/{lat}/{lon}")
    Call<ArrayList<Salon>> getNearSalons(@Path("lat")double user_lat,@Path("lon")double user_lon);
}
