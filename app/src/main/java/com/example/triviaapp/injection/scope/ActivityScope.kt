package com.example.triviaapp.injection.scope

import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.RUNTIME

@Scope
@MustBeDocumented
@Retention(value = RUNTIME)
annotation class ActivityScope