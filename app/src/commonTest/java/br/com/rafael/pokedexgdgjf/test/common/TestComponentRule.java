package br.com.rafael.pokedexgdgjf.test.common;

import android.content.Context;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import br.com.rafael.pokedexgdgjf.BuildConfig;
import br.com.rafael.pokedexgdgjf.PokedexApplication;
import br.com.rafael.pokedexgdgjf.data.DataManager;
import br.com.rafael.pokedexgdgjf.injection.module.NetworkModule;
import br.com.rafael.pokedexgdgjf.test.common.injection.component.DaggerTestComponent;
import br.com.rafael.pokedexgdgjf.test.common.injection.component.TestComponent;
import br.com.rafael.pokedexgdgjf.test.common.injection.module.ApplicationTestModule;

/**
 * Created by rafael on 8/29/16.
 **/
public class TestComponentRule implements TestRule {

    private TestComponent mTestComponent;
    private Context mContext;

    public TestComponentRule(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    public DataManager getMockDataManager() {
        return mTestComponent.dataManager();
    }

    public void setupDaggerTestComponentInApplication() {
        PokedexApplication application = PokedexApplication.get(mContext);
        mTestComponent = DaggerTestComponent.builder()
                .applicationTestModule(new ApplicationTestModule(application))
                .networkModule(new NetworkModule(BuildConfig.API_URL))
                .build();
        application.setComponent(mTestComponent);
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    setupDaggerTestComponentInApplication();
                    base.evaluate();
                } finally {
                    mTestComponent = null;
                }
            }
        };
    }
}
