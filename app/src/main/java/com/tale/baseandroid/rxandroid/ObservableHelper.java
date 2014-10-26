package com.tale.baseandroid.rxandroid;

import android.view.View;
import android.view.ViewTreeObserver;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.observables.Assertions;
import rx.android.subscriptions.AndroidSubscriptions;
import rx.functions.Action0;

/**
 * Created by tale on 10/26/14.
 */
public class ObservableHelper {

    public static Observable<View> preDraw(View target) {
        return Observable.create(new OperatorViewOnPreDraw(target));
    }

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

    public static class OperatorViewOnPreDraw implements Observable.OnSubscribe<View> {
        private final View target;

        public OperatorViewOnPreDraw(final View target) {
            this.target = target;
        }

        @Override
        public void call(final Subscriber<? super View> observer) {
            Assertions.assertUiThread();


            final ViewTreeObserver.OnPreDrawListener onPreDrawListener = new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    target.getViewTreeObserver().removeOnPreDrawListener(this);
                    observer.onNext(target);
                    return true;
                }
            };

            final Subscription subscription = AndroidSubscriptions.unsubscribeInUiThread(new Action0() {
                @Override
                public void call() {
                    target.getViewTreeObserver().removeOnPreDrawListener(onPreDrawListener);
                }
            });

            target.getViewTreeObserver().addOnPreDrawListener(onPreDrawListener);
            observer.add(subscription);
        }


    }
}
