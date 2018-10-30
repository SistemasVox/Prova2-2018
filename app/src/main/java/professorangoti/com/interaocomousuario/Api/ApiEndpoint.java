package professorangoti.com.interaocomousuario.Api;

import java.util.ArrayList;
import java.util.List;

import professorangoti.com.interaocomousuario.Produto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface ApiEndpoint {


    @GET("/precos")
    Call<Produto> obeterPost(@Path("produto") String produto);
    Call<List<Produto>> obeterPosts();
}
