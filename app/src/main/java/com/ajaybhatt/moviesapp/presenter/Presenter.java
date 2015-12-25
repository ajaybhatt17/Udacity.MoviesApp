package com.ajaybhatt.moviesapp.presenter;

import com.squareup.otto.Bus;

public abstract class Presenter<S> {

    private Bus mBus;
    private S view;

    public Presenter(Bus bus, S view) {
        this.mBus = bus;
        this.view = view;
    }

    public void start() {
        if(mBus!=null) {
            mBus.register(this);
        }
    }

    public void stop() {
        if(mBus!=null) {
            mBus.unregister(this);
        }
    }

    public Bus getBus() {
        return mBus;
    }

    public S getView() {
        return view;
    }

    public void setView(S view) {
        this.view = view;
    }
}
