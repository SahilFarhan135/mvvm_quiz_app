package com.example.triviaapp.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.triviaapp.injection.scope.ViewModelScope
import com.example.triviaapp.ui.history.HistoryViewModel
import com.example.triviaapp.ui.main.MainActivityViewModel
import com.example.triviaapp.ui.quizactivity.QuizViewModel
import com.example.triviaapp.ui.splash.SplashActitvity
import com.example.triviaapp.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelScope(QuizViewModel::class)
    abstract fun bindQuizViewModel(quizViewModel: QuizViewModel): ViewModel

  @Binds
    @IntoMap
    @ViewModelScope(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelScope(MainActivityViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelScope(HistoryViewModel::class)
    abstract fun bindHistoryViewModel(historyViewModel: HistoryViewModel): ViewModel





}

