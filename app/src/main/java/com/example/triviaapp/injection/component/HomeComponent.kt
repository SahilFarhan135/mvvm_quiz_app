package com.example.triviaapp.injection.component

import com.example.triviaapp.injection.scope.ActivityScope
import com.example.triviaapp.ui.history.HistoryActitvity
import com.example.triviaapp.ui.main.MainActivity
import com.example.triviaapp.ui.quizactivity.QuizActivity
import com.example.triviaapp.ui.splash.SplashActitvity
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface HomeComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(activity: QuizActivity)
    fun inject(activity: MainActivity)
    fun inject(activity: SplashActitvity)
    fun inject(activity: HistoryActitvity)

}