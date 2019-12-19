package com.anamarin.bitcoinpricesapp.core.utils.schedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulersProvider : BaseSchedulerProvider{
    override fun io(): Scheduler = Schedulers.io()
    override fun computation(): Scheduler = Schedulers.computation()
    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
}