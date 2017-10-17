package com.ap.advisor.networking.api;

import com.ap.advisor.data.Advisor;

import java.util.List;

public interface IAdvisorListCallBack {

    public void onCallBackComplete(List<Advisor> advisorList);

    public void onCallBackFailed();
}
