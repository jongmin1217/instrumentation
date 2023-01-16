package com.bellminp.instrumentation.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bellminp.domain.model.ApiResult
import com.bellminp.domain.model.DomainAutoLogin
import com.bellminp.domain.usecase.LocalUseCase
import com.bellminp.domain.usecase.RemoteUseCase
import com.bellminp.instrumentation.InstrumentationApplication
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.mapper.mapToPresentation
import com.bellminp.instrumentation.model.Connect
import com.bellminp.instrumentation.ui.base.BaseViewModel
import com.bellminp.instrumentation.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val localUseCase: LocalUseCase,
    private val remoteUseCase: RemoteUseCase
): BaseViewModel() {

    init {
        val loginData = localUseCase.getAutoLogin()
        if(loginData.username != null && loginData.password != null) login(loginData.username!!,loginData.password!!)
        else goLogin()
    }

    fun login(id : String, password : String){
        viewModelScope.launch {
            remoteUseCase.login(DomainAutoLogin(id,password))
                .collect {

                    when(it.status){
                        ApiResult.Status.SUCCESS -> {
                            if(it.data?.code == 0){
                                it.data?.let{ data->
                                    localUseCase.setAutoLogin(DomainAutoLogin(id,password))
                                    localUseCase.setToken("Bearer "+data.token)

                                    val items = data.mapToPresentation()

                                    localUseCase.setAdmin(items.fieldList != null)
                                    val connect = Connect(
                                        data.apichk,
                                        data.mobilenum,
                                        data.recvsms,
                                        data.appid,
                                        data.nsmip,
                                        data.nsmadminid,
                                        data.nsmdbname,
                                        data.nsmadminpw,
                                        data.appversion,
                                        data.authorityNum
                                    )
                                    if(items.fieldList == null) goMain(items.fieldNum?:0,connect)
                                    else{
                                        if(items.fieldList.size == 1) goMain(items.fieldList[0].num,connect)
                                        else showFieldList(items.fieldList,connect)
                                    }
                                }
                            }else{
                                showShortToast(InstrumentationApplication.mInstance.resources.getString(R.string.no_exist_user))
                                goLogin()
                            }
                        }

                        else -> {
                            showShortToast(InstrumentationApplication.mInstance.resources.getString(R.string.no_exist_user))
                            goLogin()
                        }
                    }

                }
        }
    }
}