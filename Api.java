package com.example.makesurest;

import com.example.makesurest.model.AggregationRequest;
import com.example.makesurest.model.AggregationResponce;
import com.example.makesurest.model.BatchRequest;
import com.example.makesurest.model.BatchResponse;
import com.example.makesurest.model.LoginResponce;
import com.example.makesurest.model.PackagingCountRequest;
import com.example.makesurest.model.PackagingCountResponce;
import com.example.makesurest.model.PackgingRequest;
import com.example.makesurest.model.PackgingResponce;
import com.example.makesurest.model.ProductRequest;
import com.example.makesurest.model.ProductResponse;
import com.example.makesurest.model.ResponseCompanyListModel;
import com.example.makesurest.model.SendLoginRequestModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    @POST("Login/CheckLogin")
    Call<LoginResponce> userLogin(@Body SendLoginRequestModel loginRequest);

    @GET("Company/GetCompanyInfo")
    Call<List<ResponseCompanyListModel>> getCompanyListAPI();

    @POST("Product/Productbind")
    Call<List<ProductResponse>> getProductList(@Body ProductRequest productRequest);

    @POST("BatchDetail/Batchbind")
    Call<List<BatchResponse>> getBatch(@Body BatchRequest productRequest);

    @POST("Packeging/Packeginginfo")
    Call<List<PackgingResponce>> getPackgeing(@Body PackgingRequest productRequest);

    @POST("Packeging/getcount")
    Call<List<PackagingCountResponce>> getPackagingCount(@Body PackagingCountRequest productRequest);

    @POST("Aggregation/BulkAggregationInsert")
    Call<List<AggregationResponce>> aggregationApi(@Body AggregationRequest aggregationRequest);


}
