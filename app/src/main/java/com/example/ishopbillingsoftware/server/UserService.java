package com.example.ishopbillingsoftware.server;






import com.example.ishopbillingsoftware.accounts.APIResponseData;
import com.example.ishopbillingsoftware.items.APIResponseItem;
import com.example.ishopbillingsoftware.items.APIResponseProductItem;
import com.example.ishopbillingsoftware.items.APIResponseUpdate;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserService {
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("api/users/userRegister")
    Call<APIResponse>userReg(@Body HashMap<String, Object> fields);


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("api/users/userLogin")
    Call<APIResponseLogin>userLogin(@Body HashMap<String, String> fields);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("api/users/forgerPassword")
    Call<APIResponseUpdate>forget(@Body HashMap<String, String> fields);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("api/productitems/getAllProductItems")
    Call<APIResponseProductItem> getAllproductIitem();

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("api/productitems/addProductItem")
    Call<APIResponseItem> addItem(@Body HashMap<String, Object> fields);



    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @PUT("api/productitems/updateProductItem")
    Call<APIResponseUpdate>update(@Body HashMap<String, Object> fields);



    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("api/productitems/deleteProductItem")
    Call<APIResponseUpdate> delete (@Body HashMap<String, String> fields);


    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("api/users/createAccount")
    Call<APIResponseUpdate> create (@Body HashMap<String, String> fields);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("api/users/getAllAccountDetails")
    Call<APIResponseData> getAllAccountDetails();

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("api/users/updateAccountDetails")
    Call<APIResponseUpdate> accountupdate (@Body HashMap<String, String> fields);

}
