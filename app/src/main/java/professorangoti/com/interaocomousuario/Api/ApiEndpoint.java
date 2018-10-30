package professorangoti.com.interaocomousuario.Api;

import java.util.ArrayList;

import professorangoti.com.interaocomousuario.Produto;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoint {
    @GET("precos")
    Call<ArrayList<Produto>> obterPosts();
}
