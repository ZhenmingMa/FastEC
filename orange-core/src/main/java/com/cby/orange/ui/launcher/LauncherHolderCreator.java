package com.cby.orange.ui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by baiyanfang on 2017/12/13.
 */

public class LauncherHolderCreator implements CBViewHolderCreator<launcherHolder>{

    @Override
    public launcherHolder createHolder() {
        return new launcherHolder();
    }
}
