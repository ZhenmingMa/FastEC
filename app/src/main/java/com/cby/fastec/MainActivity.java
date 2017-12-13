package com.cby.fastec;

import com.cby.orange.activites.ProxyActivity;
import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.ec.launcher.LauncherDelegate;
import com.cby.orange.ec.launcher.LauncherScrollDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public OrangeDelegate setRootDelegate() {
        return new LauncherScrollDelegate();
    }
}
