package com.app.picpayapp.di;

import com.app.picpayapp.ui.component.ComponentsViewModel
import com.app.picpayapp.repository.TransactionRepository
import com.app.picpayapp.repository.TransactionRepositoryImpl
import com.app.picpayapp.services.ApiService
import com.app.picpayapp.services.RetrofitService
import com.app.picpayapp.ui.home.HomeViewModel
import com.app.picpayapp.ui.login.LoginViewModel
import com.app.picpayapp.ui.pay.PayViewModel
import com.app.picpayapp.ui.settings.SettingsViewModel
import com.app.picpayapp.ui.transaction.TransactionViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/** Manage Dependency Injection (di) **/
val viewModelModule = module {
    // 3 forms to instance an object:
    // Form 1 - Inject => inject { }
    // Form 2 - Single => single { }
    // Form 3 - ViewModel => viewModel { }
    viewModel { ComponentsViewModel() }
    viewModel { HomeViewModel(get()) }
    // Koin will will make this injection
    viewModel { PayViewModel(get()) }
    viewModel { SettingsViewModel() }
    viewModel { TransactionViewModel(get()) }
    viewModel { LoginViewModel(get()) }
}

val serviceModule = module {
    single { RetrofitService.create<ApiService>() }
}

val repositoryModule = module {
    single<TransactionRepository> { TransactionRepositoryImpl(get(), get()) }
}

val daoModule = module {
    single { AppDatabase.getInstance(androidContext()).transactionDAO() }
}
