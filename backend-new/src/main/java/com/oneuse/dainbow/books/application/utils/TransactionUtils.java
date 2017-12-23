package com.oneuse.dainbow.books.application.utils;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import java.util.function.Consumer;

public class TransactionUtils {

    public static TransactionCallback<Object> withoutResult(Consumer<TransactionStatus> callback) {
        return new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                callback.accept(status);
            }
        };
    }

    private TransactionUtils() {

    }
}
