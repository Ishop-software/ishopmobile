package com.example.ishopbillingsoftware.server;


import com.example.ishopbillingsoftware.APIResponse;
import com.example.ishopbillingsoftware.accounts.APIResponseData;
import com.example.ishopbillingsoftware.accounts.APIResponseGP;
import com.example.ishopbillingsoftware.accounts.APIResponseGroup;
import com.example.ishopbillingsoftware.items.APIResponseItem;
import com.example.ishopbillingsoftware.items.APIResponseProductItem;
import com.example.ishopbillingsoftware.items.APIResponseUpdate;
import com.example.ishopbillingsoftware.sales.APIResponseCharges;
import com.example.ishopbillingsoftware.sales.APIResponseChargesList;
import com.example.ishopbillingsoftware.sales.APIResponseSale;
import com.example.ishopbillingsoftware.sales.APIResponseSalesList;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserService {
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("api/users/userRegister")
    Call<APIResponse> userReg(@Body HashMap<String, Object> fields);


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("api/users/userLogin")
    Call<APIResponseLogin>userLogin(@Body HashMap<String, String> fields);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("api/users/forgerPassword")
    Call<APIResponseUpdate>forget(@Body HashMap<String, String> fields);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("api/productitems/getAllProductItems")
    Call<APIResponseProductItem> getAllproductIitem(@Header("Authorization") String token);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("api/productitems/addProductItem")
    Call<APIResponseItem> addItem(@Body HashMap<String, Object> fields,@Header("Authorization") String token);



    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @PUT("api/productitems/updateProductItem")
    Call<APIResponseUpdate>update(@Body HashMap<String, Object> fields);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("api/productitems/deleteProductItem")
    Call<APIResponseUpdate> delete (@Body HashMap<String, String> fields);


    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("api/users/createAccount")
    Call<APIResponseUpdate> createaccount (@Body HashMap<String, String> fields,@Header("Authorization") String token);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/api/users/getAllAccountDetails")
    Call<APIResponseData> getAllAccountDetails(@Header("Authorization") String token);


    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("api/users/updateAccountDetails")
    Call<APIResponseUpdate> accountupdate (@Body HashMap<String, String> fields);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("api/usersales/addSalesRegister")
    Call<APIResponseSale> addsale(@Body HashMap<String, Object> fields,@Header("Authorization") String token);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("api/usercharge/addChargeRegister")
    Call<APIResponseCharges> addcharges(@Body HashMap<String, Object> fields);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("api/usercharge/getAllChargeList")
    Call<APIResponseChargesList> viewlistcharge ();


    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("api/groupaccounts/addGroupAccount")
    Call<APIResponseGroup> addgroup (@Body HashMap<String, String> fields);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("api/groupaccounts/getAllGroupAccount")
    Call<APIResponseGP> grouplist ();

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("api/usersales/getAllSalesList")
    Call<APIResponseSalesList> viewsaleschargelist (@Header("Authorization") String token);

}
