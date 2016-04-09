package com.cs.se.ta.response;

import com.cs.se.ta.resobj.Mall;

public class MallResponse {
    
    private Mall[] malls;
    
    public MallResponse() {
        super();
    }

    public void setMalls(Mall[] malls) {
        this.malls = malls;
    }

    public Mall[] getMalls() {
        return malls;
    }
}
