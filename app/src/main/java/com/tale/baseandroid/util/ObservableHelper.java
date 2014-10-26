package com.tale.baseandroid.util;

import rx.Subscriber;

/**
 * Created by tale on 10/26/14.
 */
public class ObservableHelper {

    public static <T> void onNext(Subscriber<T> subscriber, T emitItem) {
        if (!subscriber.isUnsubscribed()) {
            subscriber.onNext(emitItem);
        }
    }

    public static void onCompleted(Subscriber subscriber) {
        if (!subscriber.isUnsubscribed()) {
            subscriber.onCompleted();
        }
    }

    public static void onError(Subscriber subscriber, Throwable e) {
        if (!subscriber.isUnsubscribed()) {
            subscriber.onError(e);
        }
    }
}
