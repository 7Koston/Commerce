package com.scand.commerce;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class LifecycleCallbacks {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected void addSubscription(Disposable subscription) {
        compositeDisposable.add(subscription);
    }

    public void onStop() {
        compositeDisposable.clear();
    }

}
