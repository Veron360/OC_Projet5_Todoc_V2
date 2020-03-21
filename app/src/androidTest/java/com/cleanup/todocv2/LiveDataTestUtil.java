package com.cleanup.todocv2;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


// aide pour crée test de type liveData et bloque l'execution du test tant que le résultat n'est pas retourné.
public class LiveDataTestUtil {

        public static <T> T getValue(final LiveData<T> liveData) throws InterruptedException {
            final Object[] data = new Object[1];
            final CountDownLatch latch = new CountDownLatch(1);
            Observer<T> observer = new Observer<T>() {
                @Override
                public void onChanged(@Nullable T o) {
                    data[0] = o;
                    latch.countDown();
                    liveData.removeObserver(this);
                }
            };
            liveData.observeForever(observer);
            latch.await(2, TimeUnit.SECONDS);
            //noinspection unchecked
            return (T) data[0];
    }
}
