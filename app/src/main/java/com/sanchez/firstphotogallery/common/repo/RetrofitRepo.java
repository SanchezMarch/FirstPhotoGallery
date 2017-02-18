package com.sanchez.firstphotogallery.common.repo;

import com.sanchez.firstphotogallery.common.events.AccessTokenHasExpiredEvent;
import com.sanchez.firstphotogallery.common.model.responses.base.VkResponse;
import com.sanchez.firstphotogallery.common.model.responses.errors.VkError;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Олександр on 22.01.2017.
 */

public class RetrofitRepo extends Repo {


    public static class VkCallback<T extends VkResponse> implements Callback<T> {

        private static final int ERROR_CODE_UNAUTHORIZED = 5;

        private Result<T> onSuccess;
        private Result<VkError> onError;

        public VkCallback(Result<T> onSuccess, Result<VkError> onError) {
            this.onSuccess = onSuccess;
            this.onError = onError;
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if (response.body().isSuccessfull()) {
                onSuccess.response(response.body());
            } else handleError(response.body().getError());   // FIXME: 22.01.2017
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {

        }

        private void handleError(VkError vkError) {
            switch (vkError.getErrorCode()) {
                case ERROR_CODE_UNAUTHORIZED:
                    EventBus.getDefault().post(new AccessTokenHasExpiredEvent());

                    break;
                default:
                    onError.response(vkError);
            }
        }
    }


}
