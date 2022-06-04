package com.obviousnasapictures.constant;

public interface MvpView {

    void showLoading();

    void hideLoading();

    void showMessage(String message);

    void showMessageAnimated(String message);

    void getClickPosition(int position);

}
