package com.example.basic.network.adapter

import io.reactivex.schedulers.Schedulers
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

open class RxApiResponseCallAdapterFactory : CallAdapter.Factory {

    companion object {

        @JvmStatic
        fun with(clazz: Class<out RxApiResponseCallAdapter<*>>): RxApiResponseCallAdapterFactory {
            return RxApiResponseCallAdapterFactory(clazz)
        }
    }

    private val clazz: Class<out RxApiResponseCallAdapter<*>>

    private val rxFactory = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())

    constructor() : super() {
        this.clazz = RxApiResponseCallAdapter::class.java
    }

    private constructor(clazz: Class<out RxApiResponseCallAdapter<*>>) : super() {
        this.clazz = clazz
    }

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        var type = returnType

//        if (returnType is ParameterizedType && returnType.actualTypeArguments.isNotEmpty()) {
//            val apiResponseType = ParameterizedTypeImpl(null, ApiPageResponse::class.java, returnType.actualTypeArguments[0])
//
//            type = ParameterizedTypeImpl(null, returnType.rawType, apiResponseType)
//        }

        return createAdapter(
            rxFactory.get(type, annotations, retrofit),
            type,
            annotations,
            retrofit
        )
    }

    protected open fun createAdapter(
        rxAdapter: CallAdapter<*, *>?,
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): RxApiResponseCallAdapter<*>? {
        if (rxAdapter == null) {
            return null
        }

        return clazz.getConstructor(CallAdapter::class.java)
            .newInstance(rxAdapter)
    }

    protected class ParameterizedTypeImpl(
        private val ownerType: Type?,
        private val rawType: Type,
        vararg typeArgument: Type
    ) : ParameterizedType {

        private val actualTypeArguments = typeArgument

        override fun getRawType(): Type = rawType

        override fun getOwnerType(): Type? = ownerType

        override fun getActualTypeArguments(): Array<out Type> = actualTypeArguments.clone()
    }
}