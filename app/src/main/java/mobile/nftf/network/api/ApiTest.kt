package mobile.nftf.network.api

import io.reactivex.Single
import mobile.nftf.model.Content
import mobile.nftf.network.adapter.AuthInterceptor
import retrofit2.http.*


interface ApiTest {

    @GET("todos")
    @Headers(AuthInterceptor.ENABLE_AUTH)
    fun fetchTodos(): Single<List<Content>>

    @FormUrlEncoded
    @POST("todos")
    @Headers(AuthInterceptor.ENABLE_AUTH)
    fun addTodo(@Field("content") contenst: String): Single<Content>

    @FormUrlEncoded
    @PUT("todos/{itemId}")
    @Headers(AuthInterceptor.ENABLE_AUTH)
    fun updateTodo(@Path("itemId") id: Int,
                   @Field("content") content: String? = null,
                   @Field("done") done: Boolean? = null)
            : Single<Content>

    @DELETE("todos/{itemId}")
    @Headers(AuthInterceptor.ENABLE_AUTH)
    fun deleteTodo(@Path("itemId") id: Int)
            : Single<List<Content>>

    @FormUrlEncoded
    @POST("todos/{itemId}/arrangement")
    @Headers(AuthInterceptor.ENABLE_AUTH)
    fun updateSeq(@Path("itemId") id: Int, @Field("seq") seq: Int)
            : Single<List<Content>>
}