package com.bellminp.instrumentation.ui.login

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.bellminp.common.timberMsg
import com.bellminp.domain.model.ApiResult
import com.bellminp.domain.model.DomainAutoLogin
import com.bellminp.domain.usecase.LocalUseCase
import com.bellminp.domain.usecase.RemoteUseCase
import com.bellminp.instrumentation.InstrumentationApplication
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.mapper.mapToPresentation
import com.bellminp.instrumentation.model.FieldList
import com.bellminp.instrumentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val localUseCase: LocalUseCase,
    private val remoteUseCase: RemoteUseCase
): BaseViewModel() {

    var id = String()
    var password = String()

    fun login(){
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
                                    if(items.fieldList == null) goMain(items.fieldNum?:0)
                                    else showFieldList(items.fieldList)
                                }
                            }else{
                                showShortToast(InstrumentationApplication.mInstance.resources.getString(R.string.no_exist_user))
                            }
                        }
                        else -> {
                            showShortToast(InstrumentationApplication.mInstance.resources.getString(R.string.no_exist_user))
                        }
                    }
                }
        }
    }
}