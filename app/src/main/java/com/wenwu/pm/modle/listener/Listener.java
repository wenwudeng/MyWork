package com.wenwu.pm.modle.listener;

import com.wenwu.pm.goson.LRReturnJson;

/**
 * @author:wenwudeng
 * @date:3:38 PM 3/23/2020
 */
public interface Listener {
    public void onSuccess(Object json);

    public void onFail(Object json);
}
